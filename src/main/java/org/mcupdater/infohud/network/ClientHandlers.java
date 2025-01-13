package org.mcupdater.infohud.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.mcupdater.infohud.InfoHUDClient;
import org.mcupdater.infohud.setup.Config;

public class ClientHandlers {

	public static void inventoryHandler(final InventoryStatus status, IPayloadContext iPayloadContext) {
		InfoHUDClient.localStatus = status;
	}

	public static void structureHandler(final StructureKey structureKey, IPayloadContext iPayloadContext) {
		InfoHUDClient.currentStructure = structureKey.key();
	}

	public static void itemConfigHandler(ItemConfigPacket itemConfigPacket, IPayloadContext iPayloadContext) {
		InfoHUDClient.serverRequiresItems = itemConfigPacket.value();
	}

	public static void bedDistanceHandler(BedDistance bedDistance, IPayloadContext iPayloadContext) {
		InfoHUDClient.bedDistance = bedDistance.distance();
	}
}
