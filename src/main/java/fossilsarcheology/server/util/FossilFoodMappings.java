package fossilsarcheology.server.util;

import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.entity.prehistoric.*;
import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FossilFoodMappings {

	public static void register() {
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.REEDS), 15);
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.WHEAT), 13);
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.MELON), 10);
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.APPLE), 20);
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.POTATO), 35);
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.BAKED_POTATO), 35);
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.CAKE), 50);
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.CARROT), 15);
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.COOKIE), 10);
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.PUMPKIN_PIE), 25);
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.SUGAR), 7);
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.BREAD), 25);
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.WHEAT_SEEDS), 5);
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.MELON_SEEDS), 5);
		FoodMappings.INSTANCE.addPlant(new ItemStack(Items.PUMPKIN_SEEDS), 5);
		FoodMappings.INSTANCE.addPlant(Blocks.CAKE, 35);
		FoodMappings.INSTANCE.addPlant(Blocks.CARROTS, 20);
		FoodMappings.INSTANCE.addPlant(Blocks.WHEAT, 10);
		FoodMappings.INSTANCE.addPlant(Blocks.LEAVES, 20);
		FoodMappings.INSTANCE.addPlant(Blocks.MELON_BLOCK, 65);
		FoodMappings.INSTANCE.addPlant(Blocks.BROWN_MUSHROOM, 15);
		FoodMappings.INSTANCE.addPlant(Blocks.RED_MUSHROOM, 15);
		FoodMappings.INSTANCE.addPlant(Blocks.RED_FLOWER, 5);
		FoodMappings.INSTANCE.addPlant(Blocks.YELLOW_FLOWER, 5);
		FoodMappings.INSTANCE.addPlant(Blocks.POTATOES, 25);
		FoodMappings.INSTANCE.addPlant(Blocks.PUMPKIN, 30);
		FoodMappings.INSTANCE.addPlant(Blocks.REEDS, 15);
		FoodMappings.INSTANCE.addPlant(Blocks.SAPLING, 15);
		FoodMappings.INSTANCE.addPlant(Blocks.TALLGRASS, 5);
		FoodMappings.INSTANCE.addPlant(Blocks.WATERLILY, 15);
		FoodMappings.INSTANCE.addPlant(FABlockRegistry.FERNS, 55);
		FoodMappings.INSTANCE.addPlant(FABlockRegistry.PALM_LEAVES, 40);

		FoodMappings.INSTANCE.addFish(new ItemStack(Items.FISH), 30);
		FoodMappings.INSTANCE.addFish(new ItemStack(Items.FISH, 1, 1), 30);
		FoodMappings.INSTANCE.addFish(new ItemStack(Items.FISH, 1, 2), 30);
		FoodMappings.INSTANCE.addFish(new ItemStack(Items.FISH, 1, 3), 30);
		FoodMappings.INSTANCE.addFish(new ItemStack(Items.COOKED_FISH), 45);
		FoodMappings.INSTANCE.addFish(new ItemStack(Items.COOKED_FISH, 1, 1), 45);
		FoodMappings.INSTANCE.addFish(new ItemStack(FAItemRegistry.SJL), 65);

		FoodMappings.INSTANCE.addMeat(new ItemStack(Items.COOKED_BEEF), 60);
		FoodMappings.INSTANCE.addMeat(new ItemStack(Items.BEEF), 40);
		FoodMappings.INSTANCE.addMeat(new ItemStack(Items.COOKED_CHICKEN), 15);
		FoodMappings.INSTANCE.addMeat(new ItemStack(Items.CHICKEN), 10);
		FoodMappings.INSTANCE.addMeat(new ItemStack(Items.PORKCHOP), 35);
		FoodMappings.INSTANCE.addMeat(new ItemStack(Items.COOKED_PORKCHOP), 55);
		FoodMappings.INSTANCE.addMeat(new ItemStack(FAItemRegistry.FAILURESAURUS_FLESH), 15);
		FoodMappings.INSTANCE.addMeat(new ItemStack(Items.MUTTON), 30);
		FoodMappings.INSTANCE.addMeat(new ItemStack(Items.COOKED_MUTTON), 50);

		FoodMappings.INSTANCE.addEgg(new ItemStack(Items.EGG), 7);

		for (int i = 0; i < PrehistoricEntityType.values().length; i++) {
			PrehistoricEntityType entityType = PrehistoricEntityType.values()[i];
			if (entityType.timePeriod != TimePeriod.CURRENT) {
				if (entityType.mobType != MobType.FISH) {
					FoodMappings.INSTANCE.addMeat(new ItemStack(entityType.foodItem), 35);
					FoodMappings.INSTANCE.addMeat(new ItemStack(entityType.cookedFoodItem), 7);
				} else {
					FoodMappings.INSTANCE.addFish(new ItemStack(entityType.eggItem), 35);
					FoodMappings.INSTANCE.addFish(new ItemStack(entityType.fishItem), 35);
					FoodMappings.INSTANCE.addFish(new ItemStack(entityType.cookedFoodItem), 75);
				}
			}
			if (entityType.mobType == MobType.BIRD || entityType.mobType == MobType.CHICKEN) {
				FoodMappings.INSTANCE.addEgg(new ItemStack(entityType.bestBirdEggItem), 15);
				if (PrehistoricEntityType.values()[i].mobType != MobType.CHICKEN) {
					FoodMappings.INSTANCE.addEgg(new ItemStack(entityType.birdEggItem), 10);
				}
			}
		}

		FoodMappings.INSTANCE.addMeat(EntityPlayer.class, 27);
		FoodMappings.INSTANCE.addMeat(EntityPlayerMP.class, 27);
		FoodMappings.INSTANCE.addMeat(EntityVillager.class, 27);
		FoodMappings.INSTANCE.addMeat(EntityZombie.class, 23);
		FoodMappings.INSTANCE.addMeat(EntityChicken.class, 5);
		FoodMappings.INSTANCE.addMeat(EntityCow.class, 40);
		FoodMappings.INSTANCE.addMeat(EntityHorse.class, 55);
		FoodMappings.INSTANCE.addMeat(EntityPig.class, 20);
		FoodMappings.INSTANCE.addMeat(EntitySheep.class, 35);
		FoodMappings.INSTANCE.addMeat(EntitySquid.class, 30);
		FoodMappings.INSTANCE.addMeat(EntityNautilus.class, 100);
		FoodMappings.INSTANCE.addMeat(EntityTriceratops.class, 120);
		FoodMappings.INSTANCE.addMeat(EntityVelociraptor.class, 20);
		FoodMappings.INSTANCE.addMeat(EntityTyrannosaurus.class, 120);
		FoodMappings.INSTANCE.addMeat(EntityPterosaur.class, 35);
		FoodMappings.INSTANCE.addMeat(EntityMosasaurus.class, 50);
		FoodMappings.INSTANCE.addMeat(EntitySarcosuchus.class, 50);
		FoodMappings.INSTANCE.addMeat(EntityLiopleurodon.class, 50);
		FoodMappings.INSTANCE.addMeat(EntityStegosaurus.class, 50);
		FoodMappings.INSTANCE.addMeat(EntityDilophosaurus.class, 25);
		FoodMappings.INSTANCE.addMeat(EntityBrachiosaurus.class, 90);
		FoodMappings.INSTANCE.addMeat(EntitySpinosaurus.class, 70);
		FoodMappings.INSTANCE.addMeat(EntityCompsognathus.class, 10);
		FoodMappings.INSTANCE.addMeat(EntityAnkylosaurus.class, 50);
		FoodMappings.INSTANCE.addMeat(EntityPachycephalosaurus.class, 50);
		FoodMappings.INSTANCE.addMeat(EntityDeinonychus.class, 35);
		FoodMappings.INSTANCE.addMeat(EntityGallimimus.class, 40);
		FoodMappings.INSTANCE.addMeat(EntityAllosaurus.class, 25);
		FoodMappings.INSTANCE.addMeat(EntityDodo.class, 20);
		FoodMappings.INSTANCE.addMeat(EntityQuagga.class, 50);
		FoodMappings.INSTANCE.addMeat(EntityTitanis.class, 40);
		FoodMappings.INSTANCE.addMeat(EntityPhorusrhacos.class, 40);
		FoodMappings.INSTANCE.addMeat(EntityKelenken.class, 40);
		FoodMappings.INSTANCE.addMeat(EntityTitanis.class, 40);
		FoodMappings.INSTANCE.addMeat(EntityMammoth.class, 100);
		FoodMappings.INSTANCE.addMeat(EntityElasmotherium.class, 80);
		FoodMappings.INSTANCE.addMeat(EntityConfuciusornis.class, 15);
		FoodMappings.INSTANCE.addMeat(EntityCeratosaurus.class, 50);
		FoodMappings.INSTANCE.addMeat(EntityDryosaurus.class, 25);
		FoodMappings.INSTANCE.addMeat(EntityTherizinosaurus.class, 125);
		FoodMappings.INSTANCE.addMeat(EntityParasaurolophus.class, 150);
		FoodMappings.INSTANCE.addFish(EntityCoelacanth.class, 20);
		FoodMappings.INSTANCE.addFish(EntitySturgeon.class, 20);
		FoodMappings.INSTANCE.addFish(EntityAlligatorGar.class, 20);
		FoodMappings.INSTANCE.addMeat(EntityMeganeura.class, 15);
		FoodMappings.INSTANCE.addMeat(EntityMegaloceros.class, 60);
		FoodMappings.INSTANCE.addMeat(EntityMegalania.class, 70);
		FoodMappings.INSTANCE.addMeat(EntityMegalograptus.class, 25);
		FoodMappings.INSTANCE.addFish(EntityNautilus.class, 30);
		FoodMappings.INSTANCE.addFish(EntityCoelacanth.class, 40);
		FoodMappings.INSTANCE.addFish(EntityAlligatorGar.class, 50);
		FoodMappings.INSTANCE.addFish(EntitySturgeon.class, 50);
		FoodMappings.INSTANCE.addFish(EntitySquid.class, 40);
		FoodMappings.INSTANCE.removeItemMapping(new ItemStack(PrehistoricEntityType.HORSE.embryoItem), Diet.CARNIVORE_EGG);
		FoodMappings.INSTANCE.addFish("listAllfishraw", 5);
		FoodMappings.INSTANCE.addFish("foodOctopuscooked", 30);
		FoodMappings.INSTANCE.addFish("listAllfishcooked", 45);
		FoodMappings.INSTANCE.addFish("foodCalamariraw", 30);
		FoodMappings.INSTANCE.addFish("foodCalamaricooked", 45);
		FoodMappings.INSTANCE.addFish("foodClamraw", 15);
		FoodMappings.INSTANCE.addFish("foodCrabraw", 10);
		FoodMappings.INSTANCE.addFish("foodCrabcooked", 15);
		FoodMappings.INSTANCE.addFish("foodShrimpcooked", 13);
		FoodMappings.INSTANCE.addFish("foodSnailcooked", 10);
		FoodMappings.INSTANCE.addFish("foodClamcooked", 22);
		FoodMappings.INSTANCE.addFish("foodCookedClam", 22);
		FoodMappings.INSTANCE.addFish("cookingClam", 15);
		FoodMappings.INSTANCE.addPlant("cropYuzu", 20);
		FoodMappings.INSTANCE.addPlant("listAllfruit", 20);
		FoodMappings.INSTANCE.addPlant("listAllveggie", 15);
		FoodMappings.INSTANCE.addPlant("listAllrootveggie", 15);
		FoodMappings.INSTANCE.addPlant("cropWildcarrots", 15);
		FoodMappings.INSTANCE.addPlant("foodMushroompowder", 5);
		FoodMappings.INSTANCE.addPlant("listAllgreenveggie", 15);
		FoodMappings.INSTANCE.addPlant("listAllmushroom", 15);
		FoodMappings.INSTANCE.addPlant("listAllberry", 7);
		FoodMappings.INSTANCE.addPlant("cropStrawberry", 7);
		FoodMappings.INSTANCE.addPlant("cropCamellia", 5);
		FoodMappings.INSTANCE.addPlant("cropCassis", 7);
		FoodMappings.INSTANCE.addPlant("treeLeaves", 20);
		FoodMappings.INSTANCE.addPlant("treeSapling", 15);
		FoodMappings.INSTANCE.addPlant("sapling", 15);
		FoodMappings.INSTANCE.addPlant("saplingPalm", 15);
		FoodMappings.INSTANCE.addPlant("leaves", 20);
		FoodMappings.INSTANCE.addPlant("leavesPalm", 20);
		FoodMappings.INSTANCE.addPlant("listAllgrain", 10);
		FoodMappings.INSTANCE.addPlant("cropRice", 10);
		FoodMappings.INSTANCE.addPlant("cropCorn", 10);
		FoodMappings.INSTANCE.addPlant("reedTypha", 15);
		FoodMappings.INSTANCE.addPlant("listAllnut", 15);
		FoodMappings.INSTANCE.addPlant("listAllseed", 5);
		FoodMappings.INSTANCE.addPlant("cropCoconut", 20);
		FoodMappings.INSTANCE.addPlant("listAllherb", 10);
		FoodMappings.INSTANCE.addPlant("listAllspice", 10);
		FoodMappings.INSTANCE.addPlant("cropEdibleroot", 7);
		FoodMappings.INSTANCE.addPlant("cropSesame", 5);
		FoodMappings.INSTANCE.addPlant("listAllpepper", 5);
		FoodMappings.INSTANCE.addPlant("cropAvocado", 20);
		FoodMappings.INSTANCE.addPlant("cropApple", 20);
		FoodMappings.INSTANCE.addPlant("cropCherry", 5);
		FoodMappings.INSTANCE.addPlant("cropWalnut", 7);
		FoodMappings.INSTANCE.addPlant("cropChestnut", 7);
		FoodMappings.INSTANCE.addPlant("cropLemon", 10);
		FoodMappings.INSTANCE.addPlant("cropPlum", 10);
		FoodMappings.INSTANCE.addPlant("cropDate", 5);
		FoodMappings.INSTANCE.addPlant("cropDate", 10);
		FoodMappings.INSTANCE.addPlant("cropOrange", 10);
		FoodMappings.INSTANCE.addPlant("cropKumquat", 7);
		FoodMappings.INSTANCE.addPlant("cropLime", 10);
		FoodMappings.INSTANCE.addPlant("cropBlackthorn", 7);
		FoodMappings.INSTANCE.addPlant("cropAlmond", 7);
		FoodMappings.INSTANCE.addPlant("cropApricot", 10);
		FoodMappings.INSTANCE.addPlant("cropGrapefruit", 15);
		FoodMappings.INSTANCE.addPlant("cropPeach", 10);
		FoodMappings.INSTANCE.addPlant("cropCitron", 20);
		FoodMappings.INSTANCE.addPlant("cropPomelo", 25);
		FoodMappings.INSTANCE.addPlant("cropPear", 20);
		FoodMappings.INSTANCE.addPlant("cropSandPear", 20);
		FoodMappings.INSTANCE.addPlant("cropHazelnut", 10);
		FoodMappings.INSTANCE.addPlant("cropBeechnut", 10);
		FoodMappings.INSTANCE.addPlant("cropPecan", 10);
		FoodMappings.INSTANCE.addPlant("cropBanana", 15);
		FoodMappings.INSTANCE.addPlant("cropPlantain", 15);
		FoodMappings.INSTANCE.addPlant("cropBrazilNut", 10);
		FoodMappings.INSTANCE.addPlant("cropFig", 12);
		FoodMappings.INSTANCE.addPlant("cropAcorn", 5);
		FoodMappings.INSTANCE.addPlant("cropElderberry", 7);
		FoodMappings.INSTANCE.addPlant("cropOlive", 7);
		FoodMappings.INSTANCE.addPlant("cropGinkgoNut", 7);
		FoodMappings.INSTANCE.addPlant("cropOsangeOrange", 30);
		FoodMappings.INSTANCE.addPlant("cropClove", 7);
		FoodMappings.INSTANCE.addPlant("cropDurian", 25);
		FoodMappings.INSTANCE.addPlant("cropTea", 10);
		FoodMappings.INSTANCE.addPlant("cropSpiceleaf", 10);
		FoodMappings.INSTANCE.addPlant("cropCurryleaf", 10);
		FoodMappings.INSTANCE.addPlant("cropCoffee", 5);
		FoodMappings.INSTANCE.addPlant("cropNutmeg", 7);
		FoodMappings.INSTANCE.addPlant("cropCoffee", 5);
		FoodMappings.INSTANCE.addPlant("cropCinnamon", 5);
		FoodMappings.INSTANCE.addPlant("cropVanillabean", 5);
		FoodMappings.INSTANCE.addPlant("cropMaplesyrup", 5);
		FoodMappings.INSTANCE.addPlant("dropHoney", 7);
		FoodMappings.INSTANCE.addPlant("foodSugar", 7);
		FoodMappings.INSTANCE.addPlant("bamboo", 8);
		FoodMappings.INSTANCE.addPlant("cropTaro", 6);
		FoodMappings.INSTANCE.addPlant("cropCentella", 8);
		FoodMappings.INSTANCE.addPlant("cropLaksaLeaf", 7);
		FoodMappings.INSTANCE.addPlant("cropSacredLotus", 10);
		FoodMappings.INSTANCE.addPlant("cropWasabi", 6);
		FoodMappings.INSTANCE.addPlant("cropWatercress", 7);
		FoodMappings.INSTANCE.addPlant("cropWaterSpinach", 7);
		FoodMappings.INSTANCE.addPlant("cropWildRice", 10);
		FoodMappings.INSTANCE.addPlant("groundcoverClover", 5);
		FoodMappings.INSTANCE.addPlant("groundcoverGrassGRNLT", 5);
		FoodMappings.INSTANCE.addPlant("groundcoverGrassGRNDK", 5);
		FoodMappings.INSTANCE.addPlant("groundcoverFlowers", 6);
		FoodMappings.INSTANCE.addPlant("groundcoverIvy", 5);
		FoodMappings.INSTANCE.addPlant("floatingDuckweed", 5);
		FoodMappings.INSTANCE.addPlant("grassSwitch", 10);
		FoodMappings.INSTANCE.addPlant("forestWildMint", 6);
		FoodMappings.INSTANCE.addPlant("plainsReedCanaryGrass", 10);
		FoodMappings.INSTANCE.addPlant("grassSilverMoor", 10);
		FoodMappings.INSTANCE.addPlant("grassSeaOats", 10);
		FoodMappings.INSTANCE.addPlant("grassBlueWheatgrass", 10);
		FoodMappings.INSTANCE.addPlant("grassCord", 10);
		FoodMappings.INSTANCE.addPlant("grassFountain", 10);
		FoodMappings.INSTANCE.addPlant("grassPrairie", 10);
		FoodMappings.INSTANCE.addPlant("grassMeadow", 10);
		FoodMappings.INSTANCE.addPlant("grassMaiden", 10);
		FoodMappings.INSTANCE.addPlant("beachSeaLymeGrass", 10);
		FoodMappings.INSTANCE.addPlant("grassMeadowFoxtail", 10);
		FoodMappings.INSTANCE.addPlant("shrubLavender", 7);
		FoodMappings.INSTANCE.addPlant("immersedCommonReed", 8);
		FoodMappings.INSTANCE.addPlant("immersedDuckPotato", 7);
		FoodMappings.INSTANCE.addPlant("immersedArrowArum", 8);
		FoodMappings.INSTANCE.addPlant("immersedGreySedge", 7);
		FoodMappings.INSTANCE.addPlant("immersedYellowFlag", 8);
		FoodMappings.INSTANCE.addPlant("immersedEuropeanBurReed", 7);
		FoodMappings.INSTANCE.addPlant("immersedSimplestemBurReed", 8);
		FoodMappings.INSTANCE.addPlant("immersedSoftstemBulrush", 7);
		FoodMappings.INSTANCE.addPlant("immersedWaterMannagrass", 8);
		FoodMappings.INSTANCE.addPlant("riverAmazonSword", 8);
		FoodMappings.INSTANCE.addPlant("riverCanadianWaterweed", 7);
		FoodMappings.INSTANCE.addPlant("riverCoonsTail", 8);
		FoodMappings.INSTANCE.addPlant("waterCryptWendtii", 7);
		FoodMappings.INSTANCE.addPlant("waterDwarfHairGrass", 8);
		FoodMappings.INSTANCE.addPlant("riverEelgrass", 7);
		FoodMappings.INSTANCE.addPlant("riverWrightsWaternymph", 8);
		FoodMappings.INSTANCE.addPlant("waterMondoGrass", 7);
		FoodMappings.INSTANCE.addPlant("riverWaterWisteria", 8);
		FoodMappings.INSTANCE.addPlant("oceanCommonEelgrass", 8);
		FoodMappings.INSTANCE.addPlant("waterKelpGiantGRN", 7);
		FoodMappings.INSTANCE.addPlant("waterKelpGiantYEL", 8);
		FoodMappings.INSTANCE.addPlant("oceanSeaGrapes", 7);
		FoodMappings.INSTANCE.addPlant("oceanMozuku", 8);
		FoodMappings.INSTANCE.addPlant("oceanRockweed", 7);
		FoodMappings.INSTANCE.addPlant("oceanTangle", 8);
		FoodMappings.INSTANCE.addPlant("oceanSeaLettuce", 7);
		FoodMappings.INSTANCE.addPlant("floatingWaterLettuce", 8);
		FoodMappings.INSTANCE.addPlant("floatingWaterHyacinth", 7);
		FoodMappings.INSTANCE.addPlant("flowerThistle", 6);
		FoodMappings.INSTANCE.addPlant("cropVine", 6);
		FoodMappings.INSTANCE.addPlant("cropIronberry", 10);
		FoodMappings.INSTANCE.addPlant("cropWildberry", 6);
		FoodMappings.INSTANCE.addPlant("cropGrape", 8);
		FoodMappings.INSTANCE.addPlant("cropPepper", 8);
		FoodMappings.INSTANCE.addPlant("cropChilipepper", 8);
		FoodMappings.INSTANCE.addMeat("listAllmeatcooked", 60);
		FoodMappings.INSTANCE.addMeat("listAllmeatraw", 40);
		FoodMappings.INSTANCE.addMeat("foodOffal", 10);
		FoodMappings.INSTANCE.addMeat("listAllchickenraw", 10);
		FoodMappings.INSTANCE.addMeat("listAllchickencooked", 15);
		FoodMappings.INSTANCE.addMeat("listAllmuttonraw", 20);
		FoodMappings.INSTANCE.addMeat("listAllmuttoncooked", 30);
		FoodMappings.INSTANCE.addMeat("listAllporkraw", 30);
		FoodMappings.INSTANCE.addMeat("listAllporkcooked", 55);
		FoodMappings.INSTANCE.addMeat("listAllvenisonraw", 30);
		FoodMappings.INSTANCE.addMeat("listAllvenisoncooked", 55);
		FoodMappings.INSTANCE.addMeat("listAllbeefraw", 40);
		FoodMappings.INSTANCE.addMeat("listAllbeefcooked", 60);
		FoodMappings.INSTANCE.addMeat("foodRabbitraw", 10);
		FoodMappings.INSTANCE.addMeat("nuggetMeat", 8);
		FoodMappings.INSTANCE.addMeat("foodRabbitcooked", 15);
		FoodMappings.INSTANCE.addMeat("listAllturkeyraw", 15);
		FoodMappings.INSTANCE.addMeat("listAllturkeycooked", 20);
		FoodMappings.INSTANCE.addMeat("foodTurtleraw", 25);
		FoodMappings.INSTANCE.addMeat("foodTurtlecooked", 40);
		FoodMappings.INSTANCE.addMeat("foodFrograw", 15);
		FoodMappings.INSTANCE.addMeat("foodFrogcooked", 25);
		FoodMappings.INSTANCE.addEgg("listAllegg", 7);
		FoodMappings.INSTANCE.addEgg("egg", 7);
	}
}
