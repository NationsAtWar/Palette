package org.nationsatwar.palette.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.server.MinecraftServer;

import com.google.gson.Gson;

public class JSONUtil {
	
	public static void saveObject(String directory, String fileName, Object object) {
		
		Gson gson = new Gson();
		String json = gson.toJson(object);
		
		String saveDirectory = MinecraftServer.getServer().getDataDirectory().getAbsolutePath() + "/";
		
		File directoryFile = new File(saveDirectory + directory);
		if (!directoryFile.exists())
			directoryFile.mkdir();
		
		try {
			FileWriter writer = new FileWriter(saveDirectory + directory + "/" + fileName);
			writer.write(json);
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object loadObject(String directory, String fileName, Object object) {
		
		Gson gson = new Gson();
		
		String saveDirectory = MinecraftServer.getServer().getDataDirectory().getAbsolutePath() + "/";
		
		File directoryFile = new File(saveDirectory + directory);
		if (!directoryFile.exists())
			return null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(saveDirectory + directory + "/" + fileName));
			object = gson.fromJson(br, object.getClass());
			return object;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<Object> loadAllObjects(String directory, Object object) {
		
		List<Object> allObjects = new ArrayList<Object>();
		
		String saveDirectory = MinecraftServer.getServer().getDataDirectory().getAbsolutePath() + "/";
		
		File directoryFile = new File(saveDirectory + directory);
		
		for (File file : directoryFile.listFiles())
			if (file.isFile())
				allObjects.add(loadObject(directory, file.getName(), object));
		
		return allObjects;
	}
	
	public static Map<String, Object> loadAllObjectsWithFilename(String directory, Object object) {
		
		Map<String, Object> allObjects = new HashMap<String, Object>();
		
		String saveDirectory = MinecraftServer.getServer().getDataDirectory().getAbsolutePath() + "/";
		
		File directoryFile = new File(saveDirectory + directory);
		
		if (!directoryFile.exists())
			return allObjects;
		
		for (File file : directoryFile.listFiles())
			if (file.isFile())
				allObjects.put(file.getName(), loadObject(directory, file.getName(), object));
		
		return allObjects;
	}
}