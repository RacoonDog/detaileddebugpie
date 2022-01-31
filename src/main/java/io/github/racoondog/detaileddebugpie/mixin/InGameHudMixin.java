package io.github.racoondog.detaileddebugpie.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "tick()V", at = @At("HEAD"))
    private void injectHead(CallbackInfo ci) {
        client.getProfiler().push("guiTick");
    }
    @Inject(method = "tick()V", at = @At("TAIL"))
    private void injectTail(CallbackInfo ci) {
        client.getProfiler().pop();
    }

    @Inject(method = "updateVignetteDarkness(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"))
    private void vignette(CallbackInfo ci) {
        client.getProfiler().swap("vignetteMath");
    }
    @Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;getMainHandStack()Lnet/minecraft/item/ItemStack;", ordinal = 0))
    private void itemTooltip(CallbackInfo ci) {
        client.getProfiler().swap("itemTooltip");
    }
}
