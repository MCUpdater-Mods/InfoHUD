package org.mcupdater.infohud.gui;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import org.mcupdater.infohud.setup.Config;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.mcupdater.infohud.tags.TagProvider;
import org.mcupdater.infohud.tags.TagRegistry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@EventBusSubscriber
public class Overlay {

	@SubscribeEvent
	public static void renderOverlay(RenderGuiEvent.Post event) {
		GuiGraphics guiGraphics = event.getGuiGraphics();
		Font font = Minecraft.getInstance().font;
		StrSubstitutor substitutor = new StrSubstitutor(getFields(Minecraft.getInstance()));
		int xOffset;
		int yOffset;
		//Top Left
		xOffset = Config.topLeftXOffset.get();
		yOffset = Config.topLeftYOffset.get();
		int lineNum = 0;
		for (String line : Config.topLeftEntries.get()) {
			MutableComponent finalLine = Component.empty();
			Arrays.stream(substitutor.replace(line).split(" ")).forEach(
					element -> finalLine.append(Component.translatable(element).append(Component.literal(" ")))
			);
			guiGraphics.drawString(font, finalLine, xOffset, (lineNum * font.lineHeight) + yOffset, 0x00FFFFFF, true);
			lineNum++;
		}
	}

	private static Map<String, String> getFields(Minecraft minecraft) {
		Map<String,String> fields = new HashMap<>();
		ClientLevel level = (ClientLevel) minecraft.player.level();
		LocalPlayer player = minecraft.player;
		for(Map.Entry<String, TagProvider> tagProviderEntry : TagRegistry.getEntries()) {
			fields.put(tagProviderEntry.getKey(), tagProviderEntry.getValue().getValue(minecraft, level, player, minecraft.getTimer().getGameTimeDeltaPartialTick(true)).toString());
		}
		return fields;
	}
}
