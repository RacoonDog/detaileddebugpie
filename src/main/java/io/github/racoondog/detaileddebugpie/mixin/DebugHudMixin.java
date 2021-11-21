package io.github.racoondog.detaileddebugpie.mixin;

import io.github.racoondog.detaileddebugpie.impl.OptimizedRightText;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(DebugHud.class)
public abstract class DebugHudMixin {
    //More debug pie entries
    @Inject(method = "renderLeftText(Lnet/minecraft/client/util/math/MatrixStack;)V", at = @At("HEAD"))
    private void renderLeft(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().push("leftText");
    }
    @Inject(method = "renderRightText(Lnet/minecraft/client/util/math/MatrixStack;)V", at = @At("HEAD"))
    private void renderRight(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().swap("rightText");
    }
    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledWidth()I", ordinal = 0), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledWidth()I", ordinal = 0)))
    private void tpsChart(MatrixStack matrices, CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().swap("metrics");
    }
    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;)V", at = @At("TAIL"))
    private void tail(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().pop();
    }

    //Optimizations
    /**
     *
     * @author Crosby
     * @reason Replaces the vanilla-style array declaration with my own implementation that utilizes caching.
     */
    @Redirect(method = "getRightText", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Lists;newArrayList([Ljava/lang/Object;)Ljava/util/ArrayList;", ordinal = 0, remap = false))
    private <E> ArrayList<String> optimizedRightText(E[] elements) {
        return OptimizedRightText.generate();
    }
}
