package fossilsarcheology.client.render.entity;

import fossilsarcheology.Revival;
import fossilsarcheology.client.model.ModelPigBoss;
import fossilsarcheology.client.render.entity.layer.LayerAnuGlow;
import fossilsarcheology.server.entity.monster.EntityAnu;
import fossilsarcheology.server.entity.utility.EntityAnuStatue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

public class RenderAnuStatue extends RenderLiving<EntityAnuStatue> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(Revival.MODID, "textures/model/anu_statue.png");

	public RenderAnuStatue(RenderManager renderManager) {
		super(renderManager, new ModelPigBoss(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAnuStatue entity) {
		return TEXTURE;
	}
}
