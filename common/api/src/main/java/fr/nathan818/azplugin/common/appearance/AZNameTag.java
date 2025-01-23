package fr.nathan818.azplugin.common.appearance;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

/**
 * A name tag configuration for an entity.
 * <p>
 * The name tag is displayed above the entity's head. AZ Launcher supports three name tags per entity:
 * {@linkplain Slot#MAIN main}, {@linkplain Slot#SUP sup} and {@linkplain Slot#SUB sub}.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public final class AZNameTag {

    /**
     * The text displayed in the name tag.
     * <p>
     * This is a Minecraft legacy text (e.g. {@code "§cRed §eYellow"}).
     * <p>
     * If null or empty:
     * <ul>
     * <li>for {@linkplain Slot#MAIN main} slot, the entity name is used;</li>
     * <li>for {@linkplain Slot#SUP sup} and {@linkplain Slot#SUB sub} slots, the name tag is hidden.</li>
     * </ul>
     */
    private final @Nullable String text;

    /**
     * The rarity of the name tag.
     * <p>
     * When defined, the name tag is displayed with a colored frame around it.
     * <p>
     * If null, the default rarity is used.
     */
    private final @Nullable Rarity rarity;

    /**
     * The maximum distance at which the name tag is rendered (when the entity is not sneaking, nor pointed).
     * <p>
     * If null, the default distance is used:
     * <ul>
     * <li>{@code 64.0F} for players or entities with {@code isCustomNameVisible()} enabled;</li>
     * <li>{@code 0.0F} for other entities.</li>
     * </ul>
     */
    private final @Nullable Float viewDistance;

    /**
     * The text opacity of the name tag (when the entity is not sneaking, nor pointed).
     * <p>
     * If null, the default opacity is used: {@code 1.0F}.
     */
    private final @Nullable Float opacity;

    /**
     * The text opacity of the name tag when seen through walls (when the entity is not sneaking, nor pointed).
     * <p>
     * If null, the default opacity is used: {@code 0.125F}.
     */
    private final @Nullable Float throughWallOpacity;

    /**
     * The scale of the name tag (when the entity is not sneaking, nor pointed).
     * <p>
     * If null, the default scale is used: {@code 1.0F}.
     */
    private final @Nullable Float scale;

    /**
     * The visibility condition for other teams (when the entity is not sneaking, nor pointed).
     * <p>
     * If the condition is not met, the name tag is hidden.
     * <p>
     * If null, the default visibility is used: {@link Visibility#ALWAYS}.
     */
    private final @Nullable Visibility teamVisibility;

    /**
     * The maximum distance at which the name tag is rendered when the entity is sneaking.
     * <p>
     * If null, the default distance is used:
     * <ul>
     * <li>{@code 32.0F} for players or entities with {@code isCustomNameVisible()} enabled;</li>
     * <li>{@code 0.0F} for other entities.</li>
     * </ul>
     */
    private final @Nullable Float sneakViewDistance;

    /**
     * The text opacity of the name tag when the entity is sneaking.
     * <p>
     * If null, the default opacity is used: {@code 0.125F}.
     */
    private final @Nullable Float sneakOpacity;

    /**
     * The text opacity of the name tag when seen through walls and the entity is sneaking.
     * <p>
     * If null, the default opacity is used: {@code 0.0F}.
     */
    private final @Nullable Float sneakThroughWallOpacity;

    /**
     * The scale of the name tag when the entity is sneaking.
     * <p>
     * If null, the default scale is used: {@code 1.0F}.
     */
    private final @Nullable Float sneakScale;

    /**
     * The visibility condition for other teams when the entity is sneaking.
     * <p>
     * If the condition is not met, the name tag is hidden.
     * <p>
     * If null, the default visibility is used: {@link Visibility#ALWAYS}.
     */
    private final @Nullable Visibility sneakTeamVisibility;

    /**
     * The text opacity of the name tag when the entity is pointed.
     * <p>
     * If null, the {@linkplain #getOpacity() default opacity} or the {@linkplain #getSneakOpacity() sneak opacity} is
     * used.
     */
    private final @Nullable Float pointedOpacity;

    /**
     * The scale of the name tag when the entity is pointed.
     * <p>
     * If null, the {@linkplain #getScale() default scale} or the {@linkplain #getSneakScale() sneak scale} is used.
     */
    private final @Nullable Float pointedScale;

    /**
     * The visibility condition for other teams when the entity is pointed.
     * <p>
     * If the condition is not met, the name tag is hidden.
     * <p>
     * If null, the {@linkplain #getTeamVisibility() default visibility} or the
     * {@linkplain #getSneakTeamVisibility() sneak visibility} is used.
     */
    private final @Nullable Visibility pointedTeamVisibility;

    /**
     * Checks if all fields are null.
     *
     * @return true if all fields are null, false otherwise.
     */
    public boolean isNull() {
        return (
            text == null &&
            rarity == null &&
            viewDistance == null &&
            opacity == null &&
            throughWallOpacity == null &&
            scale == null &&
            teamVisibility == null &&
            sneakViewDistance == null &&
            sneakOpacity == null &&
            sneakThroughWallOpacity == null &&
            sneakScale == null &&
            sneakTeamVisibility == null &&
            pointedOpacity == null &&
            pointedScale == null &&
            pointedTeamVisibility == null
        );
    }

    /**
     * The visibility condition for other teams.
     */
    public enum Visibility {
        /**
         * Always visible.
         */
        ALWAYS,

        /**
         * Hidden for all players with a team (including the entity's own team).
         */
        NEVER,

        /**
         * Hidden for players from other teams.
         */
        HIDE_FOR_OTHER_TEAMS,

        /**
         * Hidden for players from the same team.
         */
        HIDE_FOR_OWN_TEAM,
    }

    /**
     * The rarity of the name tag.
     */
    public enum Rarity {
        /**
         * No rarity, vanilla name tag.
         */
        NONE,

        /**
         * Uncommon, green frame.
         */
        UNCOMMON,

        /**
         * Rare, blue frame.
         */
        RARE,

        /**
         * Epic, purple frame.
         */
        EPIC,

        /**
         * Legendary, gold frame.
         */
        LEGENDARY,

        /**
         * Mythic, red frame.
         */
        MYTHIC,
    }

    /**
     * The location of the name tag.
     */
    public enum Slot {
        /**
         * Vanilla name tag.
         */
        MAIN,

        /**
         * Above the main name tag.
         */
        SUP,

        /**
         * Below the main name tag.
         */
        SUB,
    }
}
