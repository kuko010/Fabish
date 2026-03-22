package net.kuko.ish;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IshClient implements ClientModInitializer {

    private static List<BlockPos> renderBlockPos = Collections.synchronizedList(new ArrayList<>());

    public static void add(BlockPos pos) {
        renderBlockPos.add(pos);
    }

    public static void remove(BlockPos pos) {
        if (renderBlockPos.contains(pos)) {
            renderBlockPos.remove(pos);
        }
    }

    public static void override(List<BlockPos> blockPoss) {
        renderBlockPos = Collections.synchronizedList(new ArrayList<>(blockPoss));
    }


    public static List<BlockPos> renderBlockPos() {
        return renderBlockPos;
    }

    public static boolean contains(BlockPos pos) {
        return renderBlockPos.contains(pos);
    }

    private static void render(WorldRenderContext context) {

        Camera camera = context.camera();
        PoseStack poseStack = context.matrixStack();
        if (poseStack == null) return;

        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) return;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.lineWidth(2.0F);

        MultiBufferSource.BufferSource buffer = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());

        double cx = camera.getPosition().x;
        double cy = camera.getPosition().y;
        double cz = camera.getPosition().z;

        for (BlockPos pos : renderBlockPos) {
            drawBlockOutline(pos, poseStack, buffer, cx, cy, cz, 1f, 0f, 0f, 1f);
        }

        buffer.endBatch();
        RenderSystem.disableBlend();
    }




    private static void drawBlockOutline(BlockPos pos, PoseStack poseStack, MultiBufferSource buffer,
                                         double cx, double cy, double cz, float r, float g, float b, float a) {
        float x = (float)(pos.getX() - cx);
        float y = (float)(pos.getY() - cy);
        float z = (float)(pos.getZ() - cz);

        VertexConsumer vertex = buffer.getBuffer(RenderType.lines());
        Matrix4f matrix = poseStack.last().pose();

        float[][] corners = {
                {x,y,z},{x+1,y,z},{x+1,y,z+1},{x,y,z+1},
                {x,y+1,z},{x+1,y+1,z},{x+1,y+1,z+1},{x,y+1,z+1}
        };
        int[][] edges = {
                {0,1},{1,2},{2,3},{3,0},
                {4,5},{5,6},{6,7},{7,4},
                {0,4},{1,5},{2,6},{3,7}
        };

        for (int[] edge : edges) {
            float[] c1 = corners[edge[0]];
            float[] c2 = corners[edge[1]];
            float nx = c2[0]-c1[0], ny = c2[1]-c1[1], nz = c2[2]-c1[2];
            vertex.vertex(matrix, c1[0], c1[1], c1[2]).color(r,g,b,a).normal(poseStack.last().normal(), nx, ny, nz).endVertex();
            vertex.vertex(matrix, c2[0], c2[1], c2[2]).color(r,g,b,a).normal(poseStack.last().normal(), nx, ny, nz).endVertex();
        }
    }





    @Override
    public void onInitializeClient() {
        WorldRenderEvents.LAST.register(IshClient::render);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {});

        if (FabricLoader.getInstance().isModLoaded("computercraft")) {
            net.kuko.ish.computercraft.UpgradeRegistry.clientRegister();
        }
    }

}