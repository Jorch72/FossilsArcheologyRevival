package fossilsarcheology.client.render.entity;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;

public class RenderPrehistoric extends RenderLiving<EntityPrehistoric> {

	public RenderPrehistoric(ModelBase model) {
		super(Minecraft.getMinecraft().getRenderManager(), model, 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPrehistoric entity) {
		return new ResourceLocation(entity.getTexture());
	}

	@Override
	protected void preRenderCallback(EntityPrehistoric entity, float f) {
		float scale = entity.getGender() == 1 ? entity.getMaleSize() : 1 * entity.getAgeScale();
		GlStateManager.scale(scale, scale, scale);
		this.shadowSize = entity.width * 0.45F;
	}
}
