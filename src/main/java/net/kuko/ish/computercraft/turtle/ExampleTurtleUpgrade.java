package net.kuko.ish.computercraft.turtle;

import dan200.computercraft.api.turtle.AbstractTurtleUpgrade;
import dan200.computercraft.api.turtle.TurtleUpgradeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;


public class ExampleTurtleUpgrade extends AbstractTurtleUpgrade {
    public ExampleTurtleUpgrade(ResourceLocation id, ItemStack stack) {
        super(id, TurtleUpgradeType.PERIPHERAL, stack);
    }
}

// why is cc so hard