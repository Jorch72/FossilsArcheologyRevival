package fossilsarcheology.server.world.village;

import fossilsarcheology.Revival;
import fossilsarcheology.server.structure.StructureUtils;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.Random;

public class WorldGenArcheologistHouse extends WorldGenerator {

    private static final ResourceLocation HOUSE = new ResourceLocation(Revival.MODID, "archeologist_house");
    private static final ResourceLocation CHEST = LootTableList.register(new ResourceLocation(Revival.MODID, "archeologist_house"));

    private Rotation rotation;
    public WorldGenArcheologistHouse(EnumFacing facing){
        switch(facing){
            case SOUTH:
                rotation = Rotation.CLOCKWISE_180;
                break;
            case EAST:
                rotation = Rotation.CLOCKWISE_90;
                break;
            case WEST:
                rotation = Rotation.COUNTERCLOCKWISE_90;
                break;
            default:
                rotation = Rotation.NONE;
                break;
        }
    }
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if (worldIn == null) {
            return false;
        }
        StructureUtils.generateStructureAtWithRotationWithLoot(HOUSE, CHEST, worldIn, position.up(3), rand, rotation, false, false);
        return true;
    }
}
