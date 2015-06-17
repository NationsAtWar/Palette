package org.nationsatwar.palette;

import java.util.ArrayList;
import java.util.List;

import org.nationsatwar.palette.packets.PacketUpdateItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class NBTHelper {
	
	public static void addStringToNBTList(NBTTagCompound tagCompound, String listName, String listString) {
		
		NBTTagList tagList = tagCompound.getTagList(listName, Constants.NBT.TAG_COMPOUND);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString(listName + tagList.tagCount(), listString);
		tagList.appendTag(tag);
		tagCompound.setTag(listName, tagList);
	}
	
	public static List<String> getNBTStringList(NBTTagCompound tagCompound, String listName) {
		
		List<String> nbtStringList = new ArrayList<String>();
		
		NBTTagList tagList = tagCompound.getTagList(listName, Constants.NBT.TAG_COMPOUND);
		
		for (int i = 0; i < tagList.tagCount(); i++) {
			
			String listString = tagList.getCompoundTagAt(i).getString(listName + i);
			
			if (listString != null)
				nbtStringList.add(listString);
		}
		
		return nbtStringList;
	}
	
	/**
	 * Use this if you want to update a player's currently equipped item's NBTTagCompound
	 * 
	 * @param player
	 * @param tagCompound
	 */
	public static void syncNBTWithServer(EntityPlayer player, NBTTagCompound tagCompound) {
		
		ItemStack itemStack = player.getCurrentEquippedItem();
		itemStack.setTagCompound(tagCompound);
		
		Palette.channel.sendToServer(new PacketUpdateItem(player.getUniqueID().toString(), tagCompound));
	}
}