package cookie.thaumaturgy.api;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.item.ItemStack;

public class ThaumaturgyAPI {

	public static ItemDunamisContainer getItemDunamisContainer(ItemStack stack) {
		if (!itemStackContainsDunamis(stack)) throw new NullPointerException("Attempt to get dunamis container for item that doesn't have one!");
		return new ItemDunamisContainer(stack);
	}

	public static boolean itemStackContainsDunamis(ItemStack stack, boolean checkAmount) {
		CompoundTag tag = stack.getData();
		if (tag.containsKey("dunami")) {
			if (checkAmount) return !getItemDunamisContainer(stack).isEmpty();
			return true;
		}
		return false;
	}

	public static boolean itemStackContainsDunamis(ItemStack stack) {
		return itemStackContainsDunamis(stack, false);
	}
}
