package org.nationsatwar.palette.gui;

import java.util.HashMap;
import java.util.Map;

import org.nationsatwar.palette.Palette;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {
	
	private static Map<Integer, GUIScreen> guiScreens = new HashMap<Integer, GUIScreen>();
	
	/**
	 * Use this to open up your custom GUIScreen
	 * 
	 * @param guiScreen The screen you wish to open
	 * @param unique Setting unique to false will open the guiScreen, true will open the class
	 * 		(Unless you have want multiple unique instances of the same GUIScreen, set to true)
	 */
	public static void openGUI(GUIScreen guiScreen, boolean unique) {
		
		if (!containsGUI(guiScreen, unique))
			registerGUIScreen(guiScreen);
		
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
		player.openGui(Palette.instance, getScreenID(guiScreen, unique), player.getEntityWorld(), 0, 0, 0);
	}
	
	private static boolean containsGUI(GUIScreen guiScreen, boolean unique) {
		
		for (GUIScreen gui : guiScreens.values()) {
			
			if (!unique && gui.equals(guiScreen))
				return true;
			
			if (unique && gui.getClass().equals(guiScreen.getClass()))
				return true;
		}
		
		return false;
	}
	
	private static int registerGUIScreen(GUIScreen guiScreen) {
		
		int screenID = getNextFreeID();
		guiScreens.put(screenID, guiScreen);
		
		return screenID;
	}
	
	private static int getNextFreeID() {
		
		int id = 1;
		
		while (true) {
			
			if (!guiScreens.containsKey(id))
				return id;
			id++;
		}
	}
	
	private static int getScreenID(Object guiScreen, boolean unique) {
		
		for (int guiID : guiScreens.keySet()) {
			
			if (!unique && guiScreens.get(guiID).equals(guiScreen))
				return guiID;
			
			if (unique && guiScreens.get(guiID).getClass().equals(guiScreen.getClass()))
				return guiID;
		}
		
		return 0;
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		if (guiScreens.containsKey(ID))
			return guiScreens.get(ID);
		
		return null;
	}
}