package com.nexus.io.ResonanceCrystals;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.PrintUtils;
import com.nexus.io.NexusObject.AbstractNexusObject;

public class EchoicLocator extends AbstractNexusObject
{

	public EchoicLocator() 
	{
		super("Resonance Crystal: Echoic Locator", "locator_crystal", Material.ECHO_SHARD, true, 
				"&r&fA highly unstable form of &e&l&oEchoic Energy&r&f.",
				"&r&f&lRight-Click&r&f to reveal nearby &r&d&oEntities&r&f.", 
				"&r&f&lRange&r&f: &b&o50 meters&r&f | &lDuration&r&f: &b&o15 Seconds&r&f",
				"&r&cDestroys&r&f this item upon use. This item is stackable.");
	}
	
	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) 
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
			
			e.getPlayer().sendMessage(PrintUtils.ColorParser("&7&oThe crystal shatters in a flash of light.."));
			NexusItemCollector.remove(e);
			return true;
		}
		return false;
	}
}
