package io.github.racoondog.detaileddebugpie.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundManager.class)
public abstract class SoundManagerMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void injectHead(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().push("soundSystemTick");
    }
    @Inject(method = "tick", at = @At("TAIL"))
    private void injectTail(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().pop();
    }
}
