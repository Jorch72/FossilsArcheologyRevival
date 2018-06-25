package fossilsarcheology.server.recipe;

import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.entity.prehistoric.MobType;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FARecipeRegistry {

	public static String[] dyes = {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White"};

	private FARecipeRegistry() {
	}

	public static void blocks() {

		//Fixme: Bone armor does not show up as a valid recipe in JEI
		FAItemRegistry.ANCIENT_HELMET_MATERIAL.setRepairItem(new ItemStack(FAItemRegistry.SCARAB_GEM));
		FAItemRegistry.SCARAB_MATERIAL.setRepairItem(new ItemStack(FAItemRegistry.SCARAB_GEM));
		FAItemRegistry.BONE.setRepairItem(new ItemStack(Items.BONE));

		GameRegistry.addSmelting(FABlockRegistry.DENSE_SAND, new ItemStack(FABlockRegistry.STRONG_GLASS), 3.0F);
		GameRegistry.addSmelting(FAItemRegistry.RAW_CHICKEN_SOUP, new ItemStack(FAItemRegistry.COOKED_CHICKEN_SOUP), 1.0F);
		GameRegistry.addSmelting(Items.EGG, new ItemStack(FAItemRegistry.COOKED_EGG), 1.0F);
		for (int i = 0; i < PrehistoricEntityType.values().length; i++) {
			if (PrehistoricEntityType.values()[i].mobType == MobType.DINOSAUR && !PrehistoricEntityType.values()[i].isAquatic()) {
				GameRegistry.addSmelting(PrehistoricEntityType.values()[i].eggItem, new ItemStack(FAItemRegistry.COOKED_EGG), 1.0F);
			}
			if (PrehistoricEntityType.values()[i].mobType == MobType.BIRD) {
				GameRegistry.addSmelting(PrehistoricEntityType.values()[i].birdEggItem, new ItemStack(FAItemRegistry.COOKED_EGG), 1.0F);
				GameRegistry.addSmelting(PrehistoricEntityType.values()[i].bestBirdEggItem, new ItemStack(FAItemRegistry.COOKED_EGG), 1.0F);
			}
			if (PrehistoricEntityType.values()[i].mobType == MobType.CHICKEN) {
				GameRegistry.addSmelting(PrehistoricEntityType.values()[i].bestBirdEggItem, new ItemStack(FAItemRegistry.COOKED_EGG), 1.0F);
			}
			if (PrehistoricEntityType.values()[i].foodItem != null) {
				GameRegistry.addSmelting(PrehistoricEntityType.values()[i].foodItem, new ItemStack(PrehistoricEntityType.values()[i].cookedFoodItem), 1.5F);
			}
			if (PrehistoricEntityType.values()[i].fishItem != null && PrehistoricEntityType.values()[i] != PrehistoricEntityType.NAUTILUS) {
				GameRegistry.addSmelting(PrehistoricEntityType.values()[i].fishItem, new ItemStack(PrehistoricEntityType.values()[i].cookedFoodItem), 1.5F);
			}
			if (PrehistoricEntityType.values()[i].fishItem != null && PrehistoricEntityType.values()[i] == PrehistoricEntityType.NAUTILUS) {
				GameRegistry.addSmelting(PrehistoricEntityType.values()[i].fishItem, new ItemStack(FAItemRegistry.SJL), 1.5F);
			}

		}
	}
}
