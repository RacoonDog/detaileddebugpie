package io.github.racoondog.detaileddebugpie.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MusicTracker.class)
public abstract class MusicTrackerMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "tick()V", at = @At("HEAD"))
    private void injectHead(CallbackInfo ci) {
        client.getProfiler().push("trackMusic");
    }
    @Inject(method = "tick()V", at = @At("TAIL"))
    private void injectTail(CallbackInfo ci) {
        client.getProfiler().pop();
    }
}
