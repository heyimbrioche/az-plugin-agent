package fr.nathan818.azplugin.common.network;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A value that may vary depending on the network context.
 * <p>
 * This is useful to easily show different things to different players.
 *
 * @param <T> the type of the value
 */
@FunctionalInterface
public interface AZNetworkValue<T> {
    /**
     * Wraps the given value into a fixed network value.
     *
     * @param value the value to wrap, may be null
     * @param <T>   the type of the value
     * @return the fixed network value, or null if the given value is null
     */
    @Contract(value = "null -> null; !null -> !null", pure = true)
    static <T> @Nullable AZNetworkValue<T> fixed(@Nullable T value) {
        return (value == null) ? null : new AZNetworkValueFixed<>(value);
    }

    /**
     * Retrieves the fixed value from the given network value.
     * <p>
     * If the given value is null or not fixed, this method returns null.
     *
     * @param value the fixed network value to unwrap, may be null
     * @param <T>   the type of the value
     * @return the unwrapped fixed value, or null if the given value is null or not fixed
     */
    static <T> @Nullable T getFixed(@Nullable AZNetworkValue<T> value) {
        return (value == null || !value.isFixed()) ? null : value.get(AZNetworkContext.unknown());
    }

    /**
     * Gets the value for the given context.
     * <p>
     * <b>WARNING:</b> This method may be called from any thread, often from a network thread. So be sure to use
     * thread-safe code, with no side effects.
     *
     * @param ctx the network context
     * @return the evaluated value for the given context
     */
    @Nullable
    T get(@NotNull AZNetworkContext ctx);

    /**
     * Checks if this value is fixed.
     * <p>
     * A fixed value is a value that does not depend on the network context and never changes. A fixed value should
     * NEVER be null.
     *
     * @return true if this value is fixed, false otherwise
     */
    default boolean isFixed() {
        return false;
    }
}
