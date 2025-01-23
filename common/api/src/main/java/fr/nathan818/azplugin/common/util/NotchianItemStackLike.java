package fr.nathan818.azplugin.common.util;

import java.util.Objects;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pactify.client.api.mcprotocol.model.NotchianItemStack;

/**
 * Something that can be represented as a PLSP item stack.
 */
@FunctionalInterface
public interface NotchianItemStackLike {
    /**
     * Wraps a PLSP item stack into a {@link NotchianItemStackLike}.
     *
     * @param itemStack the itemStack to wrap
     * @return the wrapped itemStack
     */
    @Contract("null -> null; !null -> !null")
    static @Nullable NotchianItemStackLike box(@Nullable NotchianItemStack itemStack) {
        if (itemStack == null) {
            return null;
        }
        if (itemStack instanceof NotchianItemStackLike) {
            return (NotchianItemStackLike) itemStack;
        }
        return () -> itemStack;
    }

    /**
     * Unwraps a {@link NotchianItemStackLike} to get the underlying PLSP item stack.
     *
     * @param itemStack the itemStack to unwrap, or null
     * @return the unwrapped itemStack, or null
     */
    static @Nullable NotchianItemStack unbox(@Nullable NotchianItemStackLike itemStack) {
        return itemStack == null ? null : itemStack.asNotchianItemStack();
    }

    /**
     * Unwraps a {@link NotchianItemStackLike} to get the underlying PLSP item stack.
     *
     * @param itemStack the itemStack to unwrap
     * @return the unwrapped itemStack
     * @throws NullPointerException if the wrapped or unwrapped itemStack is null
     */
    static @NotNull NotchianItemStack unboxNonNull(@NotNull NotchianItemStackLike itemStack) {
        return Objects.requireNonNull(itemStack.asNotchianItemStack(), "asNotchianItemStack() returned null");
    }

    /**
     * Gets the underlying PLSP item stack representation.
     *
     * @return the PLSP item stack
     */
    @Nullable
    NotchianItemStack asNotchianItemStack();
}
