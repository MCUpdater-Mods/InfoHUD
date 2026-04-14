package org.mcupdater.infohud.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.mcupdater.infohud.InfoHUD;
import org.mcupdater.infohud.InfoHUDClient;
import org.mcupdater.infohud.setup.Config;
import org.mcupdater.infohud.tags.TagRegistry;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
public class Overlay {
	protected static StringSubstitutor substitutor = new StringSubstitutor(StringLookupFactory.builder().get().functionStringLookup(TagRegistry::lookup));

	static {
		substitutor.setEnableSubstitutionInVariables(true);
	}

	@SubscribeEvent
	public static void renderOverlay(RenderGuiEvent.Post event) {
		if (!InfoHUDClient.showOverlay) return;
		GuiGraphicsExtractor guiGraphics = event.getGuiGraphics();
		Font font = Minecraft.getInstance().font;
		int xOffset;
		int yOffset;
		List<String> lines;
		//Top Left
		lines = getPrunedLines(Config.topLeftEntries.get());
		xOffset = Config.topLeftXOffset.get();
		yOffset = Config.topLeftYOffset.get();
		int lineNum = 0;
		for (String line : lines) {
			guiGraphics.text(font, line, xOffset, (lineNum * font.lineHeight) + yOffset, 0xFFFFFFFF, true);
			lineNum++;
		}
		//Middle Left
		lines = getPrunedLines(Config.middleLeftEntries.get());
		xOffset = Config.middleLeftXOffset.get();
		yOffset = (guiGraphics.guiHeight()/2) - ((lines.size() * font.lineHeight)/2);
		lineNum = 0;
		for (String line : lines) {
			guiGraphics.text(font, line, xOffset, (lineNum * font.lineHeight) + yOffset, 0xFFFFFFFF, true);
			lineNum++;
		}
		//Bottom Left
		lines = getPrunedLines(Config.bottomLeftEntries.get());
		xOffset = Config.bottomLeftXOffset.get();
		yOffset = guiGraphics.guiHeight() - Config.bottomLeftYOffset.get() - (lines.size() * font.lineHeight);
		lineNum = 0;
		for (String line : lines) {
			guiGraphics.text(font, line, xOffset, (lineNum * font.lineHeight) + yOffset, 0xFFFFFFFF, true);
			lineNum++;
		}
		//Top Center
		lines = getPrunedLines(Config.topCenterEntries.get());
		xOffset = guiGraphics.guiWidth()/2;
		yOffset = Config.topCenterYOffset.get();
		lineNum = 0;
		for (String line : lines) {
			int width = font.width(line);
			guiGraphics.text(font, line, xOffset - (width/2), (lineNum * font.lineHeight) + yOffset, 0xFFFFFFFF, true);
			lineNum++;
		}
		//Bottom Center
		lines = getPrunedLines(Config.bottomCenterEntries.get());
		xOffset = guiGraphics.guiWidth()/2;
		yOffset = guiGraphics.guiHeight() - Config.bottomCenterYOffset.get() - (lines.size() * font.lineHeight);
		lineNum = 0;
		for (String line : lines) {
			int width = font.width(line);
			guiGraphics.text(font, line, xOffset - (width/2), (lineNum * font.lineHeight) + yOffset, 0xFFFFFFFF, true);
			lineNum++;
		}
		//Top Right
		lines = getPrunedLines(Config.topRightEntries.get());
		xOffset = Config.topRightXOffset.get();
		yOffset = Config.topRightYOffset.get();
		lineNum = 0;
		for (String line : lines) {
			int width = font.width(line);
			guiGraphics.text(font, line, guiGraphics.guiWidth() - xOffset - width, (lineNum * font.lineHeight) + yOffset, 0xFFFFFFFF, true);
			lineNum++;
		}
		//Middle Right
		lines = getPrunedLines(Config.middleRightEntries.get());
		xOffset = Config.middleRightXOffset.get();
		yOffset = (guiGraphics.guiHeight()/2) - ((lines.size() * font.lineHeight)/2);
		lineNum = 0;
		for (String line : lines) {
			int width = font.width(line);
			guiGraphics.text(font, line, guiGraphics.guiWidth() - xOffset - width, (lineNum * font.lineHeight) + yOffset, 0xFFFFFFFF, true);
			lineNum++;
		}
		//Bottom Right
		lines = getPrunedLines(Config.bottomRightEntries.get());
		xOffset = Config.bottomRightXOffset.get();
		yOffset = guiGraphics.guiHeight() - Config.bottomRightYOffset.get() - (lines.size() * font.lineHeight);
		lineNum = 0;
		for (String line : lines) {
			int width = font.width(line);
			guiGraphics.text(font, line, guiGraphics.guiWidth() - xOffset - width, (lineNum * font.lineHeight) + yOffset, 0xFFFFFFFF, true);
			lineNum++;
		}
	}

	private static List<String> getPrunedLines(List<? extends String> entries) {
		List<String> finalEntries = new ArrayList<>();
		for (String line : entries) {
			String result = substitutor.replace(line).replace("\\:",":");
			if (!result.isEmpty()) {
				finalEntries.add(result);
			}
		}
		return finalEntries;
	}

}
