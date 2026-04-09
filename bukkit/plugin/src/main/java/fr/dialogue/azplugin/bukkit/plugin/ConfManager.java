package fr.dialogue.azplugin.bukkit.plugin;

import static fr.dialogue.azplugin.bukkit.AZBukkitShortcuts.az;
import static fr.dialogue.azplugin.bukkit.compat.BukkitCompat.compat;

import fr.dialogue.azplugin.bukkit.compat.agent.CompatBridge;
import fr.dialogue.azplugin.bukkit.entity.AZPlayer;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public class ConfManager implements Listener {

    private final AZPlugin plugin;

    public void register() {
        CompatBridge.isAttackCooldownDisabledFunction = ConfManager::isAttackCooldownDisabled;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLogin(PlayerLoginEvent event) {
        AZPlayer azPlayer = az(event.getPlayer());
        if (azPlayer != null) {
            // TODO: Add config
            azPlayer.setDisableAttackCooldown(true);
            azPlayer.setDisableHitIndicator(true);
            azPlayer.setSwordBlocking(CompatBridge.isSwordBlockingEnabled());
            azPlayer.setHitAndBlock(true);
            azPlayer.setLargeHitbox(true);
            azPlayer.setPvpHitPriority(true);
            azPlayer.setDisablePlayerPush(true);
            azPlayer.setServerSideAnvil(true);
            azPlayer.setDisableSidebarScores(true);
            azPlayer.setChatMessageMaxSize(256);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        AZPlayer azPlayer = az(event.getPlayer());
        int limit = getChatMessageMaxSize(azPlayer);
        String message = event.getMessage();
        if (message.length() > limit) {
            event.setMessage(message.substring(0, limit));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        AZPlayer azPlayer = az(event.getPlayer());
        int limit = getChatMessageMaxSize(azPlayer);
        String message = event.getMessage();
        if (message.length() > limit) {
            event.setMessage(message.substring(0, limit));
        }
    }

    private static int getChatMessageMaxSize(@Nullable AZPlayer azPlayer) {
        if (azPlayer == null) {
            return compat().getDefaultChatMessageMaxSize();
        }
        return Math.max(0, azPlayer.getChatMessageMaxSize());
    }

    private static boolean isAttackCooldownDisabled(@NotNull Entity entity) {
        if (!(entity instanceof Player)) {
            return false;
        }
        AZPlayer azPlayer = az((Player) entity);
        return azPlayer != null && azPlayer.isDisableAttackCooldown();
    }
}
