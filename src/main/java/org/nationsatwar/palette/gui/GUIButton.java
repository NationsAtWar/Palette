package org.nationsatwar.palette.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import org.nationsatwar.palette.Palette;

public class GUIButton extends GuiButton {
	
	private ResourceLocation buttonImage = new ResourceLocation(Palette.MODID + ":" + 
			"textures/client/gui/GUI_Button.png");

	public GUIButton(int buttonId, int x, int y, int widthIn,
			int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		
		FontRenderer fontrenderer = mc.fontRendererObj;
		mc.getTextureManager().bindTexture(buttonImage);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && 
				mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
		int k = this.getHoverState(this.hovered);
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.blendFunc(770, 771);
		this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
		this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
		this.mouseDragged(mc, mouseX, mouseY);
		int l = 14737632;
		
		if (packedFGColour != 0)
			l = packedFGColour;
        else if (!this.enabled)
        	l = 10526880;
        else if (this.hovered)
        	l = 16777120;
		
		this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
	}
}