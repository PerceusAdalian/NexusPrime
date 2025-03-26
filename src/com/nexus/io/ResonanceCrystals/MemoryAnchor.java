package com.nexus.io.ResonanceCrystals;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusEffects;
import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusParticles;
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
				p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SENSOR_CLICKING, SoundCategory.MASTER, 1, 1);
				NexusPrintUtils.Print(p, "&7&oThe crystal begins to glow..");
				return true;
			}
			pStoredLocation.remove(p.getUniqueId());
			pStoredLocation.put(p.getUniqueId(), p.getLocation());
			p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SENSOR_CLICKING, SoundCategory.MASTER, 1, 1);
			NexusPrintUtils.Print(p, "&7&oThe crystal shows a different memory..");
			return true;
		}
		
		if (NexusPlayerActions.rightClickAir(e)) 
		{
			if (pStoredLocation.containsKey(p.getUniqueId())) 
			{					
				p.teleport(pStoredLocation.get(p.getUniqueId()));
				Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()->
				{
					p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);
					p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.MASTER, 1, 1);
					NexusParticles.drawVerticalVortex(p.getLocation(), p.getWidth()+0.5, p.getHeight()+2, 0.5, 3, 10, 0, Particle.SCULK_SOUL, null);
					NexusEffects.add(p, PotionEffectType.NAUSEA, 100, 0);
					NexusEffects.add(p, PotionEffectType.DARKNESS, 100, 2);
					NexusPrintUtils.Print(p, "&7&oThe crystal shatters.. You are as before in time.");
					pStoredLocation.remove(p.getUniqueId());					
				}, 10);
				NexusItemCollector.remove(e);
				return true;				
			}
			NexusPrintUtils.Print(p, "&r&7&oThe crystal resonates, but nothing happens..");
			return false;
		}
		return false;
	}
}
