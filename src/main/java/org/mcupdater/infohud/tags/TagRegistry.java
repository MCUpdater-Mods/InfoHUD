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
		register("daytime", Tags.World::daytime);
		register("nighttime", Tags.World::nighttime);
		register("structure", Tags.World::structure);
		register("dimension", Tags.World::dimension);
		register("slimechunk", Tags.World::slimeChunk);
		register("moonphase", Tags.World::moonPhase);
		register("moonphasenum", Tags.World::moonPhaseNum);
		register("raining", Tags.World::raining);
		register("thundering", Tags.World::thundering);

		// Player
		register("fullposition", Tags.Player::position);
		register("pos_x", Tags.Player::pos_x);
		register("pos_y", Tags.Player::pos_y);
		register("pos_z", Tags.Player::pos_z);
		register("bed_distance", Tags.Player::bedDistance);
		register("heading", Tags.Player::heading);
		register("awake_days", Tags.Player::awakeDays);

		// Equipment
		register("mainhand_damage", Tags.Equipment::mainhandDamage);
		register("mainhand_damage_formatted", Tags.Equipment::mainhandDamageFormatted);
		register("mainhand_name", Tags.Equipment::mainhandName);
		register("offhand_damage", Tags.Equipment::offhandDamage);
		register("offhand_damage_formatted", Tags.Equipment::offhandDamageFormatted);
		register("offhand_name", Tags.Equipment::offhandName);
		register("armor", Tags.Equipment::armor);
		register("curio", Tags.Equipment::curio);

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
	}

	public static Object lookup(String key) {
		Minecraft minecraft = Minecraft.getInstance();
		ClientLevel level = (ClientLevel) minecraft.player.level();
		LocalPlayer player = minecraft.player;
		String[] elements = key.split("(?<!\\\\):");
		try {
			return TAG_MAP.get(elements[0]).getValue(elements, minecraft, level, player, minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(true));
		} catch (IllegalArgumentException e) {
			InfoHUD.LOGGER.error(e.getMessage() + "\n" + Arrays.toString(elements));
			return "";
		}
	}

}
