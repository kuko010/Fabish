package net.kuko.ish.compat.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.kuko.ish.Fabish;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigUtils {
    public static ConfigOptions handler = new ConfigOptions();

    public static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir().resolve("fisch.json");
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void save(ConfigOptions handler) {
        try {
            // Save the handler instance instead of a "new Config()"
            Files.writeString(CONFIG_PATH, GSON.toJson(handler));
        } catch (IOException e) {
            Fabish.LOGGER.error("Failed to save config", e);
        }
    }

    public static void  load(ConfigOptions handler) {
        if (!Files.exists(CONFIG_PATH)) return;
        try {
            // Load the data back into our handler instance
            ConfigUtils.handler = GSON.fromJson(Files.readString(CONFIG_PATH), ConfigOptions.class);
        } catch (IOException e) {
            Fabish.LOGGER.error("Failed to load config", e);
        }
    }
}
