package fossilsarcheology.server.item;

import fossilsarcheology.Revival;
import fossilsarcheology.client.sound.FASoundRegistry;
import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.entity.EntityDinosaurEgg;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.prehistoric.OrderType;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI;
import fossilsarcheology.server.entity.utility.FossilsPlayerProperties;
import fossilsarcheology.server.message.MessageUpdateEgg;
import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DinoEggItem extends PrehistoricEntityItem implements DefaultRenderedItem {
	public DinoEggItem(PrehistoricEntityType type) {
		super("egg", type);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.maxStackSize = 1;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		boolean success = this.spawnEgg(world, player, this.type, pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
		if (success && !player.capabilities.isCreativeMode) {
			player.getHeldItem(hand).shrink(1);
		}
		return success ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
	}

	private boolean spawnEgg(World world, EntityPlayer player, PrehistoricEntityType type, double x, double y, double z) {
		Entity egg;
		if (!type.isAquatic()) {
			egg = new EntityDinosaurEgg(world, type);
			egg.setLocationAndAngles(x, y + 1.5F, z, world.rand.nextFloat() * 360.0F, 0.0F);
			if (!world.isRemote) {
				world.spawnEntity(egg);
			}
			((EntityDinosaurEgg) egg).selfType = type;
			if (!world.isRemote) {
				Revival.NETWORK_WRAPPER.sendToAll(new MessageUpdateEgg(egg.getEntityId(), type.ordinal()));
			}
			return true;
		} else {
			egg = type.invokeClass(world);
			if (egg != null) {
				egg.setLocationAndAngles(x, y + 1, z, world.rand.nextFloat() * 360.0F, 0.0F);

				if (egg instanceof EntityPrehistoric) {
					EntityPrehistoric prehistoric = (EntityPrehistoric) egg;
					prehistoric.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(prehistoric)), null);
					prehistoric.setAgeInDays(0);
					prehistoric.grow(0);
					prehistoric.updateAbilities();
					prehistoric.setNoAI(false);
					if (prehistoric.aiTameType() == PrehistoricEntityTypeAI.Taming.IMPRINTING) {
						prehistoric.setTamed(true);
						prehistoric.setOwnerId(player.getUniqueID());
						FossilsPlayerProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(player, FossilsPlayerProperties.class);
						if(properties != null && !properties.hasHatchedDinosaur){
							properties.hasHatchedDinosaur = true;
							Revival.PROXY.playSound(FASoundRegistry.MUSIC_FIRST_DINOSAUR);
						}
						prehistoric.setOwnerDisplayName(player.getName());
						prehistoric.currentOrder = OrderType.WANDER;
						prehistoric.setHealth((float) prehistoric.baseHealth);
					}
				}
				if (!world.isRemote) {
					world.spawnEntity(egg);
				}
			}
		}
		return egg != null;
	}
}
