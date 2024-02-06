package cookie.thaumaturgy.block;

import cookie.thaumaturgy.block.entity.TileEntityNode;
import net.minecraft.core.block.Block;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.EntityHelper;

import static cookie.thaumaturgy.Thaumaturgy.MOD_ID;

public class ThaumBlocks {
	private static int baseID = 1575;
	public static Block NODE;

	public static void initializeTileEntities() {
		EntityHelper.Core.createTileEntity(TileEntityNode.class, "ThaumaturgyNode");
	}

	public static void initializeBlocks() {
		NODE = new BlockBuilder(MOD_ID)
			.setTextures("node.png")
			.setHardness(10.0F)
			.setResistance(10.0F)
			.build(new BlockNode("node", baseID++))
			.withLightEmission(0.75F);
	}
}
