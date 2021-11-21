package io.github.racoondog.detaileddebugpie.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(SoundManager.class)
public abstract class SoundManagerMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void injectHead(CallbackInfo ci) {
        MinecraftClient.getInstance().getProfiler().push("soundSystemTick");
    }
    @Inject(method = "tick", at = @At("TAIL"))
    private void injectTail(CallbackInfo ci) {
        MinecraftClient.getInstance().getProfiler().pop();
    }
}
