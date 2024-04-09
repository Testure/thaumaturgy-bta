package cookie.thaumaturgy.api;

import com.mojang.nbt.CompoundTag;

public interface IDunamisContainer {
	default int addDunamis(DunamisStack stack, boolean simulate) {
		return addDunamis(stack.getDunamis(), stack.amount, simulate);
	}

	int addDunamis(Dunamis dunamis, int amount, boolean simulate);

	int takeDunamis(Dunamis dunamis, int amount, boolean simulate);

	default boolean takeDunamis(DunamisStack stack, boolean simulate) {
		return setDunamis(stack.getDunamis(), stack.amount, simulate);
	}

	boolean setDunamis(Dunamis dunamis, int amount, boolean simulate);

	int getDunamis(Dunamis dunamis);

	boolean hasDunamis(Dunamis dunamis);

	DunamisStack[] getDunami();

	boolean isEmpty();

	default int getCapacity() {
		return 8192;
	}

	default boolean isDunamisValid(Dunamis dunamis) {
		return true;
	}

	void writeToNBT(CompoundTag tag);

	void readFromNBT(CompoundTag tag);
}
