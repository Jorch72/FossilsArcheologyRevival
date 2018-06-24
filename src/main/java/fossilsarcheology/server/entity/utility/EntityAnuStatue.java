package fossilsarcheology.server.entity.utility;

import fossilsarcheology.server.block.FABlockRegistry;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAnuStatue extends EntityLiving {

    public EntityAnuStatue(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 1.8F);
    }

    public boolean isAIDisabled(){
        return true;
    }

    public void onUpdate() {
        super.onUpdate();
        this.motionY += MathHelper.cos(this.ticksExisted);
        if(this.ticksExisted > 200){
            this.createPortal();
        }
    }

    private void createPortal() {
        BlockPos pos = new BlockPos(this);
        world.setBlockState(pos.down(), Blocks.OBSIDIAN.getDefaultState());
        world.setBlockState(pos, FABlockRegistry.ANU_PORTAL.getDefaultState());
        world.setBlockState(pos.up(), FABlockRegistry.ANU_PORTAL.getDefaultState());
        world.setBlockState(pos.up(2), Blocks.OBSIDIAN.getDefaultState());

    }


}
