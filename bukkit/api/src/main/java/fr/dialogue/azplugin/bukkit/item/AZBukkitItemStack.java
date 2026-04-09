package fr.dialogue.azplugin.bukkit.item;

import fr.dialogue.azplugin.bukkit.AZBukkit;
import fr.dialogue.azplugin.common.util.NotchianItemStackLike;
import java.util.Objects;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pactify.client.api.mcprotocol.model.NotchianItemStack;
import pactify.client.api.mcprotocol.model.NotchianNbtTagCompound;

/**
 * Bukkit implementation for {@link NotchianItemStack}.
 */
public interface AZBukkitItemStack extends NotchianItemStack, NotchianItemStackLike {
    /**
     * Wraps a copy of the given item stack into an {@link AZBukkitItemStack}.
     *
     * @param itemStack the item stack to copy and wrap
     * @return the wrapped item stack, or null if the given item stack is null
     */
    @Contract("null -> null; !null -> new")
    static @Nullable AZBukkitItemStack copyOf(@Nullable ItemStack itemStack) {
        if (itemStack == null) {
            return null;
        }
        return new AZBukkitItemStackImpl(AZBukkit.platform().asCraftCopy(itemStack));
    }

    /**
     * Wraps the given item stack into an {@link AZBukkitItemStack}.
     * <p>
     * The given item stack is not copied, but directly wrapped. Any changes to the given item stack will be reflected.
     * This may be unsafe depending on the usage. If you are not sure, use {@link #copyOf(ItemStack)} instead.
     *
     * @param itemStack the item stack to wrap
     * @return the wrapped item stack, or null if the given item stack is null
     */
    @Contract("null -> null; !null -> new")
    static @Nullable AZBukkitItemStack mirrorOf(@Nullable ItemStack itemStack) {
        if (itemStack == null) {
            return null;
        }
        return new AZBukkitItemStackImpl(itemStack);
    }

    /**
     * Unwraps the given {@link AZBukkitItemStack} into a {@link ItemStack}.
     * <p>
     * If the given item stack is an instance of {@link AZBukkitItemStack}, the underlying {@link ItemStack} is
     * returned. Otherwise, a new item stack is created.
     *
     * @param notchianItemStack the item stack to unwrap
     * @return the unwrapped item stack, or null if the given item stack is null
     */
    @Contract("null -> null; !null -> _")
    static @Nullable ItemStack mirrorBukkitItemStack(@Nullable NotchianItemStackLike notchianItemStack) {
        NotchianItemStack that = NotchianItemStackLike.unbox(notchianItemStack);
        if (that == null) {
            return null;
        }
        if (that instanceof AZBukkitItemStack) {
            return ((AZBukkitItemStack) that).getBukkitItemStack();
        }
        return AZBukkit.platform().createItemStack(that.getItemId(), that.getCount(), that.getDamage(), that.getTag());
    }

    /**
     * Gets the bukkit item stack wrapped by this instance.
     *
     * @return the bukkit item stack
     */
    @NotNull
    ItemStack getBukkitItemStack();

    @Override
    default int getItemId() {
        return getBukkitItemStack().getTypeId();
    }

    @Override
    default int getCount() {
        return getBukkitItemStack().getAmount();
    }

    @Override
    default int getDamage() {
        return getBukkitItemStack().getDurability();
    }

    @Override
    @Nullable
    default NotchianNbtTagCompound getTag() {
        return AZBukkit.platform().getItemStackTag(getBukkitItemStack());
    }

    @Override
    default @NotNull AZBukkitItemStack shallowClone() {
        return mirrorOf(getBukkitItemStack());
    }

    @Override
    default @NotNull AZBukkitItemStack deepClone() {
        return copyOf(getBukkitItemStack());
    }

    @Override
    default AZBukkitItemStack asNotchianItemStack() {
        return this;
    }

    static String toString(@Nullable AZBukkitItemStack item) {
        if (item == null) {
            return "null";
        }
        ItemStack itemStack = item.getBukkitItemStack();
        NotchianNbtTagCompound tag = AZBukkit.platform().getItemStackTag(itemStack);
        return (
            "AZBukkitItemStack[" +
            itemStack.getTypeId() +
            (":" + itemStack.getDurability()) +
            ("*" + itemStack.getAmount()) +
            (tag == null ? "" : " " + tag) +
            "]"
        );
    }

    static boolean equals(@Nullable AZBukkitItemStack a, @Nullable Object b) {
        if (a == b) {
            return true;
        }
        if (a == null || !(b instanceof AZBukkitItemStack)) {
            return false;
        }
        AZBukkitItemStack that = (AZBukkitItemStack) b;
        return Objects.equals(a.getBukkitItemStack(), that.getBukkitItemStack());
    }

    static int hashCode(@Nullable AZBukkitItemStack item) {
        return (item == null) ? 0 : Objects.hashCode(item.getBukkitItemStack());
    }
}
