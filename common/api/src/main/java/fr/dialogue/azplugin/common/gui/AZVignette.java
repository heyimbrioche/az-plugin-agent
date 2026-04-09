package fr.dialogue.azplugin.common.gui;

import fr.dialogue.azplugin.common.AZClient;
import fr.dialogue.azplugin.common.AZColors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A custom vignette.
 * <p>
 * The vignette is the circular shadow around the player's screen, usually black or darkened.
 *
 * @see AZClient#setVignette(AZVignette)
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public final class AZVignette {

    /**
     * The {@linkplain AZColors color} of the vignette.
     * <p>
     * Alpha is always 255.
     */
    private final int colorARGB;

    /**
     * The red component of the color (0-255).
     *
     * @az.equivalent {@code AZColors.getRed(getColorARGB())}
     * @see #getColorARGB()
     */
    public int getRed() {
        return AZColors.getRed(colorARGB);
    }

    /**
     * The green component of the color (0-255).
     *
     * @az.equivalent {@code AZColors.getGreen(getColorARGB())}
     * @see #getColorARGB()
     */
    public int getGreen() {
        return AZColors.getGreen(colorARGB);
    }

    /**
     * The blue component of the color (0-255).
     *
     * @az.equivalent {@code AZColors.getBlue(getColorARGB())}
     * @see #getColorARGB()
     */
    public int getBlue() {
        return AZColors.getBlue(colorARGB);
    }

    /**
     * The alpha component of the color as a float (0.0-1.0).
     *
     * @az.equivalent {@code getRed() / 255.0F}
     * @see #getColorARGB()
     */
    public float getRedFloat() {
        return getRed() / 255.0F;
    }

    /**
     * The alpha component of the color as a float (0.0-1.0).
     *
     * @az.equivalent {@code getGreen() / 255.0F}
     * @see #getColorARGB()
     */
    public float getGreenFloat() {
        return getGreen() / 255.0F;
    }

    /**
     * The alpha component of the color as a float (0.0-1.0).
     *
     * @az.equivalent {@code getBlue() / 255.0F}
     * @see #getColorARGB()
     */
    public float getBlueFloat() {
        return getBlue() / 255.0F;
    }

    /**
     * Create a new vignette with the given color.
     *
     * @param colorRGB the {@linkplain AZColors#ofRGB(int) color in RGB format}
     * @return the new vignette
     * @az.equivalent {@code builder().colorRGB(colorRGB).build()}
     */
    public static AZVignette buildRGB(int colorRGB) {
        return new AZVignette(AZColors.ofRGB(colorRGB));
    }

    /**
     * Create a new vignette with the given color.
     *
     * @param red   the red component (0-255).
     * @param green the green component (0-255).
     * @param blue  the blue component (0-255).
     * @return the new vignette
     * @az.equivalent {@code builder().colorRGB(red, green, blue).build()}
     */
    public static AZVignette buildRGB(int red, int green, int blue) {
        return new AZVignette(AZColors.ofRGB(red, green, blue));
    }

    public static class Builder {

        public Builder colorARGB(int colorARGB) {
            this.colorARGB = colorARGB | 0xFF000000;
            return this;
        }

        public Builder colorRGB(int red, int green, int blue) {
            colorARGB = AZColors.ofRGB(red, green, blue);
            return this;
        }

        public Builder colorRGB(int colorRGB) {
            this.colorARGB = AZColors.ofRGB(colorRGB);
            return this;
        }
    }
}
