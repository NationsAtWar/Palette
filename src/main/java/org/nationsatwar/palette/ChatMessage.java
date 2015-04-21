package org.nationsatwar.palette;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class ChatMessage {
	
	public static void sendMessage(EntityPlayer player, String message) {
		
		player.getCommandSenderEntity().addChatMessage(new ChatComponentText(message));
	}
}