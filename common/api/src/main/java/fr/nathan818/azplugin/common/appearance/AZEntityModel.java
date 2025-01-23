package fr.nathan818.azplugin.common.appearance;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;
import pactify.client.api.mcprotocol.model.NotchianNbtTagCompound;

/**
 * A model configuration for an entity.
 * <p>
 * The configuration changes the entity model. This does not affect the bounding box (a.k.a. hitbox).
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public final class AZEntityModel {

    /**
     * The default entity model.
     *
     * @see #getModelId()
     */
    public static final int MODEL_SELF = 3072;

    /**
     * A player model.
     *
     * @see #getModelId()
     */
    public static final int MODEL_PLAYER = 3073;

    /**
     * The model ID to use.
     * <p>
     * Known values are:
     * <ul>
     * <li>{@link #MODEL_SELF} for default entity model;</li>
     * <li>{@link #MODEL_PLAYER} for a player model;</li>
     * <li>A Minecraft 1.9.4 entity type ID (e.g., {@code 57} for pig).</li>
     * </ul>
     */
    @lombok.Builder.Default
    private final int modelId = MODEL_SELF;

    /**
     * Custom metadata to apply to the model.
     */
    // TODO(doc): Add a link to the model metadata wiki page.
    private final @Nullable NotchianNbtTagCompound metadata;

    /**
     * The render offset, on the X axis.
     */
    private final float offsetX;

    /**
     * The render offset, on the Y axis.
     */
    private final float offsetY;

    /**
     * The render offset, on the Z axis.
     */
    private final float offsetZ;

    /**
     * The eye height when standing.
     * <p>
     * If NaN, the default entity eye height is used.
     */
    @lombok.Builder.Default
    private final float eyeHeightStand = Float.NaN;

    /**
     * The eye height when sneaking.
     * <p>
     * If NaN, the default entity eye height is used.
     */
    @lombok.Builder.Default
    private final float eyeHeightSneak = Float.NaN;

    /**
     * The eye height when sleeping.
     * <p>
     * If NaN, the default entity eye height is used.
     */
    @lombok.Builder.Default
    private final float eyeHeightSleep = Float.NaN;

    /**
     * The eye height when using an elytra.
     * <p>
     * If NaN, the default entity eye height is used.
     */
    @lombok.Builder.Default
    private final float eyeHeightElytra = Float.NaN;

    public static class Builder {

        public Builder offset(float offsetX, float offsetY, float offsetZ) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.offsetZ = offsetZ;
            return this;
        }

        public Builder eyeHeight(float eyeHeightStand) {
            return eyeHeight(eyeHeightStand, eyeHeightStand, Float.NaN, Float.NaN);
        }

        public Builder eyeHeight(float eyeHeightStand, float eyeHeightSneak) {
            return eyeHeight(eyeHeightStand, eyeHeightSneak, Float.NaN, Float.NaN);
        }

        public Builder eyeHeight(
            float eyeHeightStand,
            float eyeHeightSneak,
            float eyeHeightSleep,
            float eyeHeightElytra
        ) {
            this.eyeHeightStand$value = eyeHeightStand;
            this.eyeHeightStand$set = true;
            this.eyeHeightSneak$value = eyeHeightSneak;
            this.eyeHeightSneak$set = true;
            this.eyeHeightSleep$value = eyeHeightSleep;
            this.eyeHeightSleep$set = true;
            this.eyeHeightElytra$value = eyeHeightElytra;
            this.eyeHeightElytra$set = true;
            return this;
        }
    }
}
