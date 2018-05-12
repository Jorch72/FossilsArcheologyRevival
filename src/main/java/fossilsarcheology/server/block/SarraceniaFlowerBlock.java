package fossilsarcheology.server.block;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

public class SarraceniaFlowerBlock extends Block implements DefaultRenderedItem {

	public SarraceniaFlowerBlock() {
		super(Material.PLANTS);
		setCreativeTab(FATabRegistry.BLOCKS);
		setUnlocalizedName("sarracenia_flower");
		setSoundType(SoundType.PLANT);
	}

	@Override
    public boolean isFullCube(IBlockState state) {
		return false;
	}

	public enum EnumPlantTypes implements IStringSerializable {
		SARRACENIA(6, "sarracenia_flower_bottom", "sarracenia_flower_top");

		private static final EnumPlantTypes[] META_LOOKUP = new EnumPlantTypes[values().length];

		static {
			for (EnumPlantTypes blockdoubleplant$enumplanttype : values()) {
				META_LOOKUP[blockdoubleplant$enumplanttype.getMeta()] = blockdoubleplant$enumplanttype;
			}
		}

		private final int meta;
		private final String name;
		private final String unlocalizedName;

		EnumPlantTypes(int meta, String name) {
			this(meta, name, name);
		}

		EnumPlantTypes(int meta, String name, String unlocalizedName) {
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}

		public static EnumPlantTypes byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		public int getMeta() {
			return this.meta;
		}

		public String toString() {
			return this.name;
		}

		@Override
        public String getName() {
			return this.name;
		}

		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}
	}

}
