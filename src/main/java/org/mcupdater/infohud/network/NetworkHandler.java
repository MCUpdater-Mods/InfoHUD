package org.mcupdater.infohud.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.mcupdater.infohud.InfoHUD;

@EventBusSubscriber(modid = InfoHUD.MODID)
public class NetworkHandler {

	@SubscribeEvent
	public static void register(final RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar("1").optional();
		registrar.playToClient(
				InventoryStatus.TYPE,
				InventoryStatus.STREAM_CODEC,
				ClientHandlers::inventoryHandler
		);
		registrar.playToClient(
				StructureKey.TYPE,
				StructureKey.STREAM_CODEC,
				ClientHandlers::structureHandler
		);
		registrar.playToClient(
				ItemConfigPacket.TYPE,
				ItemConfigPacket.STREAM_CODEC,
				ClientHandlers::itemConfigHandler
		);
		registrar.playToClient(
				BedDistance.TYPE,
				BedDistance.STREAM_CODEC,
				ClientHandlers::bedDistanceHandler
		);
		registrar.playToClient(
				AwakeDays.TYPE,
				AwakeDays.STREAM_CODEC,
				ClientHandlers::awakeDaysHandler
		);
		registrar.playToClient(
				SlimeChunk.TYPE,
				SlimeChunk.STREAM_CODEC,
				ClientHandlers::slimeChunkHandler
		);
		registrar.playToClient(
				DaytimePacket.TYPE,
				DaytimePacket.STREAM_CODEC,
				ClientHandlers::daytimePacketHandler
		);
	}
}
