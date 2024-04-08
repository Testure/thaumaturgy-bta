package cookie.thaumaturgy.item;

import cookie.thaumaturgy.block.entity.TileEntityNode;
import cookie.thaumaturgy.interfaces.IEntityManaCharge;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class ItemThaumicReader extends Item {
	public ItemThaumicReader(String name, int id) {
		super(name, id);
	}

	// Ideally this needs to read the "thaumic values" of blocks, items, and entities.
	// But for now it just prints the node's thaum count.

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		if (!world.isClientSide) {
			TileEntityNode tileEntityNode = (TileEntityNode) world.getBlockTileEntity(blockX, blockY, blockZ);

			if (tileEntityNode != null && player != null) {
				player.addChatMessage("Node air: " + tileEntityNode.airCount);
				player.addChatMessage("Node earth: " + tileEntityNode.earthCount);
				player.addChatMessage("Node fire: " + tileEntityNode.fireCount);
				player.addChatMessage("Node water: " + tileEntityNode.waterCount);
				player.addChatMessage("Node order: " + tileEntityNode.orderCount);
				player.addChatMessage("Node chaos: " + tileEntityNode.chaosCount);
			}

			return true;
		}

		return false;
	}
}
