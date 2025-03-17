package com.nexus.io.NexusObject;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;

public class NexusObjectCastHandler implements Listener
{
	// Make the cast e for nexus objects
	@EventHandler
	public boolean onCast(PlayerInteractEvent e) 
	{
		ItemStack held = e.getPlayer().getInventory().getItem(EquipmentSlot.HAND);
		
		if (e.getHand() == null || !e.getHand().equals(EquipmentSlot.HAND)) 
		{
			return false;
		}
		
		if (!NexusPlayerActions.rightClickAir(e)) 
		{
			return false;
		}
		
		if (held == null || held.getType().equals(Material.AIR)) 
		{
			return false;
		}
		
		if (held.getItemMeta() == null || !held.getItemMeta().getPersistentDataContainer().has(AbstractNexusObject.nexusObject, PersistentDataType.STRING))
		{
			return false;
		}
	    
	    if (NexusItemRegistry.itemRegistry.get(held.getItemMeta().getPersistentDataContainer().get(AbstractNexusObject.nexusObject, PersistentDataType.STRING)).Cast(e)) 
	    {
	    	e.setCancelled(true);
			
			if (NexusProper.debug) 
			{
				String internalName = null;
				if (held.getItemMeta() != null) 
				{
					internalName = held.getItemMeta().getPersistentDataContainer().get(AbstractNexusObject.nexusObject, PersistentDataType.STRING);
				}
				
				if (internalName == null) 
				{
					NexusPrintUtils.NexusConsoleError("From: NexusObjectCastHandler.java | Could not retrieve internal name from baked item.");
	                return true;
				}
				
				AbstractNexusObject nexusObject = NexusItemRegistry.itemRegistry.get(internalName);
				if (nexusObject == null) 
				{
					NexusPrintUtils.NexusConsoleError("From: NexusObjectCastHandler.java | Internal name exists, but item is not in the registry.");
	                return true;
				}
				NexusPrintUtils.NexusConsoleDebug(e.getPlayer().getName() + " has used item: " + nexusObject.getName() + " | ID: (" + AbstractNexusObject.getInternalNameAsID(internalName) + ")");
			}
			return true;
	    }
		return false;
	}
}
