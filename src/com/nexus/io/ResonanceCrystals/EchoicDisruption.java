package com.nexus.io.ResonanceCrystals;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusObjectAbilityType;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.epsilon.RayCastEntity;

public class EchoicDisruption extends AbstractResonanceCrystal
{

	public EchoicDisruption() 
	{
		super("Resonance Crystal: Echoic Disruption", "echoic_disruption_crystal", Material.ECHO_SHARD, true, true, 
				NexusPrintUtils.assignAbilityType(NexusObjectAbilityType.OFFENSIVE),
				"&r&f&lRight-Click&r&f to emit a sonic boom that blasts a target away.",
				"&r&f&lRange&r&f: &b10 meters&f | &r&f&lDamage&r&f: HP*10%",
				"&r&f&lShift_Right-Click&r&f to violently fling upward.",
				"&r&f&lRange&r&f: &b40 meters&f");
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		if (NexusPlayerActions.shiftRightClickAir(e)) 
		{
			Entity target = RayCastEntity.getNearest(p, 40);
			if (target != null && (target instanceof LivingEntity && !(target instanceof Player))) 
			{
//				Vector newVector = target.getLocation().getDirection().clone().toBlockVector().add(new Vector(0,5,0)).normalize();
//					target.setVelocity(newVector.subtract(target.getLocation().toVector()).normalize().multiply(3));
				Vector newVector = target.getLocation().toVector().add(new Vector(0, 5, 0));
				Vector direction = newVector.subtract(target.getLocation().toVector()).normalize().multiply(3);
				p.playSound(p.getLocation(), Sound.ENTITY_WARDEN_SONIC_CHARGE, SoundCategory.MASTER, 1, 1);
				NexusParticles.drawLine(p.getLocation(), target.getLocation(), 1, 0.5, Particle.SCULK_SOUL, null);
				Bukkit.getScheduler().runTaskLater(NexusProper.instance, () -> 
				{
					p.playSound(target.getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.MASTER, 1, 1);
					NexusParticles.drawLine(target.getLocation(), target.getLocation().add(0,5,0), 2, 0, Particle.SONIC_BOOM, null);
					NexusParticles.drawVerticalVortex(target.getLocation(), target.getWidth()+0.5, target.getHeight()+1, 1, 4, 20, 1, Particle.SCULK_SOUL, null);
					NexusParticles.drawWisps(target.getLocation(), target.getWidth(), target.getHeight(), 20, Particle.WARPED_SPORE, null);
					target.setVelocity(direction);
					
				}, 14);
				NexusPrintUtils.Print(p,"&r&7&oThe crystal bursts forwad, disintegrating..");
				NexusItemCollector.remove(e);
				return true;
			}			
		}
		
		if (NexusPlayerActions.rightClickAir(e)) 
		{
			Entity target = RayCastEntity.getNearest(p, 10);
			if (target != null && (target instanceof LivingEntity && !(target instanceof Player))) 
			{
				NexusParticles.drawLine(p.getLocation(), target.getLocation(), 3, 0.5, Particle.SONIC_BOOM, null);
				Bukkit.getScheduler().runTaskLater(NexusProper.instance, () -> 
				{
					p.playSound(p.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.MASTER, 1, 1);
					NexusParticles.drawWisps(target.getLocation(), target.getWidth(), target.getHeight(), 20, Particle.WARPED_SPORE, null);
					target.setVelocity(target.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(7));
					((LivingEntity) target).damage((((LivingEntity) target).getHealth()*(10d/100d)));	
				}, 9);
				NexusPrintUtils.Print(p,"&r&7&oThe crystal bursts forwad, disintegrating..");
				NexusItemCollector.remove(e);
				return true;
			}
		}
		
		NexusPrintUtils.Print(p,"&r&7&oThe crystal resonates, but nothing happens..");
		return false;
	}

}
