package org.mcupdater.infohud.tags;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import org.mcupdater.infohud.InfoHUD;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TagRegistry {
	private static Map<String,TagProvider> TAG_MAP = new HashMap<>();

	public static Set<Map.Entry<String, TagProvider>> getTagEntries() {
		return TAG_MAP.entrySet();
	}

	public static void register(String key, TagProvider function) {
		if (!TAG_MAP.containsKey(key)) {
			TAG_MAP.put(key, function);
		} else {
			InfoHUD.LOGGER.error("Rejected duplicate tag: {}", key);
		}
	}

	public static void init() {
		// Formatting
		register("aqua", Tags.Formatting::aqua);
		register("black", Tags.Formatting::black);
		register("blue", Tags.Formatting::blue);
		register("darkaqua", Tags.Formatting::dark_aqua);
		register("darkblue", Tags.Formatting::dark_blue);
		register("darkgray", Tags.Formatting::dark_gray);
		register("darkgreen", Tags.Formatting::dark_green);
		register("darkpurple", Tags.Formatting::dark_purple);
		register("darkred", Tags.Formatting::dark_red);
		register("gold", Tags.Formatting::gold);
		register("gray", Tags.Formatting::gray);
		register("green", Tags.Formatting::green);
		register("lightpurple", Tags.Formatting::light_purple);
		register("red", Tags.Formatting::red);
		register("white", Tags.Formatting::white);
		register("yellow", Tags.Formatting::yellow);
		register("bold", Tags.Formatting::bold);
		register("italic", Tags.Formatting::italic);
		register("magic", Tags.Formatting::magic);
		register("reset", Tags.Formatting::reset);
		register("strike", Tags.Formatting::strike);
		register("underline", Tags.Formatting::underline);

		// Time
		register("day", Tags.Time::day);
		register("mctime", Tags.Time::mctime);
		register("rltime", Tags.Time::rltime);

		// World
		register("biome", Tags.World::biome);
		register("rawbiome", Tags.World::rawbiome);
		register("light", Tags.World::light);
		register("skylight", Tags.World::skylight);
		register("blocklight", Tags.World::blocklight);
		register("structure", Tags.World::structure);
		register("dimension", Tags.World::dimension);

		// Player
		register("fullposition", Tags.Player::position);
		register("pos_x", Tags.Player::pos_x);
		register("pos_y", Tags.Player::pos_y);
		register("pos_z", Tags.Player::pos_z);
		register("bed_distance", Tags.Player::bedDistance);
		register("heading", Tags.Player::heading);

		// Equipment
		register("bootsdamage", Tags.Equipment::bootsdamage);
		register("bootsdamage_formatted", Tags.Equipment::bootsdamage_formatted);
		register("bootsname", Tags.Equipment::bootsname);
		register("leggingsdamage", Tags.Equipment::leggingsdamage);
		register("leggingsdamage_formatted", Tags.Equipment::leggingsdamage_formatted);
		register("leggingsname", Tags.Equipment::leggingsname);
		register("chestplatedamage", Tags.Equipment::chestplatedamage);
		register("chestplatedamage_formatted", Tags.Equipment::chestplatedamage_formatted);
		register("chestplatename", Tags.Equipment::chestplatename);
		register("helmetdamage", Tags.Equipment::helmetdamage);
		register("helmetdamage_formatted", Tags.Equipment::helmetdamage_formatted);
		register("helmetname", Tags.Equipment::helmetname);
		register("mainhanddamage", Tags.Equipment::mainhanddamage);
		register("mainhanddamage_formatted", Tags.Equipment::mainhanddamage_formatted);
		register("mainhandname", Tags.Equipment::mainhandname);
		register("offhanddamage", Tags.Equipment::offhanddamage);
		register("offhanddamage_formatted", Tags.Equipment::offhanddamage_formatted);
		register("offhandname", Tags.Equipment::offhandname);

		//Target Info
		register("targetname", Tags.TargetInfo::targetName);
		register("targetowner", Tags.TargetInfo::targetOwner);

		register("translate", Tags.Functions::translate);
		register("lt", Tags.Functions::lessThan);
		register("le", Tags.Functions::lessThanOrEqual);
		register("gt", Tags.Functions::greaterThan);
		register("ge", Tags.Functions::greaterThanOrEqual);
		register("eq", Tags.Functions::equals);
		register("ne", Tags.Functions::notEquals);
		register("exists", Tags.Functions::exists);
		register("curio", Tags.Functions::curio);

		//registerTag("debug", Tags::debug);
	}

	public static Object lookup(String key) {
		Minecraft minecraft = Minecraft.getInstance();
		ClientLevel level = (ClientLevel) minecraft.player.level();
		LocalPlayer player = minecraft.player;
		String[] elements = key.split("(?<!\\\\):");
		try {
			return TAG_MAP.get(elements[0]).getValue(elements, minecraft, level, player, minecraft.getTimer().getGameTimeDeltaPartialTick(true));
		} catch (IllegalArgumentException e) {
			InfoHUD.LOGGER.error(e.getMessage() + "\n" + Arrays.toString(elements));
			return "";
		}
	}

}
