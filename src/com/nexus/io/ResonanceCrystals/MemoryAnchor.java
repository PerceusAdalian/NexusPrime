package com.nexus.io.ResonanceCrystals;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusEffects;
import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.io.NexusObject.AbstractNexusObject;

public class MemoryAnchor extends AbstractNexusObject
{
	public MemoryAnchor() 
	{
		super("Resonance Crystal: Memory Anchor", "anchor_crystal", Material.ECHO_SHARD, true,
				"&r&fA highly unstable form of &e&l&oEchoic Energy&r&f.",
				"&r&f&lShift_Right-Click&r&f to set a &b&orecall&r&f.",
				"&r&f&lRight-Click&r&f to &b&orecall&r&f to set location.",
				"&r&cDestroys&r&f this item upon use. Stackable.");
	}
	private static Map<UUID, Location> playerStoredLocation = new HashMap<>();
	
	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player player = e.getPlayer();
		if (NexusPlayerActions.shiftRightClickAir(e)) 
		{				
			if (!playerStoredLocation.containsKey(player.getUniqueId())) 
			{
				playerStoredLocation.put(player.getUniqueId(), player.getLocation());
				player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
				player.sendMessage(NexusPrintUtils.ColorParser("&7&oThe crystal begins to glow.."));
				return true;
			}
			playerStoredLocation.remove(player.getUniqueId());
			playerStoredLocation.put(player.getUniqueId(), player.getLocation());
			player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			player.sendMessage(NexusPrintUtils.ColorParser("&7&oThe crystal shows a different memory.."));
			return true;
		}
		
		if (NexusPlayerActions.shiftRightClickAir(e)) 
		{
			if (playerStoredLocation.containsKey(player.getUniqueId())) 
			{					
				player.teleport(playerStoredLocation.get(player.getUniqueId()));
				Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()->
				{
					player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);
					player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.MASTER, 1, 1);
					NexusEffects.add(player, PotionEffectType.NAUSEA, 100, 0);
					player.sendMessage(NexusPrintUtils.ColorParser("&7&oThe crystal shatters.. You are as before in time."));
					playerStoredLocation.remove(player.getUniqueId());					
				}, 10);
				NexusItemCollector.remove(e);
				return true;				
			}
			return false;
		}
		return false;
	}
}
