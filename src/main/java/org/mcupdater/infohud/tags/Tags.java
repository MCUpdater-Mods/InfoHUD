package org.mcupdater.infohud.tags;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.LightLayer;

import java.util.Locale;

public class Tags {
	public static String day(Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
		return String.format(Locale.ENGLISH, "%d", level.getDayTime() / 24000);
	}

	public static String mctime(Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
		long time = level.getDayTime();
		return String.format(Locale.ENGLISH, "%02d:%02d", (time / 1000) % 24, (time % 1000) * 60 / 1000);
	}

	public static String biome(Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
		return Util.makeDescriptionId(
				"biome",
				level.registryAccess().registryOrThrow(Registries.BIOME).getKey(level.getBiome(localPlayer.blockPosition()).value())
		);
	}

	public static String light(Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
		return String.format(Locale.ENGLISH, "%d", Math.max(level.getBrightness(LightLayer.BLOCK, localPlayer.blockPosition()), Math.min(level.getBrightness(LightLayer.SKY, localPlayer.blockPosition()), Math.round(level.getSkyDarken(partialTick) * 15.0f))));
	}

	public static String skylight(Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
		return String.format(Locale.ENGLISH, "%d", level.getBrightness(LightLayer.SKY, localPlayer.blockPosition()));
	}

	public static String blocklight(Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
		return String.format(Locale.ENGLISH, "%d", level.getBrightness(LightLayer.BLOCK, localPlayer.blockPosition()));
	}

	public static String debug(Minecraft minecraft, ClientLevel level, LocalPlayer localPlayer, float partialTick) {
		return Float.toString(level.getSkyDarken(partialTick) * 15.0f);
	}
}
