package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;

public class FossilFenceGateBlock extends BlockFenceGate implements DefaultRenderedItem {

    public FossilFenceGateBlock(String name) {
        super(BlockPlanks.EnumType.OAK);
        this.setCreativeTab(FATabRegistry.BLOCKS);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.WOOD);
    }
}
