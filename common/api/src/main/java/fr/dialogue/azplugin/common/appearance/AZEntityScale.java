package fr.dialogue.azplugin.common.appearance;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A scale configuration for an entity.
 * <p>
 * The configuration changes the entity proportions.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public final class AZEntityScale {

    /**
     * The bounding box width ratio.
     * <p>
     * This value multiplies the entity bounding box width and depth.
     */
    @lombok.Builder.Default
    private final float bboxWidth = 1.0F;

    /**
     * The bounding box height ratio.
     * <p>
     * This value multiplies the entity bounding box height.
     */
    @lombok.Builder.Default
    private final float bboxHeight = 1.0F;

    /**
     * The render width ratio.
     * <p>
     * This value multiplies the entity model width.
     */
    @lombok.Builder.Default
    private final float renderWidth = 1.0F;

    /**
     * The render depth ratio.
     * <p>
     * This value multiplies the entity model depth.
     */
    @lombok.Builder.Default
    private final float renderDepth = 1.0F;

    /**
     * The render height ratio.
     * <p>
     * This value multiplies the entity model height.
     */
    @lombok.Builder.Default
    private final float renderHeight = 1.0F;

    /**
     * The item in hand width ratio.
     * <p>
     * This value multiplies the model width of the items in the entity's hand.
     */
    @lombok.Builder.Default
    private final float itemInHandWidth = 1.0F;

    /**
     * The item in hand depth ratio.
     * <p>
     * This value multiplies the model depth of the items in the entity's hand.
     */
    @lombok.Builder.Default
    private final float itemInHandDepth = 1.0F;

    /**
     * The item in hand height ratio.
     * <p>
     * This value multiplies the model height of the items in the entity's hand.
     */
    @lombok.Builder.Default
    private final float itemInHandHeight = 1.0F;

    /**
     * The name tags scale ratio.
     * <p>
     * This value multiplies the name tags scale.
     */
    @lombok.Builder.Default
    private final float nameTags = 1.0F;

    /**
     * Checks if all fields are equal to 1.
     *
     * @return true if all fields are equal to 1, false otherwise
     */
    public boolean isOne() {
        return (
            bboxWidth == 1.0F &&
            bboxHeight == 1.0F &&
            renderWidth == 1.0F &&
            renderDepth == 1.0F &&
            renderHeight == 1.0F &&
            itemInHandWidth == 1.0F &&
            itemInHandDepth == 1.0F &&
            itemInHandHeight == 1.0F &&
            nameTags == 1.0F
        );
    }

    public static class Builder {

        public Builder bbox(float bbox) {
            bboxWidth(bbox);
            bboxHeight(bbox);
            return this;
        }

        public Builder render(float render) {
            renderWidth(render);
            renderDepth(render);
            renderHeight(render);
            return this;
        }

        public Builder itemInHand(float itemInHand) {
            itemInHandWidth(itemInHand);
            itemInHandDepth(itemInHand);
            itemInHandHeight(itemInHand);
            return this;
        }

        public AZEntityScale build() {
            float bboxWidth = this.bboxWidth$set ? this.bboxWidth$value : 1.0F;
            float bboxHeight = this.bboxHeight$set ? this.bboxHeight$value : 1.0F;
            float renderWidth = this.renderWidth$set ? this.renderWidth$value : bboxWidth;
            float renderDepth = this.renderDepth$set ? this.renderDepth$value : bboxWidth;
            float renderHeight = this.renderHeight$set ? this.renderHeight$value : bboxHeight;
            float itemInHandWidth = this.itemInHandWidth$set ? this.itemInHandWidth$value : renderWidth;
            float itemInHandDepth = this.itemInHandDepth$set ? this.itemInHandDepth$value : renderDepth;
            float itemInHandHeight = this.itemInHandHeight$set ? this.itemInHandHeight$value : renderHeight;
            float nameTags = this.nameTags$set ? this.nameTags$value : 1.0F;
            return new AZEntityScale(
                bboxWidth,
                bboxHeight,
                renderWidth,
                renderDepth,
                renderHeight,
                itemInHandWidth,
                itemInHandDepth,
                itemInHandHeight,
                nameTags
            );
        }
    }
}
