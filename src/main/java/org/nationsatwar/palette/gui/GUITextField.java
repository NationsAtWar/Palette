package org.nationsatwar.palette.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class GUITextField extends GuiTextField {
	
	private String regEx = "";

	public GUITextField(int ID, FontRenderer fontRenderer, int posX, int posY, int width, int height) {
		
		super(ID, fontRenderer, posX, posY, width, height);
	}
	
	public String getRegEx() {
		return regEx;
	}
	
	public void setRegEx(String regEx) {
		this.regEx = regEx;
	}
}