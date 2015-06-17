package org.nationsatwar.palette;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.nationsatwar.palette.packets.PacketGiveItem;

public class ItemHelper {
	
	public static void giveItemToPlayer(EntityPlayer player, Item item, int itemAmount) {
		
		if (item == null) {
			
			System.out.println("Error: Not a valid item!");
			return;
		}
		
		String playerUUID = player.getUniqueID().toString();
		
		ItemStack itemStack = new ItemStack(item, itemAmount);
		player.inventory.addItemStackToInventory(itemStack);
		
		int itemID = Item.getIdFromItem(item);
		
		if (player.worldObj.isRemote)
			Palette.channel.sendToServer(new PacketGiveItem(playerUUID, itemID, itemAmount));
		else if (player instanceof EntityPlayerMP)
			Palette.channel.sendTo(new PacketGiveItem(playerUUID, itemID, itemAmount), (EntityPlayerMP) player);
	}
}