package fossilsarcheology.server.compat.jei.culture;

import fossilsarcheology.server.block.entity.TileEntityCultivate;
import fossilsarcheology.server.compat.jei.analyzer.RecipeAnalyzer;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CultivateRecipes {

    public static List<RecipeCultivate> getRecipes() {
        List<RecipeCultivate> list = new ArrayList<>();
        for(PrehistoricEntityType type : PrehistoricEntityType.values()){
            addRecipe(list, new ItemStack(type.dnaItem), TileEntityCultivate.getCultivationOutput(new ItemStack(type.dnaItem)));
        }
        return list;
    }

    private static Item[] FUEL = new Item[]{FAItemRegistry.BIOFOSSIL, Items.PORKCHOP, Items.FISH, Items.BEEF, Items.MUTTON, Items.EGG, Items.SLIME_BALL, Items.MILK_BUCKET};

    private static void addRecipe(List<RecipeCultivate> list, ItemStack input, ItemStack output){
        for(Item item : FUEL){
            list.add(new RecipeCultivate(input, output, new ItemStack(item)));

        }

    }
}
