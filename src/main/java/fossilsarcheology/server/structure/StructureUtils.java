package fossilsarcheology.server.structure;

import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Map;
import java.util.Random;

public class StructureUtils {

    public static boolean generateStructureAtWithRandomRotation(ResourceLocation structure, World world, BlockPos pos, Random random, boolean checkGround, boolean removeAir) {
        Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
        Mirror mirror = Mirror.values()[random.nextInt(Mirror.values().length)];
        MinecraftServer server = world.getMinecraftServer();
        TemplateManager templateManager = world.getSaveHandler().getStructureTemplateManager();
        PlacementSettings settings = new PlacementSettings().setRotation(rotation).setMirror(mirror);
        if (removeAir) {
            settings.setReplacedBlock(Blocks.AIR);
        }
        Template template = templateManager.getTemplate(server, structure);
        BlockPos center = pos.add(template.getSize().getX() / 2, 0, template.getSize().getZ() / 2);
        if (checkGround) {
            BlockPos corner1 = pos.down();
            BlockPos corner2 = pos.add(template.getSize().getX(), -1, 0);
            BlockPos corner3 = pos.add(template.getSize().getX(), -1, template.getSize().getZ());
            BlockPos corner4 = pos.add(0, -1, template.getSize().getZ());
            if (world.getBlockState(center).isOpaqueCube() && world.getBlockState(corner1).isOpaqueCube() && world.getBlockState(corner2).isOpaqueCube() && world.getBlockState(corner3).isOpaqueCube() && world.getBlockState(corner4).isOpaqueCube()) {
                template.addBlocksToWorldChunk(world, center, settings);
                return true;
            }
            return false;
        } else {
            template.addBlocksToWorldChunk(world, center, settings);
            return true;
        }
    }

    public static void generateStructureAtWithRandomRotationWithLoot(ResourceLocation structure, ResourceLocation loot, World world, BlockPos pos, Random random, boolean checkGround, boolean removeAir) {
        Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
        Mirror mirror = Mirror.values()[random.nextInt(Mirror.values().length)];
        MinecraftServer server = world.getMinecraftServer();
        TemplateManager templateManager = world.getSaveHandler().getStructureTemplateManager();
        PlacementSettings settings = new PlacementSettings().setRotation(rotation).setMirror(mirror);
        if (removeAir) {
            settings.setReplacedBlock(Blocks.AIR);
        }
        Template template = templateManager.getTemplate(server, structure);
        BlockPos center = pos.add(template.getSize().getX() / 2, 0, template.getSize().getZ() / 2);
        Map<BlockPos, String> map = template.getDataBlocks(center, settings);
        if (checkGround) {
            BlockPos corner1 = pos.down();
            BlockPos corner2 = pos.add(template.getSize().getX(), -1, 0);
            BlockPos corner3 = pos.add(template.getSize().getX(), -1, template.getSize().getZ());
            BlockPos corner4 = pos.add(0, -1, template.getSize().getZ());
            if (world.getBlockState(center).isOpaqueCube() && world.getBlockState(corner1).isOpaqueCube() && world.getBlockState(corner2).isOpaqueCube() && world.getBlockState(corner3).isOpaqueCube() && world.getBlockState(corner4).isOpaqueCube()) {
                template.addBlocksToWorldChunk(world, center, settings);
            }
        } else {
            template.addBlocksToWorldChunk(world, center, settings);
        }


        for (Map.Entry<BlockPos, String> entry : map.entrySet()) {
            System.out.println(entry.getValue());
            if ("chest".equals(entry.getValue())) {
                BlockPos blockpos2 = entry.getKey();
                world.setBlockState(blockpos2, Blocks.AIR.getDefaultState(), 3);
                TileEntity tileentity = world.getTileEntity(blockpos2.down());
                if (tileentity instanceof TileEntityChest) {
                    ((TileEntityChest) tileentity).setLootTable(loot, random.nextLong());
                }
            }
        }
    }


    public static boolean generateStructureAtWithRotation(ResourceLocation structure, World world, BlockPos pos, Random random, Rotation rotation, boolean checkGround, boolean removeAir) {
        Mirror mirror = Mirror.values()[random.nextInt(Mirror.values().length)];
        MinecraftServer server = world.getMinecraftServer();
        TemplateManager templateManager = world.getSaveHandler().getStructureTemplateManager();
        PlacementSettings settings = new PlacementSettings().setRotation(rotation).setMirror(Mirror.NONE);
        if (removeAir) {
            settings.setReplacedBlock(Blocks.AIR);
        }
        Template template = templateManager.getTemplate(server, structure);
        BlockPos center = pos.add(template.getSize().getX() / 2, 0, template.getSize().getZ() / 2);
        if (checkGround) {
            BlockPos corner1 = pos.down();
            BlockPos corner2 = pos.add(template.getSize().getX(), -1, 0);
            BlockPos corner3 = pos.add(template.getSize().getX(), -1, template.getSize().getZ());
            BlockPos corner4 = pos.add(0, -1, template.getSize().getZ());
            if (world.getBlockState(center).isOpaqueCube() && world.getBlockState(corner1).isOpaqueCube() && world.getBlockState(corner2).isOpaqueCube() && world.getBlockState(corner3).isOpaqueCube() && world.getBlockState(corner4).isOpaqueCube()) {
                template.addBlocksToWorldChunk(world, center, settings);
                return true;
            }
            return false;
        } else {
            template.addBlocksToWorldChunk(world, center, settings);
            return true;
        }
    }
}
