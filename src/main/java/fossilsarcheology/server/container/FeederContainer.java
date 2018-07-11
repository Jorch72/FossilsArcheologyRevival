package fossilsarcheology.server.container;

import fossilsarcheology.server.block.entity.TileEntityFeeder;
import fossilsarcheology.server.entity.prehistoric.Diet;
import fossilsarcheology.server.util.FoodMappings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class FeederContainer extends SyncedFieldContainer {
	public static final int CARN_INPUT = 0, HERB_INPUT = 1;
	int lastVegValue = 0;
	int lastMeatValue = 0;
	private final TileEntityFeeder feeder;

	public FeederContainer(IInventory inventory, TileEntity var2) {
		super(inventory);
		this.feeder = (TileEntityFeeder) var2;
		this.addSlotToContainer(new Slot(this.feeder, 0, 60, 62));
		this.addSlotToContainer(new Slot(this.feeder, 1, 104, 62));
		int var3;

		for (var3 = 0; var3 < 3; ++var3) {
			for (int var4 = 0; var4 < 9; ++var4) {
				this.addSlotToContainer(new Slot(inventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3) {
			this.addSlotToContainer(new Slot(inventory, var3, 8 + var3 * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.feeder.isUsableByPlayer(player);
	}
}
