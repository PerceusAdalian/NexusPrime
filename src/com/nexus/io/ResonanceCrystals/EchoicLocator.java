package com.nexus.io.ResonanceCrystals;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusPlayerActions;

public class EchoicLocator extends AbstractResonanceCrystal
{

	public EchoicLocator() 
	{
		super("Resonance Crystal: Echoic Locator", "locator_crystal", Material.ECHO_SHARD, true, true,
				"&r&f&lRight-Click&r&f to reveal nearby &r&d&oEntities&r&f.", 
				"&r&f&lRange&r&f: &b&o50 meters&r&f | &lDuration&r&f: &b&o15 Seconds&r&f");
	}
	
	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		if (NexusPlayerActions.rightClickAir(e)) 
		{			
			if (e.getPlayer().getNearbyEntities(50, 50, 50).size() == 0) 
			{
				e.getPlayer().sendMessage("[!] There are no available targets nearby.");
				return false;
			}
			
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, SoundCategory.MASTER, 1, 1);
			
			for (Entity entity : e.getPlayer().getNearbyEntities(50, 50, 50)) 
			{
				if (entity instanceof Player) continue;
				if (entity instanceof LivingEntity) 
				{
					((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 300, 0));
				}
			}
			
			e.getPlayer().sendMessage("The crystal shatters in a flash of light..");
			NexusItemCollector.remove(e);
			return true;
		}
		return false;
	}
}
