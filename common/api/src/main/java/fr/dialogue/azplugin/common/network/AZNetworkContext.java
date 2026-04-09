package fr.dialogue.azplugin.common.network;

import fr.dialogue.azplugin.common.AZClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pactify.client.api.plsp.PLSPProtocol;

/**
 * A context used to resolve {@link AZNetworkValue network values}.
 */
public interface AZNetworkContext {
    /**
     * Gets an unknown network context.
     * <p>
     * The unknown context may be used to resolve network values not depending on a specific client.
     * <ul>
     * <li>{@link #getViewer()} returns null for the unknown context.</li>
     * <li>{@link #isEffective()} returns false for the unknown context.</li>
     * </ul>
     *
     * @return the unknown network context
     */
    static AZNetworkContext unknown() {
        return AZNetworkContextEmpty.UNKNOWN;
    }

    /**
     * Gets the effective network context.
     * <p>
     * The effective context is used when resolving values applied internally to the server (for physics calculations,
     * etc.).
     * <ul>
     * <li>{@link #getViewer()} returns null for the effective context.</li>
     * <li>{@link #isEffective()} returns true for the effective context.</li>
     * </ul>
     *
     * @return the effective network context
     */
    static AZNetworkContext effective() {
        return AZNetworkContextEmpty.EFFECTIVE;
    }

    /**
     * Gets the network context of the given client.
     *
     * @param client the client to get the network context from, may be null
     * @return the network context of the given client, or {@link #unknown()} if the client is null
     * @az.equivalent {@code client == null ? unknown() : client.getNetworkContext()}
     */
    static @NotNull AZNetworkContext of(@Nullable AZClient client) {
        return client == null ? unknown() : client.getNetworkContext();
    }

    /**
     * Gets the client receiving the value.
     *
     * @return the client, or null if unknown
     */
    @Nullable
    AZClient getViewer();

    /**
     * Checks if the context is effective.
     * <p>
     * The effective context is used when resolving values applied internally to the server (for physics calculations,
     * etc.).
     *
     * @return true if the context is effective, false otherwise
     */
    default boolean isEffective() {
        return false;
    }

    /**
     * Gets the protocol version of the client.
     * <p>
     * If the client is unknown, this method returns the highest supported protocol version.
     *
     * @return the protocol version of the client, or the highest supported protocol version if unknown
     */
    default int getAZProtocolVersion() {
        return getAZProtocolVersion(PLSPProtocol.PROTOCOL_VERSION);
    }

    /**
     * Gets the protocol version of the client.
     * <p>
     * If the client is unknown, this method returns the given fallback.
     *
     * @param fallback the fallback value if the client is unknown
     * @return the protocol version of the client, or the fallback if unknown
     */
    default int getAZProtocolVersion(int fallback) {
        AZClient viewer = getViewer();
        return viewer == null ? fallback : viewer.getAZProtocolVersion();
    }
}
