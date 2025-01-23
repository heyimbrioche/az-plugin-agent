package fr.nathan818.azplugin.common.gui;

import fr.nathan818.azplugin.common.AZClient;
import fr.nathan818.azplugin.common.AZColors;
import fr.nathan818.azplugin.common.util.NotchianChatComponentLike;
import java.util.Map;
import java.util.UUID;
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

/**
 * Hint for using personalized chat behaviors.
 * <p>
 * The chat behaviors are checked when the player types a message in the chat. All behaviors are checked in order of
 * priority and the first one that matches is selected.
 * <p>
 * When a behavior is selected, it's message is displayed above the chat input, and the "tag"-part is highlighted with a
 * specific color.
 *
 * @see AZClient#setChatBehavior(UUID, AZChatBehavior)
 * @see AZClient#setChatBehaviors(Map)
 * @see AZClient#setChatBehaviors(Iterable)
 * @see AZClient#removeChatBehaviors()
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public final class AZChatBehavior {

    /**
     * The pattern to detect the personalized chat behavior.
     * <p>
     * Match group 1 identifies the "tag"-part of the message, and is colored with the
     * {@linkplain #getTagColorARGB() tag color}.
     */
    private final @NonNull PLSPRegex pattern;

    /**
     * The hint message displayed above the chat input when the pattern is detected.
     * <p>
     * All match groups can be used in the message (using {@code $1}, {@code $2}, etc.).
     */
    private final @NonNull NotchianChatComponent message;

    /**
     * The color of the "tag" part of the message.
     */
    private final int tagColorARGB;

    /**
     * The priority of the chat behavior.
     * <p>
     * Lower values are checked first.
     */
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
            this.message = NotchianChatComponentLike.unboxNonNull(message);
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
