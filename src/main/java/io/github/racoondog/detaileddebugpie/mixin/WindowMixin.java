package io.github.racoondog.detaileddebugpie.mixin;

import io.github.racoondog.detaileddebugpie.DetailedDebugPieClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(Window.class)
public abstract class WindowMixin {
    @Inject(method = "swapBuffers()V", at = @At("HEAD"))
    private void injectHead(CallbackInfo ci) {
        DetailedDebugPieClient.client.getProfiler().push("swapBuffers");
    }
    @Inject(method = "swapBuffers()V", at = @At("TAIL"))
    private void injectTail(CallbackInfo ci) {
        DetailedDebugPieClient.client.getProfiler().pop();
    }
}
