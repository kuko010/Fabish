package net.kuko.ish;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.kuko.ish.compat.config.ConfigUtils;
import net.kuko.ish.data.DataRegistry;
import net.kuko.ish.registry.*;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ish implements ModInitializer {
	public static final String MOD_ID = "ish";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static ResourceLocation id(String id) {
		return new ResourceLocation(MOD_ID, id);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModBlocks.register();
		ModBlockEntities.register();
		ModItems.register();
		ModEnergy.register();
		DataRegistry.register();

		if (FabricLoader.getInstance().isModLoaded("computercraft")) {
			net.kuko.ish.computercraft.UpgradeRegistry.register();
		}
		ConfigUtils.load(ConfigUtils.handler);
	}
}