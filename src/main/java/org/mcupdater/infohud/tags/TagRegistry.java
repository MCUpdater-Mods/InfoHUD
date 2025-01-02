package org.mcupdater.infohud.tags;

import org.mcupdater.infohud.InfoHUD;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TagRegistry {
	private static Map<String,TagProvider> TAG_MAP = new HashMap<>();

	public static Set<Map.Entry<String, TagProvider>> getEntries() {
		return TAG_MAP.entrySet();
	}

	public static void register(String key, TagProvider function) {
		if (!TAG_MAP.containsKey(key)) {
			TAG_MAP.put(key, function);
		} else {
			InfoHUD.LOGGER.error("Rejected duplicate tag: {}", key);
		}
	}

	public static void init() {
		register("day", Tags::day);
		register("mctime", Tags::mctime);
		register("biome", Tags::biome);
		register("light", Tags::light);
		register("skylight", Tags::skylight);
		register("blocklight", Tags::blocklight);
		register("debug", Tags::debug);
	}
}
