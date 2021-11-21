package io.github.racoondog.detaileddebugpie.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void injectHead(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().push("guiTick");
    }
    @Inject(method = "tick", at = @At("TAIL"))
    private void injectTail(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().pop();
    }

    @Inject(method = "updateVignetteDarkness", at = @At("HEAD"), cancellable = true)
    private void vignette(CallbackInfo info) {
        if (!MinecraftClient.isFancyGraphicsOrBetter()) info.cancel();
        MinecraftClient.getInstance().getProfiler().swap("vignetteMath");
    }
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;getMainHandStack()Lnet/minecraft/item/ItemStack;", ordinal = 0))
    private void itemTooltip(CallbackInfo info) {
        MinecraftClient.getInstance().getProfiler().swap("itemTooltip");
    }
}
