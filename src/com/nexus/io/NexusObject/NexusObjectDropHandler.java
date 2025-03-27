package com.nexus.io.NexusObject;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.nexus.alpha.NexusProper;

public class NexusObjectDropHandler implements Listener
{
	
	public static HashMap<AbstractNexusObject, Boolean> hasBeenRecentlyDropped = new HashMap<>();
	
	@EventHandler
	public void drop(EntityDeathEvent e) 
	{
		if (!(e.getEntity() instanceof LivingEntity)) 
		{
			return;
		}
		
		Random r = new Random();
		final int maxDrops = 2;
		int currentDrops = 0;
		final double dropChance = 0.1599d;
		
		for (AbstractNexusObject item : NexusItemRegistry.itemRegistry.values()) 
		{
			if (currentDrops >= maxDrops) break;
		    if (r.nextDouble() >= dropChance) continue;
		    if (hasBeenRecentlyDropped.getOrDefault(item, false)) continue;
	   
	    	e.getDrops().add(item.bake());
	    	hasBeenRecentlyDropped.put(item, true);
	    	currentDrops++;
		}
		
		Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()-> hasBeenRecentlyDropped.clear(), 600L);
	}
}
