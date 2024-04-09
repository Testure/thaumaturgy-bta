package cookie.thaumaturgy.interfaces;

import com.mojang.nbt.CompoundTag;
import cookie.thaumaturgy.api.Dunamis;
import cookie.thaumaturgy.api.DunamisStack;

public interface IAspectContainer {
	default int addAspect(DunamisStack stack, boolean simulate) {
		return addAspect(stack.getAspect(), stack.amount, simulate);
	}

	int addAspect(Dunamis dunamis, int amount, boolean simulate);

	int takeAspect(Dunamis dunamis, int amount, boolean simulate);

	default boolean setAspect(DunamisStack stack, boolean simulate) {
		return setAspect(stack.getAspect(), stack.amount, simulate);
	}

	boolean setAspect(Dunamis dunamis, int amount, boolean simulate);

	int getAspect(Dunamis dunamis);

	boolean hasAspect(Dunamis dunamis);

	DunamisStack[] getAspects();

	default int getCapacity() {
		return 8192;
	}

	default boolean isAspectValid(Dunamis dunamis) {
		return true;
	}

	void writeToNBT(CompoundTag tag);

	void readFromNBT(CompoundTag tag);
}
