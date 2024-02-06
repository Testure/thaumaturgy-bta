package cookie.thaumaturgy.recipe;

import net.minecraft.core.block.Block;
import net.minecraft.core.data.DataLoader;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.util.RecipeEntrypoint;

import static cookie.thaumaturgy.Thaumaturgy.MOD_ID;

public class ThaumRecipes implements RecipeEntrypoint {
	public static final RecipeNamespace THAUMATURGY = new RecipeNamespace();
	public static final RecipeGroup<RecipeEntryCrafting<?,?>> WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));

	@Override
	public void onRecipesReady() {
		THAUMATURGY.register("workbench", WORKBENCH);
		Registries.RECIPES.register(MOD_ID, THAUMATURGY);
		DataLoader.loadRecipes("/assets/thaumaturgy/recipes/workbench/items.json");
	}
}
