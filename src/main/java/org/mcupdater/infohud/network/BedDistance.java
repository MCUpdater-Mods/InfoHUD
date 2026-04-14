package org.mcupdater.infohud.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.mcupdater.infohud.InfoHUD;

public record BedDistance(Long distance) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<BedDistance> TYPE = new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(InfoHUD.MODID,"bed_distance"));
	public static final StreamCodec<ByteBuf,BedDistance> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.VAR_LONG,
			BedDistance::distance,
			BedDistance::new
	);
	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
