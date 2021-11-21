package io.github.racoondog.detaileddebugpie.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public abstract class ScreenMixin {
    @Inject(method = "updateNarrator", at = @At("HEAD"))
    private void injectHeadNarrator(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().push("updateNarrator");
    }
    @Inject(method = "updateNarrator", at = @At("TAIL"))
    private void injectTailNarrator(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().pop();
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void injectHeadScreen(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().push("renderScreen");
    }
    @Inject(method = "render", at = @At("TAIL"))
    private void injectTailScreen(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().pop();
    }
}
