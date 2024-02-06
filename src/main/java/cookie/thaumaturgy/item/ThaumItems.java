package cookie.thaumaturgy.item;

import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemHelper;

import static cookie.thaumaturgy.Thaumaturgy.MOD_ID;

public class ThaumItems {
	private static int baseID = 16575;

	public static Item WAND_CAP_IRON;
	public static Item WAND_BASIC;
	public static Item THAUMATURGY_AND_YOU;

	public static void initializeItems() {
		WAND_CAP_IRON = ItemHelper.createItem(MOD_ID,
			new Item("wandcap.iron", baseID++),
			"iron_wand_cap.png");

		WAND_BASIC = ItemHelper.createItem(MOD_ID,
			new ItemWand("wand.ironcap.wood", baseID++),
			"iron_capped_wooden_wand.png");

		THAUMATURGY_AND_YOU = ItemHelper.createItem(MOD_ID,
			new ItemBookThaumaturgy("book.thaum", baseID++),
			"book_thaum.png");
	}
}
