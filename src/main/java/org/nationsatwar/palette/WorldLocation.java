package org.nationsatwar.palette;

import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class WorldLocation {
	
	private String worldName;
	private int worldID;
	
	private double posX;
	private double posY;
	private double posZ;
	
	public WorldLocation(String worldName, double posX, double posY, double posZ) {
		
		this.worldName = worldName;
		this.posX = posX; this.posY = posY; this.posZ = posZ;
		
		findWorldID();
	}
	
	public WorldLocation(String worldName, BlockPos blockPos) {
		
		this.worldName = worldName;
		this.posX = blockPos.getX();
		this.posY = blockPos.getY();
		this.posZ = blockPos.getZ();
		
		findWorldID();
	}
	
	public WorldLocation(Entity entity) {
		
		this.worldName = entity.worldObj.provider.getDimensionName();
		this.posX = entity.posX;
		this.posY = entity.posY;
		this.posZ = entity.posZ;
		
		findWorldID();
	}
	
	public String getWorldName() {
		return worldName;
	}
	
	public void setWorldName(String worldName) {
		this.worldName = worldName;
	}
	
	public int getWorldID() {
		return worldID;
	}
	
	public void setWorldID(int worldID) {
		this.worldID = worldID;
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
	
	public String getFormattedCoords() {
		
		return (int) posX + "," + (int) posY + "," + (int) posZ;
	}
	
	public Vec3 getVector() {
		
		return new Vec3(posX, posY, posZ);
	}
	
	private void findWorldID() {
		
		for (WorldServer world : MinecraftServer.getServer().worldServers) {
			
			if (world.provider.getDimensionName().equals(worldName)) {
				
				this.worldID = world.provider.getDimensionId();
				break;
			}
		}
	}
	
	public World getWorldObject() {
		
		for (WorldServer world : MinecraftServer.getServer().worldServers)
			if (world.provider.getDimensionName().equals(worldName))
				return world;
		
		return null;
	}
}