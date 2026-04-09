package fr.dialogue.azplugin.bukkit.compat.network;

import fr.dialogue.azplugin.common.network.AZNetworkContext;
import org.jetbrains.annotations.NotNull;

public interface BlockRewriter {
    int@NotNull[] getRewriteBlockOutPalette(@NotNull AZNetworkContext ctx);
}
