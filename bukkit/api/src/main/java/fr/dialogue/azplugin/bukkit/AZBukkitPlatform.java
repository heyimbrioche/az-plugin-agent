package fr.dialogue.azplugin.bukkit;

import fr.dialogue.azplugin.bukkit.entity.AZEntity;
import fr.dialogue.azplugin.bukkit.entity.AZPlayer;
import fr.dialogue.azplugin.bukkit.item.ItemStackProxy;
import fr.dialogue.azplugin.common.AZPlatform;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pactify.client.api.mcprotocol.model.NotchianNbtTagCompound;

/**
 * The Bukkit AZPlugin platform interface.
 *
 * @az.low-level
 * @see AZBukkit#platform()
 */
public interface AZBukkitPlatform extends AZPlatform<Player, AZPlayer> {
    /**
     * Returns the API instance for Bukkit.
     *
     * @return the API instance
     * @az.async-safe
     */
    @Override
    @NotNull
    AZBukkitAPI getAPI();

    /**
     * Clone the given item stack and convert it to a CraftItemStack if possible.
     *
     * @param item the item stack to clone
     * @return the cloned item stack, or null if the input was null
     * @az.async-safe
     */
    @Contract("null -> null; !null -> !null")
    @Nullable
    ItemStack asCraftCopy(@Nullable ItemStack item);

    /**
     * Create a new item stack with the given parameters.
     *
     * @param itemId the item ID
     * @param count  the item count
     * @param damage the item damage
     * @param tag    the item tag, or null if none
     * @return the created item stack, or null if the item is unknown
     * @az.async-safe
     */
    @Nullable
    ItemStack createItemStack(int itemId, int count, int damage, @Nullable NotchianNbtTagCompound tag);

    /**
     * Get the NBT tag of the given item stack.
     *
     * @param itemStack the item stack to get the tag from
     * @return the NBT tag of the item stack, or null if the item stack is null or has no tag
     * @az.async-safe
     */
    @Contract("null -> null")
    @Nullable
    NotchianNbtTagCompound getItemStackTag(@Nullable ItemStack itemStack);

    /**
     * Get the proxy for the given item stack.
     *
     * @param itemStack the item stack to get the proxy for
     * @return the proxy for the item stack, or null if the item stack is null
     * @az.async-safe
     */
    @Contract("null -> null; !null -> !null")
    @Nullable
    ItemStackProxy getItemStackProxy(@Nullable ItemStack itemStack);

    /**
     * Checks if the current thread is the main thread of the target entity.
     *
     * @param target the target entity to check
     * @return true if the current thread is the main thread of the target entity, false otherwise
     * @az.async-safe
     */
    boolean isSync(@NotNull AZEntity target);

    /**
     * Asserts that the current thread is the main thread of the target entity.
     *
     * @param target the target entity to check
     * @param method the method name to include in the exception message
     * @throws IllegalStateException if the current thread is not the main thread of the target entity
     * @az.async-safe
     */
    void assertSync(@NotNull AZEntity target, String method);

    /**
     * Schedules a task to run on the main thread of the target entity.
     *
     * @param target the target entity to run the task on
     * @param task   the task to run
     * @az.async-safe
     */
    void scheduleSync(@NotNull AZEntity target, @NotNull Runnable task);
}
