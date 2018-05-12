package fossilsarcheology.server.block;

import fossilsarcheology.server.block.entity.TileEntityVolute;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class VoluteVaseBlock extends VaseBlock {
    protected VoluteVaseBlock() {
        super("volute");
    }

    @Override
    public Class<? extends TileEntity> getEntity() {
        return TileEntityVolute.class;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityVolute();
    }
}
