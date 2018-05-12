package fossilsarcheology.server.block;

import fossilsarcheology.server.block.entity.TileEntityKylix;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class KylixVaseBlock extends VaseBlock {
    protected KylixVaseBlock() {
        super("kylix");
    }

    @Override
    public Class<? extends TileEntity> getEntity() {
        return TileEntityKylix.class;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityKylix();
    }
}
