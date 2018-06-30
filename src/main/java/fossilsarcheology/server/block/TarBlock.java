package fossilsarcheology.server.block;

import fossilsarcheology.server.entity.monster.EntityTarSlime;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class TarBlock extends BlockFluidClassic {
	public TarBlock() {
		super(FAFluidRegistry.TAR_FLUID, FAFluidRegistry.TAR_MATERIAL);
		this.setLightOpacity(1);
		this.setUnlocalizedName("tar");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		IBlockState neighbor = blockAccess.getBlockState(pos.offset(side));
		return neighbor.getBlock() != blockState.getBlock() || !(neighbor.getBlock() == blockState.getBlock() || (!neighbor.isOpaqueCube() && neighbor.getBlock() != Blocks.AIR)) && (side == EnumFacing.UP || neighbor.getBlock() instanceof BlockChest || super.shouldSideBeRendered(blockState, blockAccess, pos, side));
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public void updateTick(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand) {
		super.updateTick(world, pos, state, rand);
		if(rand.nextInt(this.isSourceBlock(world, pos) ? 50 : 250) == 0){
			EntityTarSlime tarSlime = new EntityTarSlime(world);
			tarSlime.onInitialSpawn(world.getDifficultyForLocation(pos), null);
			tarSlime.setPositionAndRotation(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 0, 0);
			if(!world.isRemote){
				world.spawnEntity(tarSlime);
			}
		}
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		if (world.isAirBlock(pos)) {
			return true;
		}
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		if (block == this) {
			return false;
		}
		if (this.displacements.containsKey(block)) {
			if (this.displacements.get(block)) {
				block.dropBlockAsItem(world, pos, state, 0);
				return true;
			}
			return false;
		}
		Material material = state.getMaterial();
		if (material.blocksMovement() || material == Material.WATER || material == Material.PORTAL) {
			return false;
		}
		int density = getDensity(world, pos);
		if (density == Integer.MAX_VALUE) {
			block.dropBlockAsItem(world, pos, state, 0);
			return true;
		}
		return this.density > density;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityTarSlime) {
			EntityLivingBase living = (EntityLivingBase) entity;
			living.motionY += 0.01F;
			living.fallDistance = 0;
		}
		else if (entity instanceof EntityLivingBase) {
			EntityLivingBase living = (EntityLivingBase) entity;
			living.motionX *= 0.1;
			living.motionZ *= 0.1;
			living.setInWeb();
		}
	}

	@Deprecated
	public boolean causesSuffocation(IBlockState state) {
		return true;
	}
}
