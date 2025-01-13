package org.mcupdater.infohud.setup;

import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

public class KeyMappings {
	public static final Lazy<KeyMapping> TOGGLE_INFOHUD = Lazy.of(() -> new KeyMapping("key.infohud.toggle.desc", GLFW.GLFW_KEY_F4, "key.infohud.category"));

	@SubscribeEvent
	public static void registerKeys(final RegisterKeyMappingsEvent registerKeyMappingsEvent) {
		registerKeyMappingsEvent.register(TOGGLE_INFOHUD.get());
	}
}
