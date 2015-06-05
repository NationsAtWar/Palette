package org.nationsatwar.palette;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class PlayerUtil {
	
	public static EntityPlayer getPlayerByUUID(UUID uuid) {
		
		EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerByUUID(uuid);
		return player;
	}
	
	@SuppressWarnings("unchecked")
	public static EntityItem findPlayerLookEntityItem(EntityPlayerSP player) {
		
		Vec3 hitVec = Minecraft.getMinecraft().objectMouseOver.hitVec;
		
		double playerX = player.posX;
		double playerY = player.posY;
		double playerZ = player.posZ;
		double hitX = hitVec.xCoord - playerX;
		double hitY = hitVec.yCoord - playerY;
		double hitZ = hitVec.zCoord - playerZ;
		
		double segLengthMax = 0.25;
		double segLengthMin = 0.125;
		
		double lineLength = Math.sqrt(Math.pow(hitX, 2) + Math.pow(hitY, 2) + Math.pow(hitZ, 2));
		double segNumDouble = lineLength / segLengthMax;
		int segNum = (int) segNumDouble;
		
		World world = player.getEntityWorld();
		AxisAlignedBB curAABB;
		
		List<EntityItem> items = new ArrayList<EntityItem>();
		int index = 1;
		
		while (index++ <= segNum) {
			
			double cenX = playerX + (hitX / segNumDouble) * index;
			double cenY = playerY + (hitY / segNumDouble) * index;
			double cenZ = playerZ + (hitZ / segNumDouble) * index;
			
			curAABB = AxisAlignedBB.fromBounds(cenX - segLengthMin, cenY - segLengthMin, cenZ - segLengthMin, 
					cenX + segLengthMin, cenY + segLengthMin, cenZ + segLengthMin);
			items = world.getEntitiesWithinAABB(EntityItem.class, curAABB);
			
			if(items != null && !items.isEmpty())
				break;
        }
		
		if (items != null && !items.isEmpty()) {
			
			EntityItem closeItem = items.get(0);
			double closeDist = 10.0;
			
			for (EntityItem item : items) {
				
				double itemDist = Math.pow(item.posX-playerX,2) + Math.pow(item.posY-playerY,2) + 
						Math.pow(item.posZ-playerZ,2);
				
				if (itemDist < closeDist) {
					
					closeDist = itemDist;
					closeItem = item;
				}
			}
			
			return closeItem;
		}
		
		return null;
	}
}