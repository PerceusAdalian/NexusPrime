package com.nexus.epsilon;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nexus.alpha.NexusProper;

public class NexusItemCollector 
{
	public static void remove(PlayerInteractEvent e) 
	{
		if (NexusProper.debug == false) 
		{
			Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()->
			{
				e.getItem().setAmount(e.getItem().getAmount() - 1);
			}, 1);			
		}
	}
}
