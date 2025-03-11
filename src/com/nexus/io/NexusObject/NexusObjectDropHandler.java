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
			if (r.nextDouble() >= 0.5) return;
			e.getDrops().add(item.bake());
		}
	}
}
