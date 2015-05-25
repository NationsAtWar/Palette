package org.nationsatwar.palette.packets;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandlerGiveItem implements IMessageHandler<PacketGiveItem, IMessage> {

	@Override
	public IMessage onMessage(PacketGiveItem message, MessageContext ctx) {
		
		UUID playerUUID = UUID.fromString(message.playerUUID);
		EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerByUUID(playerUUID);
		
		Item item = Item.getByNameOrId(message.itemName);
		int itemAmount = message.itemAmount;
		
		if (item == null)
			return null;
		
		player.inventory.addItemStackToInventory(new ItemStack(item, itemAmount));
		
		return null;
	}
}