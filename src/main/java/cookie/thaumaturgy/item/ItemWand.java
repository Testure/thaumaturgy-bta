package cookie.thaumaturgy.item;

import com.mojang.nbt.CompoundTag;
import cookie.thaumaturgy.Thaumaturgy;
import cookie.thaumaturgy.api.Aspect;
import cookie.thaumaturgy.api.AspectStack;
import cookie.thaumaturgy.block.entity.TileEntityNode;
import cookie.thaumaturgy.interfaces.IAspectContainer;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import sunsetsatellite.catalyst.core.util.ICustomDescription;

import java.util.HashMap;
import java.util.Map;

public class ItemWand extends Item implements ICustomDescription, IAspectContainer {
	private final Map<Aspect, Integer> aspects = new HashMap<>();

	public ItemWand(String name, int id) {
		super(name, id);
		this.setFull3D();
		this.maxStackSize = 1;
	}

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

	@Override
	public void writeToNBT(CompoundTag tag) {
		CompoundTag aspects = new CompoundTag();
		for (Aspect aspect : this.aspects.keySet()) {
			aspects.putInt(aspect.getName(), this.aspects.get(aspect));
		}
		tag.put("aspects", aspects);
	}

	@Override
	public void readFromNBT(CompoundTag tag) {
		CompoundTag aspects = tag.getCompound("aspects");
		for (Aspect aspect : Thaumaturgy.ASPECTS) {
			if (aspects.containsKey(aspect.getName())) {
				setAspect(aspect, aspects.getInteger(aspect.getName()), false);
			}
		}
	}

	@Override
	public CompoundTag getDefaultTag() {
		CompoundTag tag = new CompoundTag();
		tag.put("aspects", new CompoundTag());
		return tag;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		if (world.getBlock(blockX, blockY, blockZ) == Block.bookshelfPlanksOak) {
			player.swingItem();

			// Spawn a 'Thaumaturgy and You' book at the bookshelf.
			EntityItem item = new EntityItem(world, blockX, blockY, blockZ, new ItemStack(ThaumItems.THAUMATURGY_AND_YOU));
			item.setPos(blockX, blockY + 0.5F, blockZ);
			world.entityJoinedWorld(item);

			// Then replace the bookshelf with air, and spawn some fancy particles.
			world.setBlockWithNotify(blockX, blockY, blockZ, 0);

			for (int i = 0; i < itemRand.nextInt(18) + 9; i++) {
				double randX = blockX + itemRand.nextDouble();
				double randY = blockY + itemRand.nextDouble();
				double randZ = blockZ + itemRand.nextDouble();
				world.spawnParticle("portal", randX, randY + 0.22, randZ, 0.0, 0.0, 0.0);
			}
			for (int i = 0; i < 3; i++) {
				double randX = blockX + itemRand.nextDouble();
				double randY = blockY + itemRand.nextDouble();
				double randZ = blockZ + itemRand.nextDouble();
				world.spawnParticle("explode", randX, randY + 0.22, randZ, 0.0, 0.1, 0.0);
			}
			return true;
		}

		// Check if the node and player aren't null. If it passes, lower the node count and raise the player's mana.
		TileEntityNode tileEntityNode = (TileEntityNode) world.getBlockTileEntity(blockX, blockY, blockZ);
		if (tileEntityNode != null && player != null) {
			for (int i = 0; i < Thaumaturgy.ASPECTS.size(); i++) {
				Aspect aspect = Thaumaturgy.ASPECTS.get(i);
				if (tileEntityNode.hasAspect(aspect)) {
					String particle = i < TileEntityNode.particles.length ? TileEntityNode.particles[i] : "flame";
					world.spawnParticle(particle, blockX, blockY, blockZ, 0, 0, 0);
					int taken = tileEntityNode.takeAspect(aspect, 2, false);
					if (taken > 0) {
						int added = addAspect(aspect, taken, false);
						if (added != taken) {
							tileEntityNode.addAspect(aspect, taken - added, false);
						}
					}
				}
			}
			CompoundTag data = itemstack.getData();
			writeToNBT(data);
			itemstack.setData(data);
			return true;
		}
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (player != null) {
			CompoundTag aspects = itemstack.getData().getCompound("aspects");
			for (Aspect aspect : Thaumaturgy.ASPECTS) {
				int amount = aspects.getInteger(aspect.getName());
				player.addChatMessage("Aspect " + aspect.getName() + ", Amount " + amount);
			}
		}
		return itemstack;
	}

	@Override
	public String getDescription(ItemStack itemStack) {
		I18n i18n = I18n.getInstance();
		return "§125 " + i18n.translateDescKey("item.thaumaturgy.wand.charge");
	}
}
