package fossilsarcheology.client.render.entity;

import fossilsarcheology.Revival;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerCompsognathusEyes<T extends EntityPrehistoric> implements LayerRenderer<T> {
	private static final ResourceLocation EYES = new ResourceLocation(Revival.MODID, "textures/model/compsognathus_0/overlay.png");
	private final RenderGlowingPrehistoric prehistoric;

	public LayerCompsognathusEyes(RenderGlowingPrehistoric renderer) {
		this.prehistoric = renderer;
	}

	@Override
	public void doRenderLayer(T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (!entity.isSleeping()) {
			GlStateManager.disableLighting();
			this.prehistoric.bindTexture(EYES);
			this.prehistoric.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.enableLighting();

		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
