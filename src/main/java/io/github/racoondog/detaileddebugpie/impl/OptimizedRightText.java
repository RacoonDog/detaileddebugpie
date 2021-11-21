package io.github.racoondog.detaileddebugpie.impl;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.GlDebugInfo;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;

@SuppressWarnings("StringBufferReplaceableByString")
public class OptimizedRightText {
    private static final MinecraftClient CLIENT = MinecraftClient.getInstance();
    private static final ArrayList<String> list = Lists.newArrayList(
            new StringBuilder("Java: ").append(System.getProperty("java.version")).append(' ').append(CLIENT.is64Bit() ? "64" : "32").append("bit").toString(), //JAVA
            "", //MEMORY
            new StringBuilder("Allocated: ").append(Runtime.getRuntime().totalMemory() * 100L / Runtime.getRuntime().maxMemory()).append("% ").append(toMiB(Runtime.getRuntime().totalMemory())).append("MB").toString(), //ALLOCATED
            "", //VOID
            new StringBuilder("CPU: ").append(GlDebugInfo.getCpuInfo()).toString(), //CPU
            "", //VOID
            "", //DISPLAY
            GlDebugInfo.getRenderer(), //RENDERER
            GlDebugInfo.getVersion() //VERSION
    );

    private static final String MEMORY = new StringBuilder("Mem: % 2d%% %03d/").append(toMiB(Runtime.getRuntime().maxMemory())).append("MB").toString();
    private static final String DISPLAY = new StringBuilder("Display: %dx%d (").append(GlDebugInfo.getVendor()).append(')').toString();

    private static String parseMemory() {
        final long mem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        return String.format(MEMORY, mem * 100L / Runtime.getRuntime().maxMemory(), toMiB(mem));
    }

    private static String parseDisplay() {
        return String.format(DISPLAY, CLIENT.getWindow().getFramebufferWidth(), CLIENT.getWindow().getFramebufferHeight());
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<String> generate() {
        final ArrayList<String> out = (ArrayList<String>) list.clone(); //Clone instead of overwrite to prevent memory leak
        out.set(1, parseMemory());
        out.set(6, parseDisplay());
        return out;
    }

    private static long toMiB(long bytes) {
        return bytes / 1024L / 1024L;
    }
}
