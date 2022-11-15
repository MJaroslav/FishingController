package io.github.mjaroslav.mjutils.object.game.item;

import io.github.mjaroslav.mjutils.object.DelegatingSet;
import io.github.mjaroslav.mjutils.util.game.item.UtilsItemStack;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;

import static io.github.mjaroslav.mjutils.util.game.item.UtilsItemStack.*;

/**
 * Set for ItemStacks.
 */
public class ItemStackSet extends DelegatingSet<ItemStack> {
    public ItemStackSet() {
        this(ITEM | COUNT | META | NBT);
    }

    public ItemStackSet(int params) {
        super((stack, obj) -> {
            ItemStack secondStack = null;
            if (obj instanceof ItemStack)
                secondStack = (ItemStack) obj;
            return UtilsItemStack.isEquals(stack, secondStack, params);
        }, null, new HashSet<>());
    }

    public ItemStackSet(@NotNull Collection<ItemStack> stacks, int params) {
        this(params);
        addAll(stacks);
    }
}
