package cookie.thaumaturgy.item;

import cookie.thaumaturgy.Thaumaturgy;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import sunsetsatellite.catalyst.Catalyst;

public class ItemBookThaumaturgy extends Item {
	public ItemBookThaumaturgy(String name, int id) {
		super(name, id);
		this.maxStackSize = 1;
	}

	// TODO - this
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		Thaumaturgy.LOGGER.info("{} opened a Thaumamage's book!", entityplayer.username);
		return itemstack;
	}
}
