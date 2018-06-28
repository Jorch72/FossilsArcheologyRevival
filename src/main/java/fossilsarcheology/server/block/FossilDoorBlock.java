package fossilsarcheology.server.block;

import fossilsarcheology.server.item.FAItemRegistry;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

public class FossilDoorBlock extends BlockDoor {

    protected FossilDoorBlock(IBlockState parent, String name) {
        super(parent.getMaterial());
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.WOOD);
    }

    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return getItem(world, pos, state);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return getDoorItem();
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(getDoorItem());
    }

    public Item getDoorItem(){
        return FAItemRegistry.PALM_DOOR_ITEM;
    }
}
