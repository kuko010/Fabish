package net.kuko.fisch.registry;


import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.kuko.fisch.Fisch;
//import net.kuko.fisch.block.BlockEntityExample;
import net.kuko.fisch.block.UltimateMachine;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ModBlocks {
    /*
    public static final Block CONDENSED_DIRT = register(
		new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.GRASS)),
		"condensed_dirt",
		true
);
     */
/*
    public static final Block BLOCK_ENTITY_EXAMPLE = block("block_entity_example",
            new BlockEntityExample(FabricBlockSettings.copyOf(Blocks.STONE)));
*/

    public static final Block ULTIMATE_MACHINE = block("ultimate_machine",
            new UltimateMachine(FabricBlockSettings.copyOf(Blocks.ANVIL)));

    private static Block block(String name, Block block) { return block(name, block, true); }
    private static Block block(String name, Block block , boolean shouldRegisterItem) {
        ResourceLocation rl = new ResourceLocation(Fisch.MOD_ID, name);
        if (shouldRegisterItem) {
            BlockItem bi = new BlockItem(block, new Item.Properties());
            Registry.register(BuiltInRegistries.ITEM, rl, bi);
        }
        return Registry.register(BuiltInRegistries.BLOCK, rl, block);
    }

    public static void register() {}
}