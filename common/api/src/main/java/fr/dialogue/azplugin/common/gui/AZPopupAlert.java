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
 * A popup that informs the user of something.
 * <p>
 * Composed of:
 * <ul>
 * <li>A description message</li>
 * <li>OK button</li>
 * </ul>
 *
 * @see AZClient#openPopup(AZPopupAlert)
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@ToString
public final class AZPopupAlert {

    /**
     * The description message, displayed above the button.
     */
    private final @NonNull NotchianChatComponent description;

    /**
     * The ClickEvent triggered when the user closes the popup or clicks on the OK button.
     * <p>
     * You are guaranteed that this event will ALWAYS be triggered.
     * <p>
     * <b>IMPORTANT:</b> Only the root component ClickEvent is used, children and everything else is ignored.
     */
    private final @Nullable NotchianChatComponent closeEvent;

    /**
     * Creates a new alert popup with the specified description.
     *
     * @param description the description message
     * @return the new alert popup
     * @az.equivalent {@code builder().description(description).build()}
     */
    public static AZPopupAlert build(@NotNull NotchianChatComponentLike description) {
        return new AZPopupAlert(NotchianChatComponentLike.unboxNonNull(description), null);
    }

    /**
     * Creates a new alert popup with the specified description and closeEvent.
     *
     * @param description the description message
     * @param closeEvent  the ClickEvent triggered when the user closes the popup or clicks on the OK button
     * @return the new alert popup
     * @az.equivalent {@code builder().description(description).closeEvent(closeEvent).build()}
     */
    public static AZPopupAlert build(
        @NotNull NotchianChatComponentLike description,
        @Nullable NotchianChatComponentLike closeEvent
    ) {
        return new AZPopupAlert(
            NotchianChatComponentLike.unboxNonNull(description),
            NotchianChatComponentLike.unbox(closeEvent)
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

        private Builder closeEvent(@Nullable NotchianChatComponent closeEvent) {
            this.closeEvent = closeEvent;
            return this;
        }

        public Builder closeEvent(@Nullable NotchianChatComponentLike closeEvent) {
            this.closeEvent = NotchianChatComponentLike.unbox(closeEvent);
            return this;
        }
    }
}
