package fossilsarcheology.server.compat.jei.sifter;

import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.compat.jei.analyzer.RecipeAnalyzer;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import fossilsarcheology.server.entity.prehistoric.TimePeriod;
import fossilsarcheology.server.item.FAItemRegistry;
import fossilsarcheology.server.item.variant.DinosaurBoneType;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SifterRecipes {

    private static Block[] SEDIMENT = new Block[]{Blocks.SAND, Blocks.DIRT, Blocks.GRAVEL, Blocks.CLAY, FABlockRegistry.VOLCANIC_ASH};

    public static List<RecipeSifter> getRecipes(){
        List<RecipeSifter> list = new ArrayList<>();
        for(Block block : SEDIMENT){
            if(block == Blocks.SAND){
                list.add(new RecipeSifter(new ItemStack(block), new ItemStack(Blocks.SAND), 25));
            }else{
                list.add(new RecipeSifter(new ItemStack(block), new ItemStack(Blocks.SAND), 20));
                list.add(new RecipeSifter(new ItemStack(block), new ItemStack(block), 5));
            }
            list.add(new RecipeSifter(new ItemStack(block), new ItemStack(FAItemRegistry.DOMINICAN_AMBER), 1));
            list.add(new RecipeSifter(new ItemStack(block), new ItemStack(FAItemRegistry.PLANT_FOSSIL), 14));
            list.add(new RecipeSifter(new ItemStack(block), new ItemStack(Items.POTATO), 15));
            list.add(new RecipeSifter(new ItemStack(block), new ItemStack(Items.CARROT), 10));
            list.add(new RecipeSifter(new ItemStack(block), new ItemStack(Items.DYE, 1, 15), 20));
            list.add(new RecipeSifter(new ItemStack(block), new ItemStack(FAItemRegistry.FERN_SEED), 10));
            list.add(new RecipeSifter(new ItemStack(block), new ItemStack(FAItemRegistry.POTTERY_SHARD), 5));
            list.add(new RecipeSifter(new ItemStack(block), new ItemStack(FAItemRegistry.BIOFOSSIL), 5));
        }
        return list;
    }
}
