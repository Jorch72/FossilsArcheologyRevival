package fossilsarcheology.server.compat.jei;

import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.compat.jei.analyzer.*;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import fossilsarcheology.server.entity.prehistoric.TimePeriod;
import fossilsarcheology.server.item.FAItemRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@JEIPlugin
public class FAJEIPlugin implements IModPlugin {

    public static final String ANALYZER_UID = "fossil.analyzer";

    public void register(IModRegistry registry) {
        registry.addRecipes(AnalyzerRecipes.getRecipes(), ANALYZER_UID);
        registry.addRecipeHandlers(new AnalyzerRecipeHandler());
        registry.handleRecipes(RecipeAnalyzer.class, new AnalyzerFactory(), ANALYZER_UID);
        registry.addRecipeCategoryCraftingItem(new ItemStack(FABlockRegistry.ANALYZER), ANALYZER_UID);
    }

    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new AnalyzerRecipeCatagory());
    }

    public class AnalyzerFactory implements IRecipeWrapperFactory<RecipeAnalyzer> {
        @Override
        public IRecipeWrapper getRecipeWrapper(RecipeAnalyzer recipe) {
            return new AnalyzerRecipeWrapper(recipe);
        }
    }
}
