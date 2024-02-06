package cookie.thaumaturgy.block.entity;

import com.mojang.nbt.CompoundTag;
import cookie.thaumaturgy.Thaumaturgy;
import net.minecraft.core.block.entity.TileEntity;

import java.util.Random;

public class TileEntityNode extends TileEntity {
	private final Random rand = new Random();
	private int regenRate;
	private int particleRate;
	public boolean isAir;
	public boolean isEarth;
	public boolean isFire;
	public boolean isWater;
	public boolean isOrder;
	public boolean isChaos;
	public int airCount;
	public int earthCount;
	public int fireCount;
	public int waterCount;
	public int orderCount;
	public int chaosCount;

	public TileEntityNode() {
		// Choose a random mana and random count for it, each one has 6 chances to get chosen.
		for (int i = 0; i < 6; i++) {
			switch (rand.nextInt(6)) {
				default: case 0:
					isAir = true;
					airCount = rand.nextInt(5) + 1;
					break;
				case 1:
					isEarth = true;
					earthCount = rand.nextInt(5) + 1;
					break;
				case 2:
					isFire = true;
					fireCount = rand.nextInt(5) + 1;
					break;
				case 3:
					isWater = true;
					waterCount = rand.nextInt(5) + 1;
					break;
				case 4:
					isOrder = true;
					orderCount = rand.nextInt(5) + 1;
					break;
				case 5:
					isChaos = true;
					chaosCount = rand.nextInt(5) + 1;
					break;
			}
		}
	}

	@Override
	public void tick() {
		// Regenerate the available mana if it's below 32 and above 0.
		if (regenRate++ >= 200) {
			regenRate = 0;
			if (isAir && airCount < 32 && airCount > 0) airCount++;
			if (isEarth && earthCount < 32 && earthCount > 0) earthCount++;
			if (isFire && fireCount < 32 && fireCount > 0) fireCount++;
			if (isWater && waterCount < 32 && waterCount > 0) waterCount++;
			if (isOrder && orderCount < 32 && orderCount > 0) orderCount++;
			if (isChaos && chaosCount < 32 && chaosCount > 0) chaosCount++;
		}

		// TODO - find or create more particles!
		// This spawns 5 of the corresponding particles for each mana type.
		if (particleRate++ >= 100) {
			particleRate = 0;
			for (int i = 0; i < 5; i++) {
				if (isAir && airCount > 0) {
					double randX = x + rand.nextDouble();
					double randY = y + rand.nextDouble();
					double randZ = z + rand.nextDouble();
					worldObj.spawnParticle("bubble", randX, randY, randZ, 0, 0, 0);
				}
				if (isEarth && earthCount > 0) {
					double randX = x + rand.nextDouble();
					double randY = y + rand.nextDouble();
					double randZ = z + rand.nextDouble();
					worldObj.spawnParticle("explode", randX, randY, randZ, 0, 0, 0);
				}
				if (isFire && fireCount > 0) {
					double randX = x + rand.nextDouble();
					double randY = y + rand.nextDouble();
					double randZ = z + rand.nextDouble();
					worldObj.spawnParticle("flame", randX, randY, randZ, 0, 0, 0);
				}
				if (isWater && waterCount > 0) {
					double randX = x + rand.nextDouble();
					double randY = y + rand.nextDouble();
					double randZ = z + rand.nextDouble();
					worldObj.spawnParticle("splash", randX, randY, randZ, 0, 0, 0);
				}
				if (isOrder && orderCount > 0) {
					double randX = x + rand.nextDouble();
					double randY = y + rand.nextDouble();
					double randZ = z + rand.nextDouble();
					worldObj.spawnParticle("bigsmoke", randX, randY, randZ, 0, 0, 0);
				}
				if (isChaos && chaosCount > 0) {
					double randX = x + rand.nextDouble();
					double randY = y + rand.nextDouble();
					double randZ = z + rand.nextDouble();
					worldObj.spawnParticle("portal", randX, randY, randZ, 0, 0, 0);
				}
			}
		}
	}

	@Override
	public void writeToNBT(CompoundTag tag) {
		super.writeToNBT(tag);
		tag.putBoolean("IsAir", isAir);
		tag.putBoolean("IsEarth", isEarth);
		tag.putBoolean("IsFire", isFire);
		tag.putBoolean("IsWater", isWater);
		tag.putBoolean("IsOrder", isOrder);
		tag.putBoolean("IsChaos", isChaos);

		tag.putInt("AirCount", airCount);
		tag.putInt("EarthCount", earthCount);
		tag.putInt("FireCount", fireCount);
		tag.putInt("WaterCount", waterCount);
		tag.putInt("OrderCount", orderCount);
		tag.putInt("ChaosCount", chaosCount);
	}

	@Override
	public void readFromNBT(CompoundTag tag) {
		super.readFromNBT(tag);
		isAir = tag.getBoolean("IsAir");
		isEarth = tag.getBoolean("IsEarth");
		isFire = tag.getBoolean("IsFire");
		isWater = tag.getBoolean("IsWater");
		isOrder = tag.getBoolean("IsOrder");
		isChaos = tag.getBoolean("IsChaos");

		airCount = tag.getInteger("AirCount");
		earthCount = tag.getInteger("EarthCount");
		fireCount = tag.getInteger("FireCount");
		waterCount = tag.getInteger("WaterCount");
		orderCount = tag.getInteger("OrderCount");
		chaosCount = tag.getInteger("ChaosCount");
	}
}
