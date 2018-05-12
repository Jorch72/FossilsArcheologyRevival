package fossilsarcheology.client.render.entity;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;

public class RenderGlowingPrehistoric extends RenderLiving<EntityPrehistoric> {

    public RenderGlowingPrehistoric(ModelBase model, ResourceLocation overlay) {
        super(Minecraft.getMinecraft().getRenderManager(), model, 0.3F);
        this.addLayer(new LayerCompsognathusEyes<>(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPrehistoric entity) {
        return new ResourceLocation(entity.getTexture());
    }

    @Override
    protected void preRenderCallback(EntityPrehistoric entity, float f) {
        GlStateManager.scale(entity.getAgeScale(), entity.getAgeScale(), entity.getAgeScale());
    }
}
