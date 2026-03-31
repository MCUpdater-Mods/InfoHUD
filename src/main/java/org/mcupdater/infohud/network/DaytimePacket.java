package org.mcupdater.infohud.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.mcupdater.infohud.InfoHUD;

public record DaytimePacket(Boolean isDaytime) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<DaytimePacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(InfoHUD.MODID, "daytime"));
	public static final StreamCodec<ByteBuf,DaytimePacket> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.BOOL,
			DaytimePacket::isDaytime,
			DaytimePacket::new
	);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
