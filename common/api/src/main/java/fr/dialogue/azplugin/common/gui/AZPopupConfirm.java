package fr.dialogue.azplugin.common.gui;

import fr.dialogue.azplugin.common.AZClient;
import fr.dialogue.azplugin.common.util.NotchianChatComponentLike;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pactify.client.api.mcprotocol.model.NotchianChatComponent;

/**
 * A popup that requires the user to confirm an action.
 * <p>
 * Composed of:
 * <ul>
 * <li>A description message</li>
 * <li>Two buttons: OK and Cancel</li>
 * </ul>
 *
 * @see AZClient#openPopup(AZPopupConfirm)
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@ToString
public final class AZPopupConfirm {

    /**
     * The description message, displayed above the buttons.
     */
    private final @NonNull NotchianChatComponent description;

    /**
     * The ClickEvent triggered when the user clicks on the OK button.
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
     * Creates a new confirmation popup with the specified description and okEvent.
     *
     * @param description the description message
     * @param okEvent     the ClickEvent triggered when the user clicks on the OK button
     * @return the new confirmation popup
     * @az.equivalent {@code builder().description(description).okEvent(okEvent).build()}
     */
    public static AZPopupConfirm build(
        @NotNull NotchianChatComponentLike description,
        @Nullable NotchianChatComponentLike okEvent
    ) {
        return new AZPopupConfirm(
            NotchianChatComponentLike.unboxNonNull(description),
            NotchianChatComponentLike.unbox(okEvent),
            null
        );
    }

    /**
     * Creates a new confirmation popup with the specified description, okEvent and cancelEvent.
     *
     * @param description the description message
     * @param okEvent     the ClickEvent triggered when the user clicks on the OK button
     * @param cancelEvent the ClickEvent triggered when the user closes the popup or clicks on the Cancel button
     * @return the new confirmation popup
     * @az.equivalent {@code builder().description(description).okEvent(okEvent).cancelEvent(cancelEvent).build()}
     */
    public static AZPopupConfirm build(
        @NotNull NotchianChatComponentLike description,
        @Nullable NotchianChatComponentLike okEvent,
        @Nullable NotchianChatComponentLike cancelEvent
    ) {
        return new AZPopupConfirm(
            NotchianChatComponentLike.unboxNonNull(description),
            NotchianChatComponentLike.unbox(okEvent),
            NotchianChatComponentLike.unbox(cancelEvent)
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
    }
}
