package net.kuko.ish.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kuko.ish.Ish;
import net.kuko.ish.registry.item.MarkerItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItems {
    /*
    public static final Item SUSPICIOUS_SUBSTANCE = item(
		// Ignore the food component for now, we'll cover it later in the food section.
		new Item(new FabricItemSettings().food(SUSPICIOUS_FOOD_COMPONENT)),
		"suspicious_substance"
);
     */

    public static final Item MARKER = item("marker",
            new MarkerItem(new FabricItemSettings()));

    private static Item item(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Ish.MOD_ID, name), item);
    }

    public static void register() {

    }
}