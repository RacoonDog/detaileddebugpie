package io.github.racoondog.detaileddebugpie.util;

import io.github.racoondog.detaileddebugpie.compat.SodiumBridge;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;

public class Util {
    public static boolean getVignette() {
        if (FabricLoader.getInstance().isModLoaded("sodium")) {
            return SodiumBridge.getVignetteSetting();
        } else {
            return MinecraftClient.isFancyGraphicsOrBetter();
        }
    }
}
