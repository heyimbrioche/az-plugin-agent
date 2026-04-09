package fr.dialogue.azplugin.bukkit;

import fr.dialogue.azplugin.bukkit.item.ItemStackProxy;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Création d’items cartes pour le client AZ : {@code minecraft:playing_card} avec NBT {@code Pattern}, et {@code minecraft:playing_card_deck}.
 */
@UtilityClass
public class AZPlayingCards {

    /** Clé NBT attendue par le launcher (voir spec client). */
    public static final String PATTERN_TAG = "Pattern";

    /**
     * Une carte à jouer ({@link AZMaterial#PLAYING_CARD}, damage 0) avec {@code tag.{@value #PATTERN_TAG}}.
     */
    public static @NotNull ItemStack playingCard(@NotNull String pattern) {
        ItemStack stack = new ItemStack(AZMaterial.PLAYING_CARD, 1, (short) 0);
        ItemStackProxy proxy = AZBukkit.platform().getItemStackProxy(stack);
        proxy.getTagForWrite().setString(PATTERN_TAG, pattern);
        return proxy.asItemStack();
    }

    /**
     * Jeu de cartes ({@link AZMaterial#PLAYING_CARD_DECK}), sans tag.
     */
    public static @NotNull ItemStack playingCardDeck() {
        return new ItemStack(AZMaterial.PLAYING_CARD_DECK, 1, (short) 0);
    }
}
