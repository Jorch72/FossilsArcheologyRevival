package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.entity.monster.EntityAnu;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ObsidianSpikesBlock extends Block implements DefaultRenderedItem {
    protected static final AxisAlignedBB SPIKES_AABB = new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);

    public ObsidianSpikesBlock() {
        super(Material.ROCK);
        this.setHarvestLevel("pickaxe", 3);
        this.setCreativeTab(FATabRegistry.BLOCKS);
        this.setHardness(50.0F);
        this.setResistance(2000.0F);
        this.setSoundType(SoundType.STONE);
        this.setUnlocalizedName("obsidian_spikes");
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SPIKES_AABB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos);
    }

    private boolean canBlockStay(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!this.canBlockStay(worldIn, pos)) {
            worldIn.setBlockToAir(pos);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
        if (!(entity instanceof EntityAnu) && entity instanceof EntityLivingBase) {
            EntityLivingBase living = (EntityLivingBase) entity;
            double deltaX = pos.getX() - entity.posX;
            double deltaZ = pos.getZ() - entity.posZ;

            entity.attackEntityFrom(DamageSource.CACTUS, 1.0F);
            if ((entity instanceof EntityPlayer) && !((EntityPlayer) entity).capabilities.isCreativeMode) {
                living.knockBack(entity, 0, deltaX * 5.0D, deltaZ * 5.0D);
            }
            if (!world.isRemote && !(entity instanceof EntityPlayer)) {
                living.knockBack(entity, 0, deltaX * 5.0D, deltaZ * 5.0D);
            }
        }
    }
}
