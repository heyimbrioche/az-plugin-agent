package fr.nathan818.azplugin.common;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for colors.
 * <p>
 * Colors are represented as ARGB integers. So the best way to represent a color in java source code is to use the
 * hexadecimal format: {@code 0xAARRGGBB} (where {@code AA} is the alpha, {@code RR} is the red, {@code GG} is the green
 * and {@code BB} is the blue). Example: {@code 0xFF00FF00} is a green color with no transparency.
 */
@UtilityClass
public class AZColors {

    /**
     * Convert a color from RGB to ARGB.
     * <p>
     * The alpha is set to 255 (no transparency).
     *
     * @param colorRGB the color in RGB format ({@code 0xRRGGBB}).
     * @return the color in ARGB format ({@code 0xAARRGGBB}).
     * @az.equivalent {@code 0xFF000000 | colorRGB}
     */
    public static int ofRGB(int colorRGB) {
        return 0xFF000000 | colorRGB;
    }

    /**
     * Returns an ARGB color from the given values.
     * <p>
     * The alpha is set to 255 (no transparency).
     *
     * @param red   the red component (0-255).
     * @param green the green component (0-255).
     * @param blue  the blue component (0-255).
     * @return the color in ARGB format ({@code 0xAARRGGBB}).
     */
    public static int ofRGB(int red, int green, int blue) {
        return ofRGB(red, green, blue, 255);
    }

    /**
     * Returns an ARGB color from the given values.
     *
     * @param red   the red component (0-255).
     * @param green the green component (0-255).
     * @param blue  the blue component (0-255).
     * @param alpha the alpha component (0-255).
     * @return the color in ARGB format ({@code 0xAARRGGBB}).
     */
    public static int ofRGB(int red, int green, int blue, int alpha) {
        return (alpha << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | (blue & 0xFF);
    }

    /**
     * Returns the alpha component of the given color.
     *
     * @param colorARGB the ARGB color.
     * @return the alpha component (0-255).
     */
    public static int getAlpha(int colorARGB) {
        return (colorARGB >> 24) & 0xFF;
    }

    /**
     * Returns the red component of the given color.
     *
     * @param colorARGB the ARGB color.
     * @return the red component (0-255).
     */
    public static int getRed(int colorARGB) {
        return (colorARGB >> 16) & 0xFF;
    }

    /**
     * Returns the green component of the given color.
     *
     * @param colorARGB the ARGB color.
     * @return the green component (0-255).
     */
    public static int getGreen(int colorARGB) {
        return (colorARGB >> 8) & 0xFF;
    }

    /**
     * Returns the blue component of the given color.
     *
     * @param colorARGB the ARGB color.
     * @return the blue component (0-255).
     */
    public static int getBlue(int colorARGB) {
        return colorARGB & 0xFF;
    }

    public static int parseHexString(String hex) {
        if (hex.length() == 7 && hex.charAt(0) == '#') {
            int rgb = parseHexValue(hex, 1, 7);
            return 0xFF000000 | rgb;
        }
        if (hex.length() == 9 && hex.charAt(0) == '#') {
            int rgb = parseHexValue(hex, 1, 7);
            int alpha = parseHexValue(hex, 7, 9);
            return (alpha << 24) | rgb;
        }
        throw new IllegalArgumentException("Invalid hex color: \"" + hex + "\"");
    }

    private static int parseHexValue(String str, int start, int end) {
        int value = 0;
        for (int i = start; i < end; i++) {
            int c = str.charAt(i);
            value <<= 4;
            if (c >= '0' && c <= '9') {
                value |= (c - (int) '0');
            } else if (c >= 'A' && c <= 'F') {
                value |= (c - (int) 'A' + 10);
            } else if (c >= 'a' && c <= 'f') {
                value |= (c - (int) 'a' + 10);
            } else {
                throw new IllegalArgumentException(
                    "Invalid hex character: '" + ((char) c) + "' (at index " + i + " in \"" + str + "\")"
                );
            }
        }
        return value;
    }

    public static @NotNull String toHexString(int colorARGB) {
        int alpha = getAlpha(colorARGB);
        if (alpha == 0xFF) {
            return String.format("#%06X", colorARGB & 0xFFFFFF);
        } else {
            return String.format("#%06X%02X", colorARGB & 0xFFFFFF, alpha);
        }
    }
}
