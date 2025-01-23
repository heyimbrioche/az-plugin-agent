package fr.nathan818.azplugin.bukkit;

import fr.nathan818.azplugin.common.AZ;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Access point to Bukkit AZPlugin singletons.
 *
 * @see AZBukkitAPI
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AZBukkit {

    private static AZBukkitPlatform platform;

    /**
     * Returns the API instance for Bukkit.
     *
     * @return the API instance
     * @throws IllegalStateException if the platform is not initialized yet
     * @az.async-safe
     */
    @Contract(pure = true)
    public static @NotNull AZBukkitAPI api() {
        return platform().getAPI();
    }

    /**
     * Returns the current platform instance.
     *
     * @return the current platform instance
     * @throws IllegalStateException if the platform is not initialized yet
     * @az.low-level {@link AZBukkit#api()}
     * @az.async-safe
     */
    @Contract(pure = true)
    public static @NotNull AZBukkitPlatform platform() {
        AZBukkitPlatform ret = platform;
        if (ret == null) {
            throw new IllegalStateException("AZBukkitPlatform is not initialized yet");
        }
        return ret;
    }

    /**
     * @deprecated Used internally to initialize the platform.
     */
    @Deprecated
    public static void init(AZBukkitPlatform platform) {
        synchronized (AZ.class) {
            AZ.init(platform);
            AZBukkit.platform = platform;
        }
    }
}
