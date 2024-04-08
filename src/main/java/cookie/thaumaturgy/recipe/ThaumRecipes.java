package cookie.thaumaturgy.recipe;

import cookie.thaumaturgy.item.ThaumItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.DataLoader;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.RecipeEntrypoint;

import static cookie.thaumaturgy.Thaumaturgy.MOD_ID;

public class ThaumRecipes implements RecipeEntrypoint {
	@Override
	public void onRecipesReady() {
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(" 1 ", "1 1")
			.addInput('1', Item.ingotIron)
			.create("iron_wand_cap", new ItemStack(ThaumItems.WAND_CAP_IRON, 2));

		RecipeBuilder.Shaped(MOD_ID)
			.setShape("  1", " 2 ", "1  ")
			.addInput('1', ThaumItems.WAND_CAP_IRON)
			.addInput('2', Item.stick)
			.create("iron_capped_wooden_wand", new ItemStack(ThaumItems.WAND_BASIC));
	}

	@Override
	public void initNamespaces() {

	}
}
