package org.nationsatwar.palette;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import org.nationsatwar.palette.gui.GUIHandler;
 
@Mod(modid = Palette.MODID, name = Palette.MODNAME, version = Palette.MODVER)
public class Palette {

    @Instance(Palette.MODID)
    public static Palette instance;
	
	public static final String MODID = "palette";
	public static final String MODNAME = "Palette";
	public static final String MODVER = "0.0.1";
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		
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