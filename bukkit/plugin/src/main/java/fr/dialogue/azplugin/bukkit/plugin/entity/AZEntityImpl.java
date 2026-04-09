package fr.dialogue.azplugin.bukkit.plugin.entity;

import fr.dialogue.azplugin.bukkit.entity.AZEntity;
import fr.dialogue.azplugin.bukkit.plugin.AZPlugin;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public class AZEntityImpl extends AZEntityTrait {

    private final @Getter AZPlugin plugin;
    private final @Getter Entity bukkitEntity;

    @Override
    protected AZEntity self() {
        return this;
    }

    @Override
    protected @Nullable Player getBukkitPlayer() {
        return null;
    }
}
