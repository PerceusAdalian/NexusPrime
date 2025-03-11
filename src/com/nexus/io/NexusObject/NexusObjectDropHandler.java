package com.nexus.io.NexusObject;

import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class NexusObjectDropHandler implements Listener
{
	public Random r = new Random();
	
	@EventHandler
	public void drop(EntityDeathEvent e) 
	{
		if (!(e.getEntity() instanceof LivingEntity)) 
		{
			return;
		}
		
		for (AbstractNexusObject item : NexusItemRegistry.itemRegistry.values()) 
		{
			final double dropChance = 0.1d;
			if (r.nextDouble() >= dropChance) return;
			e.getDrops().add(item.bake());
		}
	}
}
