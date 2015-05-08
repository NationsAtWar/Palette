package org.nationsatwar.palette;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class PlayerUtil {
	
	public static EntityPlayer getPlayerByUUID(UUID uuid) {
		
		EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerByUUID(uuid);
		return player;
	}
}