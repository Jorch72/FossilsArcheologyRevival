package fossilsarcheology.server.compat.jei.analyzer;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RecipeAnalyzer {

    private ItemStack input;
    private ItemStack output;
    private int chance;

    public RecipeAnalyzer(ItemStack input, ItemStack output, int chance){
        this.input = input;
        this.output = output;
        this.chance = chance;
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getOutputChance() {
        return chance;
    }

    public class AnalyzerFactory implements IRecipeWrapperFactory<RecipeAnalyzer> {
        @Override
        public IRecipeWrapper getRecipeWrapper(RecipeAnalyzer recipe) {
            return new AnalyzerRecipeWrapper(recipe);
        }
    }
}
