package fossilsarcheology.server.entity.prehistoric;

import com.google.common.base.Predicate;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.entity.ai.DinoAIEatBlocks;
import fossilsarcheology.server.entity.ai.DinoAIEatFeeders;
import fossilsarcheology.server.entity.ai.DinoAIEatItems;
import fossilsarcheology.server.entity.ai.DinoAIFollowOwner;
import fossilsarcheology.server.entity.ai.DinoAIHunt;
import fossilsarcheology.server.entity.ai.DinoAILookIdle;
import fossilsarcheology.server.entity.ai.DinoAIRiding;
import fossilsarcheology.server.entity.ai.DinoAIWander;
import fossilsarcheology.server.entity.ai.DinoAIWatchClosest;
import fossilsarcheology.server.entity.ai.DinoMeleeAttackAI;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI.Activity;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI.Attacking;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI.Climbing;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI.Following;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI.Jumping;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI.Moving;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI.Response;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI.Stalking;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI.Taming;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI.Untaming;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI.WaterAbility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPachycephalosaurus extends EntityPrehistoric {

	public EntityPachycephalosaurus(World world) {
		super(world, PrehistoricEntityType.PACHYCEPHALOSAURUS, 2, 12, 6, 28, 0.25, 0.4);
		this.setActualSize(1.0F, 1.5F);
		this.nearByMobsAllowed = 4;
		minSize = 0.5F;
		maxSize = 2F;
		teenAge = 4;
		developsResistance = true;
		breaksBlocks = false;
		hasBabyTexture = false;
		this.ridingY = 1.55F;
		this.pediaScale = 40F;
	}

	public void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit = new EntityAISit(this));
		this.tasks.addTask(3, new DinoAIRiding(this, 1.5D));
		this.tasks.addTask(4, new DinoMeleeAttackAI(this, 1.5D, false));
		this.tasks.addTask(5, new DinoAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(6, new DinoAIEatBlocks(this));
		this.tasks.addTask(6, new DinoAIEatFeeders(this));
		this.tasks.addTask(6, new DinoAIEatItems(this));
		this.tasks.addTask(7, new DinoAIWander(this, 1.0D));
		this.tasks.addTask(8, new DinoAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new DinoAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(4, new DinoAIHunt(this, EntityLivingBase.class, false, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));
	}

	@Override
	public int getAttackLength() {
		return 30;
	}

	@Override
	public void setSpawnValues() {
	}

	@Override
	public Activity aiActivityType() {
		return Activity.DIURINAL;
	}

	@Override
	public Attacking aiAttackType() {
		return Attacking.KNOCKUP;
	}

	@Override
	public Climbing aiClimbType() {
		return Climbing.NONE;
	}

	@Override
	public Following aiFollowType() {

		return Following.SKITTISH;
	}

	@Override
	public Jumping aiJumpType() {

		return Jumping.BASIC;
	}

	@Override
	public Response aiResponseType() {

		return Response.TERITORIAL;
	}

	@Override
	public Stalking aiStalkType() {

		return Stalking.NONE;
	}

	@Override
	public Taming aiTameType() {

		return Taming.IMPRINTING;
	}

	@Override
	public Untaming aiUntameType() {

		return Untaming.STARVE;
	}

	@Override
	public Moving aiMovingType() {

		return Moving.WALK;
	}

	@Override
	public WaterAbility aiWaterAbilityType() {
		return WaterAbility.NONE;
	}

	@Override
	public boolean doesFlock() {
		return false;
	}

	@Override
	public Item getOrderItem() {
		return Items.STICK;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 12 && this.getAttackTarget() != null) {
			this.attackEntityAsMob(this.getAttackTarget());
		}
	}

	@Override
	public int getAdultAge() {
		return 10;
	}

	@Override
	public int getTailSegments() {
		return 3;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (this.getAttackBounds().intersects(entity.getEntityBoundingBox())) {
			if (this.getAnimation() == NO_ANIMATION) {
				this.setAnimation(ATTACK_ANIMATION);
				return false;
			}

			if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 12) {
				IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
				boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) iattributeinstance.getAttributeValue());
				if (entity.getRidingEntity() != null) {
					if (entity.isRidingOrBeingRiddenBy(this)) {
						entity.dismountRidingEntity();
					}
				}
				entity.motionY += 0.4000000059604645D;
				knockbackEntity(entity, 2F, 0.1F);
				return flag;
			}
		}
		return false;
	}

	@Override
	public int getMaxHunger() {
		return 100;
	}

	@Override
	public boolean canBeRidden() {
		return true;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return FASoundRegistry.PACHYCEPHALOSAURUS_LIVING;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return FASoundRegistry.PACHYCEPHALOSAURUS_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return FASoundRegistry.PACHYCEPHALOSAURUS_DEATH;
	}
}
