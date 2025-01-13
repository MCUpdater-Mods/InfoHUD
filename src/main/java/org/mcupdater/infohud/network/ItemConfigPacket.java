package org.mcupdater.infohud.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.mcupdater.infohud.InfoHUD;

public record ItemConfigPacket(Boolean value) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<ItemConfigPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(InfoHUD.MODID,"require_items"));
	public static final StreamCodec<ByteBuf, ItemConfigPacket> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.BOOL,
			ItemConfigPacket::value,
			ItemConfigPacket::new
	);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
