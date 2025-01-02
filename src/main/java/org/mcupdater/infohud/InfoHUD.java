package org.mcupdater.infohud;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.mcupdater.infohud.setup.Config;
import org.mcupdater.infohud.tags.TagRegistry;
import org.slf4j.Logger;

@Mod(InfoHUD.MODID)
public class InfoHUD
{
    public static final String MODID = "infohud";
    public static final Logger LOGGER = LogUtils.getLogger();

    public InfoHUD(IEventBus modEventBus, ModContainer modContainer)
    {
        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        TagRegistry.init();
    }
}
