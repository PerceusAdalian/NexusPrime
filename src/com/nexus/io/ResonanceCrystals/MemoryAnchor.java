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

public class MemoryAnchor extends AbstractResonanceCrystal
{
	public MemoryAnchor() 
	{
		super("Resonance Crystal: Memory Anchor", "anchor_crystal", Material.ECHO_SHARD, true, true,
				"&r&f&lShift_Right-Click&r&f to set a &b&orecall&r&f.",
				"&r&f&lRight-Click&r&f to &b&orecall&r&f to set location.");
	}
	private static Map<UUID, Location> pStoredLocation = new HashMap<>();
	
	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		if (NexusPlayerActions.shiftRightClickAir(e)) 
		{				
			if (!pStoredLocation.containsKey(p.getUniqueId())) 
			{
				pStoredLocation.put(p.getUniqueId(), p.getLocation());
				p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
				p.sendMessage(NexusPrintUtils.ColorParser("&7&oThe crystal begins to glow.."));
				return true;
			}
			pStoredLocation.remove(p.getUniqueId());
			pStoredLocation.put(p.getUniqueId(), p.getLocation());
			p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			p.sendMessage(NexusPrintUtils.ColorParser("&7&oThe crystal shows a different memory.."));
			return true;
		}
		
		if (NexusPlayerActions.shiftRightClickAir(e)) 
		{
			if (pStoredLocation.containsKey(p.getUniqueId())) 
			{					
				p.teleport(pStoredLocation.get(p.getUniqueId()));
				Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()->
				{
					p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);
					p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.MASTER, 1, 1);
					NexusEffects.add(p, PotionEffectType.NAUSEA, 100, 0);
					p.sendMessage(NexusPrintUtils.ColorParser("&7&oThe crystal shatters.. You are as before in time."));
					pStoredLocation.remove(p.getUniqueId());					
				}, 10);
				NexusItemCollector.remove(e);
				return true;				
			}
			return false;
		}
		return false;
	}
}
