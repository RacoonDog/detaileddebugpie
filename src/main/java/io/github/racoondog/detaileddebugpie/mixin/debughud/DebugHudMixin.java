package io.github.racoondog.detaileddebugpie.mixin.debughud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(DebugHud.class)
public abstract class DebugHudMixin {
    //More debug pie entries
    @Inject(method = "renderLeftText(Lnet/minecraft/client/util/math/MatrixStack;)V", at = @At("HEAD"))
    private void renderLeft(CallbackInfo ci) {
        MinecraftClient.getInstance().getProfiler().push("leftText");
    }
    @Inject(method = "renderRightText(Lnet/minecraft/client/util/math/MatrixStack;)V", at = @At("HEAD"))
    private void renderRight(CallbackInfo ci) {
        MinecraftClient.getInstance().getProfiler().swap("rightText");
    }
    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledWidth()I", ordinal = 0), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledWidth()I", ordinal = 0)))
    private void tpsChart(CallbackInfo ci) {
        MinecraftClient.getInstance().getProfiler().swap("metrics");
    }
    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;)V", at = @At("TAIL"))
    private void tail(CallbackInfo ci) {
        MinecraftClient.getInstance().getProfiler().pop();
    }
}
