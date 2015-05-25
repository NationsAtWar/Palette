package org.nationsatwar.palette.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketGiveItem implements IMessage {

	public String playerUUID;
	public String itemName;
	public int itemAmount;
	
	public PacketGiveItem() {
		
	}
	
	public PacketGiveItem(String playerUUID, String itemName, int itemAmount) {

		this.playerUUID = playerUUID;
		this.itemName = itemName;
		this.itemAmount = itemAmount;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		
		playerUUID = ByteBufUtils.readUTF8String(buf);
		itemName = ByteBufUtils.readUTF8String(buf);
		itemAmount = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {

		ByteBufUtils.writeUTF8String(buf, playerUUID);
		ByteBufUtils.writeUTF8String(buf, itemName);
		buf.writeInt(itemAmount);
	}
}