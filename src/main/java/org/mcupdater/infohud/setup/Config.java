package org.mcupdater.infohud.setup;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.List;

public class Config {
    public static ForgeConfigSpec CLIENT_CONFIG;
    public static ForgeConfigSpec.ConfigValue<List<String>> topLeft;
    public static ForgeConfigSpec.ConfigValue<List<String>> middleLeft;
    public static ForgeConfigSpec.ConfigValue<List<String>> bottomLeft;
    public static ForgeConfigSpec.ConfigValue<List<String>> topCenter;
    public static ForgeConfigSpec.ConfigValue<List<String>> topRight;
    public static ForgeConfigSpec.ConfigValue<List<String>> middleRight;
    public static ForgeConfigSpec.ConfigValue<List<String>> bottomRight;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        CLIENT_CONFIG = configBuilder.build();
    }

    private static void setupConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("These are the valid tokens to display",
                "${day} - Current Minecraft day",
                "${mctime} - Current Minecraft time",
                "${biome} - Current biome",
                "${light} - Current light level"
        );
        builder.push("Screen sections");
        topLeft = builder.comment("Top Left").define("topLeft", Arrays.asList("Day: ${day} ${mctime}","Biome: ${biome}","Light: ${light}"),(entry) -> true);
        middleLeft = builder.comment("Middle Left").define("middleLeft", Arrays.asList(""),(entry) -> true);
        bottomLeft = builder.comment("Bottom Left").define("bottomLeft", Arrays.asList(""),(entry) -> true);
        topCenter = builder.comment("Top Center").define("topCenter", Arrays.asList(""),(entry) -> true);
        topRight = builder.comment("Top Right").define("topRight", Arrays.asList(""),(entry) -> true);
        middleRight = builder.comment("Middle Right").define("middleRight", Arrays.asList(""),(entry) -> true);
        bottomRight = builder.comment("Bottom Right").define("bottomRight", Arrays.asList(""),(entry) -> true);
        builder.pop();
    }
}
