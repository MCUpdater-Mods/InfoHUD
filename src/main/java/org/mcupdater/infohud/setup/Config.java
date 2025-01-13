package org.mcupdater.infohud.setup;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {
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
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        REQUIRE_ITEMS = builder.define("require_items", true);
        builder.push("top_left");
        topLeftEntries = builder.defineListAllowEmpty("entries", Arrays.asList("Day: ${yellow}${day} ${mctime}","Biome: ${yellow}${biome}","Light: ${gt:${light}:4:${yellow}:${red}}${light}"),() -> "", (entry) -> true);
        topLeftXOffset = builder.defineInRange("xOffset", 2, 0, Integer.MAX_VALUE);
        topLeftYOffset = builder.defineInRange("yOffset", 2, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("middle_left");
        middleLeftEntries = builder.defineListAllowEmpty("entries", new ArrayList<>(),() -> "",(entry) -> true);
        middleLeftXOffset = builder.defineInRange("xOffset", 2, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("bottom_left");
        bottomLeftEntries = builder.defineListAllowEmpty("entries", Arrays.asList("Helmet: ${helmetdamage_formatted} ${helmetname}", "Chestplate: ${chestplatedamage_formatted} ${chestplatename}", "Leggings: ${leggingsdamage_formatted} ${leggingsname}", "Boots: ${bootsdamage_formatted} ${bootsname}"),() -> "",(entry) -> true);
        bottomLeftXOffset = builder.defineInRange("xOffset", 2, 0, Integer.MAX_VALUE);
        bottomLeftYOffset = builder.defineInRange("yOffset", 2, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("top_center");
        topCenterEntries = builder.defineListAllowEmpty("entries", new ArrayList<>(), () -> "", (entry) -> true);
        topCenterYOffset = builder.defineInRange("yOffset", 2, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("bottom_center");
        bottomCenterEntries = builder.defineListAllowEmpty("entries", Arrays.asList("Heading: ${heading}","${fullposition}"), () -> "", (entry) -> true);
        bottomCenterYOffset = builder.defineInRange("yOffset", 50, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("top_right");
        topRightEntries = builder.defineListAllowEmpty("entries", new ArrayList<>(),() -> "",(entry) -> true);
        topRightXOffset = builder.defineInRange("xOffset", 2, 0, Integer.MAX_VALUE);
        topRightYOffset = builder.defineInRange("yOffset", 2, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("middle_right");
        middleRightEntries = builder.defineListAllowEmpty("entries", new ArrayList<>(),() -> "",(entry) -> true);
        middleRightXOffset = builder.defineInRange("xOffset", 2, 0, Integer.MAX_VALUE);
        builder.pop();

        builder.push("bottom_right");
        bottomRightEntries = builder.defineListAllowEmpty("entries", Arrays.asList("Main: ${mainhandname} ${mainhanddamage_formatted}", "Offhand: ${offhandname} ${offhanddamage_formatted}"),() -> "",(entry) -> true);
        bottomRightXOffset = builder.defineInRange("xOffset", 2, 0, Integer.MAX_VALUE);
        bottomRightYOffset = builder.defineInRange("yOffset", 2, 0, Integer.MAX_VALUE);
        builder.pop();
        CLIENT_CONFIG = builder.build();
    }
}
