package fossilsarcheology.server.compat.jei.worktable;

import fossilsarcheology.server.compat.jei.culture.RecipeCultivate;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class WorktableRecipeWrapper implements IRecipeWrapper {

    private RecipeWorktable recipeWorktable;

    public WorktableRecipeWrapper(RecipeWorktable recipeWorktable){
       this.recipeWorktable = recipeWorktable;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        List<ItemStack> list = new ArrayList<>();
        list.add(recipeWorktable.getInput());
        list.add(recipeWorktable.getFuel());
        ingredients.setInputs(ItemStack.class, list);
        ingredients.setOutput(ItemStack.class,recipeWorktable.getOutput());
    }

    public RecipeWorktable getRecipeWorktable() {
        return recipeWorktable;
    }
}
