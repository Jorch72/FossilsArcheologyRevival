package fossilsarcheology.server.compat.jei;

import fossilsarcheology.client.gui.AnalyzerGUI;
import fossilsarcheology.client.gui.CultivateGUI;
import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.compat.jei.analyzer.*;
import fossilsarcheology.server.compat.jei.culture.*;
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
    public static final String CULTURE_VAT_UID = "fossil.culture_vat";

    public void register(IModRegistry registry) {
        registry.addRecipes(AnalyzerRecipes.getRecipes(), ANALYZER_UID);
        registry.addRecipeHandlers(new AnalyzerRecipeHandler());
        registry.handleRecipes(RecipeAnalyzer.class, new AnalyzerFactory(), ANALYZER_UID);
        registry.addRecipeCategoryCraftingItem(new ItemStack(FABlockRegistry.ANALYZER), ANALYZER_UID);
        registry.addRecipeClickArea(AnalyzerGUI.class, 79, 21, 23, 11, ANALYZER_UID);

        registry.addRecipes(CultivateRecipes.getRecipes(), CULTURE_VAT_UID);
        registry.addRecipeHandlers(new CultivateRecipeHandler());
        registry.handleRecipes(RecipeCultivate.class, new CultureVatFactory(), CULTURE_VAT_UID);
        registry.addRecipeCategoryCraftingItem(new ItemStack(FABlockRegistry.CULTIVATE_IDLE), CULTURE_VAT_UID);
        registry.addRecipeClickArea(CultivateGUI.class, 79, 20, 23, 13, CULTURE_VAT_UID);
    }

    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new AnalyzerRecipeCatagory());
        registry.addRecipeCategories(new CultivateRecipeCatagory());
    }

    public class AnalyzerFactory implements IRecipeWrapperFactory<RecipeAnalyzer> {
        @Override
        public IRecipeWrapper getRecipeWrapper(RecipeAnalyzer recipe) {
            return new AnalyzerRecipeWrapper(recipe);
        }
    }

    public class CultureVatFactory implements IRecipeWrapperFactory<RecipeCultivate> {
        @Override
        public IRecipeWrapper getRecipeWrapper(RecipeCultivate recipe) {
            return new CultivateRecipeWrapper(recipe);
        }
    }
}
