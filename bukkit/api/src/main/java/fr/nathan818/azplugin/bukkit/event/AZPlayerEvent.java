package fr.nathan818.azplugin.bukkit.event;

import fr.nathan818.azplugin.bukkit.entity.AZPlayer;
import lombok.Getter;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an {@link AZPlayer} related event.
 */
@Getter
public abstract class AZPlayerEvent extends PlayerEvent {

    private final @NotNull AZPlayer azPlayer;

    public AZPlayerEvent(@NotNull AZPlayer azPlayer) {
        super(azPlayer.getBukkitPlayer());
        this.azPlayer = azPlayer;
    }
}
