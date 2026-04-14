package org.mcupdater.infohud.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.mcupdater.infohud.InfoHUD;

import java.util.HashMap;
import java.util.Map;

public record InventoryStatus(Map<String, Boolean> status) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<InventoryStatus> TYPE = new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(InfoHUD.MODID,"inventory"));

	public static final StreamCodec<ByteBuf, InventoryStatus> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.map(
					HashMap::new,
					ByteBufCodecs.STRING_UTF8,
					ByteBufCodecs.BOOL
			),
			InventoryStatus::status,
			InventoryStatus::new
	);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}
