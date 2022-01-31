package io.github.racoondog.detaileddebugpie.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow private static MinecraftClient instance;

    @Inject(method = "drawProfilerResults(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/profiler/ProfileResult;)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;disableBlend()V", remap = false, ordinal = 0))
    private void renderPush(CallbackInfo ci) {
        instance.getProfiler().push("render");
    }
    @Inject(method = "drawProfilerResults(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/profiler/ProfileResult;)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableTexture()V", remap = false, ordinal = 0))
    private void renderTextSwap(CallbackInfo ci) {
        instance.getProfiler().swap("renderText");
    }
    @Inject(method = "drawProfilerResults(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/profiler/ProfileResult;)V", at = @At("TAIL"))
    private void injectTail(CallbackInfo ci) {
        instance.getProfiler().pop();
    }
}
