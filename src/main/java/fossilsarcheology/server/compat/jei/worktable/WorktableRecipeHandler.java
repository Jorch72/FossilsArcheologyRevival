package fossilsarcheology.server.compat.jei.worktable;

import fossilsarcheology.server.compat.jei.FAJEIPlugin;
import fossilsarcheology.server.compat.jei.culture.CultivateRecipeWrapper;
import fossilsarcheology.server.compat.jei.culture.RecipeCultivate;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class WorktableRecipeHandler implements IRecipeHandler<WorktableRecipeWrapper> {

    @Override
    public Class getRecipeClass() {
        return RecipeWorktable.class;
    }

    @Override
    public String getRecipeCategoryUid(WorktableRecipeWrapper recipe) {
        return FAJEIPlugin.WORKTABLE_UID;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(WorktableRecipeWrapper recipe) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(WorktableRecipeWrapper recipe) {
        return true;
    }
}
