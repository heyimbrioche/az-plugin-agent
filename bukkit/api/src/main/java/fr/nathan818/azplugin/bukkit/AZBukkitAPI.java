package fr.nathan818.azplugin.bukkit;

import fr.nathan818.azplugin.bukkit.entity.AZEntity;
import fr.nathan818.azplugin.bukkit.entity.AZPlayer;
import fr.nathan818.azplugin.common.AZAPI;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * Entry point to Bukkit AZPlugin API.
 * <p>
 * Get an instance of this class by calling {@link AZBukkit#api()}.
 *
 * @see AZBukkit#api()
 */
public interface AZBukkitAPI extends AZAPI<Player, AZPlayer> {
    /**
     * Returns the AZEntity instance associated with the given entity.
     * <p>
     * For players, this method is equivalent to {@link #getClient(Player)}. For other entities, this method will try to
     * instantiate an AZEntity if not already done.
     *
     * @param entity the entity to get the AZEntity for
     * @return the AZEntity instance associated with the given entity, or null if not found
     * @az.async-safe
     * @see AZBukkitShortcuts#az(Entity)
     */
    @Contract("null -> null")
    @Nullable
    AZEntity getEntity(@Nullable Entity entity);

    /**
     * Returns the AZEntity instance associated with the given entity if it is already present.
     * <p>
     * For players, this method is equivalent to {@link #getClient(Player)}. For other entities, this method will return
     * null if the entity has no AZEntity instantiated yet.
     *
     * @param entity the entity to get the AZEntity for
     * @return the AZEntity instance associated with the given entity, or null if not found
     * @az.async-safe
     * @see AZBukkitShortcuts#azIfPresent(Entity)
     */
    @Contract("null -> null")
    @Nullable
    AZEntity getEntityIfPresent(@Nullable Entity entity);

    /**
     * Returns the AZPlayer instance associated with the given player.
     * <p>
     * This method returns a non-null value when the player is online, from the {@link PlayerLoginEvent} to the
     * {@link PlayerQuitEvent}. Else, if the player is pre-login, offline or null, this method returns null.
     *
     * @param player the player to get the AZPlayer for
     * @return the AZPlayer instance associated with the given player, or null if not found
     * @az.async-safe
     * @see AZBukkitShortcuts#az(Player)
     */
    @Override
    @Nullable
    AZPlayer getClient(@Nullable Player player);
}
