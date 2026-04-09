package fr.dialogue.azplugin.bukkit;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * AZ Launcher custom items and blocks, extending {@link Material}.
 */
@UtilityClass
public class AZMaterial {

    public static final Material EMERALD_HELMET = Material.getMaterial("EMERALD_HELMET");
    public static final Material EMERALD_CHESTPLATE = Material.getMaterial("EMERALD_CHESTPLATE");
    public static final Material EMERALD_LEGGINGS = Material.getMaterial("EMERALD_LEGGINGS");
    public static final Material EMERALD_BOOTS = Material.getMaterial("EMERALD_BOOTS");
    public static final Material EMERALD_SWORD = Material.getMaterial("EMERALD_SWORD");
    public static final Material EMERALD_SPADE = Material.getMaterial("EMERALD_SPADE");
    public static final Material EMERALD_PICKAXE = Material.getMaterial("EMERALD_PICKAXE");
    public static final Material EMERALD_AXE = Material.getMaterial("EMERALD_AXE");
    public static final Material EMERALD_HOE = Material.getMaterial("EMERALD_HOE");
    /** {@code minecraft:ticket} (id 777); use {@link #TICKET_LAUNCHER_DAMAGE} for the launcher texture. */
    public static final Material TICKET = Material.getMaterial("TICKET");

    /** Item damage/meta for {@link #TICKET} matching the AZ Launcher ({@code minecraft:ticket} #777). */
    public static final short TICKET_LAUNCHER_DAMAGE = 777;

    /** {@code minecraft:playing_card} (#778); NBT {@code Pattern} via {@link AZPlayingCards}. */
    public static final Material PLAYING_CARD = Material.getMaterial("PLAYING_CARD");

    /** {@code minecraft:playing_card_deck} (#779). */
    public static final Material PLAYING_CARD_DECK = Material.getMaterial("PLAYING_CARD_DECK");

    public static final Material COLORED_PORTAL = Material.getMaterial("COLORED_PORTAL");
    public static final Material COLORED_PORTAL2 = Material.getMaterial("COLORED_PORTAL2");
    public static final Material BETTER_BARRIER = Material.getMaterial("BETTER_BARRIER");
    public static final Material BETTER_BARRIER2 = Material.getMaterial("BETTER_BARRIER2");
    public static final Material BETTER_BARRIER3 = Material.getMaterial("BETTER_BARRIER3");
    public static final Material STAINED_OBSIDIAN = Material.getMaterial("STAINED_OBSIDIAN");

    /**
     * Check if the material is an emerald armor.
     * <p>
     * Matches: {@link #EMERALD_HELMET}, {@link #EMERALD_CHESTPLATE}, {@link #EMERALD_LEGGINGS}, {@link #EMERALD_BOOTS}
     *
     * @param material the material to check
     * @return true if the material is an emerald armor
     * @az.async-safe
     */
    @Contract("null -> false")
    public static boolean isEmeraldArmor(@Nullable Material material) {
        return (
            material != null &&
            (material == EMERALD_HELMET ||
                material == EMERALD_CHESTPLATE ||
                material == EMERALD_LEGGINGS ||
                material == EMERALD_BOOTS)
        );
    }

    /**
     * Check if the material is an emerald tool.
     * <p>
     * Matches: {@link #EMERALD_SWORD}, {@link #EMERALD_SPADE}, {@link #EMERALD_PICKAXE}, {@link #EMERALD_AXE},
     * {@link #EMERALD_HOE}
     *
     * @param material the material to check
     * @return true if the material is an emerald tool
     * @az.async-safe
     */
    @Contract("null -> false")
    public static boolean isEmeraldTool(@Nullable Material material) {
        return (
            material != null &&
            (material == EMERALD_SWORD ||
                material == EMERALD_SPADE ||
                material == EMERALD_PICKAXE ||
                material == EMERALD_AXE ||
                material == EMERALD_HOE)
        );
    }

    /**
     * Check if the material is a portal.
     * <p>
     * Matches: {@link Material#PORTAL}, {@link #COLORED_PORTAL}, {@link #COLORED_PORTAL2}
     *
     * @param material the material to check
     * @return true if the material is a portal
     * @az.async-safe
     */
    @Contract("null -> false")
    public static boolean isPortal(@Nullable Material material) {
        return (
            material != null &&
            (material == Material.PORTAL || material == COLORED_PORTAL || material == COLORED_PORTAL2)
        );
    }

    /**
     * Check if the material is a colored portal.
     * <p>
     * Matches: {@link #COLORED_PORTAL}, {@link #COLORED_PORTAL2}
     *
     * @param material the material to check
     * @return true if the material is a colored portal
     * @az.async-safe
     */
    @Contract("null -> false")
    public static boolean isColoredPortal(@Nullable Material material) {
        return material != null && (material == COLORED_PORTAL || material == COLORED_PORTAL2);
    }

    /**
     * Check if the material is a barrier.
     * <p>
     * Matches: {@link Material#BARRIER}, {@link #BETTER_BARRIER}, {@link #BETTER_BARRIER2}, {@link #BETTER_BARRIER3}
     *
     * @param material the material to check
     * @return true if the material is a barrier
     * @az.async-safe
     */
    @Contract("null -> false")
    public static boolean isBarrier(@Nullable Material material) {
        return (
            material != null &&
            (material == Material.BARRIER ||
                material == BETTER_BARRIER ||
                material == BETTER_BARRIER2 ||
                material == BETTER_BARRIER3)
        );
    }

    /**
     * Check if the material is a better barrier.
     * <p>
     * Matches: {@link #BETTER_BARRIER}, {@link #BETTER_BARRIER2}, {@link #BETTER_BARRIER3}
     *
     * @param material the material to check
     * @return true if the material is a better barrier
     * @az.async-safe
     */
    @Contract("null -> false")
    public static boolean isBetterBarrier(@Nullable Material material) {
        return (
            material != null &&
            (material == BETTER_BARRIER || material == BETTER_BARRIER2 || material == BETTER_BARRIER3)
        );
    }

    /**
     * Check if the material is obsidian.
     * <p>
     * Matches: {@link Material#OBSIDIAN}, {@link #STAINED_OBSIDIAN}
     *
     * @param material the material to check
     * @return true if the material is obsidian
     * @az.async-safe
     */
    @Contract("null -> false")
    public static boolean isObsidian(@Nullable Material material) {
        return material != null && (material == Material.OBSIDIAN || material == STAINED_OBSIDIAN);
    }
}
