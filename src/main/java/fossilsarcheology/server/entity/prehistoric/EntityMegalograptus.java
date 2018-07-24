package fossilsarcheology.server.entity.prehistoric;

import com.google.common.base.Predicate;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.entity.ai.*;
import fossilsarcheology.server.entity.utility.EntityToyBase;
import fossilsarcheology.server.item.FAItemRegistry;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMegalograptus extends EntityPrehistoricSwimming {
    private static final DataParameter<Boolean> SWIMMING = EntityDataManager.createKey(EntitySarcosuchus.class, DataSerializers.BOOLEAN);
    public float swimProgress;
    private boolean isSwimming;

    public EntityMegalograptus(World world) {
        super(world, PrehistoricEntityType.MEGALOGRAPTUS, 1, 3, 5, 25, 0.25, 0.25);
        this.setActualSize(0.9F, 0.5F);
        isAmphibious = true;
        minSize = 0.2F;
        maxSize = 0.8F;
        teenAge = 3;
        this.hasBabyTexture = true;
        this.pediaScale = 30;
    }

    public EnumCreatureAttribute getCreatureAttribute(){
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    protected void dropFewItems(boolean bool, int rand) {
        if (this.isBurning() && this.type.cookedFoodItem != null) {
            this.dropItem(this.type.cookedFoodItem, 1);
        } else if (this.type.foodItem != null) {
            this.dropItem(this.type.foodItem, 1);
        }
    }

    @Override
    public String getTexture() {
        String gender = this.isChild() ? "_baby" : this.getGender() == 0 ? "_female" : "_male";
        if(this.isSkeleton()){
            return "fossil:textures/model/" + type.toString().toLowerCase() + "_0/" + type.toString().toLowerCase() + "_skeleton.png";
        }
        return "fossil:textures/model/" + type.toString().toLowerCase() + "_0/" + type.toString().toLowerCase() + gender + ".png";
    }

    public void initEntityAI() {
        this.tasks.addTask(0, new DinoAIFindWaterTarget(this, 10, true));
        this.tasks.addTask(1, new DinoAIGetInWater(this, 1.0D));
        this.tasks.addTask(2, this.aiSit = new EntityAISit(this));
        this.tasks.addTask(3, new DinoAIRiding(this, 1.0F));
        this.tasks.addTask(3, new DinoMeleeAttackAI(this, 1.5D, false));
        this.tasks.addTask(4, new DinoAIEatFeeders(this));
        this.tasks.addTask(4, new DinoAIEatItems(this));
        this.tasks.addTask(5, new DinoAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, new DinoAIWander(this, 1.0D));
        this.tasks.addTask(7, new DinoAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new DinoAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new DinoAIHunt(this, EntityLivingBase.class, false, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));
        this.navigator.getNodeProcessor().setCanSwim(true);
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
        return PrehistoricEntityTypeAI.Taming.FEEDING;
    }

    @Override
    public PrehistoricEntityTypeAI.Untaming aiUntameType() {
        return PrehistoricEntityTypeAI.Untaming.NONE;
    }

    @Override
    public PrehistoricEntityTypeAI.Moving aiMovingType() {
        return PrehistoricEntityTypeAI.Moving.SEMIAQUATIC;
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
        return 5;
    }

    @Override
    public int getMaxHunger() {
        return 50;
    }

    @Override
    public double swimSpeed() {
        return 0.5D;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        boolean swimming = this.isSwimming();
        if (swimming && swimProgress < 20.0F) {
            swimProgress += 0.5F;
        } else if (!swimming && swimProgress > 0.0F) {
            swimProgress -= 0.5F;
        }
        if (this.isSwimming() && (this.isSitting() || this.isSleeping())) {
            this.setSitting(false);
            this.setSleeping(false);
        }
        if (this.getAttackTarget() != null) {
            if (getAttackBounds().intersects(this.getAttackTarget().getEntityBoundingBox())) {
                if (this.getAnimation() != ATTACK_ANIMATION) {
                    this.setAnimation(ATTACK_ANIMATION);
                }
                if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() > 5) {
                    this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), (float) this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
                }
                this.attackEntityAsMob(this.getAttackTarget());
            }
        }
        if (this.isInWaterMaterial()) {
            this.setSwimming(true);
        } else {
            this.setSwimming(false);
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SWIMMING, false);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Swimming", this.isSwimming);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSwimming(compound.getBoolean("Swimming"));

    }

    public boolean isSwimming() {
        if (world.isRemote) {
            boolean isSwimming = this.dataManager.get(SWIMMING);
            this.isSwimming = isSwimming;
            return isSwimming;
        }

        return isSwimming;
    }

    public void setSwimming(boolean swimming) {
        this.dataManager.set(SWIMMING, swimming);
        if (!world.isRemote) {
            this.isSwimming = swimming;
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FASoundRegistry.MEGALOGRAPTUS_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return FASoundRegistry.MEGALOGRAPTUS_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FASoundRegistry.MEGALOGRAPTUS_DEATH;
    }

    @Override
    public boolean canBeRidden() {
        return false;
    }
}
