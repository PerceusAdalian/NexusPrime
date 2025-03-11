package com.nexus.alpha;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.io.EchoTag.EchoTagAssignmentHandler;
import com.nexus.io.EchoTag.EchoTagDespawnHandler;
import com.nexus.io.NexusObject.NexusItemRegistry;
import com.nexus.io.NexusObject.NexusObjectCastHandler;
import com.nexus.io.NexusObject.NexusObjectDropHandler;

public class NexusProper extends JavaPlugin
{
	public static NexusProper instance; 
	public static boolean debug;
	
	@Override
	public void onEnable() 
	{		
		instance = this;
		debug = false;
		
		this.getCommand("nexus").setExecutor(new NexusCommand());;
		
		Bukkit.getPluginManager().registerEvents(new EchoTagAssignmentHandler(), instance);
		Bukkit.getPluginManager().registerEvents(new EchoTagDespawnHandler(), instance);
		Bukkit.getPluginManager().registerEvents(new NexusObjectCastHandler(), instance);
		Bukkit.getPluginManager().registerEvents(new NexusObjectDropHandler(), instance);
		
		NexusItemRegistry.itemInit();
		
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
 URGENT >> Todo: Some items' internal names aren't being retrieved properly via NexusObjectCasterListener's debug script. Find out why MemoryAnchor.java's internal name is being fetched, but not EchoicLocator.java's.
 URGENT >> Todo: Make it so that you can transfer ownership of echo bound items to other players.
 */

/*
 * Commonly Used Commands:
 * /kill @e[type=! player]
 */