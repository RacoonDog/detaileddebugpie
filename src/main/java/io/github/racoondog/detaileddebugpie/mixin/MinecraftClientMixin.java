package io.github.racoondog.detaileddebugpie.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Inject(method = "drawProfilerResults(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/profiler/ProfileResult;)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;disableBlend()V", ordinal = 0))
    private void renderPush(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().push("render");
    }
    @Inject(method = "drawProfilerResults(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/profiler/ProfileResult;)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableTexture()V", ordinal = 0))
    private void renderTextSwap(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().swap("renderText");
    }
    @Inject(method = "drawProfilerResults(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/profiler/ProfileResult;)V", at = @At("TAIL"))
    private void injectTail(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().pop();
    }
}
