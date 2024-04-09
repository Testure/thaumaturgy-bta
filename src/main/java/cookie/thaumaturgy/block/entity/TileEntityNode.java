package cookie.thaumaturgy.block.entity;

import com.mojang.nbt.CompoundTag;
import cookie.thaumaturgy.api.Dunami;
import cookie.thaumaturgy.api.Dunamis;
import cookie.thaumaturgy.api.DunamisStack;
import cookie.thaumaturgy.interfaces.IDunamisContainer;
import net.minecraft.core.block.entity.TileEntity;

import java.util.HashMap;
import java.util.Map;

public class TileEntityNode extends TileEntity implements IDunamisContainer {
	private int regenRate;
	private int particleRate;
	private final Map<Dunamis, Integer> aspects = new HashMap<>();
	public static final String[] particles = new String[]{
		"bubble",
		"splash",
		"explode",
		"flame",
		"bigsmoke",
		"portal"
	};

	@Override
	public int addDunamis(Dunamis dunamis, int amount, boolean simulate) {
		if (!isDunamisValid(dunamis)) return 0;
		int current = getDunamis(dunamis);
		if (current < getCapacity()) {
			int result = Math.min(current + amount, getCapacity());
			int diff = result - (current + amount);
			int added = amount - diff;
			if (!simulate) aspects.put(dunamis, result);
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
			if (!simulate) aspects.put(dunamis, result);
			return removed;
		}
		return 0;
	}

	@Override
	public boolean setDunamis(Dunamis dunamis, int amount, boolean simulate) {
		if (!isDunamisValid(dunamis)) return false;
		aspects.put(dunamis, Math.min(amount, getCapacity()));
		return true;
	}

	@Override
	public int getDunamis(Dunamis dunamis) {
		return aspects.get(dunamis) != null ? aspects.get(dunamis) : 0;
	}

	@Override
	public boolean hasDunamis(Dunamis dunamis) {
		return getDunamis(dunamis) > 0;
	}

	@Override
	public DunamisStack[] getDunami() {
		DunamisStack[] stacks = new DunamisStack[aspects.size()];
		int i = 0;
		for (Dunamis dunamis : aspects.keySet()) {
			stacks[i++] = new DunamisStack(dunamis, aspects.get(dunamis));
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
				for (Dunamis dunamis : aspects.keySet()) {
					int amount = aspects.get(dunamis);
					if (amount > 0 && amount < 32) addDunamis(new DunamisStack(dunamis, 1), false);
				}
			}

			if (aspects.isEmpty()) {
				for (Dunamis dunamis : Thaumaturgy.DUNAMI) {
					if (worldObj.rand.nextInt(5) == 0) {
						setDunamis(dunamis, worldObj.rand.nextInt(4) + 1, false);
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
					for (int a = 0; a < Thaumaturgy.DUNAMI.size(); a++) {
						if (hasDunamis(Dunami.DUNAMI.get(a))) {
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
		for (Dunamis dunamis : this.aspects.keySet()) {
			aspects.putInt(dunamis.getName(), getDunamis(dunamis));
		}
	}

	@Override
	public void readFromNBT(CompoundTag tag) {
		super.readFromNBT(tag);
		this.aspects.clear();
		CompoundTag aspects = tag.getCompound("aspects");
		for (Dunamis dunamis : Thaumaturgy.DUNAMI) {
			setDunamis(dunamis, aspects.getInteger(dunamis.getName()), false);
		}
	}
}
