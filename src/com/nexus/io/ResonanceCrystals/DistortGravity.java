package com.nexus.io.ResonanceCrystals;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import com.nexus.epsilon.NexusEffects;
import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.epsilon.RayCastEntity;
import com.nexus.io.NexusObject.AbstractNexusObject;

//Not yet added

public class DistortGravity extends AbstractNexusObject
{

	public DistortGravity() 
	{
		super("Gravity Null-Point Protocol", "distort_gravity", Material.ECHO_SHARD, true, 
				"&r&fA highly unstable form of &e&l&oEchoic Energy&r&f.",
				"&r&f&lRight-Click&r&f to distort gravity for you or others.",
				"&r&f&lTarget Range&r&f: &b&o25 meters&r&f | &lDuration&r&f: &b&o15 Seconds&r&f",
				"&r&cDestroys&r&f this item upon use. Stackable.");
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		if (NexusPlayerActions.rightClickAir(e)) 
		{
			if (RayCastEntity.getNearest(e.getPlayer(), 25) == null) 
			{
				NexusEffects.add(e, PotionEffectType.SLOW_FALLING, 300, 0);
				NexusEffects.add(e, PotionEffectType.JUMP_BOOST, 300, 2);
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, SoundCategory.MASTER, 1, 1);
				e.getPlayer().sendMessage(NexusPrintUtils.ColorParser("&7&oThe crystal returns to dust.."));
				NexusItemCollector.remove(e);
				return true;
			}
			Entity target = RayCastEntity.getNearest(e.getPlayer(), 25);
			NexusEffects.add(target, PotionEffectType.LEVITATION, 30, 0);
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.MASTER, 1, 1);
			NexusItemCollector.remove(e);
			return true;
		}
		return false;
	}
	
}
