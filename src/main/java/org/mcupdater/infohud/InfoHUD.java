package org.mcupdater.infohud;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.PacketDistributor;
import org.mcupdater.infohud.events.ServerMonitor;
import org.mcupdater.infohud.network.ItemConfigPacket;
import org.mcupdater.infohud.setup.Config;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Mod(InfoHUD.MODID)
public class InfoHUD
{
    public static final String MODID = "infohud";
    public static final ResourceLocation EMPTY_STRUCTURE = ResourceLocation.fromNamespaceAndPath(MODID, "empty_structure");
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final Map<ResourceLocation,ResourceLocation> structureMap = new HashMap<>();

    public InfoHUD(IEventBus modEventBus, ModContainer modContainer)
    {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        modEventBus.addListener(this::onConfigReload);
        NeoForge.EVENT_BUS.register(ServerMonitor.INSTANCE);
        ServerMonitor.registerItem("clock", Items.CLOCK);
        ServerMonitor.registerItem("compass", Items.COMPASS);
        InfoHUD.structureMap.put(EMPTY_STRUCTURE,EMPTY_STRUCTURE);
    }

    private void onConfigReload(ModConfigEvent.Reloading event) {
        for (ServerPlayer player : ServerMonitor.INSTANCE.playerSet) {
            PacketDistributor.sendToPlayer(player, new ItemConfigPacket(Config.REQUIRE_ITEMS.get()));
        }
    }
}
