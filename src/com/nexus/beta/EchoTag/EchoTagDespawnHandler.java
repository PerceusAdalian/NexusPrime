package com.nexus.beta.EchoTag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.inventory.ItemStack;


public class EchoTagDespawnHandler implements Listener
{
	@EventHandler
	public void onItemDespawn(ItemDespawnEvent e) 
	{
		ItemStack item = e.getEntity().getItemStack();
		if (EchoNamespace.isEchoBound(item)) 
		{
			e.setCancelled(true);
		}
	}
}
