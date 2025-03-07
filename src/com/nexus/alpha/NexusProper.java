package com.nexus.alpha;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.io.EchoTag.EchoTagDespawnHandler;
import com.nexus.io.EchoTag.EchoTagItemHandler;

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
		Bukkit.getPluginManager().registerEvents(new EchoTagItemHandler(), instance);
		Bukkit.getPluginManager().registerEvents(new EchoTagDespawnHandler(), instance);
		NexusPrintUtils.NexusConsolePrint("Nexus -- &aOK");
		
	}
	
	@Override
	public void onDisable() 
	{
		NexusPrintUtils.NexusConsolePrint("Nexus -- &3Disabling...");
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