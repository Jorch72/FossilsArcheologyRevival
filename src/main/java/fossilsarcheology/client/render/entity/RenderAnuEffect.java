package fossilsarcheology.client.render.entity;

import fossilsarcheology.Revival;
import fossilsarcheology.client.model.ModelAnuTotem;
import fossilsarcheology.server.entity.utility.EntityAnuEffect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderAnuEffect extends RenderLiving<EntityAnuEffect> {
    private static final ResourceLocation EXPLODING_TEXTURE = new ResourceLocation(Revival.MODID, "textures/model/anu_statue_explosion.png");
    private static final ResourceLocation texture = new ResourceLocation(Revival.MODID, "textures/model/anu_statue.png");
    protected final ModelAnuTotem modelAnuTotem;

    public RenderAnuEffect(RenderManager render) {
        super(render, new ModelAnuTotem(), 0.3F);
        this.modelAnuTotem = (ModelAnuTotem) this.mainModel;
    }

    @Override
    protected void renderModel(EntityAnuEffect entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        boolean flag = this.isVisible(entitylivingbaseIn);
        boolean flag1 = !flag && !entitylivingbaseIn.isInvisibleToPlayer(Minecraft.getMinecraft().player);
        if (entitylivingbaseIn.deathTicks > 0) {
            float f = (float) entitylivingbaseIn.deathTicks / 200.0F;
            GlStateManager.depthFunc(515);
            GlStateManager.enableAlpha();
            GlStateManager.alphaFunc(516, f);
            this.bindTexture(EXPLODING_TEXTURE);
            this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.depthFunc(514);
        }
        if (flag || flag1) {
            if (!this.bindEntityTexture(entitylivingbaseIn)) {
                return;
            }

            if (flag1) {
                GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }

            this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

            if (flag1) {
                GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAnuEffect entity) {
        return texture;
    }
}
