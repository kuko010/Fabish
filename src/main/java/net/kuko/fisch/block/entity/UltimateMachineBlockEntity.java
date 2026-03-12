package net.kuko.fisch.block.entity;

import net.kuko.fisch.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class UltimateMachineBlockEntity extends BlockEntity {
    public UltimateMachineBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.ULTIMATE_MACHINE_BLOCK_ENTITY_BE, blockPos, blockState);
    }
}
