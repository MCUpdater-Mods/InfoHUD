package org.mcupdater.infohud.tags;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.util.Mth;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.mcupdater.infohud.InfoHUDClient;
import org.mcupdater.infohud.setup.Config;
import top.theillusivec4.curios.api.CuriosApi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public class Tags {

	public static String debug(String[] parts, Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
		return Float.toString(level.getSkyDarken(partialTick) * 15.0f);
	}

	public static class Formatting {
		public static final String FORMATTING_CHAR = "\u00A7";
		public static final String RESET = FORMATTING_CHAR + "r";
		public static final String ITALIC = FORMATTING_CHAR + "o";
		public static final String UNDERLINE = FORMATTING_CHAR + "n";
		public static final String STRIKE = FORMATTING_CHAR + "m";
		public static final String BOLD = FORMATTING_CHAR + "l";
		public static final String MAGIC = FORMATTING_CHAR + "k";
		public static final String WHITE = FORMATTING_CHAR + "f";
		public static final String YELLOW = FORMATTING_CHAR + "e";
		public static final String LIGHT_PURPLE = FORMATTING_CHAR + "d";
		public static final String RED = FORMATTING_CHAR + "c";
		public static final String AQUA = FORMATTING_CHAR + "b";
		public static final String GREEN = FORMATTING_CHAR + "a";
		public static final String BLUE = FORMATTING_CHAR + "9";
		public static final String DARK_GRAY = FORMATTING_CHAR + "8";
		public static final String GRAY = FORMATTING_CHAR + "7";
		public static final String GOLD = FORMATTING_CHAR + "6";
		public static final String DARK_PURPLE = FORMATTING_CHAR + "5";
		public static final String DARK_RED = FORMATTING_CHAR + "4";
		public static final String DARK_AQUA = FORMATTING_CHAR + "3";
		public static final String DARK_GREEN = FORMATTING_CHAR + "2";
		public static final String DARK_BLUE = FORMATTING_CHAR + "1";
		public static final String BLACK = FORMATTING_CHAR + "0";

		public static String black(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return BLACK;
		}

		public static String dark_blue(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return DARK_BLUE;
		}

		public static String dark_green(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return DARK_GREEN;
		}

		public static String dark_aqua(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return DARK_AQUA;
		}

		public static String dark_red(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return DARK_RED;
		}

		public static String dark_purple(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return DARK_PURPLE;
		}

		public static String gold(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return GOLD;
		}

		public static String gray(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return GRAY;
		}

		public static String dark_gray(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return DARK_GRAY;
		}

		public static String blue(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return BLUE;
		}

		public static String green(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return GREEN;
		}

		public static String aqua(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return AQUA;
		}

		public static String red(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return RED;
		}

		public static String light_purple(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return LIGHT_PURPLE;
		}

		public static String yellow(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return YELLOW;
		}

		public static String white(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return WHITE;
		}

		public static String magic(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return MAGIC;
		}

		public static String bold(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return BOLD;
		}

		public static String strike(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return STRIKE;
		}

		public static String underline(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return UNDERLINE;
		}

		public static String italic(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return ITALIC;
		}

		public static String reset(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return RESET;
		}
	}

	public static class Player {
		protected static final String[] DIRECTIONS = {"infohud.direction.s","infohud.direction.sw","infohud.direction.w","infohud.direction.nw","infohud.direction.n","infohud.direction.ne","infohud.direction.e","infohud.direction.se"};
		public static String position(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return (((Config.REQUIRE_ITEMS.get() || InfoHUDClient.serverRequiresItems) && !InfoHUDClient.localStatus.status().get("compass")) ? (Formatting.FORMATTING_CHAR + "k") : "") + "[" + localPlayer.blockPosition().toShortString() + "]";
		}

		public static Integer pos_x(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return ((Config.REQUIRE_ITEMS.get() || InfoHUDClient.serverRequiresItems) && !InfoHUDClient.localStatus.status().get("compass")) ? clientLevel.getRandom().nextIntBetweenInclusive(-200000,200000) : localPlayer.blockPosition().getX();
		}

		public static Integer pos_y(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return ((Config.REQUIRE_ITEMS.get() || InfoHUDClient.serverRequiresItems) && !InfoHUDClient.localStatus.status().get("compass")) ? clientLevel.getRandom().nextIntBetweenInclusive(-200000,200000) : localPlayer.blockPosition().getY();
		}

		public static Integer pos_z(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return ((Config.REQUIRE_ITEMS.get() || InfoHUDClient.serverRequiresItems) && !InfoHUDClient.localStatus.status().get("compass")) ? clientLevel.getRandom().nextIntBetweenInclusive(-200000,200000) : localPlayer.blockPosition().getZ();
		}

		public static Long bedDistance(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return ((Config.REQUIRE_ITEMS.get() || InfoHUDClient.serverRequiresItems) && !InfoHUDClient.localStatus.status().get("compass")) ? -1 : InfoHUDClient.bedDistance;
		}

		public static String heading(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return Functions.translateInternal(DIRECTIONS[Mth.floor(localPlayer.getYRot() * 8.0 / 360.0 + 0.5) & 7], localPlayer);
		}
	}

	public static class Time {

		public static String day(String[] parts, Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return ((Config.REQUIRE_ITEMS.get() || InfoHUDClient.serverRequiresItems) && !InfoHUDClient.localStatus.status().get("clock") ? (Formatting.FORMATTING_CHAR + "k") : "") + String.format(Locale.ENGLISH, "%d", level.getDayTime() / 24000);
		}

		public static String mctime(String[] parts, Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			long time = level.getDayTime();
			return ((Config.REQUIRE_ITEMS.get() || InfoHUDClient.serverRequiresItems) && !InfoHUDClient.localStatus.status().get("clock") ? (Formatting.FORMATTING_CHAR + "k") : "") + String.format(Locale.ENGLISH, "%02d:%02d", (time / 1000) % 24, (time % 1000) * 60 / 1000);
		}

		public static String rltime(String[] parts, Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return new SimpleDateFormat("HH:mm").format(new Date());
		}
	}

	public static class World {

		public static String biome(String[] parts, Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return Functions.translateInternal(rawbiome(parts, minecraft, level, localPlayer, partialTick), localPlayer);
		}

		public static String rawbiome(String[] parts, Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return Util.makeDescriptionId(
					"biome",
					level.registryAccess().registryOrThrow(Registries.BIOME).getKey(level.getBiome(localPlayer.blockPosition()).value())
			);
		}

		public static String light(String[] parts, Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return String.format(Locale.ENGLISH, "%d", Math.max(level.getBrightness(LightLayer.BLOCK, localPlayer.blockPosition()), Math.min(level.getBrightness(LightLayer.SKY, localPlayer.blockPosition()), Math.round(level.getSkyDarken(partialTick) * 15.0f))));
		}

		public static String skylight(String[] parts, Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return String.format(Locale.ENGLISH, "%d", level.getBrightness(LightLayer.SKY, localPlayer.blockPosition()));
		}

		public static String blocklight(String[] parts, Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return String.format(Locale.ENGLISH, "%d", level.getBrightness(LightLayer.BLOCK, localPlayer.blockPosition()));
		}

		public static String structure(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return Functions.translateInternal(InfoHUDClient.currentStructure, localPlayer);
		}

		public static String dimension(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			return Functions.translateInternal(Util.makeDescriptionId("dimension", clientLevel.dimension().location()),localPlayer);
		}
	}

	public static class Equipment {

		public static String getNameFromStack(ItemStack stack) {
			return stack.getComponents().has(DataComponents.CUSTOM_NAME) ?
					stack.getComponents().get(DataComponents.CUSTOM_NAME).tryCollapseToString() :
					stack.getDescriptionId();
		}

		public static String bootsdamage(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			ItemStack boots = localPlayer.getInventory().getArmor(0);
			if (boots.isEmpty() || !boots.isDamageableItem()) return "(--/--)";
			return String.format("(%d/%d)",(boots.getMaxDamage()-boots.getDamageValue()), boots.getMaxDamage());
		}

		public static String bootsdamage_formatted(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			String color;
			ItemStack boots = localPlayer.getInventory().getArmor(0);
			if (boots.isEmpty() || !boots.isDamageableItem()) return Formatting.DARK_GRAY + "(--/--)" + Formatting.RESET;
			int damageLevelpct = Math.round(((boots.getMaxDamage()-boots.getDamageValue()) * 100f)/boots.getMaxDamage());
			if (damageLevelpct >= 80) {
				color = Formatting.GREEN;
			} else if (damageLevelpct >= 60) {
				color = Formatting.DARK_GREEN;
			} else if (damageLevelpct >= 40) {
				color = Formatting.GOLD;
			} else if (damageLevelpct >= 20) {
				color = Formatting.YELLOW;
			} else if (damageLevelpct >= 10) {
				color = Formatting.DARK_RED;
			} else {
				color = Formatting.RED;
			}
			return color + String.format("(%d/%d)",(boots.getMaxDamage()-boots.getDamageValue()), boots.getMaxDamage()) + Formatting.RESET;
		}

		public static String bootsname(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			ItemStack boots = localPlayer.getInventory().getArmor(0);
			if (boots.isEmpty()) return "";
			String name = getNameFromStack(boots);
			return Functions.translateInternal(name, localPlayer);
		}

		public static String leggingsdamage(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			ItemStack leggings = localPlayer.getInventory().getArmor(1);
			if (leggings.isEmpty() || !leggings.isDamageableItem()) return "(--/--)";
			return String.format("(%d/%d)",(leggings.getMaxDamage()-leggings.getDamageValue()), leggings.getMaxDamage());
		}

		public static String leggingsdamage_formatted(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			String color;
			ItemStack leggings = localPlayer.getInventory().getArmor(1);
			if (leggings.isEmpty() || !leggings.isDamageableItem()) return Formatting.DARK_GRAY + "(--/--)" + Formatting.RESET;
			int damageLevelpct = Math.round(((leggings.getMaxDamage()-leggings.getDamageValue()) * 100f)/leggings.getMaxDamage());
			if (damageLevelpct >= 80) {
				color = Formatting.GREEN;
			} else if (damageLevelpct >= 60) {
				color = Formatting.DARK_GREEN;
			} else if (damageLevelpct >= 40) {
				color = Formatting.GOLD;
			} else if (damageLevelpct >= 20) {
				color = Formatting.YELLOW;
			} else if (damageLevelpct >= 10) {
				color = Formatting.DARK_RED;
			} else {
				color = Formatting.RED;
			}
			return color + String.format("(%d/%d)",(leggings.getMaxDamage()-leggings.getDamageValue()), leggings.getMaxDamage()) + Formatting.RESET;
		}

		public static String leggingsname(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			ItemStack leggings = localPlayer.getInventory().getArmor(1);
			if (leggings.isEmpty()) return "";
			String name = getNameFromStack(leggings);
			return Functions.translateInternal(name, localPlayer);
		}

		public static String chestplatedamage(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			ItemStack chestplate = localPlayer.getInventory().getArmor(2);
			if (chestplate.isEmpty() || !chestplate.isDamageableItem()) return "(--/--)";
			return String.format("(%d/%d)",(chestplate.getMaxDamage()-chestplate.getDamageValue()), chestplate.getMaxDamage());
		}

		public static String chestplatedamage_formatted(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			String color;
			ItemStack chestplate = localPlayer.getInventory().getArmor(2);
			if (chestplate.isEmpty() || !chestplate.isDamageableItem()) return Formatting.DARK_GRAY + "(--/--)" + Formatting.RESET;
			int damageLevelpct = Math.round(((chestplate.getMaxDamage()-chestplate.getDamageValue()) * 100f)/chestplate.getMaxDamage());
			if (damageLevelpct >= 80) {
				color = Formatting.GREEN;
			} else if (damageLevelpct >= 60) {
				color = Formatting.DARK_GREEN;
			} else if (damageLevelpct >= 40) {
				color = Formatting.GOLD;
			} else if (damageLevelpct >= 20) {
				color = Formatting.YELLOW;
			} else if (damageLevelpct >= 10) {
				color = Formatting.DARK_RED;
			} else {
				color = Formatting.RED;
			}
			return color + String.format("(%d/%d)",(chestplate.getMaxDamage()-chestplate.getDamageValue()), chestplate.getMaxDamage()) + Formatting.RESET;
		}

		public static String chestplatename(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			ItemStack chestplate = localPlayer.getInventory().getArmor(2);
			if (chestplate.isEmpty()) return "";
			String name = getNameFromStack(chestplate);
			return Functions.translateInternal(name, localPlayer);
		}

		public static String helmetdamage(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			ItemStack helmet = localPlayer.getInventory().getArmor(3);
			if (helmet.isEmpty() || !helmet.isDamageableItem()) return "(--/--)";
			return String.format("(%d/%d)",(helmet.getMaxDamage()-helmet.getDamageValue()), helmet.getMaxDamage());
		}

		public static String helmetdamage_formatted(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			String color;
			ItemStack helmet = localPlayer.getInventory().getArmor(3);
			if (helmet.isEmpty() || !helmet.isDamageableItem()) return Formatting.DARK_GRAY + "(--/--)" + Formatting.RESET;
			int damageLevelpct = Math.round(((helmet.getMaxDamage()-helmet.getDamageValue()) * 100f)/helmet.getMaxDamage());
			if (damageLevelpct >= 80) {
				color = Formatting.GREEN;
			} else if (damageLevelpct >= 60) {
				color = Formatting.DARK_GREEN;
			} else if (damageLevelpct >= 40) {
				color = Formatting.GOLD;
			} else if (damageLevelpct >= 20) {
				color = Formatting.YELLOW;
			} else if (damageLevelpct >= 10) {
				color = Formatting.DARK_RED;
			} else {
				color = Formatting.RED;
			}
			return color + String.format("(%d/%d)",(helmet.getMaxDamage()-helmet.getDamageValue()), helmet.getMaxDamage()) + Formatting.RESET;
		}

		public static String helmetname(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			ItemStack helmet = localPlayer.getInventory().getArmor(3);
			if (helmet.isEmpty()) return "";
			String name = getNameFromStack(helmet);
			return Functions.translateInternal(name, localPlayer);
		}

		public static String mainhanddamage(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			ItemStack mainhand = localPlayer.getInventory().getSelected();
			if (mainhand.isEmpty()) return "--";
			if (!mainhand.isDamageableItem()) return mainhand.getCount() + "x";
			return String.format("(%d/%d)",(mainhand.getMaxDamage()-mainhand.getDamageValue()), mainhand.getMaxDamage());
		}

		public static String mainhanddamage_formatted(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			String color;
			ItemStack mainhand = localPlayer.getInventory().getSelected();
			if (mainhand.isEmpty()) return Formatting.DARK_GRAY + "--" + Formatting.RESET;
			if (!mainhand.isDamageableItem()) return mainhand.getCount() + "x";
			int damageLevelpct = Math.round(((mainhand.getMaxDamage()-mainhand.getDamageValue()) * 100f)/mainhand.getMaxDamage());
			if (damageLevelpct >= 80) {
				color = Formatting.GREEN;
			} else if (damageLevelpct >= 60) {
				color = Formatting.DARK_GREEN;
			} else if (damageLevelpct >= 40) {
				color = Formatting.GOLD;
			} else if (damageLevelpct >= 20) {
				color = Formatting.YELLOW;
			} else if (damageLevelpct >= 10) {
				color = Formatting.DARK_RED;
			} else {
				color = Formatting.RED;
			}
			return color + String.format("(%d/%d)",(mainhand.getMaxDamage()-mainhand.getDamageValue()), mainhand.getMaxDamage()) + Formatting.RESET;
		}

		public static String mainhandname(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			ItemStack mainhand = localPlayer.getInventory().getSelected();
			if (mainhand.isEmpty()) return "";
			String name = getNameFromStack(mainhand);
			return Functions.translateInternal(name, localPlayer);
		}

		public static String offhanddamage(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			ItemStack offhand = localPlayer.getInventory().offhand.get(0);
			if (offhand.isEmpty()) return "--";
			if (!offhand.isDamageableItem()) return offhand.getCount() + "x";
			return String.format("(%d/%d)",(offhand.getMaxDamage()-offhand.getDamageValue()), offhand.getMaxDamage());
		}

		public static String offhanddamage_formatted(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			String color;
			ItemStack offhand = localPlayer.getInventory().offhand.get(0);
			if (offhand.isEmpty()) return Formatting.DARK_GRAY + "--" + Formatting.RESET;
			if (!offhand.isDamageableItem()) return offhand.getCount() + "x";
			int damageLevelpct = Math.round(((offhand.getMaxDamage()-offhand.getDamageValue()) * 100f)/offhand.getMaxDamage());
			if (damageLevelpct >= 80) {
				color = Formatting.GREEN;
			} else if (damageLevelpct >= 60) {
				color = Formatting.DARK_GREEN;
			} else if (damageLevelpct >= 40) {
				color = Formatting.GOLD;
			} else if (damageLevelpct >= 20) {
				color = Formatting.YELLOW;
			} else if (damageLevelpct >= 10) {
				color = Formatting.DARK_RED;
			} else {
				color = Formatting.RED;
			}
			return color + String.format("(%d/%d)",(offhand.getMaxDamage()-offhand.getDamageValue()), offhand.getMaxDamage()) + Formatting.RESET;
		}

		public static String offhandname(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			ItemStack offhand = localPlayer.getInventory().offhand.get(0);
			if (offhand.isEmpty()) return "";
			String name = getNameFromStack(offhand);
			return Functions.translateInternal(name, localPlayer);
		}
	}

	public static class TargetInfo {
		public static String targetName(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			HitResult hitResult = minecraft.hitResult;
			if (hitResult.getType() == HitResult.Type.BLOCK) {
				BlockPos target = ((BlockHitResult) hitResult).getBlockPos();
				BlockState blockState = clientLevel.getBlockState(target);
				BlockEntity blockEntity = clientLevel.getBlockEntity(target);
				String name = "";
				if (blockEntity instanceof Nameable nameable) {
					name = nameable.getName().getString();
				} else {
					name = blockState.getBlock().getDescriptionId();
				}
				return Functions.translateInternal(name, localPlayer);
			} else if (hitResult.getType().equals(HitResult.Type.ENTITY)) {
				EntityHitResult entityHitResult = (EntityHitResult)hitResult;
				return entityHitResult.getEntity().getName().getString();
			}
			return "";
		}

		public static String targetOwner(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 1) throw new IllegalArgumentException(String.format("%s takes 0 arguments",parts[0]));
			if (minecraft.hitResult instanceof EntityHitResult hitResult && hitResult.getEntity() instanceof TamableAnimal pet && pet.getOwner() != null) {
				return pet.getOwner().getName().getString();
			}
			return "";
		}
	}

	public static class Functions {
		public static String translate(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 2) throw new IllegalArgumentException("translate takes 1 argument");
			return Functions.translateInternal(parts[1], localPlayer);
		}

		public static String lessThan(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 5) throw new IllegalArgumentException("lt takes 4 arguments");
			if (!isNumeric(parts[1]) && !isNumeric(parts[2])) throw new IllegalArgumentException("The first two arguments must be numeric");
			return (Integer.valueOf(parts[1]) < Integer.valueOf(parts[2])) ? parts[3] : parts[4];
		}

		public static String lessThanOrEqual(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 5) throw new IllegalArgumentException("le takes 4 arguments");
			if (!isNumeric(parts[1]) && !isNumeric(parts[2])) throw new IllegalArgumentException("The first two arguments must be numeric");
			return (Integer.valueOf(parts[1]) <= Integer.valueOf(parts[2])) ? parts[3] : parts[4];
		}

		public static String greaterThan(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 5) throw new IllegalArgumentException("gt takes 4 arguments");
			if (!isNumeric(parts[1]) && !isNumeric(parts[2])) throw new IllegalArgumentException("The first two arguments must be numeric");
			return (Integer.valueOf(parts[1]) > Integer.valueOf(parts[2])) ? parts[3] : parts[4];
		}

		public static String greaterThanOrEqual(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 5) throw new IllegalArgumentException("le takes 4 arguments");
			if (!isNumeric(parts[1]) && !isNumeric(parts[2])) throw new IllegalArgumentException("The first two arguments must be numeric");
			return (Integer.valueOf(parts[1]) >= Integer.valueOf(parts[2])) ? parts[3] : parts[4];
		}

		public static String equals(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 5) throw new IllegalArgumentException("eq takes 4 arguments");
			return (parts[1].equals(parts[2])) ? parts[3] : parts[4];
		}

		public static String notEquals(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 5) throw new IllegalArgumentException("ne takes 4 arguments");
			return (!parts[1].equals(parts[2])) ? parts[3] : parts[4];
		}

		public static Object exists(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float v) {
			if (parts.length != 3) throw new IllegalArgumentException("exists takes 2 arguments");
			return !parts[1].isEmpty() ? parts[2] : "";
		}

		public static String curio(String[] parts, Minecraft minecraft, ClientLevel clientLevel, LocalPlayer localPlayer, float partialTick) {
			if (parts.length != 4) throw new IllegalArgumentException("curio takes 3 arguments");
			if (!isNumeric(parts[2])) throw new IllegalArgumentException("curio argument 2 must be numeric");
			if (!List.of("damage", "damage_formatted", "name").contains(parts[3])) throw new IllegalArgumentException("curio argument 3 must be \"damage\", \"damage_formatted\", or \"name\"");
			AtomicReference<String> output = new AtomicReference<>("");
			CuriosApi.getCuriosInventory(localPlayer).ifPresent(curiosInventory -> {
				curiosInventory.findCurio(parts[1],Integer.valueOf(parts[2])).ifPresent(slotResult -> {
					ItemStack stack = slotResult.stack();
					switch(parts[3]) {
						case "damage":
							if (stack.isEmpty() || !stack.isDamageableItem()) {
								output.set("--");
								break;
							} else {
								output.set(String.format("(%d/%d)", stack.getMaxDamage()-stack.getDamageValue(), stack.getMaxDamage()));
							}
						case "damage_formatted":
							if (stack.isEmpty() || !stack.isDamageableItem()) {
								output.set(Formatting.DARK_GRAY + "--" + Formatting.RESET);
								break;
							} else {
								String color;
								int damageLevelpct = Math.round(((stack.getMaxDamage()-stack.getDamageValue()) * 100f)/stack.getMaxDamage());
								if (damageLevelpct >= 80) {
									color = Formatting.GREEN;
								} else if (damageLevelpct >= 60) {
									color = Formatting.DARK_GREEN;
								} else if (damageLevelpct >= 40) {
									color = Formatting.GOLD;
								} else if (damageLevelpct >= 20) {
									color = Formatting.YELLOW;
								} else if (damageLevelpct >= 10) {
									color = Formatting.DARK_RED;
								} else {
									color = Formatting.RED;
								}
								output.set(color + String.format("(%d/%d)", stack.getMaxDamage()-stack.getDamageValue(), stack.getMaxDamage()) + Formatting.RESET);
								break;
							}
						case "name":
							if (!stack.isEmpty()) {
								String name = Equipment.getNameFromStack(stack);
								output.set(Functions.translateInternal(name, localPlayer));
							}
					}
				});
			});
			return output.get();
		}

		public static String translateInternal(String key, LocalPlayer localPlayer) {
			try {
				return new TranslatableContents(key, null, TranslatableContents.NO_ARGS).resolve(null, localPlayer, 0).getString();
			} catch (Exception e) {
				return "TRANSLATION ERROR!";
			}
		}

		public static boolean isNumeric(String value) {
			try {
				Double.parseDouble(value);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}
}
