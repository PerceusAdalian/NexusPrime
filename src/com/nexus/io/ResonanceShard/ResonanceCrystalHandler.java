package com.nexus.io.ResonanceShard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.io.objectbuilder.AbstractNexusObject;
import com.nexus.io.objectbuilder.NexusItemRegistry;

public class ResonanceCrystalHandler implements Listener
{
	private static Map<UUID, Location> playerStoredLocation = new HashMap<>();
	
	@EventHandler
	public void cast(PlayerInteractEvent event) 
	{
		ItemStack held = event.getPlayer().getInventory().getItem(EquipmentSlot.HAND);
		
		if (event.getHand() == null || !event.getHand().equals(EquipmentSlot.HAND)) 
		{
			return;
		}
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			return;
		}
		
		if (held == null || held.getType().equals(Material.AIR)) 
		{
			return;
		}
		
		if (held.getItemMeta().getPersistentDataContainer().has(AbstractNexusObject.nexusObject) && NexusItemRegistry.itemRegistry.containsKey(ResonanceCrystalObject.getInternalName())) 
		{
			Player p = event.getPlayer();
			event.setCancelled(true);
			if (event.getAction().equals(Action.RIGHT_CLICK_AIR) && p.isSneaking()) 
			{				
				if (!playerStoredLocation.containsKey(p.getUniqueId())) 
				{
					playerStoredLocation.put(p.getUniqueId(), p.getLocation());
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 1, 1);
					p.sendMessage(NexusPrintUtils.NexusPrint("&7&oThe crystal begins to glow.."));
					return;
				}
				playerStoredLocation.remove(p.getUniqueId());
				playerStoredLocation.put(p.getUniqueId(), p.getLocation());
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 1, 1);
				p.sendMessage(NexusPrintUtils.NexusPrint("&7&oThe crystal shows a different memory.."));
				return;
			}
			
			if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
			{
				p.teleport(playerStoredLocation.get(p.getUniqueId()));
				Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()->
				{
					p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);
					p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.MASTER, 1, 1);
					p.sendMessage(NexusPrintUtils.NexusPrint("&7&oThe crystal shatters.. You are as before in time."));
					playerStoredLocation.remove(p.getUniqueId());
					held.setAmount(held.getAmount() - 1);
				}, 10);
				return;				
			}
			return;
		}
		return;
	}
}
