package org.mcupdater.infohud.events;

import net.minecraft.util.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.storage.LevelData;
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

@EventBusSubscriber(modid=InfoHUD.MODID)
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
			LevelData.RespawnData respawnData = serverPlayer.getRespawnConfig().respawnData();
			if (respawnData.dimension().equals(player.level().dimension()) && respawnData.pos() != null) {
				distance = Math.round(Math.sqrt(respawnData.pos().distSqr(player.blockPosition())));
			}
			PacketDistributor.sendToPlayer(serverPlayer, new BedDistance(distance));

			// Check Awake Time
			int awake = serverPlayer.getStats().getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) / 24000;
			if (!awakeDaysMap.containsKey(player) || awakeDaysMap.get(player) != awake) {
				awakeDaysMap.put(player, awake);
				PacketDistributor.sendToPlayer(serverPlayer, new AwakeDays(awake));
			}

			// Check Slime Chunk (Magic number is constant salt for slimes)
			ChunkPos chunkPos = ChunkPos.containing(player.blockPosition());
			boolean slimeChunk = WorldgenRandom.seedSlimeChunk(chunkPos.x(), chunkPos.z(), ((WorldGenLevel) player.level()).getSeed(), 987234911L).nextInt(10) == 0;
			if (!slimeChunkMap.containsKey(player) || slimeChunkMap.get(player) != slimeChunk) {
				slimeChunkMap.put(player, slimeChunk);
				PacketDistributor.sendToPlayer(serverPlayer, new SlimeChunk(slimeChunk));
			}
		}
	}

	private static void updateDaytime(Player player) {
		if (player.level() instanceof ServerLevel serverLevel) {
			Boolean daytime = !serverLevel.isDarkOutside();
			if (!daytimeMap.containsKey(player) || !daytimeMap.get(player).equals(daytime)) {
				daytimeMap.put(player, daytime);
				PacketDistributor.sendToPlayer((ServerPlayer) player, new DaytimePacket(daytime));
			}
		}
	}

	private static void checkStructure(Player player) {
		if (player.level() instanceof ServerLevel serverLevel) {
			List<StructureStart> starts = serverLevel.structureManager().startsForStructure(player.chunkPosition(), context -> true);
			Optional<Structure> structure = starts.stream().filter(start -> start.getBoundingBox().isInside(player.blockPosition())).map(StructureStart::getStructure).findFirst();
			Identifier resource = structure.map(value -> player.level().registryAccess().lookupOrThrow(Registries.STRUCTURE).getKey(value)).orElse(InfoHUD.EMPTY_STRUCTURE);
			String name = Util.makeDescriptionId("structure", resource);
			if (!structures.containsKey(player) || !structures.get(player).equals(resource.toString())) {
				structures.put(player, resource.toString());
				PacketDistributor.sendToPlayer((ServerPlayer) player, new StructureKey(name));
			}
		}
	}
}
