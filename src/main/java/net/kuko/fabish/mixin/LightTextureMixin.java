package net.kuko.fabish.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.kuko.fabish.compat.config.ConfigOptions;
import net.kuko.fabish.compat.config.ConfigUtils;
import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// Fabric Discord server - Julienraptor01│PrideVer ❤ (369179216031383552)
@Mixin(LightTexture.class)
public class LightTextureMixin {
    @WrapOperation(
            method = "updateLightTexture",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Double;floatValue()F",
                    ordinal = 1
            )
    )
    private float getGammaFloatValue(Double instance, Operation<Float> original) {
        return original.call((!ConfigUtils.handler.enableFullbright ? instance : Double.valueOf(Float.MAX_VALUE)));
    }
}