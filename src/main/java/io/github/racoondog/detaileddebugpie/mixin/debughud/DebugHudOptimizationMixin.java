package io.github.racoondog.detaileddebugpie.mixin.debughud;

import io.github.racoondog.detaileddebugpie.impl.OptimizedRightText;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
@Mixin(value = DebugHud.class, priority = 1100)
public abstract class DebugHudOptimizationMixin {
    /**
     *
     * @author Crosby
     * @reason Replaces the vanilla-style array declaration with my own implementation that utilizes caching.
     */
    @Redirect(method = "getRightText", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Lists;newArrayList([Ljava/lang/Object;)Ljava/util/ArrayList;", ordinal = 0, remap = false))
    private <E> ArrayList<String> optimizedRightText(E[] elements) {
        if (FabricLoader.getInstance().isModLoaded("sodium")) {
            return OptimizedRightText.generateSodium();
        } else {
            return OptimizedRightText.generate();
        }
    }
}
