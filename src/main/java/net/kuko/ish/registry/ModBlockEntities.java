package net.kuko.ish.registry;

//import net.kuko.fisch.block.entity.BlockEntityExampleBlockEntity;


import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kuko.ish.Fabish;
import net.kuko.ish.registry.block.entity.UltimateMachineBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities  {
  /*
    public static final BlockEntityType<BlockEntityExampleBlockEntity> EXAMPLE_BLOCK_ENTITY_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(Fabish.MOD_ID, "example_block_entity_be"),
                    FabricBlockEntityTypeBuilder.create(BlockEntityExampleBlockEntity::new,
                            ModBlocks.BLOCK_ENTITY_EXAMPLE).build());
*/

    public static final BlockEntityType<UltimateMachineBlockEntity> ULTIMATE_MACHINE_BLOCK_ENTITY_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                    new ResourceLocation(Fabish.MOD_ID, "ultimate_machine_be"),
                    FabricBlockEntityTypeBuilder.create(UltimateMachineBlockEntity::new,
                            ModBlocks.ULTIMATE_MACHINE).build());

    public static void register() {

    }
}
