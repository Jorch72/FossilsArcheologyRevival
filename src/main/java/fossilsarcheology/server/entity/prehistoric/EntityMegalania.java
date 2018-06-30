package fossilsarcheology.server.entity.prehistoric;

import com.google.common.base.Predicate;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.entity.ai.*;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityMegalania extends EntityPrehistoric {

    public static final Animation ANIMATION_FIGHT = Animation.create(40);
    private int ticksSinceLastFight;

    public EntityMegalania(World world) {
        super(world, PrehistoricEntityType.MEGALANIA, 2, 10, 10, 50, 0.25, 0.5);
        this.setActualSize(2.1F, 1.2F);
        this.pediaScale = 35F;
        this.nearByMobsAllowed = 5;
        minSize = 0.25F;
        maxSize = 1.9F;
        developsResistance = true;
        breaksBlocks = true;
        this.teenAge = 5;
        this.ridingY = 1.7F;
    }

    public void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit = new EntityAISit(this));
        this.tasks.addTask(3, new DinoAIRiding(this, 1.0F));
        this.tasks.addTask(4, new DinoMeleeAttackAI(this, 1.0D, false));
        this.tasks.addTask(5, new DinoAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
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
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("FightTicks", ticksSinceLastFight);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.ticksSinceLastFight = compound.getInteger("FightTicks");
    }
    protected void collideWithEntity(Entity entityIn) {
        if (this.canFight() && entityIn instanceof EntityMegalania && !((EntityMegalania)entityIn).canFight()) {
            this.setAnimation(ANIMATION_FIGHT);
            EntityMegalania megalania = ((EntityMegalania)entityIn);
            this.ticksSinceLastFight = 0;
            megalania.ticksSinceLastFight = 0;
            megalania.setAnimation(ANIMATION_FIGHT);
            this.faceEntity(entityIn, 180, 180);
            megalania.faceEntity(this, 180, 180);
            this.rotationYawHead = rotationYaw;
        }
        super.collideWithEntity(entityIn);
    }

    public boolean canFight(){
        return !this.isChild() && this.ticksSinceLastFight > 600 && !this.isMovementBlocked();
    }

    @Override
    public int getAttackLength() {
        return 30;
    }

    @Override
    public int getAdultAge() {
        return 10;
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
        return PrehistoricEntityTypeAI.Attacking.BASIC;
    }

    @Override
    public PrehistoricEntityTypeAI.Climbing aiClimbType() {
        return PrehistoricEntityTypeAI.Climbing.NONE;
    }

    @Override
    public PrehistoricEntityTypeAI.Following aiFollowType() {
        return PrehistoricEntityTypeAI.Following.NONE;
    }

    @Override
    public PrehistoricEntityTypeAI.Jumping aiJumpType() {
        return PrehistoricEntityTypeAI.Jumping.BASIC;
    }

    @Override
    public PrehistoricEntityTypeAI.Response aiResponseType() {
        return PrehistoricEntityTypeAI.Response.TERITORIAL;
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
        return PrehistoricEntityTypeAI.Untaming.ATTACK;
    }

    @Override
    public PrehistoricEntityTypeAI.Moving aiMovingType() {
        return PrehistoricEntityTypeAI.Moving.WALK;
    }

    @Override
    public PrehistoricEntityTypeAI.WaterAbility aiWaterAbilityType() {
        return PrehistoricEntityTypeAI.WaterAbility.NONE;
    }

    @Override
    public boolean doesFlock() {
        return false;
    }

    @Override
    public Item getOrderItem() {
        return Items.BONE;
    }

    @Override
    public int getTailSegments() {
        return 3;
    }

    @Override
    public float getMaleSize() {
        return 1.15F;
    }

    @Override
    public boolean isMovementBlocked() {
        return this.getAnimation() == ANIMATION_FIGHT || super.isMovementBlocked();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(this.getAnimation() == ANIMATION_FIGHT){
            this.motionX = 0;
            this.motionZ = 0;
            this.renderYawOffset = this.rotationYaw;
        }
        ticksSinceLastFight++;
        if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 12 && this.getAttackTarget() != null) {
            this.attackEntityAsMob(this.getAttackTarget());
        }
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
                    if (entity.getRidingEntity() == this) {
                        entity.startRiding(null);
                    }
                }
                entity.motionY += 0.1000000059604645D;
                entity.isAirBorne = false;
                return flag;
            }
        }
        return false;
    }

    @Override
    public int getMaxHunger() {
        return 125;
    }

    @Override
    public boolean canBeRidden() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FASoundRegistry.MEGALANIA_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return FASoundRegistry.MEGALANIA_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FASoundRegistry.MEGALANIA_DEATH;
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[] { SPEAK_ANIMATION, ATTACK_ANIMATION, ANIMATION_FIGHT };
    }

}