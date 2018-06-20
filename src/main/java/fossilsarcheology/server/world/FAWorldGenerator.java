package fossilsarcheology.server.world;

import fossilsarcheology.Revival;
import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.structure.StructureUtils;
import fossilsarcheology.server.world.gen.HellBoatWorldGen;
import fossilsarcheology.server.world.gen.WorldGenPalm;
import fossilsarcheology.server.world.gen.WorldGenTarPit;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class FAWorldGenerator implements IWorldGenerator {

	private static final ResourceLocation TAR_SITE = new ResourceLocation(Revival.MODID, "tar_site");
	private static final ResourceLocation FOSSIL_SITE = new ResourceLocation(Revival.MODID, "fossil_site");
	private static final ResourceLocation FOSSIL_SITE_TENT = new ResourceLocation(Revival.MODID, "fossil_site_tent");
	private static final ResourceLocation MOAI = new ResourceLocation(Revival.MODID, "moai");
	private static final ResourceLocation MOAI_WITH_HAT = new ResourceLocation(Revival.MODID, "moai_with_hat");
	private static final ResourceLocation AZTEC_TEMPLE = new ResourceLocation(Revival.MODID, "aztec_temple");
	private static final ResourceLocation AZTEC_WEAPONS_SHOP = new ResourceLocation(Revival.MODID, "aztec_weapons_shop");
	private static final ResourceLocation EGYPTIAN_ACADEMY = new ResourceLocation(Revival.MODID, "egyptian_academy");
	private static final ResourceLocation AZTEC_TEMPLE_CHEST = LootTableList.register(new ResourceLocation(Revival.MODID, "aztec_temple"));
	private static final ResourceLocation AZTEC_WEAPONS_CHEST = LootTableList.register(new ResourceLocation(Revival.MODID, "aztec_weapons_shop"));
	private static final ResourceLocation EGYPTIAN_ACADEMY_CHEST = LootTableList.register(new ResourceLocation(Revival.MODID, "egyptian_academy"));

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		for (int dimensionID : Revival.CONFIG.oreGenerationDimensions) {
			if (world.provider.getDimension() == dimensionID) {
				if (Revival.CONFIG.generateFossils) {
					for (int i = 0; i < 38; i++) {
						int Xcoord = chunkX * 16 + random.nextInt(16);
						int Ycoord = random.nextInt(100);
						int Zcoord = chunkZ * 16 + random.nextInt(16);
						(new WorldGenMinable(FABlockRegistry.FOSSIL.getDefaultState(), 5 + random.nextInt(6))).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
					}
				}
				if (Revival.CONFIG.generatePermafrost) {
					for (int i = 0; i < 8; i++) {
						int Xcoord = chunkX * 16 + random.nextInt(16);
						int Ycoord = random.nextInt(30);
						int Zcoord = chunkZ * 16 + random.nextInt(16);
						(new WorldGenMinable(FABlockRegistry.PERMAFROST.getDefaultState(), 2 + random.nextInt(4))).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
					}
				}
			}
		}

		if (world.provider.getDimension() == 0) {
			int x = chunkX * 16 + random.nextInt(16);
			int z = chunkZ * 16 + random.nextInt(16);

			if (BiomeDictionary.hasType(world.getBiome(new BlockPos(x, 0, z)), BiomeDictionary.Type.SWAMP)) {
				if (Revival.CONFIG.generateTarSites) {
					for (int k = 0; k < 10; k++) {
						int y = random.nextInt(128);
						(new WorldGenTarPit(FABlockRegistry.TAR)).generate(world, random, new BlockPos(x, y, z));
					}
				}
				if (Revival.CONFIG.generatePalaeoraphe) {
					for (int count = 0; count < 2; count++) {
						BlockPos pos = world.getHeight(new BlockPos(x, 0, z));
						new WorldGenPalm().generate(world, random, pos);
					}
				}
			}
			if (Revival.CONFIG.generateVolcanicRock) {
				for (int i = 0; i < 10; i++) {
					int y = random.nextInt(16);
					(new WorldGenMinable(FABlockRegistry.VOLCANIC_ROCK.getDefaultState(), 6)).generate(world, random, new BlockPos(x, y, z));
				}
			}
		}
		if (Revival.CONFIG.generateHellShips && world.provider.getDimension() == -1) {
			if (random.nextInt(100) == 0) {
				int x = chunkX * 16 + random.nextInt(16);
				int z = chunkZ * 16 + random.nextInt(16);
				if (world.getBlockState(new BlockPos(x, 31, z)).getMaterial() == Material.LAVA) {
					new HellBoatWorldGen().generate(world, random, new BlockPos(x, 32, z));
				}
			}
		}
		int x = (chunkX * 16) + 8;
		int z = (chunkZ * 16) + 8;
		BlockPos height = world.getHeight(new BlockPos(x, 0, z));
		Biome biome = world.getBiome(height);
		if (Revival.CONFIG.generateTarSites && random.nextInt(320) == 0 && world.provider.hasSkyLight() && !world.provider.isNether() && biome.topBlock == Blocks.GRASS && world.getBlockState(height.down()).getBlock() == Blocks.GRASS) {
			if(StructureUtils.generateStructureAtWithRandomRotation(TAR_SITE, world, height.down(3), random, true, false)){
				BlockPos tentPos = random.nextBoolean() ? height.add(10  + random.nextInt(6), 10 + random.nextInt(6) , 10 + random.nextInt(6)) : height.add(-10 - random.nextInt(6), -10 - random.nextInt(6), -10 - random.nextInt(6));
				StructureUtils.generateStructureAtWithRandomRotation(FOSSIL_SITE_TENT, world, world.getHeight(tentPos), random, false, false);
			}
		}
		if (Revival.CONFIG.generateFossilSites && random.nextInt(320) == 0 && world.provider.hasSkyLight() && !world.provider.isNether() && biome.topBlock == Blocks.GRASS && world.getBlockState(height.down()).getBlock() == Blocks.GRASS) {
			if(StructureUtils.generateStructureAtWithRandomRotation(FOSSIL_SITE, world, height.down(3), random, true, false)){
				BlockPos tentPos = random.nextBoolean() ? height.add(10  + random.nextInt(6), 10 + random.nextInt(6) , 10 + random.nextInt(6)) : height.add(-10 - random.nextInt(6), -10 - random.nextInt(6), -10 - random.nextInt(6));
				StructureUtils.generateStructureAtWithRandomRotation(FOSSIL_SITE_TENT, world, world.getHeight(tentPos), random, false, false);
			}
		}
		if (Revival.CONFIG.generateMoai && random.nextInt(100) == 0 && world.provider.hasSkyLight() && !world.provider.isNether() && BiomeDictionary.hasType(biome, BiomeDictionary.Type.BEACH) && world.getBlockState(height.down()).isOpaqueCube()) {
			StructureUtils.generateStructureAtWithRandomRotation(random.nextInt(3) == 0 ? MOAI_WITH_HAT : MOAI, world, height.down(random.nextInt(5)), random, false, true);
		}
		if (Revival.CONFIG.generateAztecWeaponShops && random.nextInt(65) == 0 && world.provider.hasSkyLight() && !world.provider.isNether() && BiomeDictionary.hasType(biome, BiomeDictionary.Type.JUNGLE)) {
			StructureUtils.generateStructureAtWithRandomRotation(AZTEC_WEAPONS_SHOP, world, height.down(2), random, true, false);
		}
		if (Revival.CONFIG.generateTemple && random.nextInt(65) == 0 && world.provider.hasSkyLight() && !world.provider.isNether() && BiomeDictionary.hasType(biome, BiomeDictionary.Type.JUNGLE)) {
			StructureUtils.generateStructureAtWithRandomRotation(AZTEC_TEMPLE, world, height, random, true, false);
		}
		if (Revival.CONFIG.generateAcademy && random.nextInt(65) == 0 && world.provider.hasSkyLight() && !world.provider.isNether() && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.DRY) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.HOT)) {
			StructureUtils.generateStructureAtWithRandomRotationWithLoot(EGYPTIAN_ACADEMY, EGYPTIAN_ACADEMY_CHEST, world, height.down(), random, true, false);
		}
	}
}
