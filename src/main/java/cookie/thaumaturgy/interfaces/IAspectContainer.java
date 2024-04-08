package cookie.thaumaturgy.interfaces;

import com.mojang.nbt.CompoundTag;
import cookie.thaumaturgy.api.Aspect;
import cookie.thaumaturgy.api.AspectStack;

public interface IAspectContainer {
	default int addAspect(AspectStack stack, boolean simulate) {
		return addAspect(stack.getAspect(), stack.amount, simulate);
	}

	int addAspect(Aspect aspect, int amount, boolean simulate);

	int takeAspect(Aspect aspect, int amount, boolean simulate);

	default boolean setAspect(AspectStack stack, boolean simulate) {
		return setAspect(stack.getAspect(), stack.amount, simulate);
	}

	boolean setAspect(Aspect aspect, int amount, boolean simulate);

	int getAspect(Aspect aspect);

	boolean hasAspect(Aspect aspect);

	AspectStack[] getAspects();

	default int getCapacity() {
		return 8192;
	}

	default boolean isAspectValid(Aspect aspect) {
		return true;
	}

	void writeToNBT(CompoundTag tag);

	void readFromNBT(CompoundTag tag);
}
