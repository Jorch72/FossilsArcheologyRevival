package fossilsarcheology.server.entity.prehistoric;

import com.google.common.base.Predicate;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.entity.ai.*;
import fossilsarcheology.server.entity.utility.EntityToyBase;
import fossilsarcheology.server.item.FAItemRegistry;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityLiopleurodon extends EntityPrehistoricSwimming {

	public static final Animation SHAKE_ANIMATION = Animation.create(50);

	public EntityLiopleurodon(World world) {
		super(world, PrehistoricEntityType.LIOPLEURODON, 2, 12, 10, 45, 0.3, 0.4);
		this.hasBabyTexture = false;
		this.setActualSize(2.25F, 0.7F);
		minSize = 0.45F;
		maxSize = 1.6F;
		teenAge = 6;
		developsResistance = true;
		breaksBlocks = false;
		hasBabyTexture = false;
		pediaScale = 30;
		this.ridingY = 1.3F;
	}

	public void initEntityAI() {
		this.tasks.addTask(0, new DinoAIFindWaterTarget(this, 10, true));
		this.tasks.addTask(2, this.aiSit = new EntityAISit(this));
		this.tasks.addTask(3, new DinoAIRiding(this, 1.5));
		this.tasks.addTask(4, new DinoAIEatFeeders(this));
		this.tasks.addTask(4, new DinoAIEatItems(this));
		this.tasks.addTask(5, new DinoAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(5, new DinoAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(4, new DinoAIHunt(this, EntityLivingBase.class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));
	}

	@Override
	public void setSpawnValues() {
	}

	@Override
	public PrehistoricEntityTypeAI.Activity aiActivityType() {

		return PrehistoricEntityTypeAI.Activity.DIURINAL;
	}

	@Override
	public PrehistoricEntityTypeAI.Attacking aiAttackType() {

		return PrehistoricEntityTypeAI.Attacking.DROWN;
	}

	@Override
	public PrehistoricEntityTypeAI.Climbing aiClimbType() {

		return PrehistoricEntityTypeAI.Climbing.NONE;
	}

	@Override
	public PrehistoricEntityTypeAI.Following aiFollowType() {

		return PrehistoricEntityTypeAI.Following.AGRESSIVE;
	}

	@Override
	public PrehistoricEntityTypeAI.Jumping aiJumpType() {

		return PrehistoricEntityTypeAI.Jumping.BASIC;
	}

	@Override
	public PrehistoricEntityTypeAI.Response aiResponseType() {

		return PrehistoricEntityTypeAI.Response.AGRESSIVE;
	}

	@Override
	public PrehistoricEntityTypeAI.Stalking aiStalkType() {

		return PrehistoricEntityTypeAI.Stalking.NONE;
	}

	@Override
	public PrehistoricEntityTypeAI.Taming aiTameType() {

		return PrehistoricEntityTypeAI.Taming.BLUEGEM;
	}

	@Override
	public PrehistoricEntityTypeAI.Untaming aiUntameType() {

		return PrehistoricEntityTypeAI.Untaming.NONE;
	}

	@Override
	public PrehistoricEntityTypeAI.Moving aiMovingType() {

		return PrehistoricEntityTypeAI.Moving.AQUATIC;
	}

	@Override
	public PrehistoricEntityTypeAI.WaterAbility aiWaterAbilityType() {

		return PrehistoricEntityTypeAI.WaterAbility.ATTACK;
	}

	@Override
	public boolean doesFlock() {

		return false;
	}

	@Override
	public Item getOrderItem() {
		return FAItemRegistry.SKULL_STICK;
	}

	@Override
	public int getAdultAge() {
		return 13;
	}

	@Override
	public double swimSpeed() {
		return 6D;
	}

	@Override
	public int getMaxHunger() {
		return 125;
	}

	public boolean breaksBoats() {
		return true;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (this.getAttackTarget() != null) {
			if (canReachPrey()) {
				this.attackEntityAsMob(this.getAttackTarget());
				if (!isEntitySmallerThan(this.getAttackTarget(), 1.5F * (this.getAgeScale() / this.maxSize)) || this.getRNG().nextInt(5) != 0) {
					if (this.getAnimation() != ATTACK_ANIMATION && this.getAnimation() != SHAKE_ANIMATION) {
						this.setAnimation(ATTACK_ANIMATION);
					}
					this.faceEntity(this.getAttackTarget(), 30, 30);
					if (this.getAnimation() == ATTACK_ANIMATION && (this.getAnimationTick() == 5 || this.getAnimationTick() == 6)) {
						this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), (float) this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue());
					}
				} else if(this.getAnimation() != ATTACK_ANIMATION){
					this.getAttackTarget().startRiding(this);
					if (this.getAnimation() != SHAKE_ANIMATION) {
						this.setAnimation(SHAKE_ANIMATION);
					}
				}
			}
		}

	}

	@Override
	public void updatePassenger(Entity passenger) {
		super.updatePassenger(passenger);
		if (passenger instanceof EntityLivingBase) {
			Entity riddenByEntity = passenger;
			if ((this.getAnimationTick() > 55 || this.getAnimation() == NO_ANIMATION)) {
				if (riddenByEntity instanceof EntityToyBase) {
					riddenByEntity.dismountRidingEntity();
					this.setAttackTarget(null);
					this.doPlayBonus(((EntityToyBase) riddenByEntity).toyBonus);
					return;
				} else {
					if (passenger instanceof EntityLivingBase) {
						riddenByEntity.attackEntityFrom(DamageSource.causeMobDamage(this), Math.max(((EntityLivingBase) riddenByEntity).getMaxHealth(), 100));

					}
					this.onKillEntity((EntityLivingBase) riddenByEntity);
				}
			}

			float modTick_1 = 5 * MathHelper.sin((float) (Math.PI + (this.getAnimationTick() * 0.275F)));
			rotationYaw = renderYawOffset;
			float radius = 0.5F * (0.7F * getAgeScale()) * -3;
			float angle = (0.01745329251F * this.renderYawOffset) + 3.15F + (modTick_1 * 1.75F) * 0.05F;
			double extraX = (double) (radius * MathHelper.sin((float) (Math.PI + angle)));
			double extraZ = (double) (radius * MathHelper.cos(angle));
			double extraY = 0.15F * (getAgeScale());
			super.updateRidden();
			riddenByEntity.setPosition(this.posX + extraX, this.posY + extraY, this.posZ + extraZ);
		}
	}

	@Override
	public void updateRidden() {
		if (this.getRidingPlayer() != null && this.isOwner(this.getRidingPlayer())) {
			super.updateRidden();
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		return true;
	}

	@Override
	public boolean canBeRidden() {
		return true;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return this.isInWater() ? FASoundRegistry.LIOPLEURODON_LIVING_INSIDE : FASoundRegistry.LIOPLEURODON_LIVING_OUTSIDE;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return FASoundRegistry.LIOPLEURODON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return FASoundRegistry.LIOPLEURODON_DEATH;
	}
}
