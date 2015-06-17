package org.nationsatwar.palette.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketUpdateItem implements IMessage {
	
	public String playerUUID;
	public NBTTagCompound tagCompound;
	
	public PacketUpdateItem() {
		
	}
	
	public PacketUpdateItem(String playerUUID, NBTTagCompound tagCompound) {
		
		this.playerUUID = playerUUID;
		this.tagCompound = tagCompound;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		
		playerUUID = ByteBufUtils.readUTF8String(buf);
		tagCompound = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {

		ByteBufUtils.writeUTF8String(buf, playerUUID);
		ByteBufUtils.writeTag(buf, tagCompound);
	}
}