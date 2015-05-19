package org.nationsatwar.palette.chat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ChatMessage {
	
	public static void sendMessage(EntityPlayer player, String message) {
		
		if (player != null)
			player.getCommandSenderEntity().addChatMessage(new ChatComponentText(message));
	}
	
	public static String formatMessage(String playerName, String message, MessageType messageType) {
		
		if (messageType.equals(MessageType.NORMAL))
			playerName = EnumChatFormatting.DARK_RED + playerName;
		else if (messageType.equals(MessageType.ADMIN))
			playerName = EnumChatFormatting.DARK_PURPLE + playerName;
		
		String newMessage = playerName + EnumChatFormatting.WHITE + ": " + message;
		
		return newMessage;
	}
}