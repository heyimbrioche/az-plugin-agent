package fr.dialogue.azplugin.bukkit.entity;

import fr.dialogue.azplugin.bukkit.AZBukkitShortcuts;
import fr.dialogue.azplugin.bukkit.chat.AZBungeeChatComponent;
import fr.dialogue.azplugin.bukkit.item.AZBukkitItemStack;
import fr.dialogue.azplugin.common.AZClient;
import fr.dialogue.azplugin.common.appearance.AZCosmeticEquipment;
import fr.dialogue.azplugin.common.network.AZNetworkValue;
import java.util.UUID;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * AZPlugin extension for Bukkit players.
 *
 * @see AZBukkitShortcuts#az(Player)
 */
public interface AZPlayer extends AZClient, AZEntity {
    /**
     * Get the bukkit player associated with this AZPlayer.
     *
     * @return the bukkit player
     * @az.async-safe
     */
    @NotNull
    Player getBukkitPlayer();

    /**
     * Get the bukkit player associated with this AZPlayer.
     *
     * @return the bukkit player
     * @az.async-safe
     */
    @Override
    default @NotNull Player getBukkitEntity() {
        return getBukkitPlayer();
    }

    /**
     * Check if the player is still valid.
     * <p>
     * This method will return {@code false} after the player is fully disconnected.
     *
     * @return true if the player is still valid, false otherwise
     * @az.async-safe
     */
    @Override
    default boolean isValid() {
        return !isClosed();
    }

    /**
     * Check if the player has {@linkplain PlayerJoinEvent joined} the server.
     * <p>
     * This method will return {@code true} after the player has reached the {@link PlayerJoinEvent}.
     *
     * @return true if the player has joined the server, false otherwise
     * @az.async-safe
     */
    boolean isJoined();

    /**
     * Sends a chat message to the player.
     * <p>
     * The chat message is identified by a unique ID, which can be used to alter the message later.
     *
     * @param id      the unique ID of the message
     * @param message the message to send
     * @return true if the player supports this feature, false otherwise
     * @az.async-safe
     */
    default boolean sendMessage(@NotNull UUID id, @NotNull BaseComponent[] message) {
        return sendMessage(id, AZBungeeChatComponent.copyOf(message));
    }

    /**
     * Replaces the content of a chat message previously sent to the player.
     * <p>
     * If no message with the given ID is found, nothing happens. Else, the existing message is replaced in-place with
     * the new one.
     *
     * @param id      the unique ID of the message to update
     * @param message the new message to replace with
     * @return true if the player supports this feature, false otherwise
     * @az.async-safe
     */
    default boolean replaceMessage(@NotNull UUID id, @NotNull BaseComponent[] message) {
        return replaceMessage(id, AZBungeeChatComponent.copyOf(message));
    }

    /**
     * Appends to the content of a chat message previously sent to the player.
     * <p>
     * If no message with the given ID is found, nothing happens. Else, the new message is appended to the existing
     * one.
     *
     * @param id      the unique ID of the message to update
     * @param message the message to append
     * @return true if the player supports this feature, false otherwise
     * @az.async-safe
     */
    default boolean appendMessage(@NotNull UUID id, @NotNull BaseComponent[] message) {
        return appendMessage(id, AZBungeeChatComponent.copyOf(message));
    }

    /**
     * Prepends to the content of a chat message previously sent to the player.
     * <p>
     * If no message with the given ID is found, nothing happens. Else, the new message is prepended to the existing
     * one.
     *
     * @param id      the unique ID of the message to update
     * @param message the message to prepend
     * @return true if the player supports this feature, false otherwise
     * @az.async-safe
     */
    default boolean prependMessage(@NotNull UUID id, @NotNull BaseComponent[] message) {
        return prependMessage(id, AZBungeeChatComponent.copyOf(message));
    }

    /**
     * Opens the book reading interface to the player.
     *
     * @param book the book to open
     * @return true if the player supports this feature, false otherwise
     * @az.async-safe
     */
    default boolean openBook(@NotNull ItemStack book) {
        return openBook(AZBukkitItemStack.copyOf(book));
    }

    /**
     * Open a menu inventory to the player.
     * <p>
     * This method is equivalent to {@link Player#openInventory(Inventory)}. The only difference is that, for players
     * using the AZ Launcher, the inventory will have the appearance of a menu inventory.
     *
     * @param inventory the inventory to open
     * @return the opened inventory view
     * @az.sync-only
     */
    InventoryView openMenuInventory(@NotNull Inventory inventory);

    /**
     * Open a menu inventory to the player.
     * <p>
     * This method is equivalent to {@link Player#openInventory(InventoryView)}. The only difference is that, for
     * players using the AZ Launcher, the inventory will have the appearance of a menu inventory.
     *
     * @param inventory the inventory to open
     * @az.sync-only
     */
    void openMenuInventory(@NotNull InventoryView inventory);

    /**
     * Close the player's current container.
     * <p>
     * This method is equivalent to {@link Player#closeInventory()}. The only difference is that, for players using the
     * AZ Launcher, it won't close all screens displayed on the client-side. Instead, only the closed container screen
     * will be closed (but not the potentially opened popup screens, etc.).
     *
     * @az.sync-only
     */
    void closeInventory();

    /**
     * Get the player's cosmetic equipment for the given slot.
     *
     * @param slot the slot to get the equipment from
     * @return the equipment, or null if none is set
     * @az.async-safe
     */
    @Nullable
    AZNetworkValue<AZCosmeticEquipment> getCosmeticEquipment(@NotNull AZCosmeticEquipment.Slot slot);

    /**
     * Set the player's cosmetic equipment for the given slot.
     *
     * @param slot      the slot to set the equipment for
     * @param equipment the equipment to set, or null to remove it
     * @az.equivalent {@code setCosmeticEquipment(slot, AZNetworkValue.fixed(equipment), true)}
     * @az.sync-only
     */
    default void setCosmeticEquipment(@NotNull AZCosmeticEquipment.Slot slot, @Nullable AZCosmeticEquipment equipment) {
        setCosmeticEquipment(slot, AZNetworkValue.fixed(equipment), true);
    }

    /**
     * Set the player's cosmetic equipment for the given slot.
     *
     * @param slot      the slot to set the equipment for
     * @param equipment the equipment to set, or null to remove it
     * @param flush     whether to flush the change to the player
     * @az.equivalent {@code setCosmeticEquipment(slot, AZNetworkValue.fixed(equipment), flush)}
     * @az.sync-only
     */
    default void setCosmeticEquipment(
        @NotNull AZCosmeticEquipment.Slot slot,
        @Nullable AZCosmeticEquipment equipment,
        boolean flush
    ) {
        setCosmeticEquipment(slot, AZNetworkValue.fixed(equipment), flush);
    }

    /**
     * Set the player's cosmetic equipment for the given slot.
     *
     * @param slot      the slot to set the equipment for
     * @param equipment the equipment to set, or null to remove it
     * @az.equivalent {@code setCosmeticEquipment(slot, equipment, true)}
     * @az.sync-only
     */
    default void setCosmeticEquipment(
        @NotNull AZCosmeticEquipment.Slot slot,
        @Nullable AZNetworkValue<AZCosmeticEquipment> equipment
    ) {
        setCosmeticEquipment(slot, equipment, true);
    }

    /**
     * Set the player's cosmetic equipment for the given slot.
     *
     * @param slot      the slot to set the equipment for
     * @param equipment the equipment to set, or null to remove it
     * @param flush     whether to flush the change to the player
     * @az.sync-only
     */
    void setCosmeticEquipment(
        @NotNull AZCosmeticEquipment.Slot slot,
        @Nullable AZNetworkValue<AZCosmeticEquipment> equipment,
        boolean flush
    );

    /**
     * Send the player's cosmetic equipment packets for the given slot to the given recipients.
     * <p>
     * Packets are sent only to recipients that are currently tracking this entity (self or
     * {@linkplain #isViewer(Player) viewer}).
     *
     * @param slot       the slot to send the equipment for
     * @param recipients the recipients to send the packets to
     * @az.sync-only
     */
    void flushCosmeticEquipment(
        @NotNull AZCosmeticEquipment.Slot slot,
        @NotNull Iterable<? extends @NotNull Player> recipients
    );
}
