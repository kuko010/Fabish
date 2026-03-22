package net.kuko.ish.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
/*

    @Shadow
    private ClientLevel level; // mirrors the private field from the target class

      @Inject(method = "renderLevel", at = @At("TAIL"))
    private void addCustomOutline(
            PoseStack poseStack, float f, long l, boolean bl,
            Camera camera, GameRenderer gameRenderer,
            LightTexture lightTexture, Matrix4f matrix4f, CallbackInfo ci) {

        // Prepare rendering
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        //   RenderSystem.disableTexture(); // wtf man.
        RenderSystem.lineWidth(2.0F);

        // Use a simple colored line (RGBA)
        MultiBufferSource.BufferSource buffer = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());

        BlockAndTintGetter world = this.level;
        if (world == null) return;

        // Loop through blocks in a small radius around the camera
        BlockPos cameraPos = new BlockPos((int) camera.getPosition().x, (int) camera.getPosition().y, (int) camera.getPosition().z);
        int radius = 5; // blocks around player
        double cx = camera.getPosition().x;
        double cy = camera.getPosition().y;
        double cz = camera.getPosition().z;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = cameraPos.offset(x, y, z);
                    BlockState state = world.getBlockState(pos);
                    if (state.isAir()) continue;


                    // Draw outline for this block
                    drawBlockOutline(pos, poseStack, buffer, cx, cy, cz, 1f, 0f, 0f, 1f);
                }
            }
        }

        buffer.endBatch();
        //   RenderSystem.enableTexture(); // wtf
        RenderSystem.disableBlend();
    }

    // wtf... the AI Thoought this WONT get injected into that class too? weird.
    @Unique
    private void drawBlockOutline(BlockPos pos, PoseStack poseStack, MultiBufferSource buffer,
                                  double cx, double cy, double cz, float r, float g, float b, float a) {
        float x = (float)(pos.getX() - cx);
        float y = (float)(pos.getY() - cy);
        float z = (float)(pos.getZ() - cz);


        VertexConsumer vertex = buffer.getBuffer(RenderType.lines());
        Matrix4f matrix = poseStack.last().pose();


        // Draw cube edges
        // 8 corners of the block
        float[][] corners = {
                {x, y, z},
                {x + 1, y, z},
                {x + 1, y, z + 1},
                {x, y, z + 1},
                {x, y + 1, z},
                {x + 1, y + 1, z},
                {x + 1, y + 1, z + 1},
                {x, y + 1, z + 1},
        };

        // edges (pairs of corner indices)
        int[][] edges = {
                {0,1},{1,2},{2,3},{3,0}, // bottom
                {4,5},{5,6},{6,7},{7,4}, // top
                {0,4},{1,5},{2,6},{3,7}  // vertical
        };

        for (int[] edge : edges) {
            float[] c1 = corners[edge[0]];
            float[] c2 = corners[edge[1]];

            // direction vector of the line (as normal)
            float nx = c2[0] - c1[0];
            float ny = c2[1] - c1[1];
            float nz = c2[2] - c1[2];

            vertex.vertex(matrix, c1[0], c1[1], c1[2]).color(r, g, b, a).normal(poseStack.last().normal(), nx, ny, nz).endVertex();
            vertex.vertex(matrix, c2[0], c2[1], c2[2]).color(r, g, b, a).normal(poseStack.last().normal(), nx, ny, nz).endVertex();
        }
    }*/
}