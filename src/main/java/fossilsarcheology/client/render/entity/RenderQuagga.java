package fossilsarcheology.client.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderAbstractHorse;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.util.ResourceLocation;

public class RenderQuagga extends RenderAbstractHorse {
    private static final ResourceLocation TEXTURE = new ResourceLocation("fossil:textures/model/quagga_0/quagga.png");
    public RenderQuagga(RenderManager renderManager) {
        super(renderManager);
    }

    protected ResourceLocation getEntityTexture(AbstractHorse entity) {
        return TEXTURE;
    }

}
