package io.github.racoondog.detaileddebugpie;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class DetailedDebugPieClient implements ClientModInitializer {
    public static final MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {
    }
}
