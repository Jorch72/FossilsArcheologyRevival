package fossilsarcheology.server.entity.ai;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class DinoAIGetInWater extends EntityAIBase {
	private final EntityCreature creature;
	private final double movementSpeed;
	private final World world;
	private double shelterX;
	private double shelterY;
	private double shelterZ;

	public DinoAIGetInWater(EntityCreature theCreatureIn, double movementSpeedIn) {
		this.creature = theCreatureIn;
		this.movementSpeed = movementSpeedIn;
		this.world = theCreatureIn.world;
		this.setMutexBits(1);
	}

	public boolean shouldExecute() {
		if (creature.isBeingRidden() || creature instanceof EntityTameable && ((EntityTameable) creature).isSitting() || creature.isInWater() || creature.getAttackTarget() != null && !creature.getAttackTarget().isInWater()) {
			return false;
		} else {
			Vec3d vec3d = this.findPossibleShelter();

			if (vec3d == null) {
				return false;
			} else {
				this.shelterX = vec3d.x;
				this.shelterY = vec3d.y;
				this.shelterZ = vec3d.z;
				return true;
			}
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean shouldContinueExecuting() {
		return !this.creature.getNavigator().noPath();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.creature.getNavigator().tryMoveToXYZ(this.shelterX, this.shelterY, this.shelterZ, this.movementSpeed);
	}

	@Nullable
	private Vec3d findPossibleShelter() {
		Random random = this.creature.getRNG();
		BlockPos blockpos = new BlockPos(this.creature.posX, this.creature.getEntityBoundingBox().minY, this.creature.posZ);

		for (int i = 0; i < 10; ++i) {
			BlockPos blockpos1 = blockpos.add(random.nextInt(20) - 10, random.nextInt(6) - 3, random.nextInt(20) - 10);

			if (this.world.getBlockState(blockpos1).getMaterial() == Material.WATER) {
				return new Vec3d((double) blockpos1.getX(), (double) blockpos1.getY(), (double) blockpos1.getZ());
			}
		}

		return null;
	}
}