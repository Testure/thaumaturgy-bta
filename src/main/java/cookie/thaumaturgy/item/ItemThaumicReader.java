package cookie.thaumaturgy.item;

import cookie.thaumaturgy.api.AspectStack;
import cookie.thaumaturgy.block.entity.TileEntityNode;
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
				for (AspectStack stack : tileEntityNode.getAspects()) {
					player.addChatMessage("Node " + stack.getAspect().getName() + ": " + stack.amount);
				}
			}

			return true;
		}

		return false;
	}
}
