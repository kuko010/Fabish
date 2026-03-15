package net.kuko.fabish.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.kuko.fabish.Fabish;
import net.kuko.fabish.client.render.ModRenderTypes;
import net.kuko.fabish.client.render.ModShaders;
import net.kuko.fabish.entity.AbstractRainbowArrow;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.jspecify.annotations.NonNull;

import static net.minecraft.resources.ResourceLocation.DEFAULT_NAMESPACE;

public class RainbowArrowRenderer extends ArrowRenderer<AbstractRainbowArrow> {

    public RainbowArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(AbstractRainbowArrow entity, float yaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        // Manually push GameTime to the shader every frame
        if (ModShaders.RAINBOW_ARROW != null) {
            float gameTime = (entity.level().getGameTime() + partialTick) / 1.0f;
            ModShaders.RAINBOW_ARROW.safeGetUniform("GameTime").set(gameTime);
        }

        super.render(entity, yaw, partialTick, poseStack,
                renderType -> bufferSource.getBuffer(ModRenderTypes.rainbowArrow(getTextureLocation(entity))),
                packedLight);
    }

    @Override
    public @NonNull ResourceLocation getTextureLocation(AbstractRainbowArrow entity) {
        return new ResourceLocation(Fabish.MOD_ID, "textures/entity/projectiles/gray_arrow.png");
    }
}