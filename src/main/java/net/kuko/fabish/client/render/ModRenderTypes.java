package net.kuko.fabish.client.render;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class ModRenderTypes extends RenderType {

    // Required by RenderType — never actually called, just satisfies the constructor
    private ModRenderTypes(String name, VertexFormat format, VertexFormat.Mode mode,
                           int bufferSize, boolean affectsCrumbling, boolean sortOnUpload,
                           Runnable setup, Runnable clear) {
        super(name, format, mode, bufferSize, affectsCrumbling, sortOnUpload, setup, clear);
    }

    // Your custom RenderType — call this to get the instance
    public static RenderType rainbowArrow(ResourceLocation texture) {
        return create(
                "rainbow_arrow",                        // unique name
                DefaultVertexFormat.NEW_ENTITY,         // vertex format (pos + tex + normal + color)
                VertexFormat.Mode.QUADS,                // draw mode
                256,                                    // buffer size hint
                false,                                  // affectsCrumbling
                false,                                  // sortOnUpload
                CompositeState.builder()
                        .setShaderState(
                                new ShaderStateShard(
                                        () -> ModShaders.RAINBOW_ARROW  // your ShaderInstance (see step 2)
                                )
                        )
                        .setTextureState(new TextureStateShard(texture, false, false))
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)  // needed for alpha
                        .setLightmapState(LIGHTMAP)
                        .setOverlayState(OVERLAY)
                        .createCompositeState(false)
        );
    }
}
