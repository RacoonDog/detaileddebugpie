package io.github.racoondog.detaileddebugpie.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(RenderSystem.class)
public abstract class RenderSystemMixin {
    @Inject(method = "limitDisplayFPS(I)V", at = @At("HEAD"))
    private static void injectHeadLimitDisplayFPS(CallbackInfo ci) {
        MinecraftClient.getInstance().getProfiler().push("limitDisplayFPS");
    }
    @Inject(method = "limitDisplayFPS(I)V", at = @At("TAIL"))
    private static void injectTailLimitDisplayFPS(CallbackInfo ci) {
        MinecraftClient.getInstance().getProfiler().pop();
    }
    @Inject(method = "flipFrame", at = @At("HEAD"))
    private static void injectHeadFlipFrame(CallbackInfo ci) {
        MinecraftClient.getInstance().getProfiler().push("flipFrame");
    }
    @Inject(method = "flipFrame", at = @At("TAIL"))
    private static void injectTailFlipFrame(CallbackInfo ci) {
        MinecraftClient.getInstance().getProfiler().pop();
    }
    @Inject(method = "replayQueue()V", at = @At("HEAD"), remap = false)
    private static void injectHeadReplayQueue(CallbackInfo ci) {
        MinecraftClient.getInstance().getProfiler().push("replayQueue");
    }
    @Inject(method = "replayQueue()V", at = @At("TAIL"), remap = false)
    private static void injectTailReplayQueue(CallbackInfo ci) {
        MinecraftClient.getInstance().getProfiler().pop();
    }
}
