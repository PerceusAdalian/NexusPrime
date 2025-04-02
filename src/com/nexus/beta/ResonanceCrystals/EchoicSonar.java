package com.nexus.beta.ResonanceCrystals;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusEffects;
import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusObjectAbilityType;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.epsilon.NexusStandardTimer;
import com.nexus.io.NexusObject.AbstractResonanceObject;

public class EchoicSonar extends AbstractResonanceObject
{

	public EchoicSonar() 
	{
		super("Echoic Sonar", "sonar_crystal", Material.ECHO_SHARD, true, true, false,
				NexusPrintUtils.assignAbilityType(NexusObjectAbilityType.UTILITY),
				"&r&f&lRight-Click&r&f to reveal nearby &r&d&oEntities&r&f.", 
				"&r&f&lRange&r&f: &b&o30 meters&r&f | &lDuration&r&f: &b&o15 Seconds&r&f",
				"&r&f&lShift_Right-Click&r&f to reveal nearby items.",
				"&r&f&lRange&r&f: &b&o50 meters&r&f | &lDuration&r&f: &b&o30 Seconds&r&f");
	}
	
	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		
		if (NexusPlayerActions.shiftRightClickAir(e)) 
		{
			if (p.getNearbyEntities(50, 50, 50).size() == 0) 
			{
				NexusPrintUtils.Print(p, "&r&7&oThe crystal resonates, but nothing happens..");
				return false;
			}
			
			p.playSound(p.getLocation(), Sound.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.MASTER, 1, 1);
			
			for (Entity entity : p.getNearbyEntities(50, 50, 50)) 
			{
				if (!(entity instanceof Item)) continue;
				NexusStandardTimer.runWithCancel(NexusProper.instance, (run) -> 
				{
					if (entity.isOnGround()) 
					{
						NexusParticles.drawPoint(entity.getLocation(), Particle.SHRIEK, 0.5, 10);
						NexusParticles.drawWisps(entity.getLocation(), entity.getWidth(), entity.getHeight(), 4, Particle.WARPED_SPORE, null);						
						entity.setGlowing(true);
					}
				}, 20, 600);
				Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()->
				{
					if (entity.isOnGround()) 
					{
						entity.setGlowing(false);
					}
				}, 600);
			}
			NexusPrintUtils.Print(p, "&r&7&oThe crystal shatters in a flash of light..");
			NexusItemCollector.remove(e);
			return true;
		}
		
		if (NexusPlayerActions.rightClickAir(e)) 
		{			
			if (p.getNearbyEntities(30, 30, 30).size() == 0) 
			{
				NexusPrintUtils.Print(p, "&r&7&oThe crystal resonates, but nothing happens..");
				return false;
			}
			
			p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.MASTER, 1, 1);
			
			for (Entity entity : p.getNearbyEntities(30, 30, 30)) 
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
			NexusPrintUtils.Print(p, "&r&7&oThe crystal shatters in a flash of light..");
			NexusItemCollector.remove(e);
			return true;
		}
		return false;
	}
}
