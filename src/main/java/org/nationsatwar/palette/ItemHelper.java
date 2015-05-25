package org.nationsatwar.palette;

import org.nationsatwar.palette.packets.PacketGiveItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHelper {
	
	public static void giveItemToPlayer(EntityPlayer player, Item item, int itemAmount) {
		
		String playerUUID = player.getUniqueID().toString();
		String itemName = item.getUnlocalizedName().substring(5);
		
		player.inventory.addItemStackToInventory(new ItemStack(item, itemAmount));
		
		System.out.println(itemName);
		
		if (player.worldObj.isRemote)
			Palette.channel.sendToServer(new PacketGiveItem(playerUUID, itemName, itemAmount));
		else if (player instanceof EntityPlayerMP)
			Palette.channel.sendTo(new PacketGiveItem(playerUUID, itemName, itemAmount), (EntityPlayerMP) player);
	}
}