package cookie.thaumaturgy.recipe;

import cookie.thaumaturgy.item.ThaumItems;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
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
		RecipeNamespace namespace = new RecipeNamespace();
		namespace.register("workbench", Registries.RECIPES.WORKBENCH);
		Registries.RECIPES.register(MOD_ID, namespace);
	}
}
