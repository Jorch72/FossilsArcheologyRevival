package fossilsarcheology.server.compat.jei.sifter;

import fossilsarcheology.server.compat.jei.FAJEIPlugin;
import fossilsarcheology.server.compat.jei.analyzer.AnalyzerRecipeWrapper;
import fossilsarcheology.server.compat.jei.analyzer.RecipeAnalyzer;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class SifterRecipeHandler implements IRecipeHandler<SifterRecipeWrapper> {

    @Override
    public Class getRecipeClass() {
        return RecipeSifter.class;
    }

    @Override
    public String getRecipeCategoryUid(SifterRecipeWrapper recipe) {
        return FAJEIPlugin.SIFTER_UID;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(SifterRecipeWrapper recipe) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(SifterRecipeWrapper recipe) {
        return true;
    }
}
