package org.mcupdater.infohud.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.mcupdater.infohud.InfoHUD;

public record SlimeChunk(Boolean slimeChunk) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<SlimeChunk> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(InfoHUD.MODID,"slime_chunk"));
	public static final StreamCodec<ByteBuf,SlimeChunk> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.BOOL,
			SlimeChunk::slimeChunk,
			SlimeChunk::new
	);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
