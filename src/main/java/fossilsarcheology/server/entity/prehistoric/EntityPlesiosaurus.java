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
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPlesiosaurus extends EntityPrehistoricSwimming {

	public EntityPlesiosaurus(World world) {
		super(world, PrehistoricEntityType.PLESIOSAUR, 2, 12, 10, 30, 0.2, 0.3);
		FISH_ANIMATION = Animation.create(40);
		this.setActualSize(1.0F, 1.5F);
		minSize = 0.25F;
		maxSize = 1.05F;
		teenAge = 3;
		developsResistance = true;
		pediaScale = 40;
	}

	public void initEntityAI() {
		this.tasks.addTask(0, new DinoAIFindWaterTarget(this, 10, true));
		this.tasks.addTask(2, this.aiSit = new EntityAISit(this));
		this.tasks.addTask(3, new DinoAIEatFeeders(this));
		this.tasks.addTask(3, new DinoAIEatItems(this));
		this.tasks.addTask(4, new DinoAIMakeFish(this));
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

		return PrehistoricEntityTypeAI.Activity.BOTH;
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

		return PrehistoricEntityTypeAI.Following.NORMAL;
	}

	@Override
	public PrehistoricEntityTypeAI.Jumping aiJumpType() {

		return PrehistoricEntityTypeAI.Jumping.BASIC;
	}

	@Override
	public PrehistoricEntityTypeAI.Response aiResponseType() {

		return PrehistoricEntityTypeAI.Response.WATERCALM;
	}

	@Override
	public PrehistoricEntityTypeAI.Stalking aiStalkType() {

		return PrehistoricEntityTypeAI.Stalking.NONE;
	}

	@Override
	public PrehistoricEntityTypeAI.Taming aiTameType() {

		return PrehistoricEntityTypeAI.Taming.IMPRINTING;
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

		return FAItemRegistry.SHELL;
	}

	@Override
	public int getAdultAge() {
		return 7;
	}

	@Override
	public String getTexture() {
		if (isSkeleton()) {
			return "fossil:textures/model/plesiosaurus_0/" + "plesiosaurus_skeleton.png";
		}
		String toggle = this.hasFeatherToggle ? !this.featherToggle ? "feathered/" : "scaled/" : "";
		String gender = this.getGender() == 0 ? "_female" : "_male";
		String sleeping = !this.isSleeping() ? "" : "_sleeping";
		String toggleList = this.hasFeatherToggle ? !this.featherToggle ? "_feathered" : "_scaled" : "";
		return "fossil:textures/model/plesiosaurus_0/" + toggle + "plesiosaurus" + gender + toggleList + sleeping + ".png";
	}

	@Override
	public double swimSpeed() {
		return 2;
	}

	@Override
	public int getMaxHunger() {
		return 125;
	}

	@Override
	public Animation[] getAnimations() {
		return new Animation[]{SPEAK_ANIMATION, ATTACK_ANIMATION, FISH_ANIMATION};
	}

	@Override
	public boolean canBeRidden() {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return this.isInWater() ? FASoundRegistry.PLESIOSAURUS_LIVING_INSIDE : FASoundRegistry.PLESIOSAURUS_LIVING_OUTSIDE;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return FASoundRegistry.PLESIOSAURUS_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return FASoundRegistry.PLESIOSAURUS_DEATH;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (this.getAttackTarget() != null) {
			this.attackEntityAsMob(this.getAttackTarget());
		}
	}

	public int getAttackLength() {
		return 10;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (this.canReachPrey()) {
			if (this.getAnimation() == NO_ANIMATION) {
				this.setAnimation(ATTACK_ANIMATION);
				return false;
			}
			if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() >= 4 && this.getAnimationTick() <= 7) {
				IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
				boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) iattributeinstance.getAttributeValue());
				if (entity.getRidingEntity() != null) {
					if (entity.getRidingEntity() == this) {
						entity.startRiding(null);
					}
				}
				if(entity instanceof EntityToyBase){
					knockBackMob(entity, 0.1F, 0.1F, 0.1F);
				}
				return flag;
			}
		}
		return false;
	}

}
