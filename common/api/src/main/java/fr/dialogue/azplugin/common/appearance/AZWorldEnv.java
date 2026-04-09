package fr.dialogue.azplugin.common.appearance;

import fr.dialogue.azplugin.common.AZClient;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

/**
 * A custom world environment.
 *
 * @see AZClient#setWorldEnv(AZWorldEnv)
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public final class AZWorldEnv {

    /**
     * The name of the custom world environment.
     * <p>
     * Appears as a tab in the "Custom environment" mod, after "Normal", "Nether" and "The End". If null or empty, no
     * tab is added and the default one is used (depending on the world dimension type).
     */
    private final @Nullable String name;

    /**
     * The dimension type to use for the current world.
     * <p>
     * If null, the default world type is used. Else, the world type is ignored and the specified one is used instead.
     * <p>
     * Note: The user can override this type in the "Custom environment" mod.
     */
    private final @Nullable Type type;

    public boolean isNull() {
        return (name == null || name.isEmpty()) && type == null;
    }

    /**
     * Creates a new custom world environment.
     *
     * @param name the name of the custom world environment, or null
     * @param type the dimension type to use for the current world, or null
     * @return the new custom world environment
     * @az.equivalent {@code builder().name(name).type(type).build()}
     */
    public static AZWorldEnv build(@Nullable String name, @Nullable Type type) {
        return new AZWorldEnv(name, type);
    }

    /**
     * The dimension type.
     */
    public enum Type {
        NORMAL,
        NETHER,
        THE_END,
    }
}
