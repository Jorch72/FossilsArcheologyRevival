package fossilsarcheology.server.entity;

import fossilsarcheology.Revival;
import fossilsarcheology.server.ServerProxy;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.prehistoric.OrderType;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import fossilsarcheology.server.item.FAItemRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityDinosaurEgg extends EntityLiving implements IEntityAdditionalSpawnData {
    private static final DataParameter<Integer> HATCHING_INDEX = EntityDataManager.createKey(EntityDinosaurEgg.class, DataSerializers.VARINT);

    public static int lastBirthTick;
    public final int totalHatchTime;
    public PrehistoricEntityType selfType;
    public String parentOwner;
    private int hatchTime;

    public EntityDinosaurEgg(World world, PrehistoricEntityType prehistoric) {
        super(world);
        this.parentOwner = "";
        this.totalHatchTime = this.hatchTime;
        this.preventEntitySpawning = true;
        this.setSize(0.5F, 0.6F);
        this.selfType = prehistoric;
        lastBirthTick = 0;
    }

    public EntityDinosaurEgg(World world) {
        this(world, PrehistoricEntityType.TRICERATOPS);
    }

    public EntityDinosaurEgg(World world, PrehistoricEntityType prehistoric, EntityPrehistoric entity) {
        this(world, prehistoric);
        this.parentOwner = entity.getDisplayName().toString();
    }

    public EntityDinosaurEgg(World world, double x, double y, double z, PrehistoricEntityType prehistoric) {
        this(world, prehistoric);
        this.setPosition(x, y, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        if (Revival.RELEASE_TYPE.enableDebugging()) {
            this.hatchTime = 1000;
        } else {
            this.hatchTime = 3000;
        }
        this.dataManager.register(HATCHING_INDEX, 0);
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public String getTexture() {
        return "fossil:textures/model/egg/" + selfType.friendlyName + "_Egg.png";
    }

    private void setPedia() {
        Revival.PEDIA_OBJECT = this;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    public int getBirthTick() {
        return this.dataManager.get(HATCHING_INDEX);
    }

    public void setBirthTick(int i) {
        this.dataManager.set(HATCHING_INDEX, i);
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity entity) {
        return this.getEntityBoundingBox();
    }

    @Override
    public boolean canBePushed() {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return !this.isDead;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.tickHatching();
    }

    private void tickHatching() {
        float brightness = this.getBrightness();
        EntityPlayer player = this.world.getClosestPlayerToEntity(this, 16.0D);
        if ((double) brightness >= 0.5D && !this.inWater) {
            lastBirthTick = this.getBirthTick();
            this.setBirthTick(this.getBirthTick() + 1);
        } else {
            Biome biome = this.world.getBiome(new BlockPos(this));
            float temperature = biome.getTemperature(new BlockPos(this));
            if ((temperature <= 0.15F && brightness < 0.5) || this.inWater) {
                this.setBirthTick(this.getBirthTick() - 1);
            }
        }
        if (this.getBirthTick() >= this.totalHatchTime) {

            Entity entity = this.selfType.invokeClass(this.world);
            if (entity != null) {
                if (entity instanceof EntityPrehistoric) {
                    if (player != null) {
                        //player.addStat(FossilAchievements.FIRST_DINO, 1);

                    }
                    EntityPrehistoric prehistoricEntity = (EntityPrehistoric) entity;
                    if (prehistoricEntity.type.isTameable() && player != null) {
                        if (prehistoricEntity.type != PrehistoricEntityType.TYRANNOSAURUS && prehistoricEntity.type != PrehistoricEntityType.ALLOSAURUS && prehistoricEntity.type != PrehistoricEntityType.SARCOSUCHUS) {
                            prehistoricEntity.setTamed(true);
                            prehistoricEntity.setOwnerId(player.getUniqueID());
                            prehistoricEntity.setOwnerDisplayName(player.getName());
                            prehistoricEntity.currentOrder = OrderType.WANDER;
                            prehistoricEntity.setHealth((float) prehistoricEntity.baseHealth);
                        }
                    }
                    prehistoricEntity.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(prehistoricEntity)), null);
                    prehistoricEntity.setAgeInDays(0);
                    prehistoricEntity.updateAbilities();
                }
                for (int i = 0; i < 4; i++) {
                    double x = this.posX + (this.rand.nextFloat() - 0.5D) * this.width;
                    double y = this.getEntityBoundingBox().minY + 0.1D;
                    double z = this.posZ + (this.rand.nextFloat() - 0.5D) * this.width;
                    double motionX = 4.0D * (this.rand.nextFloat() - 0.5D);
                    double motionZ = (this.rand.nextFloat() - 0.5D) * 4.0D;
                    this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, x, y, z, motionX, 0.5D, motionZ, Item.getIdFromItem(this.selfType.eggItem));
                }
                entity.setLocationAndAngles(Math.floor(this.posX), Math.floor(this.posY) + 1, Math.floor(this.posZ), this.world.rand.nextFloat() * 360.0F, 0.0F);
                if (this.world.isRemote) {
                    return;
                }
                this.world.spawnEntity(entity);
                if (player != null) {
                    player.sendStatusMessage(new TextComponentString(I18n.format("dinoegg.hatched")), false);
                }
                this.setDead();
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("BirthTick", this.getBirthTick());
        compound.setInteger("DinoType", this.selfType.ordinal());
        compound.setString("ParentOwner", this.parentOwner);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        this.setBirthTick(compound.getInteger("BirthTick"));
        this.selfType = PrehistoricEntityType.values()[compound.getInteger("DinoType")];
        this.parentOwner = compound.getString("ParentOwner");
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (damage > 0 && !this.world.isRemote) {
            Item item = this.selfType.eggItem;
            ItemStack stack = new ItemStack(item, 1, 1);
            this.world.spawnEntity(new EntityItem(this.world, this.posX, this.posY, this.posZ, stack));
            this.world.playSound(null, new BlockPos(this), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.NEUTRAL, 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            this.setDead();
        }
        return super.attackEntityFrom(source, damage);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.inventory.getCurrentItem();
        if (itemstack == null) {
            Item item = this.selfType.eggItem;
            ItemStack eggstack = new ItemStack(item);
            if (!player.capabilities.isCreativeMode) {
                if (player.inventory.addItemStackToInventory(eggstack)) {
                    this.world.playSound(null, new BlockPos(this), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.NEUTRAL, 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                }
            }
            this.setDead();
            return true;
        } else if (FMLCommonHandler.instance().getSide().isClient() && itemstack.getItem() == FAItemRegistry.DINOPEDIA) {
            this.setPedia();
            player.openGui(Revival.INSTANCE, ServerProxy.GUI_DINOPEDIA, world, (int) posX, (int) posY, (int) posZ);
            return true;
        }
        return false;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(this.selfType.ordinal());
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        this.selfType = PrehistoricEntityType.values()[additionalData.readInt()];
    }
}
