package cookie.thaumaturgy.api;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemDunamisContainer implements IDunamisContainer {
	private final Map<Dunamis, Integer> dunami = new HashMap<>();
	private final ItemStack item;

	public ItemDunamisContainer(ItemStack stack) {
		this.item = stack;
		readFromNBT(stack.getData());
	}

	public ItemStack save() {
		CompoundTag tag = this.item.getData();
		writeToNBT(tag);
		this.item.setData(tag);
		return this.item;
	}

	public ItemStack getItemStack() {
		return save();
	}

	@Override
	public int addDunamis(Dunamis dunamis, int amount, boolean simulate) {
		if (!isDunamisValid(dunamis)) return 0;
		int current = getDunamis(dunamis);
		if (current < getCapacity()) {
			int result = Math.min(current + amount, getCapacity());
			int diff = result - (current + amount);
			int added = amount - diff;
			if (!simulate) dunami.put(dunamis, result);
			return added;
		}
		return 0;
	}

	@Override
	public int takeDunamis(Dunamis dunamis, int amount, boolean simulate) {
		if (!hasDunamis(dunamis)) return 0;
		int current = getDunamis(dunamis);
		if (current > 0) {
			int result = Math.max(current - amount, 0);
			int diff = result - (current - amount);
			int removed = amount - diff;
			if (!simulate) dunami.put(dunamis, result);
			return removed;
		}
		return 0;
	}

	@Override
	public boolean setDunamis(Dunamis dunamis, int amount, boolean simulate) {
		if (!isDunamisValid(dunamis)) return false;
		dunami.put(dunamis, Math.min(amount, getCapacity()));
		return true;
	}

	@Override
	public int getDunamis(Dunamis dunamis) {
		return dunami.get(dunamis) != null ? dunami.get(dunamis) : 0;
	}

	@Override
	public boolean hasDunamis(Dunamis dunamis) {
		return getDunamis(dunamis) > 0;
	}

	@Override
	public boolean isEmpty() {
		if (this.dunami.isEmpty()) return true;
		for (Dunamis dunamis : this.dunami.keySet()) {
			if (getDunamis(dunamis) > 0) return false;
		}
		return true;
	}

	@Override
	public DunamisStack[] getDunami() {
		DunamisStack[] stacks = new DunamisStack[dunami.size()];
		int i = 0;
		for (Dunamis dunamis : dunami.keySet()) {
			stacks[i++] = new DunamisStack(dunamis, dunami.get(dunamis));
		}
		return stacks;
	}

	@Override
	public void writeToNBT(CompoundTag tag) {
		CompoundTag dunami = new CompoundTag();
		for (Dunamis dunamis : this.dunami.keySet()) {
			dunami.putInt(dunamis.getName(), this.dunami.get(dunamis));
		}
		tag.put("dunami", dunami);
	}

	@Override
	public void readFromNBT(CompoundTag tag) {
		CompoundTag dunami = tag.getCompound("dunami");
		for (Dunamis dunamis : Dunami.DUNAMI) {
			if (dunami.containsKey(dunamis.getName())) {
				setDunamis(dunamis, dunami.getInteger(dunamis.getName()), false);
			}
		}
	}
}
