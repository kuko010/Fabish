package net.kuko.fabish;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;

public class FabishClient implements ClientModInitializer {





    @Override
    public void onInitializeClient() {


        ClientTickEvents.END_CLIENT_TICK.register(client -> {

        });
        if (FabricLoader.getInstance().isModLoaded("computercraft")) {
            net.kuko.fabish.compat.computercraft.UpgradeRegistry.clientRegister();
        }
    }
}