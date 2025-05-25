package org.mcupdater.infohud.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.mcupdater.infohud.InfoHUD;
import org.mcupdater.infohud.network.InventoryStatus;
import org.mcupdater.infohud.network.ItemConfigPacket;
import org.mcupdater.infohud.setup.Config;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.event.CurioChangeEvent;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ServerMonitor {
	public static ServerMonitor INSTANCE = new ServerMonitor();
	private Map<ServerPlayer, ContainerListener> listenerMap;
	protected Map<String, Item> monitoredItems;
	private Map<ServerPlayer, Map<String,Boolean>> playerItemStatus;
	public Set<ServerPlayer> playerSet;

	public ServerMonitor() {
		listenerMap = new HashMap<>();
		monitoredItems = new HashMap<>();
		playerItemStatus = new HashMap<>();
		playerSet = new LinkedHashSet<>();
	}

	public static void registerItem(String name, Item item) {
		INSTANCE.monitoredItems.put(name, item);
	}

	public void updateInventory(Player player) {
		int invSize = player.getInventory().getContainerSize();
		HashMap<String, Boolean> itemStatus = new HashMap<>();
		monitoredItems.keySet().forEach(entry -> itemStatus.put(entry,false));
		for (int index = 0; index < invSize; index++) {
			for (Map.Entry<String, Item> entry : monitoredItems.entrySet()) {
				if (player.getInventory().getItem(index).is(entry.getValue())) {
					itemStatus.put(entry.getKey(), true);
				}
			}
		}
		CuriosApi.getCuriosInventory(player).ifPresent(curiosInventory -> {
			for (int index = 0; index < curiosInventory.getEquippedCurios().getSlots(); index++) {
				for (Map.Entry<String, Item> entry : monitoredItems.entrySet()) {
					if (curiosInventory.getEquippedCurios().getStackInSlot(index).is(entry.getValue())) {
						itemStatus.put(entry.getKey(), true);
					}
				}
			}
		});
		PacketDistributor.sendToPlayer((ServerPlayer) player, new InventoryStatus(itemStatus));
	}

	@SubscribeEvent
	public void curiosChanged(CurioChangeEvent curioChangeEvent) {
		if (curioChangeEvent.getEntity() instanceof ServerPlayer player && playerSet.contains(player)) {
			updateInventory(player);
		}
	}

	@SubscribeEvent
	public void playerConnected(PlayerEvent.PlayerLoggedInEvent playerLoggedInEvent) {
		if (((ServerPlayer) playerLoggedInEvent.getEntity()).connection.hasChannel(ResourceLocation.fromNamespaceAndPath(InfoHUD.MODID,"inventory"))) {
			ContainerListener listener = new PlayerContainerMonitor((ServerPlayer) playerLoggedInEvent.getEntity());
			playerLoggedInEvent.getEntity().inventoryMenu.addSlotListener(listener);
			listenerMap.put((ServerPlayer) playerLoggedInEvent.getEntity(), listener);
			playerSet.add((ServerPlayer) playerLoggedInEvent.getEntity());
			updateInventory(playerLoggedInEvent.getEntity());
			if (Config.REQUIRE_ITEMS.get() && playerSet.contains(playerLoggedInEvent.getEntity())) {
				PacketDistributor.sendToPlayer((ServerPlayer) playerLoggedInEvent.getEntity(), new ItemConfigPacket(Config.REQUIRE_ITEMS.get()));
			}
		}
	}

	@SubscribeEvent
	public void playerDisconnected(PlayerEvent.PlayerLoggedOutEvent playerLoggedOutEvent) {
		playerLoggedOutEvent.getEntity().inventoryMenu.removeSlotListener(listenerMap.get((ServerPlayer)playerLoggedOutEvent.getEntity()));
		listenerMap.remove((ServerPlayer)playerLoggedOutEvent.getEntity());
		PlayerMonitor.awakeDaysMap.remove(playerLoggedOutEvent.getEntity());
		playerSet.remove(playerLoggedOutEvent.getEntity());
	}

	private class PlayerContainerMonitor implements ContainerListener {

		private final ServerPlayer player;

		public PlayerContainerMonitor(ServerPlayer player) {
			this.player = player;
		}

		@Override
		public void slotChanged(AbstractContainerMenu containerToSend, int dataSlotIndex, ItemStack stack) {
			updateInventory(this.player);
		}

		@Override
		public void dataChanged(AbstractContainerMenu containerMenu, int dataSlotIndex, int value) {
		}
	}
}
