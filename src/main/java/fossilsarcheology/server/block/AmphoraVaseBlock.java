package fossilsarcheology.server.block;

import fossilsarcheology.server.block.entity.TileEntityAmphora;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AmphoraVaseBlock extends VaseBlock {
    protected AmphoraVaseBlock() {
        super("amphora");
    }

    @Override
    public Class<? extends TileEntity> getEntity() {
        return TileEntityAmphora.class;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityAmphora();
    }
}
