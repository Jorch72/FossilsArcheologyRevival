package fossilsarcheology.client.gui;

import fossilsarcheology.Revival;
import fossilsarcheology.server.block.entity.TileEntityCultivate;
import fossilsarcheology.server.container.CultivateContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class CultivateGUI extends GuiContainer {
	private static final ResourceLocation loc = new ResourceLocation(Revival.MODID, "textures/gui/cultivate.png");
	private TileEntityCultivate cultivateInventory;

	public CultivateGUI(InventoryPlayer playerInventory, TileEntityCultivate tile) {
		super(new CultivateContainer(playerInventory, tile));
		this.cultivateInventory = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String customName = this.cultivateInventory.getName();
		this.fontRenderer.drawString(I18n.format(customName), this.xSize / 2 - this.fontRenderer.getStringWidth(I18n.format(customName)) / 2, 6, 4210752);

		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(loc);
		int drawX = (this.width - this.xSize) / 2;
		int drawY = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(drawX, drawY, 0, 0, this.xSize, this.ySize);
		int scaledProgress;

		if (this.cultivateInventory.isBurning()) {
			scaledProgress = this.cultivateInventory.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(drawX + 82, drawY + 36 + 12 - scaledProgress, 176, 12 - scaledProgress, 14, scaledProgress + 2);
		}

		scaledProgress = this.cultivateInventory.getCultivateProgressScaled(24);
		this.drawTexturedModalRect(drawX + 79, drawY + 18, 176, 14, scaledProgress + 1, 16);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
}
