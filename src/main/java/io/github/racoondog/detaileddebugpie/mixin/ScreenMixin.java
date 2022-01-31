package io.github.racoondog.detaileddebugpie.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("ConstantConditions")
@Environment(EnvType.CLIENT)
@Mixin(Screen.class)
public abstract class ScreenMixin {
    @Shadow @Nullable protected MinecraftClient client;

    @Inject(method = "updateNarrator()V", at = @At("HEAD"))
    private void injectHeadNarrator(CallbackInfo ci) {
        client.getProfiler().push("updateNarrator");
    }
    @Inject(method = "updateNarrator()V", at = @At("TAIL"))
    private void injectTailNarrator(CallbackInfo ci) {
        client.getProfiler().pop();
    }

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V", at = @At("HEAD"))
    private void injectHeadScreen(CallbackInfo ci) {
        client.getProfiler().push("renderScreen");
    }
    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V", at = @At("TAIL"))
    private void injectTailScreen(CallbackInfo ci) {
        client.getProfiler().pop();
    }
}
