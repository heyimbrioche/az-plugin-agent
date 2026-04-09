package fr.dialogue.azplugin.common;

import fr.dialogue.azplugin.common.network.AZPacketBuffer;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pactify.client.api.mcprotocol.model.NotchianChatComponent;
import pactify.client.api.mcprotocol.model.NotchianItemStack;
import pactify.client.api.mcprotocol.model.NotchianNbtTagCompound;
import pactify.client.api.mcprotocol.model.SimpleNotchianChatComponent;
import pactify.client.api.mcprotocol.model.SimpleNotchianItemStack;

/**
 * The common (platform-independent) AZPlugin platform interface.
 *
 * @param <Player> the platform-specific player type
 * @param <Client> the platform-specific AZClient type
 * @az.low-level
 * @see AZ#platform()
 */
public interface AZPlatform<Player, Client extends AZClient> {
    /**
     * Returns the API instance for the current platform.
     *
     * @return the API instance
     * @az.async-safe
     */
    @Contract(pure = true)
    @NotNull
    AZAPI<Player, Client> getAPI();

    /**
     * Returns the AZPlugin logger.
     * <p>
     * The logger is used to log messages from the plugin, do not use it for your own logging.
     *
     * @return the logger
     */
    @Contract(pure = true)
    @NotNull
    Logger getLogger();

    /**
     * Parses a JSON string into a standard Java objects (maps, lists, strings, numbers, booleans).
     *
     * @param reader the reader to read the JSON from
     * @return the parsed object
     * @throws IOException if an I/O error occurs
     */
    @Nullable
    default Object parseJson(@NotNull Reader reader) throws IOException {
        throw new UnsupportedOperationException("Platform does not support parsing JSON");
    }

    /**
     * Creates a new packet buffer.
     *
     * @param client the client targeted by the buffer, or null if the buffer is not associated with a client
     * @return the new packet buffer
     */
    @NotNull
    AZPacketBuffer createHeapPacketBuffer(@Nullable AZClient client);

    /**
     * Creates a new packet buffer with the specified initial capacity.
     *
     * @param client          the client targeted by the buffer, or null if the buffer is not associated with a client
     * @param initialCapacity the initial capacity of the buffer
     * @return the new packet buffer
     */
    @NotNull
    AZPacketBuffer createHeapPacketBuffer(@Nullable AZClient client, int initialCapacity);

    /**
     * Reads a Notchian chat component from a packet buffer.
     *
     * @param buf the packet buffer
     * @return the chat component
     */
    default NotchianChatComponent readNotchianChatComponent(@NotNull AZPacketBuffer buf) {
        return SimpleNotchianChatComponent.read(buf);
    }

    /**
     * Writes a Notchian chat component to a packet buffer.
     *
     * @param buf           the packet buffer
     * @param chatComponent the chat component
     */
    default void writeNotchianChatComponent(@NotNull AZPacketBuffer buf, NotchianChatComponent chatComponent) {
        NotchianChatComponent.write(buf, chatComponent);
    }

    /**
     * Reads a Notchian item stack from a packet buffer.
     *
     * @param buf the packet buffer
     * @return the item stack
     */
    @Nullable
    default NotchianItemStack readNotchianItemStack(@NotNull AZPacketBuffer buf) {
        return SimpleNotchianItemStack.read(buf);
    }

    /**
     * Writes a Notchian item stack to a packet buffer.
     *
     * @param buf       the packet buffer
     * @param itemStack the item stack
     */
    default void writeNotchianItemStack(@NotNull AZPacketBuffer buf, @Nullable NotchianItemStack itemStack) {
        NotchianItemStack.write(buf, itemStack);
    }

    /**
     * Reads a Notchian NBT tag compound from a packet buffer.
     *
     * @param buf the packet buffer
     * @return the NBT tag compound
     */
    @Nullable
    default NotchianNbtTagCompound readNotchianNbtTagCompound(@NotNull AZPacketBuffer buf) {
        throw new UnsupportedOperationException("Platform does not support reading NotchianNbtTagCompound");
    }

    /**
     * Writes a Notchian NBT tag compound to a packet buffer.
     *
     * @param buf            the packet buffer
     * @param nbtTagCompound the NBT tag compound
     */
    default void writeNotchianNbtTagCompound(
        @NotNull AZPacketBuffer buf,
        @Nullable NotchianNbtTagCompound nbtTagCompound
    ) {
        NotchianNbtTagCompound.write(buf, nbtTagCompound);
    }

    /**
     * Static utility methods that return the platform logger.
     * <p>
     * If the platform is not initialized, a fallback global logger is returned.
     *
     * @return the logger
     */
    @Contract(pure = true)
    @NotNull
    static Logger logger() {
        AZPlatform<?, ?> api = AZ.platform;
        if (api != null) {
            return api.getLogger();
        } else {
            return AZFallbackLogger.getLogger();
        }
    }

    /**
     * Logs a message using the platform logger.
     *
     * @param level   the log level
     * @param message the message to log
     */
    static void log(@NotNull Level level, @NotNull String message) {
        logger().log(level, message);
    }

    /**
     * Logs a message using the platform logger.
     *
     * @param level   the log level
     * @param message the message to log
     * @param thrown  a throwable to log
     */
    static void log(@NotNull Level level, @NotNull String message, @Nullable Throwable thrown) {
        logger().log(level, message, thrown);
    }

    /**
     * Logs a message using the platform logger.
     *
     * @param level  the log level
     * @param format the format string
     * @param args   the arguments to format
     */
    static void log(@NotNull Level level, @NotNull String format, @NotNull Object... args) {
        if (args.length > 0 && args[args.length - 1] instanceof Throwable) {
            LogRecord lr = new LogRecord(level, format);
            lr.setParameters(args); // Throwable is not removed, it will be ignored
            lr.setThrown((Throwable) args[args.length - 1]);
            logger().log(lr);
        } else {
            logger().log(level, format, args);
        }
    }
}
