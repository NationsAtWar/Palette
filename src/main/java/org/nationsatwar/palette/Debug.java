package org.nationsatwar.palette;

import org.apache.logging.log4j.LogManager;

public class Debug {

	public static void Log(String log) {
		
		LogManager.getLogger(Palette.MODNAME).info("Nations at War: " + log);
	}
}