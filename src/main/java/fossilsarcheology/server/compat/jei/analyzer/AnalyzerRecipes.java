package fossilsarcheology.server.compat.jei.analyzer;

import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import fossilsarcheology.server.entity.prehistoric.TimePeriod;
import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AnalyzerRecipes {
    public static List<RecipeAnalyzer> getRecipes(){
        List<RecipeAnalyzer> list = new ArrayList<>();
        list.add(new RecipeAnalyzer(new ItemStack(FAItemRegistry.BIOFOSSIL), new ItemStack(Items.DYE, 1, 15), 50));
        list.add(new RecipeAnalyzer(new ItemStack(FAItemRegistry.BIOFOSSIL), new ItemStack(Blocks.SAND), 35));
        for(PrehistoricEntityType type : PrehistoricEntityType.getTimePeriodList(TimePeriod.MESOZOIC, TimePeriod.PALEOZOIC)){
            list.add(new RecipeAnalyzer(new ItemStack(FAItemRegistry.BIOFOSSIL), new ItemStack(type.dnaItem), 15));
        }
        return list;
    }
}
