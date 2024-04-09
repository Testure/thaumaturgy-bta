package cookie.thaumaturgy.interfaces;

import com.mojang.nbt.CompoundTag;
import cookie.thaumaturgy.api.Dunamis;
import cookie.thaumaturgy.api.DunamisStack;

public interface IDunamisContainer {
	default int addDunamis(DunamisStack stack, boolean simulate) {
		return addDunamis(stack.getAspect(), stack.amount, simulate);
	}

	int addDunamis(Dunamis dunamis, int amount, boolean simulate);

	int takeDunamis(Dunamis dunamis, int amount, boolean simulate);

	default boolean takeDunamis(DunamisStack stack, boolean simulate) {
		return setDunamis(stack.getAspect(), stack.amount, simulate);
	}

	boolean setDunamis(Dunamis dunamis, int amount, boolean simulate);

	int getDunamis(Dunamis dunamis);

	boolean hasDunamis(Dunamis dunamis);

	DunamisStack[] getDunami();

	default int getCapacity() {
		return 8192;
	}

	default boolean isDunamisValid(Dunamis dunamis) {
		return true;
	}

	void writeToNBT(CompoundTag tag);

	void readFromNBT(CompoundTag tag);
}
