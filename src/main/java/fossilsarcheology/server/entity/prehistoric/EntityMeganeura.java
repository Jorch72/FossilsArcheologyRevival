package fossilsarcheology.server.entity.prehistoric;

import com.google.common.base.Predicate;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.entity.ai.*;
import fossilsarcheology.server.item.FAItemRegistry;
import fossilsarcheology.server.item.variant.DinosaurBoneType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class EntityMeganeura extends EntityPrehistoricSwimming {


    public EntityMeganeura(World world) {
        super(world, PrehistoricEntityType.MEGANEURA, 2, 4, 4, 18, 0.15, 0.2);
        this.setActualSize(1.9F, 0.95F);
        minSize = 0.15F;
        maxSize = 0.3F;
        teenAge = 4;
        pediaScale = 45;
    }

    @Override
    public boolean attackEntityFrom(DamageSource dmg, float i) {
        if (dmg == DamageSource.IN_WALL) {
            return false;
        }
        return super.attackEntityFrom(dmg, i);
    }
    @Override
    protected void dropFewItems(boolean bool, int rand) {
        if (this.isBurning() && this.type.cookedFoodItem != null) {
            this.dropItem(this.type.cookedFoodItem, 1);
        } else if (this.type.foodItem != null) {
            this.dropItem(this.type.foodItem, 1);
        }
    }

    public void initEntityAI() {
        this.tasks.addTask(0, new DinoAIFindWaterTarget(this, 10, true));
        this.tasks.addTask(1, new MeganeuraAIGetInWater(this, 1.0D));
        this.tasks.addTask(2, this.aiSit = new EntityAISit(this));
        this.tasks.addTask(3, new MeganeuraAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(4, new AIWander());
        this.tasks.addTask(5, new DinoMeleeAttackAI(this, 1.5D, false));
        this.tasks.addTask(6, new DinoAILeapAtTarget(this));
        this.tasks.addTask(7, new DinoAIEatBlocks(this));
        this.tasks.addTask(7, new DinoAIEatFeeders(this));
        this.tasks.addTask(7, new DinoAIEatItems(this));
        this.tasks.addTask(8, new DinoAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new DinoAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new DinoAIHunt(this, EntityLivingBase.class, false, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));

    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        boolean flying = isFlying();
        if (flying && flyProgress < 20.0F) {
            flyProgress += 0.5F;
            if (sitProgress != 0)
                sitProgress = sleepProgress = 0F;
        } else if (!flying && flyProgress > 0.0F) {
            flyProgress -= 0.5F;
            if (sitProgress != 0)
                sitProgress = sleepProgress = 0F;
        }
        if(!this.isMovementBlocked() && !this.useSwimAI()){
            this.motionY += 0.08D;
        } else if(!this.isChild()){
            this.moveHelper.action = EntityMoveHelper.Action.WAIT;
        }
        if(flying && this.ticksExisted % 20 == 0 && !world.isRemote && !this.isChild()){
            this.playSound(FASoundRegistry.MEGANEURA_FLY, 1, 1);
        }
        if (this.getAnimation() == ATTACK_ANIMATION && this.getAttackTarget() != null) {
            this.attackEntityAsMob(this.getAttackTarget());
        }
    }

    private boolean isFlying(){
        return !this.onGround && !this.isMovementBlocked();
    }



    protected void switchNavigator(boolean onLand) {
        if (onLand) {
            this.moveHelper = new FlightMoveHelper(this);
            this.navigator = new PathNavigateFlying(this, world);
            this.isLandNavigator = true;
        } else {
            this.moveHelper = new EntityPrehistoricSwimming.SwimmingMoveHelper();
            this.navigator = new PathNavigateSwimmer(this, world);
            this.isLandNavigator = false;
        }
    }

    public boolean isSleeping() {
       return false;
    }

    @Override
    public String getTexture() {
        String gender = this.isChild() ? "_baby" : this.getGender() == 0 ? "_female" : "_male";
        if(this.isSkeleton()){
            return "fossil:textures/model/" + type.toString().toLowerCase() + "_0/" + type.toString().toLowerCase() + "_skeleton.png";
        }
        return "fossil:textures/model/" + type.toString().toLowerCase() + "_0/" + type.toString().toLowerCase() + gender + ".png";
    }

    protected boolean useSwimAI() {
        return this.isChild();
    }

    @Override
    public int getAdultAge() {
        return 9;
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

        return PrehistoricEntityTypeAI.Response.SCARED;
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

        return PrehistoricEntityTypeAI.Untaming.ATTACK;
    }

    @Override
    public PrehistoricEntityTypeAI.Moving aiMovingType() {

        return PrehistoricEntityTypeAI.Moving.FLIGHT;
    }

    @Override
    public PrehistoricEntityTypeAI.WaterAbility aiWaterAbilityType() {

        return PrehistoricEntityTypeAI.WaterAbility.IGNOREANDFISH;
    }

    @Override
    public double swimSpeed() {
        return 0.1D;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (this.getAttackBounds().intersects(entity.getEntityBoundingBox())) {
            if (this.getAnimation() == NO_ANIMATION) {
                this.setAnimation(ATTACK_ANIMATION);
                return false;
            }
            if (this.getAnimation() == ATTACK_ANIMATION) {
                IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
                boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) iattributeinstance.getAttributeValue());
                if (entity.getRidingEntity() != null) {
                    if (entity.getRidingEntity() == this) {
                        entity.startRiding(null);
                    }
                }
                return flag;
            }
        }
        return false;
    }



    @Override
    public boolean doesFlock() {
        return false;
    }

    @Override
    public Item getOrderItem() {
        return Items.ARROW;
    }

    @Override
    public float getMaleSize() {
        return 0.8F;
    }

    @Override
    public int getMaxHunger() {
        return 45;
    }

    @Override
    public boolean canBeRidden() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FASoundRegistry.MEGANEURA_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return FASoundRegistry.MEGANEURA_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FASoundRegistry.MEGANEURA_DEATH;
    }

    public static BlockPos getPositionRelativetoGround(Entity entity, World world, double x, double z, Random rand) {
        BlockPos pos = new BlockPos(x, entity.posY, z);
        for (int yDown = 0; yDown < 3; yDown++) {
            if (!world.isAirBlock(pos.down(yDown))) {
                return pos.up(yDown);
            }
        }
        return pos;
    }

    class FlightMoveHelper extends EntityMoveHelper {
        public FlightMoveHelper(EntityMeganeura meganura) {
            super(meganura);
            this.speed = 3F;
        }

        public void onUpdateMoveHelper() {
            if (this.action == EntityMoveHelper.Action.MOVE_TO) {
                double d0 = this.posX - EntityMeganeura.this.posX;
                double d1 = this.posY - EntityMeganeura.this.posY;
                double d2 = this.posZ - EntityMeganeura.this.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                d3 = (double) MathHelper.sqrt(d3);

                if (d3 < EntityMeganeura.this.getEntityBoundingBox().getAverageEdgeLength()) {
                    this.action = EntityMoveHelper.Action.WAIT;
                    EntityMeganeura.this.motionX *= 0.5D;
                    EntityMeganeura.this.motionY *= 0.5D;
                    EntityMeganeura.this.motionZ *= 0.5D;
                } else {
                    EntityMeganeura.this.motionX += d0 / d3 * 0.1D * this.speed;
                    EntityMeganeura.this.motionY += d1 / d3 * 0.1D * this.speed;
                    EntityMeganeura.this.motionZ += d2 / d3 * 0.1D * this.speed;

                    if (EntityMeganeura.this.getAttackTarget() == null) {
                        EntityMeganeura.this.rotationYaw = -((float) MathHelper.atan2(EntityMeganeura.this.motionX, EntityMeganeura.this.motionZ)) * (180F / (float) Math.PI);
                        EntityMeganeura.this.renderYawOffset = EntityMeganeura.this.rotationYaw;
                    } else {
                        double d4 = EntityMeganeura.this.getAttackTarget().posX - EntityMeganeura.this.posX;
                        double d5 = EntityMeganeura.this.getAttackTarget().posZ - EntityMeganeura.this.posZ;
                        EntityMeganeura.this.rotationYaw = -((float) MathHelper.atan2(d4, d5)) * (180F / (float) Math.PI);
                        EntityMeganeura.this.renderYawOffset = EntityMeganeura.this.rotationYaw;
                    }
                }
            }
        }
    }

    class AIWander extends EntityAIBase {
        BlockPos target;

        public AIWander() {
            this.setMutexBits(1);
        }

        public boolean shouldExecute() {
            target = EntityMeganeura.getPositionRelativetoGround(EntityMeganeura.this, EntityMeganeura.this.world, EntityMeganeura.this.posX + EntityMeganeura.this.rand.nextInt(32) - 16, EntityMeganeura.this.posZ + EntityMeganeura.this.rand.nextInt(32) - 16, EntityMeganeura.this.rand);
            return !EntityMeganeura.this.useSwimAI() && !EntityMeganeura.this.isSitting() && isDirectPathBetweenPoints(EntityMeganeura.this.getPosition(), target) && !EntityMeganeura.this.getMoveHelper().isUpdating() && EntityMeganeura.this.rand.nextInt(4) == 0;
        }

        protected boolean isDirectPathBetweenPoints(BlockPos posVec31, BlockPos posVec32) {
            RayTraceResult raytraceresult = EntityMeganeura.this.world.rayTraceBlocks(new Vec3d(posVec31.getX() + 0.5D, posVec31.getY() + 0.5D, posVec31.getZ() + 0.5D), new Vec3d(posVec32.getX() + 0.5D, posVec32.getY() + (double) EntityMeganeura.this.height * 0.5D, posVec32.getZ() + 0.5D), false, true, false);
            return raytraceresult == null || raytraceresult.typeOfHit == RayTraceResult.Type.MISS;
        }

        public boolean shouldContinueExecuting() {
            return false;
        }

        public void updateTask() {
            if (!isDirectPathBetweenPoints(EntityMeganeura.this.getPosition(), target)) {
                target = EntityMeganeura.getPositionRelativetoGround(EntityMeganeura.this, EntityMeganeura.this.world, EntityMeganeura.this.posX + EntityMeganeura.this.rand.nextInt(15) - 7, EntityMeganeura.this.posZ + EntityMeganeura.this.rand.nextInt(15) - 7, EntityMeganeura.this.rand);
            }
            if (EntityMeganeura.this.world.isAirBlock(target)) {
                if(!EntityMeganeura.this.isFlying()){
                    EntityMeganeura.this.switchNavigator(false);
                }
                EntityMeganeura.this.moveHelper.setMoveTo((double) target.getX() + 0.5D, (double) target.getY() + 0.5D, (double) target.getZ() + 0.5D, 0.25D);
                if (EntityMeganeura.this.getAttackTarget() == null) {
                    EntityMeganeura.this.getLookHelper().setLookPosition((double) target.getX() + 0.5D, (double) target.getY() + 0.5D, (double) target.getZ() + 0.5D, 180.0F, 20.0F);

                }
            }
        }
    }

}
