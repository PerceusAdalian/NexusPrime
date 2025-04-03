package com.nexus.alpha;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.nexus.beta.EchoTag.EchoTagAssignmentHandler;
import com.nexus.beta.EchoTag.EchoTagDespawnHandler;
import com.nexus.beta.NexusObjectRecipes.NullPointProtocolRecipe;
import com.nexus.beta.NexusObjectRecipes.OrdinalProtocolRecipe;
import com.nexus.beta.NexusObjectRecipes.ReconfigurationProtocolRecipe;
import com.nexus.beta.NexusObjectRecipes.ReticleProtocolRecipe;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.epsilon.OreValues;
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
		Bukkit.getPluginManager().registerEvents(new OnJoin(), instance);
		
		NexusItemRegistry.itemInit();
		NexusPrintUtils.NexusConsolePrint("Nexus Objects Loaded: &e"+NexusItemRegistry.itemRegistry.size());
		
		OreValues.initMaterials();
		OreValues.initBlockTypes();
		
		NullPointProtocolRecipe.register();
		OrdinalProtocolRecipe.register();
		ReticleProtocolRecipe.register();
		ReconfigurationProtocolRecipe.register();

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
 *  Ordinal Protocol still won't properly record and teleport player to a set respawn location due to 
 *  #setBestSpawnLocation() being deprecated..
 *  
 *  Null Point Protocol will not fire and break blocks.. debug 
 */

/*
 * Commonly Used Commands:
 * /kill @e[type=! player]
 */