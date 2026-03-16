package net.kuko.ish;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;

public class FabishClient implements ClientModInitializer {





    @Override
    public void onInitializeClient() {


        ClientTickEvents.END_CLIENT_TICK.register(client -> {

        });
        if (FabricLoader.getInstance().isModLoaded("computercraft")) {
            net.kuko.ish.computercraft.UpgradeRegistry.clientRegister();
        }
    }
}