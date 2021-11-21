package io.github.racoondog.detaileddebugpie.compat;

import me.jellysquid.mods.sodium.client.SodiumClientMod;
import me.jellysquid.mods.sodium.client.render.SodiumWorldRenderer;
import me.jellysquid.mods.sodium.client.util.NativeBuffer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Formatting;

import java.lang.management.ManagementFactory;
import java.util.Collection;
import java.util.Collections;

@SuppressWarnings("StringBufferReplaceableByString")
public class SodiumBridge {
    public static String getFormattedVersionText() {
        if (FabricLoader.getInstance().isModLoaded("sodium")) {
            String version = SodiumClientMod.getVersion();
            Formatting color;
            if (version.endsWith("-dirty")) {
                color = Formatting.RED;
            } else if (version.contains("+rev.")) {
                color = Formatting.LIGHT_PURPLE;
            } else {
                color = Formatting.GREEN;
            }
            return new StringBuilder(String.valueOf(color)).append(version).toString();
        } else {
            return "";
        }
    }
    public static Collection<String> getMemoryDebugStrings() {
        if (FabricLoader.getInstance().isModLoaded("sodium")) {
            SodiumWorldRenderer renderer = SodiumWorldRenderer.instanceNullable();
            if (renderer != null) {
                return renderer.getMemoryDebugStrings();
            } else {
                return Collections.singleton("");
            }
        } else {
            return Collections.singleton("");
        }
    }
    public static long getNativeMemoryString() {
        if (FabricLoader.getInstance().isModLoaded("sodium")) {
            return toMiB(ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed() + NativeBuffer.getTotalAllocated());
        } else {
            return 0;
        }
    }
    public static boolean getVignetteSetting() {
        if (FabricLoader.getInstance().isModLoaded("sodium")) {
            return SodiumClientMod.options().quality.enableVignette;
        } else {
            return false;
        }
    }

    private static long toMiB(long bytes) {
        return bytes / 1024L / 1024L;
    }
}
