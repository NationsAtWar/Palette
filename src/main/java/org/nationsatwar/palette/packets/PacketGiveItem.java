package org.nationsatwar.palette.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketGiveItem implements IMessage {

	public String playerUUID;
	public int itemID;
	public int itemAmount;
	
	public PacketGiveItem() {
		
	}
	
	public PacketGiveItem(String playerUUID, int itemID, int itemAmount) {

		this.playerUUID = playerUUID;
		this.itemID = itemID;
		this.itemAmount = itemAmount;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		
		playerUUID = ByteBufUtils.readUTF8String(buf);
		itemID = buf.readInt();
		itemAmount = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {

		ByteBufUtils.writeUTF8String(buf, playerUUID);
		buf.writeInt(itemID);
		buf.writeInt(itemAmount);
	}
}