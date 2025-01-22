package fr.nathan818.azplugin.common;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class AZColors {

    public static int ofRGB(int colorRGB) {
        return 0xFF000000 | colorRGB;
    }

    public static int ofRGB(int red, int green, int blue) {
        return ofRGB(red, green, blue, 255);
    }

    public static int ofRGB(int red, int green, int blue, int alpha) {
        return (alpha << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | (blue & 0xFF);
    }

    public static int getAlpha(int colorARGB) {
        return (colorARGB >> 24) & 0xFF;
    }

    public static int getRed(int colorARGB) {
        return (colorARGB >> 16) & 0xFF;
    }

    public static int getGreen(int colorARGB) {
        return (colorARGB >> 8) & 0xFF;
    }

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
