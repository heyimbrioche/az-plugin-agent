package fr.dialogue.azplugin.bukkit.event;

import fr.dialogue.azplugin.bukkit.entity.AZEntity;
import fr.dialogue.azplugin.common.network.AZNetworkValue;
import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called when an entity opacity was changed.
 *
 * @see AZEntity#setOpacity(Float)
 */
@Getter
public class AZEntityOpacityChangedEvent extends AZEntityEvent {

    private final @Nullable AZNetworkValue<Float> oldOpacity;
    private final @Nullable AZNetworkValue<Float> newOpacity;

    public AZEntityOpacityChangedEvent(
        @NotNull AZEntity azEntity,
        @Nullable AZNetworkValue<Float> oldOpacity,
        @Nullable AZNetworkValue<Float> newOpacity
    ) {
        super(azEntity);
        this.oldOpacity = oldOpacity;
        this.newOpacity = newOpacity;
    }

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
