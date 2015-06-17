package org.nationsatwar.palette;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import org.nationsatwar.palette.gui.GUIHandler;
import org.nationsatwar.palette.packets.PacketGiveItem;
import org.nationsatwar.palette.packets.PacketHandlerGiveItem;
import org.nationsatwar.palette.packets.PacketHandlerUpdateItem;
import org.nationsatwar.palette.packets.PacketUpdateItem;
 
@Mod(modid = Palette.MODID, name = Palette.MODNAME, version = Palette.MODVER)
public class Palette {

    @Instance(Palette.MODID)
    public static Palette instance;
	
	public static final String MODID = "palette";
	public static final String MODNAME = "Palette";
	public static final String MODVER = "0.0.1";
	
	public static SimpleNetworkWrapper channel;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		// Packet Registration
		channel = NetworkRegistry.INSTANCE.newSimpleChannel(Palette.MODID);
		channel.registerMessage(PacketHandlerGiveItem.class, PacketGiveItem.class, 0, Side.CLIENT);
		channel.registerMessage(PacketHandlerGiveItem.class, PacketGiveItem.class, 0, Side.SERVER);
		channel.registerMessage(PacketHandlerUpdateItem.class, PacketUpdateItem.class, 1, Side.SERVER);
		channel.registerMessage(PacketHandlerUpdateItem.class, PacketUpdateItem.class, 1, Side.CLIENT);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		// Register GUI
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		
	}
}