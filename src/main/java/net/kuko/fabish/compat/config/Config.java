package net.kuko.fabish.compat.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.kuko.fabish.compat.config.ConfigUtils.handler;

public class Config {
    // Create one instance to hold your data
    public static Screen genScreen(Screen screen) {
        return YetAnotherConfigLib.createBuilder()
                .save(() -> ConfigUtils.save(handler))
                .title(Component.literal("Fabish Config"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("General"))
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Options"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Enable Fullbright"))
                                        .binding(
                                                false,
                                                () -> handler.enableFullbright, // Use handler instance
                                                newVal -> handler.enableFullbright = newVal
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())


                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Enable LibTooltips"))
                                        .binding(
                                                false,
                                                () -> handler.enableLibTooltips, // Use handler instance
                                                newVal -> handler.enableLibTooltips = newVal
                                        )
                                        .flag(OptionFlag.GAME_RESTART)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .build().generateScreen(screen);
    }
}