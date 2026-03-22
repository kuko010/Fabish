package net.kuko.ish.registry.item;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kuko.ish.IshClient;
import net.minecraft.core.BlockPos;
import net.minecraft.server.dedicated.Settings;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class MarkerItem extends Item {
    public MarkerItem(FabricItemSettings settings) {
        super(settings);
    }


    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        if (useOnContext.getLevel().isClientSide()) {
            BlockPos pos = useOnContext.getClickedPos();
            Block block = useOnContext.getLevel().getBlockState(pos).getBlock();
            if (!IshClient.contains(block)) {
                IshClient.add(block);
            } else {
                IshClient.remove(block);
            }
        }

        return super.useOn(useOnContext);
    }
}
