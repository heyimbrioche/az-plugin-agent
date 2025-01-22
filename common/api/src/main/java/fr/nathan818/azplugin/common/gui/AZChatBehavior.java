package fr.nathan818.azplugin.common.gui;

import fr.nathan818.azplugin.common.AZColors;
import fr.nathan818.azplugin.common.util.NotchianChatComponentLike;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import pactify.client.api.mcprotocol.model.NotchianChatComponent;
import pactify.client.api.plsp.model.PLSPRegex;
import pactify.client.api.plsp.model.SimplePLSPRegex;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public final class AZChatBehavior {

    private final @NonNull PLSPRegex pattern;
    private final @NonNull NotchianChatComponent message;
    private final int tagColorARGB;
    private final short priority;

    public @NotNull String getSerializedTagColor() {
        return AZColors.toHexString(tagColorARGB);
    }

    public static class Builder {

        public Builder pattern(@NotNull PLSPRegex pattern) {
            this.pattern = pattern;
            return this;
        }

        public Builder patternRe2j(@NotNull String re2jPattern) {
            this.pattern = SimplePLSPRegex.re2j(re2jPattern);
            return this;
        }

        public Builder patternRe2j(@NotNull String re2jPattern, int flags) {
            this.pattern = SimplePLSPRegex.re2j(re2jPattern, flags);
            return this;
        }

        private Builder message(@NotNull NotchianChatComponent message) {
            this.message = message;
            return this;
        }

        public Builder message(@NotNull NotchianChatComponentLike message) {
            this.message = NotchianChatComponentLike.convertNonNull(message);
            return this;
        }

        public Builder tagColor(@NotNull String colorHex) {
            return tagColorARGB(AZColors.parseHexString(colorHex));
        }

        public Builder tagColorRGB(int red, int green, int blue) {
            return tagColorARGB(AZColors.ofRGB(red, green, blue));
        }

        public Builder tagColorRGB(int colorRGB) {
            return tagColorARGB(AZColors.ofRGB(colorRGB));
        }

        public Builder priority(short priority) {
            this.priority = priority;
            return this;
        }

        public Builder priority(int priority) {
            if (priority < Short.MIN_VALUE || priority > Short.MAX_VALUE) {
                throw new IllegalArgumentException(
                    ("priority out of range: " + priority) +
                    (" (expected: " + Short.MIN_VALUE + ".." + Short.MAX_VALUE) +
                    ")"
                );
            }
            this.priority = (short) priority;
            return this;
        }
    }
}
