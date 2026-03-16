package net.kuko.ish.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.kuko.ish.Fabish;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;
import java.util.function.Consumer;

public class DataRegistry extends SimpleJsonResourceReloadListener implements IdentifiableResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().create();

    public DataRegistry() {
        super(GSON, Fabish.MOD_ID + "/data");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        // use handle() here when you have actual codecs to parse
    }

    @Override
    public ResourceLocation getFabricId() {
        return new ResourceLocation(Fabish.MOD_ID, "data_listener");
    }

    public static <T> void handle(Map<ResourceLocation, JsonElement> object, Codec<T> codec, Consumer<T> onSuccess) {
        object.forEach((rl, element) ->
                codec.parse(JsonOps.INSTANCE, element)
                        .resultOrPartial(error -> Fabish.LOGGER.error("Failed to parse {}: {}", rl, error))
                        .ifPresent(onSuccess)
        );
    }

    public static void register() {
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new DataRegistry());
    }
}