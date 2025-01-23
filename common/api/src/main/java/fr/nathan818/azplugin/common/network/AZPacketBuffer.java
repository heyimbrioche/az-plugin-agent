package fr.nathan818.azplugin.common.network;

import fr.nathan818.azplugin.common.AZClient;
import java.io.DataInput;
import java.io.DataOutput;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pactify.client.api.mcprotocol.NotchianPacketBuffer;
import pactify.client.api.plsp.PLSPPacketBuffer;

/**
 * A packet buffer that can be used to read and write network packets.
 * <p>
 * Remember to close the buffer when you are done with it, as many implementations may use native resources that need to
 * be released.
 */
public interface AZPacketBuffer extends PLSPPacketBuffer<AZPacketBuffer>, AutoCloseable {
    /**
     * Wraps the given packet buffer into a {@link DataInput}.
     *
     * @param buf the packet buffer to wrap
     * @return the wrapped packet buffer as a data input, or null if the given buffer is null
     */
    @Contract("null -> null; !null -> !null")
    static @Nullable DataInput asDataInput(@Nullable NotchianPacketBuffer buf) {
        if (buf == null) {
            return null;
        }
        if (buf instanceof AZPacketBuffer) {
            return ((AZPacketBuffer) buf).asDataInput();
        }
        return new NotchianPacketBufferDataInput(buf);
    }

    /**
     * Wraps the given packet buffer into a {@link DataOutput}.
     *
     * @param buf the packet buffer to wrap
     * @return the wrapped packet buffer as a data output, or null if the given buffer is null
     */
    @Contract("null -> null; !null -> !null")
    static @Nullable DataOutput asDataOutput(@Nullable NotchianPacketBuffer buf) {
        if (buf == null) {
            return null;
        }
        if (buf instanceof AZPacketBuffer) {
            return ((AZPacketBuffer) buf).asDataOutput();
        }
        return new NotchianPacketBufferDataOutput(buf);
    }

    /**
     * Gets the client targeted by this packet buffer.
     * <p>
     * Only this client is supposed to send or receive packets using this buffer.
     *
     * @return the client targeted by this packet buffer, or null if the client is unknown
     */
    @Nullable
    AZClient getClient();

    /**
     * Converts this packet buffer into a byte array.
     * <p>
     * This method may create a copy, or returns a view of the internal buffer. So the returned array should be
     * considered as read-only. And it should not be stored, nor used after the buffer is closed.
     */
    byte@NotNull[] toByteArray();

    /**
     * Closes this packet buffer.
     * <p>
     * This method releases any resources used by this buffer. After this method is called, the buffer should not be
     * used anymore; any operation on it may have unexpected behavior (e.g., throwing exceptions).
     */
    @Override
    void close();

    /**
     * Wraps this packet buffer into a {@link DataInput}.
     *
     * @return the wrapped packet buffer as a data input
     */
    @NotNull
    DataInput asDataInput();

    /**
     * Wraps this packet buffer into a {@link DataOutput}.
     *
     * @return the wrapped packet buffer as a data output
     */
    @NotNull
    DataOutput asDataOutput();
}
