package org.mcupdater.infohud;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.mcupdater.infohud.network.InventoryStatus;
import org.mcupdater.infohud.setup.KeyMappings;

import java.util.HashMap;

@Mod(value = InfoHUD.MODID,dist = Dist.CLIENT)
public class InfoHUDClient {
	public static InventoryStatus localStatus = new InventoryStatus(new HashMap<>());
	public static String currentStructure;
	public static Boolean serverRequiresItems = false;
	public static Long bedDistance;
	public static Boolean showOverlay = true;

	public InfoHUDClient(IEventBus modEventBus, ModContainer modContainer) {
		modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		modEventBus.register(KeyMappings.class);
	}
}
