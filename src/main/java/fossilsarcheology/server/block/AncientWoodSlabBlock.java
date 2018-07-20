package fossilsarcheology.server.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public abstract class AncientWoodSlabBlock extends FossilSlabBlock {
	public AncientWoodSlabBlock(String name, float hardness, float resistance, SoundType soundType) {
		super(name, hardness, resistance, soundType, Material.WOOD, FABlockRegistry.ANCIENT_WOOD);
	}

	@Override
	public ItemBlock getItemBlock() {
		return new FossilSlabBlockItem(this, FABlockRegistry.ANCIENT_WOOD_SINGLESLAB, FABlockRegistry.ANCIENT_WOOD_DOUBLESLAB);
	}

	public static class Double extends FossilSlabBlock {
		public Double(String name, float hardness, float resistance, SoundType soundType) {
			super(name, hardness, resistance, soundType, Material.WOOD, FABlockRegistry.ANCIENT_WOOD);
		}

		@Override
        public boolean isDouble() {
			return true;
		}

		@Override
		public Item getSlabItem() {
			return Item.getItemFromBlock(FABlockRegistry.ANCIENT_WOOD_SINGLESLAB);
		}

		@Override
		public ItemBlock getItemBlock() {
			return new FossilSlabBlockItem(this, FABlockRegistry.ANCIENT_WOOD_SINGLESLAB, FABlockRegistry.ANCIENT_WOOD_DOUBLESLAB);
		}
	}

	public static class Half extends FossilSlabBlock {
		public Half(String name, float hardness, float resistance, SoundType soundType) {
			super(name, hardness, resistance, soundType, Material.WOOD, FABlockRegistry.ANCIENT_WOOD);
		}

		@Override
        public boolean isDouble() {
			return false;
		}

		@Override
		public Item getSlabItem() {
			return Item.getItemFromBlock(FABlockRegistry.ANCIENT_WOOD_SINGLESLAB);
		}

		@Override
		public ItemBlock getItemBlock() {
			return new FossilSlabBlockItem(this, FABlockRegistry.ANCIENT_WOOD_SINGLESLAB, FABlockRegistry.ANCIENT_WOOD_DOUBLESLAB);
		}
	}
}
