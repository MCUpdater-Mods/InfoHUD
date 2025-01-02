package org.mcupdater.infohud.tags;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;

@FunctionalInterface
public interface TagProvider {
	Object getValue(Minecraft minecraft, ClientLevel level, LocalPlayer player, float partialTick);
}
