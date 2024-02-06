package cookie.thaumaturgy.block;

import cookie.thaumaturgy.block.entity.TileEntityNode;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;

public class BlockNode extends BlockTileEntity {
	public BlockNode(String key, int id) {
		super(key, id, Material.air);
	}

	@Override
	protected TileEntity getNewBlockEntity() {
		return new TileEntityNode();
	}

	@Override
	public boolean isSolidRender() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}
