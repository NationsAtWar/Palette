package org.nationsatwar.palette.packets;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandlerUpdateItem implements IMessageHandler<PacketUpdateItem, IMessage> {

	@Override
	public IMessage onMessage(PacketUpdateItem message, MessageContext ctx) {
		
		NBTTagCompound tagCompound = message.tagCompound;
		UUID playerUUID = UUID.fromString(message.playerUUID);
		
		EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerByUUID(playerUUID);
		ItemStack itemStack = player.getCurrentEquippedItem();
		
		if (itemStack != null)
			itemStack.setTagCompound(tagCompound);
		
		return null;
	}
}