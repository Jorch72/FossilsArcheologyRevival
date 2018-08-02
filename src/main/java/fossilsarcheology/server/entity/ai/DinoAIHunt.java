package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.entity.FoodHelper;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.prehistoric.PrehistoricMoodType;
import fossilsarcheology.server.entity.utility.EntityToyBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;

import java.util.function.Predicate;

public class DinoAIHunt<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
	private final EntityPrehistoric dino;

	public DinoAIHunt(EntityPrehistoric entityIn, Class<T> classTarget, boolean checkSight, Predicate<? super T> targetSelector) {
		super(entityIn, classTarget, 0, checkSight, false, targetSelector::test);
		this.dino = entityIn;
	}

	@Override
	public boolean shouldExecute() {
		if (this.dino.isBeingRidden() || this.dino.isMovementBlocked()) {
			return false;
		}
		if (super.shouldExecute() && this.targetEntity != null && !this.targetEntity.getClass().equals(this.dino.getClass())) {
			if (this.dino.width >= this.targetEntity.width) {
				if (this.taskOwner instanceof EntityPrehistoric && !((EntityPrehistoric) this.taskOwner).isMovementBlocked()) {
					EntityPrehistoric prehistoric = (EntityPrehistoric) this.taskOwner;
					if (targetEntity instanceof EntityPlayer && ((EntityPlayer) targetEntity).capabilities.isCreativeMode) {
						return false;
					}
					if (targetEntity instanceof EntityPlayer) {
						if (taskOwner.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
							return false;
						}
						if (prehistoric.getMood() < 0 && prehistoric.getMoodFace() != PrehistoricMoodType.CALM) {
							return !((EntityPlayer) targetEntity).capabilities.isCreativeMode;
						} else if (prehistoric.getMood() > 25 && prehistoric.getMoodFace() != PrehistoricMoodType.CALM) {
							return false;
						} else if (prehistoric.getMoodFace() == PrehistoricMoodType.CALM) {
							return !prehistoric.isOwner(targetEntity) && prehistoric.canDinoHunt(targetEntity, true);
						}
					}

					if (FoodHelper.getMobFoodPoints(targetEntity, dino.type.diet) > 0) {
						return !prehistoric.isOwner(targetEntity) && prehistoric.canDinoHunt(targetEntity, true);
					}
					if (targetEntity instanceof EntityToyBase && prehistoric.ticksTillPlay == 0 && prehistoric.getMood() < 100) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
