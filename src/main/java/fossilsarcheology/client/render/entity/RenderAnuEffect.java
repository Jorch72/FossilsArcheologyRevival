package fossilsarcheology.client.render.entity;

import fossilsarcheology.Revival;
import fossilsarcheology.client.model.ModelAnuTotem;
import fossilsarcheology.client.render.entity.layer.LayerAnuEffect;
import fossilsarcheology.server.entity.utility.EntityAnuEffect;
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
	private static final ResourceLocation texture = new ResourceLocation(Revival.MODID, "textures/blocks/anuTotem.png");
	protected ModelAnuTotem modelAnuTotem;

	public RenderAnuEffect(RenderManager render) {
		super(render, new ModelAnuTotem(), 0.3F);
		this.modelAnuTotem = (ModelAnuTotem) this.mainModel;
		this.addLayer(new LayerAnuEffect());
	}

	@Override
	protected void applyRotations(EntityAnuEffect entity, float x, float y, float z) {
		float f3 = 0;
		float f4 = 0;
		GlStateManager.rotate(-f3, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(f4 * 10.0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.translate(0.0F, 0.0F, 1.0F);

		if (entity.deathTime > 0) {
			float f5 = (entity.deathTime + z - 1.0F) / 20.0F * 1.6F;
			f5 = MathHelper.sqrt(f5);

			if (f5 > 1.0F) {
				f5 = 1.0F;
			}

			GlStateManager.rotate(f5 * this.getDeathMaxRotation(entity), 0.0F, 0.0F, 1.0F);
		}
	}

	@Override
	protected void renderModel(EntityAnuEffect entity, float limbSwing, float limbSwingAmount, float age, float yaw, float pitch, float scale) {
		int i1 = 0;
		if (entity.world != null) {
			i1 = entity.getAnuRotation();
		}
		short short1 = 0;
		if (i1 == 2) {
			GlStateManager.translate(0, 0, -1);
			short1 = 360;
		}

		if (i1 == 3) {
			GlStateManager.translate(0, 0, -1);
			short1 = 180;
		}

		if (i1 == 4) {
			GlStateManager.translate(0, 0, -1);
			short1 = 90;
		}

		if (i1 == 5) {
			GlStateManager.translate(0, 0, -1);
			short1 = -90;
		}

		GlStateManager.rotate(-1 * short1, 0.0F, 1.0F, 0.0F);

		GlStateManager.pushMatrix();
		if (entity.deathTicks > 0) {
			float deathScale = (float) entity.deathTicks / 200.0F;
			GlStateManager.depthFunc(GL11.GL_LEQUAL);
			GlStateManager.enableAlpha();
			GlStateManager.alphaFunc(GL11.GL_GREATER, deathScale);
			this.bindTexture(EXPLODING_TEXTURE);
			this.mainModel.render(entity, limbSwing, limbSwingAmount, age, yaw, pitch, scale);
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
			GlStateManager.depthFunc(GL11.GL_EQUAL);
		}
		GlStateManager.popMatrix();

		this.bindEntityTexture(entity);
		this.mainModel.render(entity, limbSwing, limbSwingAmount, age, yaw, pitch, scale);

		if (entity.hurtTime > 0) {
			GlStateManager.pushMatrix();
			GlStateManager.depthFunc(GL11.GL_EQUAL);
			GlStateManager.disableTexture2D();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			GlStateManager.color(1.0F, 0.0F, 0.0F, 0.5F);
			GlStateManager.translate(0, 0, 1);
			this.mainModel.render(entity, limbSwing, limbSwingAmount, age, yaw, pitch, scale);
			GlStateManager.enableTexture2D();
			GlStateManager.disableBlend();
			GlStateManager.depthFunc(GL11.GL_LEQUAL);
			GlStateManager.popMatrix();
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAnuEffect entity) {
		return texture;
	}
}
