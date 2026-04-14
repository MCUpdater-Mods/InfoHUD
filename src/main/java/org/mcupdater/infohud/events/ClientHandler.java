package org.mcupdater.infohud.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.mcupdater.infohud.InfoHUD;
import org.mcupdater.infohud.InfoHUDClient;
import org.mcupdater.infohud.setup.KeyMappings;

@EventBusSubscriber(modid=InfoHUD.MODID)
public class ClientHandler {

	@SubscribeEvent
	public static void onClientTick(ClientTickEvent.Post event) {
		if (KeyMappings.TOGGLE_INFOHUD.get().consumeClick()) {
			InfoHUDClient.showOverlay = !InfoHUDClient.showOverlay;
		}
	}
}
