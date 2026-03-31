package org.mcupdater.infohud.events;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.mcupdater.infohud.InfoHUD;
import org.mcupdater.infohud.network.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@EventBusSubscriber(modid=InfoHUD.MODID,bus= EventBusSubscriber.Bus.GAME)
public class PlayerMonitor {
	protected static Map<Player, String> structures = new HashMap<>();
	public static Map<Player, Integer> awakeDaysMap = new HashMap<>();
	public static Map<Player, Boolean> slimeChunkMap = new HashMap<>();
	public static Map<Player, Boolean> daytimeMap = new HashMap<>();

	@SubscribeEvent
	public static void playerTick(PlayerTickEvent.Post tickEvent) {
		Player player = tickEvent.getEntity();

		if (player instanceof ServerPlayer serverPlayer) {
			if (!ServerMonitor.INSTANCE.playerSet.contains(serverPlayer)) return;
		// Check Structure
		checkStructure(serverPlayer);

		// Update Daytime
		updateDaytime(serverPlayer);

		long distance = -1L;
			// Check Bed Distance
			if (serverPlayer.getRespawnDimension().equals(player.level().dimension()) && serverPlayer.getRespawnPosition() != null) {
				distance = Math.round(Math.sqrt(serverPlayer.getRespawnPosition().distSqr(player.blockPosition())));
			}
			PacketDistributor.sendToPlayer(serverPlayer, new BedDistance(distance));

			// Check Awake Time
			int awake = serverPlayer.getStats().getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) / 24000;
			if (!awakeDaysMap.containsKey(player) || awakeDaysMap.get(player) != awake) {
				awakeDaysMap.put(player, awake);
				PacketDistributor.sendToPlayer(serverPlayer, new AwakeDays(awake));
			}

			// Check Slime Chunk (Magic number is constant salt for slimes)
			ChunkPos chunkPos = new ChunkPos(player.blockPosition());
			boolean slimeChunk = WorldgenRandom.seedSlimeChunk(chunkPos.x, chunkPos.z, ((WorldGenLevel) player.level()).getSeed(), 987234911L).nextInt(10) == 0;
			if (!slimeChunkMap.containsKey(player) || slimeChunkMap.get(player) != slimeChunk) {
				slimeChunkMap.put(player, slimeChunk);
				PacketDistributor.sendToPlayer(serverPlayer, new SlimeChunk(slimeChunk));
			}
		}
	}

	private static void updateDaytime(Player player) {
		if (player.getServer() != null) {
			Boolean daytime = player.getServer().getLevel(player.level().dimension()).isDay();
			if (!daytimeMap.containsKey(player) || !daytimeMap.get(player).equals(daytime)) {
				daytimeMap.put(player, daytime);
				PacketDistributor.sendToPlayer((ServerPlayer) player, new DaytimePacket(daytime));
			}
		}
	}

	private static void checkStructure(Player player) {
		if (player.getServer() != null) {
			List<StructureStart> starts = player.getServer().getLevel(player.level().dimension()).structureManager().startsForStructure(new ChunkPos(player.blockPosition()), context -> true);
			Optional<Structure> structure = starts.stream().filter(start -> start.getBoundingBox().isInside(player.blockPosition())).map(StructureStart::getStructure).findFirst();
			ResourceLocation resource = structure.map(value -> player.level().registryAccess().registryOrThrow(Registries.STRUCTURE).getKey(value)).orElse(InfoHUD.EMPTY_STRUCTURE);
			String name = Util.makeDescriptionId("structure", resource);
			if (!structures.containsKey(player) || !structures.get(player).equals(resource.toString())) {
				structures.put(player, resource.toString());
				PacketDistributor.sendToPlayer((ServerPlayer) player, new StructureKey(name));
			}
		}
	}
}
