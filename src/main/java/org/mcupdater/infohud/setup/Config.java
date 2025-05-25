package org.mcupdater.infohud.setup;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {
    public static ModConfigSpec COMMON_CONFIG;
    public static ModConfigSpec CLIENT_CONFIG;
    public static ModConfigSpec.BooleanValue REQUIRE_ITEMS;
    public static ModConfigSpec.ConfigValue<List<? extends String>> topLeftEntries;
    public static ModConfigSpec.IntValue topLeftXOffset;
    public static ModConfigSpec.IntValue topLeftYOffset;
    public static ModConfigSpec.ConfigValue<List<? extends String>> middleLeftEntries;
    public static ModConfigSpec.IntValue middleLeftXOffset;
    public static ModConfigSpec.ConfigValue<List<? extends String>> bottomLeftEntries;
    public static ModConfigSpec.IntValue bottomLeftXOffset;
    public static ModConfigSpec.IntValue bottomLeftYOffset;
    public static ModConfigSpec.ConfigValue<List<? extends String>> topCenterEntries;
    public static ModConfigSpec.IntValue topCenterYOffset;
    public static ModConfigSpec.ConfigValue<List<? extends String>> bottomCenterEntries;
    public static ModConfigSpec.IntValue bottomCenterYOffset;
    public static ModConfigSpec.ConfigValue<List<? extends String>> topRightEntries;
    public static ModConfigSpec.IntValue topRightXOffset;
    public static ModConfigSpec.IntValue topRightYOffset;
    public static ModConfigSpec.ConfigValue<List<? extends String>> middleRightEntries;
    public static ModConfigSpec.IntValue middleRightXOffset;
    public static ModConfigSpec.ConfigValue<List<? extends String>> bottomRightEntries;
    public static ModConfigSpec.IntValue bottomRightXOffset;
    public static ModConfigSpec.IntValue bottomRightYOffset;

    static {
        ModConfigSpec.Builder commonBuilder = new ModConfigSpec.Builder();
        REQUIRE_ITEMS = commonBuilder.define("require_items", true);
        COMMON_CONFIG = commonBuilder.build();

        ModConfigSpec.Builder clientBuilder = new ModConfigSpec.Builder();
        clientBuilder.push("top_left");
        topLeftEntries = clientBuilder.defineListAllowEmpty("entries", Arrays.asList("Day: ${yellow}${day} ${mctime}","Biome: ${yellow}${biome}","Light: ${gt:${light}:4:${yellow}:${red}}${light}"),() -> "", (entry) -> true);
        topLeftXOffset = clientBuilder.defineInRange("xOffset", 2, 0, Integer.MAX_VALUE);
        topLeftYOffset = clientBuilder.defineInRange("yOffset", 2, 0, Integer.MAX_VALUE);
        clientBuilder.pop();

        clientBuilder.push("middle_left");
        middleLeftEntries = clientBuilder.defineListAllowEmpty("entries", new ArrayList<>(),() -> "",(entry) -> true);
        middleLeftXOffset = clientBuilder.defineInRange("xOffset", 2, 0, Integer.MAX_VALUE);
        clientBuilder.pop();

        clientBuilder.push("bottom_left");
        bottomLeftEntries = clientBuilder.defineListAllowEmpty("entries", Arrays.asList("Helmet: ${armor:head:damage_formatted} ${armor:head:name}", "Chestplate: ${armor:chest:damage_formatted} ${armor:chest:name}", "Leggings: ${armor:legs:damage_formatted} ${armor:legs:name}", "Boots: ${armor:boots:damage_formatted} ${armor:boots:name}"),() -> "",(entry) -> true);
        bottomLeftXOffset = clientBuilder.defineInRange("xOffset", 2, 0, Integer.MAX_VALUE);
        bottomLeftYOffset = clientBuilder.defineInRange("yOffset", 2, 0, Integer.MAX_VALUE);
        clientBuilder.pop();

        clientBuilder.push("top_center");
        topCenterEntries = clientBuilder.defineListAllowEmpty("entries", new ArrayList<>(), () -> "", (entry) -> true);
        topCenterYOffset = clientBuilder.defineInRange("yOffset", 2, 0, Integer.MAX_VALUE);
        clientBuilder.pop();

        clientBuilder.push("bottom_center");
        bottomCenterEntries = clientBuilder.defineListAllowEmpty("entries", Arrays.asList("Heading: ${heading}","${fullposition}"), () -> "", (entry) -> true);
        bottomCenterYOffset = clientBuilder.defineInRange("yOffset", 50, 0, Integer.MAX_VALUE);
        clientBuilder.pop();

        clientBuilder.push("top_right");
        topRightEntries = clientBuilder.defineListAllowEmpty("entries", new ArrayList<>(),() -> "",(entry) -> true);
        topRightXOffset = clientBuilder.defineInRange("xOffset", 2, 0, Integer.MAX_VALUE);
        topRightYOffset = clientBuilder.defineInRange("yOffset", 2, 0, Integer.MAX_VALUE);
        clientBuilder.pop();

        clientBuilder.push("middle_right");
        middleRightEntries = clientBuilder.defineListAllowEmpty("entries", new ArrayList<>(),() -> "",(entry) -> true);
        middleRightXOffset = clientBuilder.defineInRange("xOffset", 2, 0, Integer.MAX_VALUE);
        clientBuilder.pop();

        clientBuilder.push("bottom_right");
        bottomRightEntries = clientBuilder.defineListAllowEmpty("entries", Arrays.asList("Main: ${mainhand_name} ${mainhand_damage_formatted}", "Offhand: ${offhand_name} ${offhand_damage_formatted}"),() -> "",(entry) -> true);
        bottomRightXOffset = clientBuilder.defineInRange("xOffset", 2, 0, Integer.MAX_VALUE);
        bottomRightYOffset = clientBuilder.defineInRange("yOffset", 2, 0, Integer.MAX_VALUE);
        clientBuilder.pop();
        CLIENT_CONFIG = clientBuilder.build();
    }
}
