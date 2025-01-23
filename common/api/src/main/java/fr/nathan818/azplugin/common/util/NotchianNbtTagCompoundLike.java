package fr.nathan818.azplugin.common.util;

import java.util.Objects;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pactify.client.api.mcprotocol.model.NotchianNbtTagCompound;

/**
 * Something that can be represented as a PLSP compound.
 */
@FunctionalInterface
public interface NotchianNbtTagCompoundLike {
    /**
     * Wraps a PLSP compound into a {@link NotchianNbtTagCompoundLike}.
     *
     * @param compound the compound to wrap
     * @return the wrapped compound
     */
    @Contract("null -> null; !null -> !null")
    static @Nullable NotchianNbtTagCompoundLike box(@Nullable NotchianNbtTagCompound compound) {
        if (compound == null) {
            return null;
        }
        if (compound instanceof NotchianNbtTagCompoundLike) {
            return (NotchianNbtTagCompoundLike) compound;
        }
        return () -> compound;
    }

    /**
     * Unwraps a {@link NotchianNbtTagCompoundLike} to get the underlying PLSP compound.
     *
     * @param compound the compound to unwrap, or null
     * @return the unwrapped compound, or null
     */
    static @Nullable NotchianNbtTagCompound unbox(@Nullable NotchianNbtTagCompoundLike compound) {
        return compound == null ? null : compound.asNotchianNbtTagCompound();
    }

    /**
     * Unwraps a {@link NotchianNbtTagCompoundLike} to get the underlying PLSP compound.
     *
     * @param compound the compound to unwrap
     * @return the unwrapped compound
     * @throws NullPointerException if the wrapped or unwrapped compound is null
     */
    static @NotNull NotchianNbtTagCompound unboxNonNull(@NotNull NotchianNbtTagCompoundLike compound) {
        return Objects.requireNonNull(compound.asNotchianNbtTagCompound(), "asNotchianNbtTagCompound() returned null");
    }

    /**
     * Gets the underlying PLSP compound representation.
     *
     * @return the PLSP compound
     */
    @Nullable
    NotchianNbtTagCompound asNotchianNbtTagCompound();
}
