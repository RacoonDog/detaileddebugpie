package io.github.racoondog.detaileddebugpie.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public abstract class WindowMixin {
    @Inject(method = "swapBuffers", at = @At("HEAD"))
    private void injectHead(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().push("swapBuffers");
    }
    @Inject(method = "swapBuffers", at = @At("TAIL"))
    private void injectTail(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().pop();
    }
}
