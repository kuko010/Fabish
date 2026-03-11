package net.kuko.fisch.data;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.kuko.fisch.Fisch;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;
import java.util.function.Consumer;

public class DataHandler {
    public static <T> void handle(
            Map<ResourceLocation, JsonElement> object,
            ResourceManager resourceManager,
            ProfilerFiller profilerFiller,
            Codec<T> codec,
            Consumer<T> onSuccess
    ) {
        object.forEach((rl, element) -> {
            codec.parse(JsonOps.INSTANCE, element)
                    .resultOrPartial(error ->
                            Fisch.LOGGER.error("Failed to parse {}: {}", rl, error))
                    .ifPresent(onSuccess);
        });
    }
}
