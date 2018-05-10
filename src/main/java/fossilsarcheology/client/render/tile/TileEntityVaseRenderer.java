package fossilsarcheology.client.render.tile;

import fossilsarcheology.Revival;
import fossilsarcheology.client.model.ModelVaseAmphora;
import fossilsarcheology.client.model.ModelVaseKylix;
import fossilsarcheology.client.model.ModelVaseVolute;
import fossilsarcheology.server.block.entity.TileEntityAmphora;
import fossilsarcheology.server.block.entity.TileEntityKylix;
import fossilsarcheology.server.block.entity.TileEntityVolute;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class TileEntityVaseRenderer extends TileEntitySpecialRenderer {

    public static final ModelVaseVolute VOLUTE_MODEL = new ModelVaseVolute();
    public static final ModelVaseAmphora AMPHORA_MODEL = new ModelVaseAmphora();
    public static final ModelVaseKylix KYLIX_MODEL = new ModelVaseKylix();
    private static final ResourceLocation DAMAGED_VOLUTE = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_damaged_volute.png");
    private static final ResourceLocation RESTORED_VOLUTE = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_restored_volute.png");
    private static final ResourceLocation RED_FIGURE_VOLUTE = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_red_figure_volute.png");
    private static final ResourceLocation BLACK_FIGURE_VOLUTE = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_black_figure_volute.png");
    private static final ResourceLocation PORCELAIN_VOLUTE = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_porcelain_volute.png");
    private static final ResourceLocation DAMAGED_AMPHORA = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_damaged_amphora.png");
    private static final ResourceLocation RESTORED_AMPHORA = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_restored_amphora.png");
    private static final ResourceLocation RED_FIGURE_AMPHORA = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_red_figure_amphora.png");
    private static final ResourceLocation BLACK_FIGURE_AMPHORA = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_black_figure_amphora.png");
    private static final ResourceLocation PORCELAIN_AMPHORA = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_porcelain_amphora.png");
    private static final ResourceLocation DAMAGED_KYLIX = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_damaged_kylix.png");
    private static final ResourceLocation RESTORED_KYLIX = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_restored_kylix.png");
    private static final ResourceLocation RED_FIGURE_KYLIX = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_red_figure_kylix.png");
    private static final ResourceLocation BLACK_FIGURE_KYLIX = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_black_figure_kylix.png");
    private static final ResourceLocation PORCELAIN_KYLIX = new ResourceLocation(Revival.MODID, "textures/blocks/vases/vase_porcelain_kylix.png");
    private int vaseType;

    public TileEntityVaseRenderer(int vaseType) {
        this.vaseType = vaseType;
    }

    @Override
    public void render(TileEntity te, double x, double y, double z, float scale, int destroyProgress, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GlStateManager.pushMatrix();
        GlStateManager.rotate(180F, 1.0F, 0.0F, 1.0F);
        this.rotateBlock(te.getWorld(), te);
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
    }

    // rotates block
    private void rotateBlock(World world, TileEntity te) {
        if (world != null) {
            int dir = 0;
            int meta = 0;
            if (te instanceof TileEntityAmphora) {
                dir = ((TileEntityAmphora) te).getVaseRotation();
                meta = ((TileEntityAmphora) te).getVaseType();
            }
            if (te instanceof TileEntityKylix) {
                dir = ((TileEntityKylix) te).getVaseRotation();
                meta = ((TileEntityKylix) te).getVaseType();
            }
            if (te instanceof TileEntityVolute) {
                dir = ((TileEntityVolute) te).getVaseRotation();
                meta = ((TileEntityVolute) te).getVaseType();
            }
            GlStateManager.pushMatrix();
            GlStateManager.rotate(dir * (90), 0F, 1F, 0F);
            GlStateManager.scale(1F, 1F, 1F);

            switch (this.vaseType) {
                case 1:
                default:
                    switch (meta) {
                        case 0:
                        default:
                            this.bindTexture(DAMAGED_VOLUTE);
                            VOLUTE_MODEL.render(0.0625F);
                            break;
                        case 1:
                            this.bindTexture(RESTORED_VOLUTE);
                            VOLUTE_MODEL.render(0.0625F);
                            break;
                        case 2:
                            this.bindTexture(RED_FIGURE_VOLUTE);
                            VOLUTE_MODEL.render(0.0625F);
                            break;
                        case 3:
                            this.bindTexture(BLACK_FIGURE_VOLUTE);
                            VOLUTE_MODEL.render(0.0625F);
                            break;
                        case 4:
                            this.bindTexture(PORCELAIN_VOLUTE);
                            VOLUTE_MODEL.render(0.0625F);
                            break;
                    }
                    break;
                case 0:
                    switch (meta) {
                        case 0:
                        default:
                            this.bindTexture(DAMAGED_AMPHORA);
                            AMPHORA_MODEL.render(0.0625F);
                            break;
                        case 1:
                            this.bindTexture(RESTORED_AMPHORA);
                            AMPHORA_MODEL.render(0.0625F);
                            break;
                        case 2:
                            this.bindTexture(RED_FIGURE_AMPHORA);
                            AMPHORA_MODEL.render(0.0625F);
                            break;
                        case 3:
                            this.bindTexture(BLACK_FIGURE_AMPHORA);
                            AMPHORA_MODEL.render(0.0625F);
                            break;
                        case 4:
                            this.bindTexture(PORCELAIN_AMPHORA);
                            AMPHORA_MODEL.render(0.0625F);
                            break;
                    }
                    break;
                case 2:
                    switch (meta) {
                        case 0:
                        default:
                            this.bindTexture(DAMAGED_KYLIX);
                            KYLIX_MODEL.render(0.0625F);
                            break;
                        case 1:
                            this.bindTexture(RESTORED_KYLIX);
                            KYLIX_MODEL.render(0.0625F);
                            break;
                        case 2:
                            this.bindTexture(RED_FIGURE_KYLIX);
                            KYLIX_MODEL.render(0.0625F);
                            break;
                        case 3:
                            this.bindTexture(BLACK_FIGURE_KYLIX);
                            KYLIX_MODEL.render(0.0625F);
                            break;
                        case 4:
                            this.bindTexture(PORCELAIN_KYLIX);
                            KYLIX_MODEL.render(0.0625F);
                            break;
                    }
                    break;
            }
            GlStateManager.popMatrix();
        } else {
            GlStateManager.pushMatrix();
            GlStateManager.rotate(0F, 0F, 1F, 0F);
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(DAMAGED_VOLUTE);
            VOLUTE_MODEL.render(0.0625F);
            GlStateManager.popMatrix();
        }
    }
}
