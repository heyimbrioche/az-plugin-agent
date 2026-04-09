package fr.dialogue.azplugin.bukkit;

import fr.dialogue.azplugin.bukkit.entity.AZEntity;
import fr.dialogue.azplugin.bukkit.entity.AZPlayer;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Utility class to access {@link AZBukkitAPI} objects.
 * <p>
 * Intended to be statically imported:
 * <pre>{@code  import static fr.dialogue.azplugin.bukkit.AZBukkitShortcuts.az;
 *
 * public void example(Player bukkitPlayer) {
 *    AZPlayer azPlayer = az(bukkitPlayer);
 *    if (azPlayer != null) {
 *      // Do something with azPlayer
 *    }
 * }}</pre>
 */
@UtilityClass
public class AZBukkitShortcuts {

    /**
     * Returns the API instance for Bukkit.
     *
     * @return the API instance
     * @az.equivalent {@code AZBukkit.api()}
     * @az.async-safe
     */
    public static @NotNull AZBukkitAPI az() {
        return AZBukkit.api();
    }

    /**
     * Returns the AZPlayer instance associated with the given player.
     *
     * @param player the player to get the AZPlayer for
     * @return the AZPlayer instance associated with the given player, or null if not found
     * @az.equivalent {@code AZBukkit.api().getClient(player)}
     * @az.async-safe
     */
    @Contract(value = "null -> null")
    public static @Nullable AZPlayer az(@Nullable Player player) {
        return AZBukkit.api().getClient(player);
    }

    /**
     * Returns the AZEntity instance associated with the given entity.
     * <p>
     * If the entity has no associated AZEntity yet, this method will try to instantiate one.
     *
     * @param entity the entity to get the AZEntity for
     * @return the AZEntity instance associated with the given entity, or null if not found
     * @az.equivalent {@code AZBukkit.api().getEntity(entity)}
     * @az.async-safe
     */
    @Contract(value = "null -> null")
    public static @Nullable AZEntity az(@Nullable Entity entity) {
        return AZBukkit.api().getEntity(entity);
    }

    /**
     * Returns the AZEntity instance associated with the given entity.
     * <p>
     * If the entity has no associated AZEntity yet, this method won't try to instantiate one and will return null.
     *
     * @param entity the entity to get the AZEntity for
     * @return the AZEntity instance associated with the given entity, or null if not found
     * @az.equivalent {@code AZBukkit.api().getEntityIfPresent(entity)}
     * @az.async-safe
     */
    @Contract(value = "null -> null")
    public static @Nullable AZEntity azIfPresent(@Nullable Entity entity) {
        return AZBukkit.api().getEntityIfPresent(entity);
    }
}
