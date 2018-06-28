package fossilsarcheology.server.item;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ItemFossilDoor extends ItemDoor implements DefaultRenderedItem {


    public ItemFossilDoor(Block block, String name) {
        super(block);
        this.setUnlocalizedName(name);
        this.setCreativeTab(FATabRegistry.BLOCKS);
    }
}
