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
	
	public static void openGUI(GUIScreen guiScreen) {
		
		if (!containsGUI(guiScreen))
			registerGUIScreen(guiScreen);
		
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
		player.openGui(Palette.instance, getScreenID(guiScreen), player.getEntityWorld(), 0, 0, 0);
	}
	
	private static void registerGUIScreen(GUIScreen guiScreen) {
		
		guiScreens.put(getNextFreeID(), guiScreen);
	}
	
	private static int getScreenID(Object guiScreen) {
		
		for (int guiID : guiScreens.keySet())
			if (guiScreens.get(guiID).getClass().equals(guiScreen.getClass()))
				return guiID;
		
		return 0;
	}
	
	private static boolean containsGUI(GUIScreen guiScreen) {
		
		for (GUIScreen gui : guiScreens.values())
			if (gui.getClass().equals(guiScreen.getClass()))
				return true;
		
		return false;
	}
	
	private static int getNextFreeID() {
		
		int id = 1;
		
		while (true) {
			
			if (!guiScreens.containsKey(id))
				return id;
			id++;
		}
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