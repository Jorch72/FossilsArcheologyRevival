package fossilsarcheology.server.compat.jei.culture;

import fossilsarcheology.server.compat.jei.FAJEIPlugin;
import fossilsarcheology.server.compat.jei.analyzer.AnalyzerRecipeWrapper;
import fossilsarcheology.server.compat.jei.analyzer.RecipeAnalyzer;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class CultivateRecipeHandler implements IRecipeHandler<CultivateRecipeWrapper> {

    @Override
    public Class getRecipeClass() {
        return RecipeCultivate.class;
    }

    @Override
    public String getRecipeCategoryUid(CultivateRecipeWrapper recipe) {
        return FAJEIPlugin.CULTURE_VAT_UID;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(CultivateRecipeWrapper recipe) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(CultivateRecipeWrapper recipe) {
        return true;
    }
}
