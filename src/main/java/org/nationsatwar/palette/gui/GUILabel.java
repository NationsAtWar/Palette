package org.nationsatwar.palette.gui;

import org.nationsatwar.palette.gui.GUIScreen.FontColor;

public class GUILabel {
	
	private String text;
	
	private int posX, posY;
	private int fontColor;
	
	private boolean isCentered;
	private boolean sizeDoubled;
	
	private int wordWrap;
	
	public GUILabel(int posX, int posY, int fontColor, String text) {
		
		this.posX = posX;
		this.posY = posY;
		
		this.fontColor = fontColor;
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public int getFontColor() {
		return fontColor;
	}
	
	public void setFontColor(int fontColor) {
		this.fontColor = fontColor;
	}
	
	public void setFontColor(FontColor fontColor) {
		this.fontColor = fontColor.value;
	}
	
	public boolean isCentered() {
		return isCentered;
	}
	
	public void setCentered(boolean isCentered) {
		this.isCentered = isCentered;
	}
	
	public boolean isSizeDoubled() {
		return sizeDoubled;
	}
	
	public void setSizeDoubled(boolean sizeDoubled) {
		this.sizeDoubled = sizeDoubled;
	}
	
	public int getWordWrap() {
		return wordWrap;
	}
	
	public void setWordWrap(int wordWrap) {
		this.wordWrap = wordWrap;
	}
}