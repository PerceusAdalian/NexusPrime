package com.nexus.alpha;

import org.bukkit.plugin.java.JavaPlugin;

import com.nexus.epsilon.NexusPrintUtils;

public class NexusProper extends JavaPlugin
{
	public static NexusProper instance; 
	public static boolean debug;
	
	@Override
	public void onEnable() 
	{		
		instance = this;
		debug = false;
		
		this.getCommand("sys").setExecutor(new NexusCommand());;
		NexusPrintUtils.NexusPrint("Nexus -- &aOK");
		
	}
	
	@Override
	public void onDisable() 
	{
		NexusPrintUtils.NexusPrint("Nexus: &3Unmounting...");
	}
}

/*
 * Project Notes:
 * 		
 * if (ProjectEchoesOfLumina.debug == true){}
 *
 *
 */

/*
 * Commonly Used Commands:
 * /kill @e[type=! player]
 */