package org.mcupdater.infohud.events;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.mcupdater.infohud.InfoHUD;
import org.mcupdater.infohud.network.BedDistance;
import org.mcupdater.infohud.network.StructureKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@EventBusSubscriber(modid=InfoHUD.MODID,bus= EventBusSubscriber.Bus.GAME)
public class PlayerMonitor {
	protected static Map<Player, String> structures = new HashMap<>();

	@SubscribeEvent
	public static void playerTick(PlayerTickEvent.Post tickEvent) {
		Player player = tickEvent.getEntity();
		checkStructure(player);
		long distance = -1L;
		if (player instanceof ServerPlayer serverPlayer) {
			if (serverPlayer.getRespawnDimension().equals(player.level().dimension()) && serverPlayer.getRespawnPosition() != null) {
				distance = Math.round(Math.sqrt(serverPlayer.getRespawnPosition().distSqr(player.blockPosition())));
			}
			PacketDistributor.sendToPlayer(serverPlayer, new BedDistance(distance));
		}
	}

	private static void checkStructure(Player player) {
		if (player.getServer() != null) {
			List<StructureStart> starts = player.getServer().getLevel(player.level().dimension()).structureManager().startsForStructure(new ChunkPos(player.blockPosition()), context -> true);
			Optional<Structure> structure = starts.stream().filter(start -> start.getBoundingBox().isInside(player.blockPosition())).map(StructureStart::getStructure).findFirst();
			ResourceLocation resource = structure.map(value -> player.level().registryAccess().registryOrThrow(Registries.STRUCTURE).getKey(value)).orElse(InfoHUD.EMPTY_STRUCTURE);
			String name = Util.makeDescriptionId("structure", resource);
			/*
			if (!InfoHUD.structureMap.containsKey(resource)) {
				InfoHUD.LOGGER.debug("resource = {}", resource);
				Registry<StructureSet> structureSetRegistry = player.level().registryAccess().registryOrThrow(Registries.STRUCTURE_SET);
				for (StructureSet set : structureSetRegistry) {
					for (StructureSet.StructureSelectionEntry entry : set.structures()) {
						InfoHUD.LOGGER.debug("set = {}, entry = {}", structureSetRegistry.getKey(set), entry.structure().getKey());
						if (entry.structure().value().equals(structure.get())) {
							InfoHUD.structureMap.put(resource, structureSetRegistry.getKey(set));
						}
					}
				}
			}

			 */
			if (!structures.containsKey(player) || !structures.get(player).equals(resource.toString())) {
				structures.put(player, resource.toString());
				PacketDistributor.sendToPlayer((ServerPlayer) player, new StructureKey(name));
			}
		}
	}
}
