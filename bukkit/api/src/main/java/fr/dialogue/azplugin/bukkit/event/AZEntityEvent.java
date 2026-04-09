package fr.dialogue.azplugin.bukkit.event;

import fr.dialogue.azplugin.bukkit.entity.AZEntity;
import lombok.Getter;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an {@link AZEntity} related event.
 */
@Getter
public abstract class AZEntityEvent extends EntityEvent {

    private final @NotNull AZEntity azEntity;

    public AZEntityEvent(@NotNull AZEntity azEntity) {
        super(azEntity.getBukkitEntity());
        this.azEntity = azEntity;
    }
}
