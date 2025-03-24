package com.nexus.io.ResonanceCrystals;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusEffects;
import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.epsilon.NexusStandardTimer;

public class EchoicLocator extends AbstractResonanceCrystal
{

	public EchoicLocator() 
	{
		super("Resonance Crystal: Echoic Sonar", "sonar_crystal", Material.ECHO_SHARD, true, true,
				"&r&f&lRight-Click&r&f to reveal nearby &r&d&oEntities&r&f.", 
				"&r&f&lRange&r&f: &b&o30 meters&r&f | &lDuration&r&f: &b&o15 Seconds&r&f");
	}
	
	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		if (NexusPlayerActions.rightClickAir(e)) 
		{			
			if (e.getPlayer().getNearbyEntities(30, 30, 30).size() == 0) 
			{
				NexusPrintUtils.Print(e.getPlayer(), "&r&7&o[!] No targets found..");
				return false;
			}
			
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.MASTER, 1, 1);
			
			for (Entity entity : e.getPlayer().getNearbyEntities(30, 30, 30)) 
			{
				if (entity instanceof Player) continue;
				if (entity instanceof LivingEntity) 
				{
					NexusStandardTimer.runWithCancel(NexusProper.instance, (run) -> 
					{
						NexusParticles.drawWisps(entity.getLocation(), entity.getWidth(), entity.getHeight(), 4, Particle.SCULK_SOUL, null);
					}, 20, 300);
					NexusEffects.add(entity, PotionEffectType.GLOWING, 300, 0);
				}
			}
			NexusPrintUtils.Print(e.getPlayer(), "&r&7&oThe crystal shatters in a flash of light..");
			NexusItemCollector.remove(e);
			return true;
		}
		return false;
	}
}
