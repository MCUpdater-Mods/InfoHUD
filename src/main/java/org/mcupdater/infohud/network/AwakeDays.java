package org.mcupdater.infohud.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.mcupdater.infohud.InfoHUD;

public record AwakeDays(Integer days) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<AwakeDays> TYPE = new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(InfoHUD.MODID,"awake_days"));
	public static final StreamCodec<ByteBuf,AwakeDays> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.INT,
			AwakeDays::days,
			AwakeDays::new
	);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
