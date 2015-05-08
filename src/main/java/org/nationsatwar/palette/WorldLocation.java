package org.nationsatwar.palette;

public class WorldLocation {
	
	private String worldName;
	
	private double posX;
	private double posY;
	private double posZ;
	
	public WorldLocation(String worldName, double posX, double posY, double posZ) {
		
		this.worldName = worldName;
		this.posX = posX; this.posY = posY; this.posZ = posZ;
	}
	
	public String getWorldName() {
		return worldName;
	}
	
	public void setWorldName(String worldName) {
		this.worldName = worldName;
	}
	
	public double getPosX() {
		return posX;
	}
	
	public void setPosX(double posX) {
		this.posX = posX;
	}
	
	public double getPosY() {
		return posY;
	}
	
	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	public double getPosZ() {
		return posZ;
	}
	
	public void setPosZ(double posZ) {
		this.posZ = posZ;
	}
}