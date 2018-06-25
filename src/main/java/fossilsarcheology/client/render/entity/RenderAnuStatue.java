package fossilsarcheology.client.render.entity;

import fossilsarcheology.Revival;
import fossilsarcheology.client.model.ModelAnuTotem;
import fossilsarcheology.client.model.ModelPigBoss;
<<<<<<< HEAD
import fossilsarcheology.client.render.entity.layer.LayerAnuGlow;
import fossilsarcheology.client.render.entity.layer.LayerAnuStatueGlow;
import fossilsarcheology.client.render.entity.layer.LayerAnuStatueOverlay;
import fossilsarcheology.server.entity.monster.EntityAnu;
=======
>>>>>>> 3c1b6c87503883d6d15dab31a413e855c8a3d46d
import fossilsarcheology.server.entity.utility.EntityAnuStatue;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderAnuStatue extends RenderLiving<EntityAnuStatue> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(Revival.MODID, "textures/model/anu_statue.png");

	public RenderAnuStatue(RenderManager renderManager) {
		super(renderManager, new ModelAnuTotem(), 0.5F);
		layerRenderers.add(new LayerAnuStatueGlow());
		layerRenderers.add(new LayerAnuStatueOverlay(this));
	}

	protected void preRenderCallback(EntityAnuStatue anu, float partialTickTime) {
		GL11.glRotatef(anu.ticksExisted * anu.ticksExisted * 0.15F, 0, 1, 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAnuStatue entity) {
		return TEXTURE;
	}
}
