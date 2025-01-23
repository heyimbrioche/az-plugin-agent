package fr.nathan818.azplugin.common.gui;

import fr.nathan818.azplugin.common.AZClient;
import fr.nathan818.azplugin.common.util.NotchianChatComponentLike;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import pactify.client.api.mcprotocol.model.NotchianChatComponent;
import pactify.client.api.plsp.packet.client.PLSPPacketUiComponent;

/**
 * A component of the AZ Launcher UI.
 *
 * @see AZClient#setUiComponent(Slot, AZUiComponent)
 * @see AZClient#setUiComponents(Map)
 * @see AZClient#setUiComponents(Iterable)
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public final class AZUiComponent {

    /**
     * The content, or null to reset it.
     */
    private final @NonNull NotchianChatComponent button;

    /**
     * Creates a new component with the specified content.
     *
     * @param button the content, or null to reset it
     * @return the new component
     * @az.equivalent {@code builder().button(button).build()}
     */
    public static AZUiComponent build(@NotNull NotchianChatComponentLike button) {
        return new AZUiComponent(NotchianChatComponentLike.unboxNonNull(button));
    }

    public static class Builder {

        private Builder button(@NotNull NotchianChatComponent button) {
            this.button = button;
            return this;
        }

        public Builder button(@NotNull NotchianChatComponentLike button) {
            this.button = NotchianChatComponentLike.unboxNonNull(button);
            return this;
        }
    }

    /**
     * Represents the location of a component in the AZ Launcher UI.
     */
    @RequiredArgsConstructor
    @Getter
    public enum Slot {
        /**
         * The "Achievements" button in the game menu (when pressing escape in-game).
         */
        GAMEMENU_ACHIEVEMENTS("gamemenu_achievements"),

        /**
         * The "Statistics" button in the game menu (when pressing escape in-game).
         */
        GAMEMENU_STATISTICS("gamemenu_statistics"),

        /**
         * The cosmetic button in the player inventory.
         */
        PLAYERINV_COSMETIC("playerinv_cosmetic"),

        /**
         * A custom button at the right of the player inventory (1/7).
         */
        PLAYERINV_BTN1("playerinv_btn1"),

        /**
         * A custom button at the right of the player inventory (2/7).
         */
        PLAYERINV_BTN2("playerinv_btn2"),

        /**
         * A custom button at the right of the player inventory (3/7).
         */
        PLAYERINV_BTN3("playerinv_btn3"),

        /**
         * A custom button at the right of the player inventory (4/7).
         */
        PLAYERINV_BTN4("playerinv_btn4"),

        /**
         * A custom button at the right of the player inventory (5/7).
         */
        PLAYERINV_BTN5("playerinv_btn5"),

        /**
         * A custom button at the right of the player inventory (6/7).
         */
        PLAYERINV_BTN6("playerinv_btn6"),

        /**
         * A custom button at the right of the player inventory (7/7).
         */
        PLAYERINV_BTN7("playerinv_btn7");

        /**
         * The {@linkplain PLSPPacketUiComponent#setSlot(String) PLSP ID} of the slot.
         */
        private final String id;
    }
}
