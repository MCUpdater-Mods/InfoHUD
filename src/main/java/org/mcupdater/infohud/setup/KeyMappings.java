package org.mcupdater.infohud.setup;

import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;
import org.mcupdater.infohud.InfoHUD;

public class KeyMappings {
	public static final Lazy<KeyMapping> TOGGLE_INFOHUD = Lazy.of(() -> new KeyMapping("key.infohud.toggle.desc", GLFW.GLFW_KEY_F4, new KeyMapping.Category(Identifier.fromNamespaceAndPath(InfoHUD.MODID,"category"))));

	@SubscribeEvent
	public static void registerKeys(final RegisterKeyMappingsEvent registerKeyMappingsEvent) {
		registerKeyMappingsEvent.register(TOGGLE_INFOHUD.get());
	}
}
