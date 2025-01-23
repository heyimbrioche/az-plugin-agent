package fr.nathan818.azplugin.common.util;

import java.util.Objects;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pactify.client.api.mcprotocol.model.NotchianChatComponent;

/**
 * Something that can be represented as a PLSP chat component.
 */
@FunctionalInterface
public interface NotchianChatComponentLike {
    /**
     * Wraps a PLSP chat component into a {@link NotchianChatComponentLike}.
     *
     * @param component the component to wrap
     * @return the wrapped component
     */
    @Contract("null -> null; !null -> !null")
    static @Nullable NotchianChatComponentLike box(@Nullable NotchianChatComponent component) {
        if (component == null) {
            return null;
        }
        if (component instanceof NotchianChatComponentLike) {
            return (NotchianChatComponentLike) component;
        }
        return () -> component;
    }

    /**
     * Unwraps a {@link NotchianChatComponentLike} to get the underlying PLSP chat component.
     *
     * @param component the component to unwrap, or null
     * @return the unwrapped component, or null
     */
    static @Nullable NotchianChatComponent unbox(@Nullable NotchianChatComponentLike component) {
        return component == null ? null : component.asNotchianChatComponent();
    }

    /**
     * Unwraps a {@link NotchianChatComponentLike} to get the underlying PLSP chat component.
     *
     * @param component the component to unwrap
     * @return the unwrapped component
     * @throws NullPointerException if the wrapped or unwrapped component is null
     */
    static @NotNull NotchianChatComponent unboxNonNull(@NotNull NotchianChatComponentLike component) {
        return Objects.requireNonNull(component.asNotchianChatComponent(), "asNotchianChatComponent() returned null");
    }

    /**
     * Gets the underlying PLSP chat component representation.
     *
     * @return the PLSP chat component
     */
    @Nullable
    NotchianChatComponent asNotchianChatComponent();
}
