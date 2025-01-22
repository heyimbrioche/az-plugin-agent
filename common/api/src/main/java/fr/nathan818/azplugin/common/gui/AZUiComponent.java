package fr.nathan818.azplugin.common.gui;

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

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public final class AZUiComponent {

    private final @NonNull NotchianChatComponent button;

    public static AZUiComponent build(@NotNull NotchianChatComponentLike button) {
        return new AZUiComponent(NotchianChatComponentLike.convertNonNull(button));
    }

    public static class Builder {

        private Builder button(@NotNull NotchianChatComponent button) {
            this.button = button;
            return this;
        }

        public Builder button(@NotNull NotchianChatComponentLike button) {
            this.button = NotchianChatComponentLike.convertNonNull(button);
            return this;
        }
    }

    @RequiredArgsConstructor
    @Getter
    public enum Slot {
        GAMEMENU_ACHIEVEMENTS("gamemenu_achievements"),
        GAMEMENU_STATISTICS("gamemenu_statistics"),
        PLAYERINV_COSMETIC("playerinv_cosmetic"),
        PLAYERINV_BTN1("playerinv_btn1"),
        PLAYERINV_BTN2("playerinv_btn2"),
        PLAYERINV_BTN3("playerinv_btn3"),
        PLAYERINV_BTN4("playerinv_btn4"),
        PLAYERINV_BTN5("playerinv_btn5"),
        PLAYERINV_BTN6("playerinv_btn6"),
        PLAYERINV_BTN7("playerinv_btn7");

        private final String id;
    }
}
