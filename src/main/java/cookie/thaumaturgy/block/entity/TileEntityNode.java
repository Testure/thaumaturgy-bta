package cookie.thaumaturgy.block.entity;

import com.mojang.nbt.CompoundTag;
import cookie.thaumaturgy.Thaumaturgy;
import cookie.thaumaturgy.api.Aspect;
import cookie.thaumaturgy.api.AspectStack;
import cookie.thaumaturgy.interfaces.IAspectContainer;
import net.minecraft.core.block.entity.TileEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TileEntityNode extends TileEntity implements IAspectContainer {
	private int regenRate;
	private int particleRate;
	private final Map<Aspect, Integer> aspects = new HashMap<>();
	public static final String[] particles = new String[]{
		"bubble",
		"splash",
		"explode",
		"flame",
		"bigsmoke",
		"portal"
	};

	@Override
	public int addAspect(Aspect aspect, int amount, boolean simulate) {
		if (!isAspectValid(aspect)) return 0;
		int current = getAspect(aspect);
		if (current < getCapacity()) {
			int result = Math.min(current + amount, getCapacity());
			int diff = result - (current + amount);
			int added = amount - diff;
			if (!simulate) aspects.put(aspect, result);
			return added;
		}
		return 0;
	}

	@Override
	public int takeAspect(Aspect aspect, int amount, boolean simulate) {
		if (!hasAspect(aspect)) return 0;
		int current = getAspect(aspect);
		if (current > 0) {
			int result = Math.max(current - amount, 0);
			int diff = result - (current - amount);
			int removed = amount - diff;
			if (!simulate) aspects.put(aspect, result);
			return removed;
		}
		return 0;
	}

	@Override
	public boolean setAspect(Aspect aspect, int amount, boolean simulate) {
		if (!isAspectValid(aspect)) return false;
		aspects.put(aspect, Math.min(amount, getCapacity()));
		return true;
	}

	@Override
	public int getAspect(Aspect aspect) {
		return aspects.get(aspect) != null ? aspects.get(aspect) : 0;
	}

	@Override
	public boolean hasAspect(Aspect aspect) {
		return getAspect(aspect) > 0;
	}

	@Override
	public AspectStack[] getAspects() {
		AspectStack[] stacks = new AspectStack[aspects.size()];
		int i = 0;
		for (Aspect aspect : aspects.keySet()) {
			stacks[i++] = new AspectStack(aspect, aspects.get(aspect));
		}
		return stacks;
	}

	public TileEntityNode() {

	}

	@Override
	public void tick() {
		if (worldObj != null && !worldObj.isClientSide) {
			// Regenerate the available mana if it's below 32 and above 0.
			if (regenRate++ >= 200) {
				regenRate = 0;
				for (Aspect aspect : aspects.keySet()) {
					int amount = aspects.get(aspect);
					if (amount > 0 && amount < 32) addAspect(new AspectStack(aspect, 1), false);
				}
			}

			if (aspects.isEmpty()) {
				for (Aspect aspect : Thaumaturgy.ASPECTS) {
					if (worldObj.rand.nextInt(5) == 0) {
						setAspect(new AspectStack(aspect, worldObj.rand.nextInt(4) + 1), false);
					}
				}
			}

			// TODO - find or create more particles!
			// This spawns 5 of the corresponding particles for each mana type.
			if (particleRate++ >= 100) {
				particleRate = 0;
				for (int i = 0; i < 5; i++) {
					double randX = x + worldObj.rand.nextDouble();
					double randY = y + worldObj.rand.nextDouble();
					double randZ = z + worldObj.rand.nextDouble();
					for (int a = 0; a < Thaumaturgy.ASPECTS.size(); a++) {
						if (hasAspect(Thaumaturgy.ASPECTS.get(a))) {
							worldObj.spawnParticle(a < particles.length ? particles[a] : "flame", randX, randY, randZ, 0, 0, 0);
						}
					}
				}
			}
		}
	}

	@Override
	public void writeToNBT(CompoundTag tag) {
		super.writeToNBT(tag);
		CompoundTag aspects = new CompoundTag();
		for (Aspect aspect : this.aspects.keySet()) {
			aspects.putInt(aspect.getName(), getAspect(aspect));
		}
	}

	@Override
	public void readFromNBT(CompoundTag tag) {
		super.readFromNBT(tag);
		this.aspects.clear();
		CompoundTag aspects = tag.getCompound("aspects");
		for (Aspect aspect : Thaumaturgy.ASPECTS) {
			setAspect(aspect, aspects.getInteger(aspect.getName()), false);
		}
	}
}
