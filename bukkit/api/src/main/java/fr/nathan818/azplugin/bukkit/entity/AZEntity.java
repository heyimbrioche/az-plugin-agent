package fr.nathan818.azplugin.bukkit.entity;

import fr.nathan818.azplugin.bukkit.AZBukkitShortcuts;
import fr.nathan818.azplugin.common.appearance.AZEntityModel;
import fr.nathan818.azplugin.common.appearance.AZEntityScale;
import fr.nathan818.azplugin.common.appearance.AZNameTag;
import fr.nathan818.azplugin.common.network.AZNetworkValue;
import java.util.function.Predicate;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

/**
 * AZPlugin extension for Bukkit entities.
 *
 * @see AZBukkitShortcuts#az(Entity)
 */
public interface AZEntity {
    /**
     * Get the bukkit entity associated with this AZEntity.
     *
     * @return the bukkit entity
     * @az.async-safe
     */
    @NotNull
    Entity getBukkitEntity();

    /**
     * Check if the entity is still valid.
     * <p>
     * For players, this method will return {@code false} after the player is fully disconnected. For other entities,
     * this method will return {@code false} if the entity is dead, removed or despawned.
     *
     * @return true if the entity is still valid, false otherwise
     * @az.async-safe
     */
    default boolean isValid() {
        return getBukkitEntity().isValid();
    }

    /**
     * Get the viewers of this entity.
     * <p>
     * The viewers are the players that are currently tracking this entity (i.e., receiving spawn/update packets about
     * this entity).
     * <p>
     * The returned iterable may be a view backed by the internal representation, such that changes to the internal
     * state of the server will be reflected immediately. However, the reuse of the returned collection (identity) is
     * not strictly guaranteed. Iteration behavior is undefined outside self-contained main-thread uses. Normal and
     * immediate iterator use without consequences that affect the collection is fully supported. The effects following
     * (non-exhaustive) {@linkplain Entity#teleport(Location) teleportation},
     * {@linkplain LivingEntity#setHealth(double) death}, and {@linkplain Entity#remove() removing} are undefined. Any
     * use of this collection from asynchronous threads is unsafe.
     *
     * @param includeSelf whether to include the entity itself in the returned viewers (only effective if it's a
     *                    player)
     * @return the viewers of this entity
     * @az.sync-only
     */
    @UnmodifiableView
    @NotNull
    Iterable<? extends Player> getViewers(boolean includeSelf);

    /**
     * Get the viewers of this entity.
     * <p>
     * The viewers are the players that are currently tracking this entity (i.e., receiving spawn/update packets about
     * this entity).
     * <p>
     * The returned iterable may be a view backed by the internal representation, such that changes to the internal
     * state of the server will be reflected immediately. However, the reuse of the returned collection (identity) is
     * not strictly guaranteed. Iteration behavior is undefined outside self-contained main-thread uses. Normal and
     * immediate iterator use without consequences that affect the collection is fully supported. The effects following
     * (non-exhaustive) {@linkplain Entity#teleport(Location) teleportation},
     * {@linkplain LivingEntity#setHealth(double) death}, and {@linkplain Entity#remove() removing} are undefined. Any
     * use of this collection from asynchronous threads is unsafe.
     *
     * @param includeSelf whether to include the entity itself in the returned viewers (only effective if it's a
     *                    player)
     * @param filter      a predicate to filter the viewers
     * @return the viewers of this entity
     * @az.sync-only
     */
    @UnmodifiableView
    @NotNull
    Iterable<? extends @NotNull Player> getViewers(boolean includeSelf, @NotNull Predicate<? super Player> filter);

    /**
     * Check if the given player is a {@linkplain #getViewers(boolean) viewer} of this entity.
     *
     * @param other the player to check
     * @return true if the player is a viewer of this entity, false otherwise
     * @az.sync-only
     */
    boolean isViewer(@NotNull Player other);

    /**
     * Get the custom scale configuration used for effective calculations (e.g., physics, hitbox, etc.).
     *
     * @return the effective scale configuration, or null if the entity uses the default scale
     * @az.async-safe
     */
    @Nullable
    AZEntityScale getEffectiveScale();

    /**
     * Get the custom model configuration used for effective calculations (e.g., physics, eye height, etc.).
     *
     * @return the effective model configuration, or null if the entity uses the default model
     * @az.async-safe
     */
    @Nullable
    AZEntityModel getEffectiveModel();

    /**
     * Get the custom scale configuration applied to this entity.
     *
     * @return the scale configuration, or null if the entity uses the default scale
     * @az.async-safe
     */
    @Nullable
    AZNetworkValue<AZEntityScale> getScale();

    /**
     * Set the custom scale configuration for this entity.
     *
     * @param scale the scale configuration, or null to use the default scale
     * @az.equivalent {@code setScale(AZNetworkValue.fixed(scale), true)}
     * @az.sync-only
     */
    default void setScale(@Nullable AZEntityScale scale) {
        setScale(AZNetworkValue.fixed(scale), true);
    }

    /**
     * Set the custom scale configuration for this entity.
     *
     * @param scale the scale configuration, or null to use the default scale
     * @param flush whether to send the change to the viewers
     * @az.equivalent {@code setScale(AZNetworkValue.fixed(scale), flush)}
     * @az.sync-only
     */
    default void setScale(@Nullable AZEntityScale scale, boolean flush) {
        setScale(AZNetworkValue.fixed(scale), flush);
    }

    /**
     * Set the custom scale configuration for this entity.
     *
     * @param scale the scale configuration, or null to use the default scale
     * @az.equivalent {@code setScale(scale, true)}
     * @az.sync-only
     */
    default void setScale(@Nullable AZNetworkValue<AZEntityScale> scale) {
        setScale(scale, true);
    }

    /**
     * Set the custom scale configuration for this entity.
     *
     * @param scale the scale configuration, or null to use the default scale
     * @param flush whether to send the change to the viewers
     * @az.sync-only
     */
    void setScale(@Nullable AZNetworkValue<AZEntityScale> scale, boolean flush);

    /**
     * Send the scale configuration packets to the given recipients.
     * <p>
     * Packets are sent only to recipients that are currently tracking this entity (self or
     * {@linkplain #isViewer(Player) viewer}).
     *
     * @param recipients the recipients to send the packets to
     * @az.sync-only
     */
    void flushScale(@NotNull Iterable<? extends @NotNull Player> recipients);

    /**
     * Get the custom model configuration applied to this entity.
     *
     * @return the model configuration, or null if the entity uses the default model
     * @az.async-safe
     */
    @Nullable
    AZNetworkValue<AZEntityModel> getModel();

    /**
     * Set the custom model configuration for this entity.
     *
     * @param model the model configuration, or null to use the default model
     * @az.equivalent {@code setModel(AZNetworkValue.fixed(model), true)}
     * @az.sync-only
     */
    default void setModel(@Nullable AZEntityModel model) {
        setModel(AZNetworkValue.fixed(model), true);
    }

    /**
     * Set the custom model configuration for this entity.
     *
     * @param model the model configuration, or null to use the default model
     * @param flush whether to send the change to the viewers
     * @az.equivalent {@code setModel(AZNetworkValue.fixed(model), flush)}
     * @az.sync-only
     */
    default void setModel(@Nullable AZEntityModel model, boolean flush) {
        setModel(AZNetworkValue.fixed(model), flush);
    }

    /**
     * Set the custom model configuration for this entity.
     *
     * @param model the model configuration, or null to use the default model
     * @az.equivalent {@code setModel(model, true)}
     * @az.sync-only
     */
    default void setModel(@Nullable AZNetworkValue<AZEntityModel> model) {
        setModel(model, true);
    }

    /**
     * Set the custom model configuration for this entity.
     *
     * @param model the model configuration, or null to use the default model
     * @param flush whether to send the change to the viewers
     * @az.sync-only
     */
    void setModel(@Nullable AZNetworkValue<AZEntityModel> model, boolean flush);

    /**
     * Send the model configuration packets to the given recipients.
     * <p>
     * Packets are sent only to recipients that are currently tracking this entity (self or
     * {@linkplain #isViewer(Player) viewer}).
     *
     * @param recipients the recipients to send the packets to
     * @az.sync-only
     */
    void flushModel(@NotNull Iterable<? extends @NotNull Player> recipients);

    /**
     * Get the custom name tag applied to this entity for the given slot.
     *
     * @param slot the slot to get the name tag from
     * @return the name tag, or null if the entity uses the default name tag
     * @az.async-safe
     */
    @Nullable
    AZNetworkValue<AZNameTag> getNameTag(@NotNull AZNameTag.Slot slot);

    /**
     * Set the custom name tag for the given slot.
     *
     * @param slot the slot to set the name tag for
     * @param tag  the name tag, or null to use the default name tag
     * @az.equivalent {@code setNameTag(slot, AZNetworkValue.fixed(tag), true)}
     * @az.sync-only
     */
    default void setNameTag(@NotNull AZNameTag.Slot slot, @Nullable AZNameTag tag) {
        setNameTag(slot, AZNetworkValue.fixed(tag), true);
    }

    /**
     * Set the custom name tag for the given slot.
     *
     * @param slot  the slot to set the name tag for
     * @param tag   the name tag, or null to use the default name tag
     * @param flush whether to send the change to the viewers
     * @az.equivalent {@code setNameTag(slot, AZNetworkValue.fixed(tag), flush)}
     * @az.sync-only
     */
    default void setNameTag(@NotNull AZNameTag.Slot slot, @Nullable AZNameTag tag, boolean flush) {
        setNameTag(slot, AZNetworkValue.fixed(tag), flush);
    }

    /**
     * Set the custom name tag for the given slot.
     *
     * @param slot the slot to set the name tag for
     * @param tag  the name tag, or null to use the default name tag
     * @az.equivalent {@code setNameTag(slot, tag, true)}
     * @az.sync-only
     */
    default void setNameTag(@NotNull AZNameTag.Slot slot, @Nullable AZNetworkValue<AZNameTag> tag) {
        setNameTag(slot, tag, true);
    }

    /**
     * Set the custom name tag for the given slot.
     *
     * @param slot  the slot to set the name tag for
     * @param tag   the name tag, or null to use the default name tag
     * @param flush whether to send the change to the viewers
     * @az.sync-only
     */
    void setNameTag(@NotNull AZNameTag.Slot slot, @Nullable AZNetworkValue<AZNameTag> tag, boolean flush);

    /**
     * Send the name tag packets for the given slot to the given recipients.
     * <p>
     * Packets are sent only to recipients that are currently tracking this entity (self or
     * {@linkplain #isViewer(Player) viewer}).
     *
     * @param slot       the slot to send the name tag for
     * @param recipients the recipients to send the packets to
     * @az.sync-only
     */
    void flushNameTag(@NotNull AZNameTag.Slot slot, @NotNull Iterable<? extends @NotNull Player> recipients);

    /**
     * Get the custom opacity applied to this entity.
     *
     * @return the opacity, or null if the entity uses the default opacity
     * @az.async-safe
     */
    @Nullable
    AZNetworkValue<Float> getOpacity();

    /**
     * Set the custom opacity for this entity.
     *
     * @param opacity the opacity, or null to use the default opacity
     * @az.equivalent {@code setOpacity(AZNetworkValue.fixed(opacity), true)}
     * @az.sync-only
     */
    default void setOpacity(@Nullable Float opacity) {
        setOpacity(AZNetworkValue.fixed(opacity), true);
    }

    /**
     * Set the custom opacity for this entity.
     *
     * @param opacity the opacity, or null to use the default opacity
     * @param flush   whether to send the change to the viewers
     * @az.equivalent {@code setOpacity(AZNetworkValue.fixed(opacity), flush)}
     * @az.sync-only
     */
    default void setOpacity(@Nullable Float opacity, boolean flush) {
        setOpacity(AZNetworkValue.fixed(opacity), flush);
    }

    /**
     * Set the custom opacity for this entity.
     *
     * @param opacity the opacity, or null to use the default opacity
     * @az.equivalent {@code setOpacity(opacity, true)}
     * @az.sync-only
     */
    default void setOpacity(@Nullable AZNetworkValue<Float> opacity) {
        setOpacity(opacity, true);
    }

    /**
     * Set the custom opacity for this entity.
     *
     * @param opacity the opacity, or null to use the default opacity
     * @param flush   whether to send the change to the viewers
     * @az.sync-only
     */
    void setOpacity(@Nullable AZNetworkValue<Float> opacity, boolean flush);

    /**
     * Send the opacity packets to the given recipients.
     * <p>
     * Packets are sent only to recipients that are currently tracking this entity (self or
     * {@linkplain #isViewer(Player) viewer}).
     *
     * @param recipients the recipients to send the packets to
     * @az.sync-only
     */
    void flushOpacity(@NotNull Iterable<? extends @NotNull Player> recipients);

    /**
     * Send all metadata (scale, model, etc.) packets to the given recipients.
     *
     * @param recipients   the recipients to send the packets to
     * @param onTrackBegin whether the metadata is being sent on track begins, if true, only non-default metadata are
     *                     sent
     * @az.sync-only
     */
    void flushAllMetadata(@NotNull Iterable<? extends @NotNull Player> recipients, boolean onTrackBegin);
}
