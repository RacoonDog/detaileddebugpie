package io.github.racoondog.detaileddebugpie.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Overlay;render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V"))
    private void overlayRenderRedirect(Overlay instance, MatrixStack matrixStack, int i, int j, float v) {
        MinecraftClient.getInstance().getProfiler().push("renderOverlay");
        instance.render(matrixStack, i, j, v);
        MinecraftClient.getInstance().getProfiler().pop();
    }
}
