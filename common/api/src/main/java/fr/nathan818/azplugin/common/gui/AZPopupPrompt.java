package fr.nathan818.azplugin.common.gui;

import fr.nathan818.azplugin.common.AZClient;
import fr.nathan818.azplugin.common.util.NotchianChatComponentLike;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pactify.client.api.mcprotocol.model.NotchianChatComponent;
import pactify.client.api.plsp.model.PLSPRegex;
import pactify.client.api.plsp.model.SimplePLSPRegex;

/**
 * A prompt popuping the user to input some text.
 * <p>
 * Composed of:
 * <ul>
 * <li>A description message</li>
 * <li>An input field</li>
 * <li>Two buttons: OK and Cancel</li>
 * </ul>
 *
 * @see AZClient#openPopup(AZPopupPrompt)
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@ToString
public final class AZPopupPrompt {

    /**
     * The description message, displayed above the input field.
     */
    private final @NonNull NotchianChatComponent description;

    /**
     * The ClickEvent triggered when the user clicks on the OK button.
     * <p>
     * The input text is appended to the event's value.
     * <p>
     * <b>IMPORTANT:</b> Only the root component ClickEvent is used, children and everything else is ignored.
     */
    private final @Nullable NotchianChatComponent okEvent;

    /**
     * The ClickEvent triggered when the user closes the popup or clicks on the Cancel button.
     * <p>
     * You are guaranteed that if the {@linkplain #getOkEvent() okEvent} is not triggered, this event will ALWAYS be
     * triggered.
     * <p>
     * <b>IMPORTANT:</b> Only the root component ClickEvent is used, children and everything else is ignored.
     */
    private final @Nullable NotchianChatComponent cancelEvent;

    /**
     * The initial value of the input field.
     * <p>
     * Empty by default.
     */
    private final @Nullable String defaultValue;

    /**
     * The regular expression used to validate the input field while the user is typing.
     * <p>
     * If the expression is not matched, the typed text is ignored and the input field does not change.
     */
    private final @Nullable PLSPRegex typingRegex;

    /**
     * The regular expression used to validate the input field when the user confirms the input.
     * <p>
     * If the expression is not matched, the OK button is disabled and cannot be clicked.
     */
    private final @Nullable PLSPRegex finalRegex;

    /**
     * Whether the input field should be a password field.
     * <p>
     * If true, the input field will hide the typed characters and show dots instead.
     */
    private final boolean password;

    /**
     * Creates a new prompt popup with the specified description and okEvent.
     *
     * @param description the description message
     * @param okEvent     the ClickEvent triggered when the user clicks on the OK button
     * @return the new prompt popup
     * @az.equivalent {@code builder().description(description).okEvent(okEvent).build()}
     */
    public static AZPopupPrompt build(
        @NotNull NotchianChatComponentLike description,
        @Nullable NotchianChatComponentLike okEvent
    ) {
        return new AZPopupPrompt(
            NotchianChatComponentLike.unboxNonNull(description),
            NotchianChatComponentLike.unbox(okEvent),
            null,
            null,
            null,
            null,
            false
        );
    }

    /**
     * Creates a new prompt popup with the specified description, okEvent and cancelEvent.
     *
     * @param description the description message
     * @param okEvent     the ClickEvent triggered when the user clicks on the OK button
     * @param cancelEvent the ClickEvent triggered when the user closes the popup or clicks on the Cancel button
     * @return the new prompt popup
     * @az.equivalent {@code builder().description(description).okEvent(okEvent).cancelEvent(cancelEvent).build()}
     */
    public static AZPopupPrompt build(
        @NotNull NotchianChatComponentLike description,
        @Nullable NotchianChatComponentLike okEvent,
        @Nullable NotchianChatComponentLike cancelEvent
    ) {
        return new AZPopupPrompt(
            NotchianChatComponentLike.unboxNonNull(description),
            NotchianChatComponentLike.unbox(okEvent),
            NotchianChatComponentLike.unbox(cancelEvent),
            null,
            null,
            null,
            false
        );
    }

    public static class Builder {

        private Builder description(@NotNull NotchianChatComponent description) {
            this.description = description;
            return this;
        }

        public Builder description(@NotNull NotchianChatComponentLike description) {
            this.description = NotchianChatComponentLike.unboxNonNull(description);
            return this;
        }

        private Builder okEvent(@Nullable NotchianChatComponent okEvent) {
            this.okEvent = okEvent;
            return this;
        }

        public Builder okEvent(@Nullable NotchianChatComponentLike okEvent) {
            this.okEvent = NotchianChatComponentLike.unbox(okEvent);
            return this;
        }

        private Builder cancelEvent(@Nullable NotchianChatComponent cancelEvent) {
            this.cancelEvent = cancelEvent;
            return this;
        }

        public Builder cancelEvent(@Nullable NotchianChatComponentLike cancelEvent) {
            this.cancelEvent = NotchianChatComponentLike.unbox(cancelEvent);
            return this;
        }

        public Builder typingRegex(@Nullable PLSPRegex typingRegex) {
            this.typingRegex = typingRegex;
            return this;
        }

        public Builder typingRegexRe2j(@NotNull String re2jPattern) {
            this.typingRegex = SimplePLSPRegex.re2j(re2jPattern);
            return this;
        }

        public Builder typingRegexRe2j(@NotNull String re2jPattern, int flags) {
            this.typingRegex = SimplePLSPRegex.re2j(re2jPattern, flags);
            return this;
        }

        public Builder finalRegex(@Nullable PLSPRegex finalRegex) {
            this.finalRegex = finalRegex;
            return this;
        }

        public Builder finalRegexRe2j(@NotNull String re2jPattern) {
            this.finalRegex = SimplePLSPRegex.re2j(re2jPattern);
            return this;
        }

        public Builder finalRegexRe2j(@NotNull String re2jPattern, int flags) {
            this.finalRegex = SimplePLSPRegex.re2j(re2jPattern, flags);
            return this;
        }
    }
}
