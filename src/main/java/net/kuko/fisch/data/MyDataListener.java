package net.kuko.fisch.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.kuko.fisch.Fisch;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;

public class MyDataListener extends SimpleJsonResourceReloadListener implements IdentifiableResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().create();
    public MyDataListener() {
        super(GSON, Fisch.MOD_ID + "/data");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        Fisch.LOGGER.info(object.toString());
        Fisch.LOGGER.info(resourceManager.toString());
        Fisch.LOGGER.info(profilerFiller.toString());
    }

    /**
     * @return The unique identifier of this listener.
     */
    @Override
    public ResourceLocation getFabricId() {
        return new ResourceLocation(Fisch.MOD_ID, "data_listener");
    }
}
