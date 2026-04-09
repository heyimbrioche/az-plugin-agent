package fr.dialogue.azplugin.bukkit.plugin.entity;

import static fr.dialogue.azplugin.bukkit.AZBukkitShortcuts.az;
import static fr.dialogue.azplugin.bukkit.compat.BukkitCompat.compat;

import fr.dialogue.azplugin.bukkit.AZBukkit;
import fr.dialogue.azplugin.bukkit.compat.agent.CompatBridge;
import fr.dialogue.azplugin.bukkit.compat.event.EntityTrackBeginEvent;
import fr.dialogue.azplugin.bukkit.entity.AZEntity;
import fr.dialogue.azplugin.bukkit.entity.AZPlayer;
import fr.dialogue.azplugin.bukkit.plugin.AZPlugin;
import fr.dialogue.azplugin.common.appearance.AZEntityModel;
import fr.dialogue.azplugin.common.appearance.AZEntityScale;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class EntityManager implements Listener {

    private final AZPlugin plugin;

    public void register() {
        CompatBridge.getHeadHeightFunction = EntityManager::getHeadHeight;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        AZPlayer azPlayer = az(event.getPlayer());
        if (azPlayer != null) {
            // Send initial metadata to self
            // Delayed to be sent after PacketPlayOutPlayerInfo
            AZBukkit.platform()
                .scheduleSync(azPlayer, () -> {
                    if (azPlayer.isValid()) {
                        azPlayer.flushAllMetadata(Collections.singleton(azPlayer.getBukkitPlayer()), true);
                    }
                });
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onEntityTrackBegin(EntityTrackBeginEvent event) {
        AZEntity azEntity = AZBukkit.api().getEntityIfPresent(event.getEntity());
        if (azEntity != null) {
            azEntity.flushAllMetadata(Collections.singleton(event.getViewer()), true);
        }
    }

    public static float getHeadHeight(@NotNull Entity entity, float unscaledHeadHeight) {
        AZEntity azEntity = AZBukkit.api().getEntityIfPresent(entity);
        if (azEntity == null) {
            return unscaledHeadHeight;
        }
        AZEntityModel model = azEntity.getEffectiveModel();
        if (model != null) {
            float modelUnscaledHeadHeight;
            if (compat().isElytraFlying(entity)) {
                modelUnscaledHeadHeight = model.getEyeHeightElytra();
            } else if (compat().isSleeping(entity)) {
                modelUnscaledHeadHeight = model.getEyeHeightSleep();
            } else if (compat().isSneaking(entity)) {
                modelUnscaledHeadHeight = model.getEyeHeightSneak();
            } else {
                modelUnscaledHeadHeight = model.getEyeHeightStand();
            }
            if (!Float.isNaN(modelUnscaledHeadHeight)) {
                unscaledHeadHeight = modelUnscaledHeadHeight;
            }
        }
        AZEntityScale scale = azEntity.getEffectiveScale();
        if (scale != null) {
            return unscaledHeadHeight * scale.getRenderHeight();
        }
        return unscaledHeadHeight;
    }
}
