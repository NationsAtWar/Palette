package org.nationsatwar.palette.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.nationsatwar.palette.Palette;

public abstract class GUIScreen extends GuiScreen {
	
	protected int windowX, windowY, windowWidth, windowHeight;
	
	protected EntityPlayer player;
	
	private ResourceLocation backgroundImage = new ResourceLocation(Palette.MODID + ":" + 
			"textures/client/gui/GUI_Screen.png");
	
	private List<GUILabel> labelList = new ArrayList<GUILabel>();
	private List<GUITextField> textFieldList = new ArrayList<GUITextField>();
	
	public GUIScreen() {
		
		player = Minecraft.getMinecraft().thePlayer;
	}
	
	protected abstract void buttonClicked(GUIButton button);
	protected abstract void setElements();
	
	protected void setWindow(int windowX, int windowY, int windowWidth, int windowHeight) {
		
		this.windowX = windowX;
		this.windowY = windowY;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}
	
	protected GUILabel addLabel(int posX, int posY, String text) {
		
		GUILabel label = new GUILabel(posX, posY, 0xFFFFFF, text);
		labelList.add(label);
		
		return label;
	}
	
	@SuppressWarnings("unchecked")
	protected GUIButton addButton(int posX, int posY, int width, int height, String text) {
		
		GUIButton button = new GUIButton(buttonList.size(), posX, posY, width, height, text);
		button.enabled = true;
		buttonList.add(button);
		
		return button;
	}
	
	@SuppressWarnings("unchecked")
	protected void addButton(GUIButton button) {
		
		button.enabled = true;
		buttonList.add(button);
	}
	
	protected GUITextField addTextField(int posX, int posY, int width, int height, String text) {
		
		if (text == null)
			text = "";
		
		GUITextField textField = new GUITextField(2, fontRendererObj, posX, posY, width, height);
		textField.setText(text);
		textField.setFocused(true);
		textFieldList.add(textField);
		
		return textField;
	}
	
	private static boolean allowedUnicodes(char key) {
		
		if (key == 8 || key == 27 || key == 0)
			return true;
		
		return false;
	}
	
	@Override
	public void initGui() {
		
		player = Minecraft.getMinecraft().thePlayer;
		labelList.clear();
		textFieldList.clear();
		setElements();
	}
	
	@Override
	protected void keyTyped(char par1, int par2) throws IOException {
		
		super.keyTyped(par1, par2);
		
		for (GUITextField textField : textFieldList) {
			
			String charString = "" + par1;
			
			// Only accept valid characters
			if ((!textField.getRegEx().isEmpty() && !charString.matches(textField.getRegEx())) && 
					!allowedUnicodes(par1))
				return;
			
			textField.textboxKeyTyped(par1, par2);
		}
	}
	
	@Override
	public void updateScreen() {
		
		super.updateScreen();
		
		for (GUITextField textField : textFieldList)
			if (textField.isFocused())
				textField.updateCursorCounter();
	}
	
	@Override
	protected void mouseClicked(int x, int y, int btn) throws IOException {
		
		super.mouseClicked(x, y, btn);

		for (GUITextField textField : textFieldList) {
			
			if (x >= textField.xPosition && x <= textField.xPosition + textField.width && 
					y >= textField.yPosition && y <= textField.yPosition + textField.height) {

				textField.setFocused(true);
				textField.mouseClicked(x, y, btn);
			} else
				textField.setFocused(false);
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
		
		// Draws the background window
		mc.getTextureManager().bindTexture(backgroundImage);
		
		int borderSize = 16;
		int imageSize = 256;
		
		// Draw top-left corner
		drawTexturedModalRect(windowX, windowY, 0, 0, borderSize, borderSize);
		// Draw top border
		drawModalRectWithCustomSizedTexture(windowX + borderSize, windowY, borderSize, 0, 
				windowWidth - borderSize * 2, borderSize, imageSize, imageSize);
		// Draw top-right corner
		drawTexturedModalRect(windowX + windowWidth - borderSize, windowY, 
				imageSize - borderSize, 0, borderSize, borderSize);
		// Draw right border
		drawModalRectWithCustomSizedTexture(windowX + windowWidth - borderSize, windowY + borderSize, imageSize - borderSize, borderSize, 
				borderSize, windowHeight - borderSize, imageSize, imageSize);
		// Draw bottom-right corner
		drawTexturedModalRect(windowX + windowWidth - borderSize, windowY + windowHeight, 
				imageSize - borderSize, imageSize - borderSize, borderSize, borderSize);
		// Draw bottom border
		drawModalRectWithCustomSizedTexture(windowX + borderSize, windowY + windowHeight, borderSize, imageSize - borderSize, 
				windowWidth - borderSize * 2, borderSize, imageSize, imageSize);
		// Draw left border
		drawModalRectWithCustomSizedTexture(windowX, windowY + borderSize, 0, borderSize, 
				borderSize, windowHeight - borderSize, imageSize, imageSize);
		// Draw bottom-left corner
		drawTexturedModalRect(windowX, windowY + windowHeight, 
				0, imageSize - borderSize, borderSize, borderSize);
		// Draw Center
		drawModalRectWithCustomSizedTexture(windowX + borderSize, windowY + borderSize, borderSize, borderSize, 
				windowWidth - borderSize * 2, windowHeight - borderSize, imageSize, imageSize);
		
		// Handles labels and label formatting
		for (GUILabel label : labelList) {

			String text = label.getText();
			int posX = label.getPosX();
			int posY = label.getPosY();
			int fontColor = label.getFontColor();
			
			if (text == null)
				text = "";
			
			// Label formatting
			GL11.glPushMatrix();
			
			// Doubles label size
			if (label.isSizeDoubled()) {
				
				posX /= 2;
				posY /= 2;
				GL11.glScaled(2, 2, 2);
			}
			
			// Handles word wrap
			if (label.getWordWrap() > 0) {
				
				int wordWrap = label.getWordWrap();
				int characterLength = (label.isSizeDoubled() ? 12 : 6);
				int charsPerLine = wordWrap / characterLength;
				int numberOfLines = wordWrap / charsPerLine;
				int textLength = text.length();
				
				for (int i = 0; i < numberOfLines; i++) {

					int beginIndex = i * charsPerLine;
					int endIndex = (i + 1) * charsPerLine;

					if (textLength < beginIndex)
						continue;
					
					String substring = ((text.length() > endIndex) ? text.substring(beginIndex, endIndex) : 
						text.substring(beginIndex));
					
					if (label.isCentered())
						drawCenteredString(fontRendererObj, substring, posX, posY + i * 10, fontColor);
					else
						drawString(fontRendererObj, substring, posX, posY + i * 10, fontColor);
				}
				
			} else {
			
				if (label.isCentered())
					drawCenteredString(fontRendererObj, text, posX, posY, fontColor);
				else
					drawString(fontRendererObj, text, posX, posY, fontColor);
			}
			
			GL11.glPopMatrix();
		}

		for (GUITextField textField : textFieldList) {
			
			//textField.writeText("Test");
			textField.drawTextBox();
		}
		
		super.drawScreen(mouseX, mouseY, renderPartialTicks);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		
		return false;
	}
	
	@Override
	public void actionPerformed(GuiButton button) {
		
		// Forge Bug Fix - Don't ask
		if(!button.isMouseOver())
			return;
		
		if (buttonList.get(button.id) instanceof GUIButton) {
			
			GUIButton guiButton = (GUIButton) buttonList.get(button.id);
			buttonClicked(guiButton);
		}
	}
	
	public enum FontColor {
		
		WHITE (0xFFFFFF), 
		RED (0xFF0000), 
		BLACK (0x000000);
		
		int value;
		
		FontColor(int value) { this.value = value; }
		
		public int getFontColor() {
			
			return this.value;
		}
	}
}