package fr.dialogue.azplugin.bukkit.compat.material;

import fr.dialogue.azplugin.bukkit.compat.type.ItemData;
import fr.dialogue.azplugin.bukkit.item.ItemStackProxy;
import fr.dialogue.azplugin.common.network.AZNetworkContext;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public class ItemHandler {

    protected final @NonNull ItemDefinition definition;

    public @Nullable ItemData applyFallbackItem(@NotNull AZNetworkContext ctx, @NotNull ItemStackProxy itemStack) {
        return null;
    }

    public @Nullable ItemData revertFallbackItem(@NotNull AZNetworkContext ctx, @NotNull ItemData orig) {
        return null;
    }

    public interface Constructor<T extends ItemHandler> {
        @NotNull
        T create(@NotNull ItemDefinition definition);
    }
}
