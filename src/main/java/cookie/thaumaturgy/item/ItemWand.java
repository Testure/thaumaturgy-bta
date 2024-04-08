package cookie.thaumaturgy.item;

import cookie.thaumaturgy.block.entity.TileEntityNode;
import cookie.thaumaturgy.interfaces.IEntityManaCharge;
import cookie.thaumaturgy.interfaces.IItemManaCharge;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import sunsetsatellite.catalyst.core.util.ICustomDescription;

public class ItemWand extends Item implements ICustomDescription, IItemManaCharge {
	int charge = 0;
	int maxCharge = 25;
	public ItemWand(String name, int id) {
		super(name, id);
		this.setFull3D();
		this.maxStackSize = 1;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		if (world.getBlock(blockX, blockY, blockZ) == Block.bookshelfPlanksOak) {
			world.playSoundAtEntity(player, null, "thaumaturgy.wand", 1.0F, 1.0F);
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
		IEntityManaCharge playerMana = (IEntityManaCharge) player;
		if (tileEntityNode != null && player != null) {
			if (tileEntityNode.isAir && tileEntityNode.airCount > 0) {
				tileEntityNode.airCount--;
				world.spawnParticle("bubble", blockX, blockY, blockZ, 0, 0, 0);
				playerMana.thaumaturgy_bta$increaseAir();
			}
			if (tileEntityNode.isEarth && tileEntityNode.earthCount > 0) {
				tileEntityNode.earthCount--;
				world.spawnParticle("explode", blockX, blockY, blockZ, 0, 0, 0);
				playerMana.thaumaturgy_bta$increaseEarth();
			}
			if (tileEntityNode.isFire && tileEntityNode.fireCount > 0) {
				tileEntityNode.fireCount--;
				world.spawnParticle("flame", blockX, blockY, blockZ, 0, 0, 0);
				playerMana.thaumaturgy_bta$increaseFire();
			}
			if (tileEntityNode.isWater && tileEntityNode.waterCount > 0) {
				tileEntityNode.waterCount--;
				world.spawnParticle("splash", blockX, blockY, blockZ, 0, 0, 0);
				playerMana.thaumaturgy_bta$increaseWater();
			}
			if (tileEntityNode.isOrder && tileEntityNode.orderCount > 0) {
				tileEntityNode.orderCount--;
				world.spawnParticle("bigsmoke", blockX, blockY, blockZ, 0, 0, 0);
				playerMana.thaumaturgy_bta$increaseOrder();
			}
			if (tileEntityNode.isChaos && tileEntityNode.chaosCount > 0) {
				tileEntityNode.chaosCount--;
				world.spawnParticle("portal", blockX, blockY, blockZ, 0, 0, 0);
				playerMana.thaumaturgy_bta$increaseChaos();
			}
			return true;
		}
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		IEntityManaCharge playerMana = (IEntityManaCharge) player;
		player.addChatMessage("Player air: " + playerMana.thaumaturgy_bta$getAir());
		player.addChatMessage("Player earth: " + playerMana.thaumaturgy_bta$getEarth());
		player.addChatMessage("Player fire: " + playerMana.thaumaturgy_bta$getFire());
		player.addChatMessage("Player water: " + playerMana.thaumaturgy_bta$getWater());
		player.addChatMessage("Player order: " + playerMana.thaumaturgy_bta$getOrder());
		player.addChatMessage("Player chaos: " + playerMana.thaumaturgy_bta$getChaos());

		return itemstack;
	}

	@Override
	public String getDescription(ItemStack itemStack) {
		I18n i18n = I18n.getInstance();
		return "§125 " + i18n.translateDescKey("item.thaumaturgy.wand.charge");
	}

	@Override
	public int setCharge() {
		return this.charge--;
	}

	@Override
	public int setDiscount() {
		return -10;
	}
}
