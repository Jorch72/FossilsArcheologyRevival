package fossilsarcheology;

import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

/**
 * Created by Joseph on 4/29/2018.
 */
public class RevivalAspectRegistry {

	public static final Aspect TIME = new Aspect("chronos", 0Xb5a642, new Aspect[]{Aspect.EXCHANGE, Aspect.MOTION}, new ResourceLocation(Revival.MODID, "textures/thaumcraft/hourglass.png"), 1);

	public static void register() {
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.BIOFOSSIL), new AspectList().add(Aspect.EARTH, 5).add(Aspect.BEAST, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.AMBER), new AspectList().add(Aspect.EARTH, 3).add(Aspect.CRYSTAL, 3).add(RevivalAspectRegistry.TIME, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.ANCIENT_CLOCK), new AspectList().add(Aspect.METAL, 15).add(RevivalAspectRegistry.TIME, 15));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.ANCIENT_HELMET), new AspectList().add(Aspect.PROTECT, 10).add(RevivalAspectRegistry.TIME, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.ANCIENT_JAVELIN), new AspectList().add(Aspect.AVERSION, 10).add(RevivalAspectRegistry.TIME, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.ANCIENT_KEY), new AspectList().add(Aspect.DESIRE, 10).add(Aspect.METAL, 10).add(RevivalAspectRegistry.TIME, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.ANCIENT_SWORD), new AspectList().add(Aspect.AVERSION, 10).add(Aspect.METAL, 10).add(RevivalAspectRegistry.TIME, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.AQUATIC_SCARAB_GEM), new AspectList().add(Aspect.WATER, 5).add(Aspect.EARTH, 5).add(Aspect.CRYSTAL, 5).add(Aspect.DESIRE, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.DOMINICAN_AMBER), new AspectList().add(Aspect.WATER, 3).add(Aspect.EARTH, 3).add(Aspect.CRYSTAL, 3).add(RevivalAspectRegistry.TIME, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.BROKEN_HELMET), new AspectList().add(Aspect.ENTROPY, 10).add(RevivalAspectRegistry.TIME, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.BROKEN_SWORD), new AspectList().add(Aspect.ENTROPY, 10).add(RevivalAspectRegistry.TIME, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.CHICKEN_ESSENCE), new AspectList().add(Aspect.CRAFT, 5).add(Aspect.BEAST, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.COOKED_CHICKEN_SOUP), new AspectList().add(Aspect.CRAFT, 10).add(Aspect.BEAST, 10).add(Aspect.WATER, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.COOKED_EGG), new AspectList().add(Aspect.DEATH, 5).add(Aspect.BEAST, 5).add(Aspect.CRAFT, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.TOOTH_DAGGER), new AspectList().add(Aspect.DEATH, 10).add(Aspect.BEAST, 10).add(Aspect.CRAFT, 10).add(Aspect.AVERSION, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.FOOT), new AspectList().add(Aspect.DEATH, 5).add(Aspect.BEAST, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.LEG_BONE), new AspectList().add(Aspect.DEATH, 5).add(Aspect.BEAST, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.UNIQUE_ITEM), new AspectList().add(Aspect.DEATH, 5).add(Aspect.BEAST, 5).add(RevivalAspectRegistry.TIME, 5).add(Aspect.DESIRE, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.RIBCAGE), new AspectList().add(Aspect.DEATH, 5).add(Aspect.BEAST, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.SKULL), new AspectList().add(Aspect.DEATH, 5).add(Aspect.BEAST, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.VERTEBRAE), new AspectList().add(Aspect.DEATH, 5).add(Aspect.BEAST, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.ARM_BONE), new AspectList().add(Aspect.DEATH, 5).add(Aspect.BEAST, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.PALAE_SAPLING_FOSSIL), new AspectList().add(Aspect.EARTH, 5).add(Aspect.PLANT, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.PLANT_FOSSIL), new AspectList().add(Aspect.EARTH, 5).add(Aspect.PLANT, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.TAR_FOSSIL), new AspectList().add(Aspect.EARTH, 5).add(Aspect.ALCHEMY, 5).add(Aspect.BEAST, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.FOSSIL_SEED), new AspectList().add(Aspect.EARTH, 5).add(Aspect.PLANT, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.FOSSIL_SEED_FERN), new AspectList().add(Aspect.EARTH, 5).add(Aspect.PLANT, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.TARDROP), new AspectList().add(Aspect.EARTH, 3).add(Aspect.WATER, 3).add(Aspect.DARKNESS, 3).add(Aspect.ALCHEMY, 3).add(Aspect.ENTROPY, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.TAR_BUCKET), new AspectList().add(Aspect.EARTH, 7).add(Aspect.WATER, 7).add(Aspect.DARKNESS, 7).add(Aspect.ALCHEMY, 7).add(Aspect.ENTROPY, 7));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.TOY_BALL), new AspectList().add(Aspect.CRAFT, 8).add(Aspect.SENSES, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.POTTERY_SHARD), new AspectList().add(Aspect.EARTH, 2).add(Aspect.ENTROPY, 2).add(RevivalAspectRegistry.TIME, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.SCARAB_GEM), new AspectList().add(Aspect.CRYSTAL, 6).add(Aspect.DESIRE, 6).add(RevivalAspectRegistry.TIME, 6));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.WHIP), new AspectList().add(Aspect.BEAST, 10).add(Aspect.DESIRE, 10).add(Aspect.AVERSION, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.ICED_MEAT), new AspectList().add(Aspect.BEAST, 5).add(Aspect.COLD, 5).add(Aspect.DEATH, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.AMPHORA_VASE), new AspectList().add(Aspect.CRAFT, 15).add(Aspect.SENSES, 15).add(Aspect.DESIRE, 15).add(RevivalAspectRegistry.TIME, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.AMPHORA_VASE, 1, 1), new AspectList().add(Aspect.CRAFT, 15).add(Aspect.SENSES, 15).add(Aspect.DESIRE, 15).add(RevivalAspectRegistry.TIME, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.AMPHORA_VASE, 1, 2), new AspectList().add(Aspect.CRAFT, 15).add(Aspect.SENSES, 15).add(Aspect.DESIRE, 15).add(RevivalAspectRegistry.TIME, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.AMPHORA_VASE, 1, 3), new AspectList().add(Aspect.CRAFT, 15).add(Aspect.SENSES, 15).add(Aspect.DESIRE, 15).add(RevivalAspectRegistry.TIME, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.KYLIX_VASE), new AspectList().add(Aspect.CRAFT, 15).add(Aspect.SENSES, 15).add(Aspect.DESIRE, 15).add(RevivalAspectRegistry.TIME, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.KYLIX_VASE, 1, 1), new AspectList().add(Aspect.CRAFT, 15).add(Aspect.SENSES, 15).add(Aspect.DESIRE, 15).add(RevivalAspectRegistry.TIME, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.KYLIX_VASE, 1, 2), new AspectList().add(Aspect.CRAFT, 15).add(Aspect.SENSES, 15).add(Aspect.DESIRE, 15).add(RevivalAspectRegistry.TIME, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.KYLIX_VASE, 1, 3), new AspectList().add(Aspect.CRAFT, 15).add(Aspect.SENSES, 15).add(Aspect.DESIRE, 15).add(RevivalAspectRegistry.TIME, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.VOLUTE_VASE), new AspectList().add(Aspect.CRAFT, 15).add(Aspect.SENSES, 15).add(Aspect.DESIRE, 15).add(RevivalAspectRegistry.TIME, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.VOLUTE_VASE, 1, 1), new AspectList().add(Aspect.CRAFT, 15).add(Aspect.SENSES, 15).add(Aspect.DESIRE, 15).add(RevivalAspectRegistry.TIME, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.VOLUTE_VASE, 1, 2), new AspectList().add(Aspect.CRAFT, 15).add(Aspect.SENSES, 15).add(Aspect.DESIRE, 15).add(RevivalAspectRegistry.TIME, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.VOLUTE_VASE, 1, 3), new AspectList().add(Aspect.CRAFT, 15).add(Aspect.SENSES, 15).add(Aspect.DESIRE, 15).add(RevivalAspectRegistry.TIME, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.TIME_MACHINE), new AspectList().add(Aspect.CRAFT, 25).add(Aspect.MOTION, 15).add(Aspect.DESIRE, 15).add(RevivalAspectRegistry.TIME, 25));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.ICED_STONE), new AspectList().add(Aspect.EARTH, 4).add(Aspect.COLD, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.SKULL_BLOCK), new AspectList().add(Aspect.DEATH, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.VOLCANIC_ROCK), new AspectList().add(Aspect.FIRE, 4).add(Aspect.EARTH, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.VOLCANIC_ASH), new AspectList().add(Aspect.FIRE, 4).add(Aspect.DARKNESS, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.FIGURINE), new AspectList().add(Aspect.MAN, 10).add(RevivalAspectRegistry.TIME, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.PALM_LOG), new AspectList().add(Aspect.PLANT, 10).add(RevivalAspectRegistry.TIME, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FABlockRegistry.ANCIENT_GLASS), new AspectList().add(Aspect.CRYSTAL, 5).add(RevivalAspectRegistry.TIME, 5));
	}

}
