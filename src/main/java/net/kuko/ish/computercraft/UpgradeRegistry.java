package net.kuko.ish.computercraft;


import com.mojang.math.Transformation;
import dan200.computercraft.api.client.FabricComputerCraftAPIClient;
import dan200.computercraft.api.client.TransformedModel;
import dan200.computercraft.api.turtle.ITurtleAccess;
import dan200.computercraft.api.turtle.ITurtleUpgrade;
import dan200.computercraft.api.turtle.TurtleSide;
import dan200.computercraft.api.turtle.TurtleUpgradeSerialiser;
import net.kuko.ish.Fabish;
import net.kuko.ish.computercraft.turtle.ExampleTurtleUpgrade;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.jspecify.annotations.Nullable;

public class UpgradeRegistry {
    public static final TurtleUpgradeSerialiser<ExampleTurtleUpgrade> EXAMPLE_TURTLE_UPGRADE = TurtleUpgradeSerialiser.simpleWithCustomItem(
            ExampleTurtleUpgrade::new
    );


    public static void register() {
        @SuppressWarnings("unchecked")
        var turtleUpgradeSerializers = (Registry<TurtleUpgradeSerialiser<?>>) BuiltInRegistries.REGISTRY.get(TurtleUpgradeSerialiser.registryId().location());
        Registry.register(turtleUpgradeSerializers, new ResourceLocation(Fabish.MOD_ID, "example_turtle_upgrade"), UpgradeRegistry.EXAMPLE_TURTLE_UPGRADE);

    }

    public static void clientRegister() {
        FabricComputerCraftAPIClient.registerTurtleUpgradeModeller(UpgradeRegistry.EXAMPLE_TURTLE_UPGRADE, UpgradeRegistry::model);
    }


    private static TransformedModel model(ITurtleUpgrade upgrade, @Nullable ITurtleAccess turtle, TurtleSide side) {

        // 1. Get the baked model for the upgrade's item
        ItemStack stack = upgrade.getCraftingItem();
        BakedModel model = Minecraft.getInstance()
                .getItemRenderer()
                .getModel(stack, null, null, 0);

        // 2. Translation: move model to the correct side (X axis)
        //    Typical offset: left side = -0.4f, right side = +0.4f
        float xOffset = 0f;
        xOffset = side == TurtleSide.LEFT ? -0.4f : 0.4f;
        Vector3f translation = new Vector3f(xOffset, 0.0f,  1f); // also nudge forward a bit (Z)

        // 3. Rotation: make the item lie flat against the turtle side and face outward
        //    Most upgrades need: rotateX 90° (lay flat) then rotateY 180° (face outward)
        Quaternionf rotation = new Quaternionf()
                .rotateY((float) Math.toRadians(90));   // or -90 for the opposite direction


        // 4. Build the transformation (rightRotation = null is fine)
        Transformation transform = new Transformation(
                translation,
                rotation,
                null,   // scale (null = 1)
                null    // right rotation (unused)
        );

        // 5. Return the transformed model
        return new TransformedModel(model, transform);
    }
}
