package fossilsarcheology.server.event;


import fossilsarcheology.Revival;
import fossilsarcheology.server.ServerProxy;
import fossilsarcheology.server.entity.prehistoric.*;
import fossilsarcheology.server.entity.utility.FossilsMammalProperties;
import fossilsarcheology.server.entity.utility.FossilsPlayerProperties;
import fossilsarcheology.server.item.FAItemRegistry;
import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class FossilLivingEvent {

	@SubscribeEvent
	public void onBreakBlock(BlockEvent.BreakEvent event) {
		FossilsPlayerProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(event.getPlayer(), FossilsPlayerProperties.class);
		if (event.getWorld().provider.getDimension() == Revival.CONFIG.dimensionIDDarknessLair && event.getState().getBlock() != Blocks.OBSIDIAN && (properties != null && !properties.killedAnu)) {
			event.getPlayer().sendStatusMessage(new TextComponentString(I18n.format("anu.breakblock")), false);
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void entityInteractEvent(PlayerInteractEvent.EntityInteract event) {
		if (event.getItemStack() != null && event.getItemStack().getItem() != null && event.getItemStack().getItem() == FAItemRegistry.DINOPEDIA && event.getTarget() instanceof EntityAnimal) {
			FossilsMammalProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(event.getTarget(), FossilsMammalProperties.class);
			if ((event.getTarget() instanceof EntityHorse || event.getTarget() instanceof EntityCow || event.getTarget() instanceof EntityPig || event.getTarget() instanceof EntitySheep || event.getTarget() instanceof EntityRabbit) && properties != null && properties.isPregnant) {
				Revival.PEDIA_OBJECT = event.getTarget();
				event.getEntityPlayer().openGui(Revival.INSTANCE, ServerProxy.GUI_DINOPEDIA, event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
			}
		}
	}

	@SubscribeEvent
	public void onEntityLiving(LivingEvent.LivingUpdateEvent event) {
		if ((event.getEntityLiving() instanceof EntityHorse || event.getEntityLiving() instanceof EntityCow || event.getEntityLiving() instanceof EntityPig || event.getEntityLiving() instanceof EntitySheep || event.getEntityLiving() instanceof EntityRabbit)) {
			FossilsMammalProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(event.getEntityLiving(), FossilsMammalProperties.class);

			if (properties.embryo != null && properties.isPregnant) {

				++properties.embryoProgress;
				this.getClass();

				if (properties.embryoProgress >= properties.embryo.growTime) // var10000
				// == 3000)
				{
					growEntity(properties.embryo, event);
					properties.embryoProgress = 0;
					properties.embryo = null;
				}
			}
		}
	}

	public void growEntity(PrehistoricEntityType embryo, LivingEvent.LivingUpdateEvent event) {
		float rnd = new Random().nextInt(100);
		Entity birthEntity;
		EntityLivingBase entity = event.getEntityLiving();
		switch (embryo) {
			case PIG:
				birthEntity = new EntityPig(entity.world);
				break;
			case SHEEP:
				birthEntity = new EntitySheep(entity.world);
				break;
			case COW:
				birthEntity = new EntityCow(entity.world);
				break;
			case CHICKEN:
				birthEntity = new EntityChicken(entity.world);
				break;
			case RABBIT:
				birthEntity = new EntityRabbit(entity.world);
				break;
			case POLARBEAR:
				birthEntity = new EntityPolarBear(entity.world);
				break;
			case LLAMA:
				if (entity instanceof AbstractHorse) {
					if (rnd < 5) {
						birthEntity = new EntityLlama(entity.world);
						if (((AbstractHorse) entity).getOwnerUniqueId() != null) {
							((EntityLlama) birthEntity).setOwnerUniqueId(((AbstractHorse) entity).getOwnerUniqueId());
							((EntityLlama) birthEntity).setHorseTamed(true);
						}
						break;
					} else if (rnd < 10) {
						birthEntity = new EntityLlama(entity.world);
						if (((AbstractHorse) entity).getOwnerUniqueId() != null) {
							((EntityLlama) birthEntity).setOwnerUniqueId(((AbstractHorse) entity).getOwnerUniqueId());
							((EntityLlama) birthEntity).setHorseTamed(true);
						}
						break;
					} else {
						birthEntity = ((EntityLlama) entity).createChild(new EntityLlama(entity.world));
					}
				} else {
					EntityLlama entityHorse = new EntityLlama(entity.world);
					birthEntity = entityHorse.createChild(new EntityLlama(entity.world));
				}
				break;
			case DONKEY:
				if (entity instanceof AbstractHorse) {
					if (rnd < 5) {
						birthEntity = new EntityDonkey(entity.world);
						if (((AbstractHorse) entity).getOwnerUniqueId() != null) {
							((EntityDonkey) birthEntity).setOwnerUniqueId(((AbstractHorse) entity).getOwnerUniqueId());
							((EntityDonkey) birthEntity).setHorseTamed(true);
						}
						break;
					} else if (rnd < 10) {
						birthEntity = new EntityDonkey(entity.world);
						if (((AbstractHorse) entity).getOwnerUniqueId() != null) {
							((EntityDonkey) birthEntity).setOwnerUniqueId(((AbstractHorse) entity).getOwnerUniqueId());
							((EntityDonkey) birthEntity).setHorseTamed(true);
						}
						break;
					} else {
						birthEntity = ((AbstractHorse) entity).createChild(new EntityDonkey(entity.world));
					}
				} else {
					EntityDonkey entityHorse = new EntityDonkey(entity.world);
					birthEntity = entityHorse.createChild(new EntityDonkey(entity.world));
				}
				break;
			case HORSE:
				if (entity instanceof AbstractHorse) {
					if (rnd < 5) {
						birthEntity = new EntityHorse(entity.world);
						((EntityHorse) birthEntity).setHorseVariant(3);
						if (((AbstractHorse) entity).getOwnerUniqueId() != null) {
							((EntityHorse) birthEntity).setOwnerUniqueId(((AbstractHorse) entity).getOwnerUniqueId());
							((EntityHorse) birthEntity).setHorseTamed(true);
						}
						break;
					} else if (rnd < 10) {
						birthEntity = new EntityHorse(entity.world);
						((EntityHorse) birthEntity).setHorseVariant(4);
						if (((AbstractHorse) entity).getOwnerUniqueId() != null) {
							((EntityHorse) birthEntity).setOwnerUniqueId(((AbstractHorse) entity).getOwnerUniqueId());
							((EntityHorse) birthEntity).setHorseTamed(true);
						}
						break;
					} else {
						birthEntity = ((AbstractHorse) entity).createChild(new EntityHorse(entity.world));
					}
				} else {
					EntityHorse entityHorse = new EntityHorse(entity.world);
					birthEntity = entityHorse.createChild(new EntityHorse(entity.world));
				}
				break;
			case SMILODON:
				birthEntity = new EntitySmilodon(entity.world);
				if (entity.world.getClosestPlayerToEntity(entity, 15) != null) {
					((EntitySmilodon) birthEntity).setTamed(true);
					((EntitySmilodon) birthEntity).setOwnerId(entity.world.getClosestPlayerToEntity(entity, 15).getUniqueID());
				}
				break;
			case MAMMOTH:
				birthEntity = (new EntityMammoth(entity.world));
				((EntityPrehistoric) birthEntity).setOwnerId(entity.world.getClosestPlayerToEntity(entity, 15).getUniqueID());
				if (entity.world.getClosestPlayerToEntity(entity, 15) != null) {
					((EntityPrehistoric) birthEntity).setTamed(true);
					((EntityPrehistoric) birthEntity).setOwnerId(entity.world.getClosestPlayerToEntity(entity, 15).getUniqueID());
				}
				break;
			case ELASMOTHERIUM:
				birthEntity = (new EntityElasmotherium(entity.world));
				if (entity.world.getClosestPlayerToEntity(entity, 15) != null) {
					((EntityPrehistoric) birthEntity).setTamed(true);
					((EntityPrehistoric) birthEntity).setOwnerId(entity.world.getClosestPlayerToEntity(entity, 15).getUniqueID());
				}
				break;
			case QUAGGA:
				birthEntity = new EntityQuagga(entity.world);
				int d0 = (int) (entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() + ((EntityQuagga) birthEntity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() + (int) ((EntityQuagga) birthEntity).getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue());
				((EntityQuagga) birthEntity).getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(d0 / 3.0D);
				double d2 = entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue() + ((EntityQuagga) birthEntity).getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue() + ((EntityQuagga) birthEntity).getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();
				((EntityQuagga) birthEntity).getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(d2 / 3.0D);
				break;
			default:
				birthEntity = new EntityPig(entity.world);
		}
		if (!(birthEntity instanceof EntityPrehistoric) && birthEntity instanceof EntityAnimal) {
			((EntityAnimal) birthEntity).setGrowingAge(-24000);
		} else if (birthEntity instanceof EntityPrehistoric) {
			((EntityPrehistoric) birthEntity).setGender(new Random().nextInt(2));
		}
		birthEntity.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);

		for (int var3 = 0; var3 < 7; ++var3) {
			double var4 = event.getEntityLiving().getRNG().nextGaussian() * 0.02D;
			double var6 = event.getEntityLiving().getRNG().nextGaussian() * 0.02D;
			double var8 = event.getEntityLiving().getRNG().nextGaussian() * 0.02D;
			entity.world.spawnParticle(EnumParticleTypes.HEART, entity.posX + (double) (event.getEntityLiving().getRNG().nextFloat() * entity.width * 2.0F) - (double) entity.width, entity.posY + 0.5D + (double) (event.getEntityLiving().getRNG().nextFloat() * entity.height), entity.posZ + (double) (event.getEntityLiving().getRNG().nextFloat() * entity.width * 2.0F) - (double) entity.width, var4, var6, var8);
		}

		if (!entity.world.isRemote) {
			entity.world.spawnEntity(birthEntity);
		}
	}

}
