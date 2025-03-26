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
import org.bukkit.potion.PotionEffectType;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusEffects;
import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.epsilon.RayCastEntity;

public class EchoicDisruption extends AbstractResonanceCrystal
{

	public EchoicDisruption() 
	{
		super("Resonance Crystal: Echoic Disruption", "echoic_disruption_crystal", Material.ECHO_SHARD, true, true, 
				"&r&f&lRight-Click&r&f to emit a sonic boom that blasts a target away.",
				"&r&f&lRange&r&f: &b10 meters&f | &r&f&lDamage&r&f: HP*10%",
				"&r&f&lShift_Right-Click&r&f to overload the target with Resonance Energy.",
				"&r&fThis causes to target to &e&odestabilize&r&f and &4&odecay&r&f overtime.",
				"&r&f&lRange&r&f: &b30 meters&f | &r&f&lEffect Duration&r&f: &b15 seconds&f | &r&f&lDamage&r&f: HP*20%");
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		if (NexusPlayerActions.shiftRightClickAir(e)) 
		{
			Entity target = RayCastEntity.getNearest(p, 30);
			if (target != null && (target instanceof LivingEntity && !(target instanceof Player))) 
			{
				p.playSound(p.getLocation(), Sound.ENTITY_WARDEN_SONIC_CHARGE, SoundCategory.MASTER, 1, 1);
				NexusParticles.drawLine(p.getLocation(), target.getLocation(), 1, 0.5, Particle.SCULK_SOUL, null);
				Bukkit.getScheduler().runTaskLater(NexusProper.instance, () -> 
				{
					p.playSound(target.getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.MASTER, 1, 1);
					NexusEffects.add(target, PotionEffectType.WITHER, 300, 2);
					NexusParticles.drawVerticalVortex(target.getLocation(), target.getWidth()+0.5, target.getHeight()+1, 0.5, 2, 10, 0, Particle.SCULK_SOUL, null);
					((LivingEntity) target).damage((((LivingEntity) target).getHealth()*(20d/100d)));
				}, 20);
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
				p.playSound(p.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.MASTER, 1, 1);
				NexusParticles.drawLine(p.getLocation(), target.getLocation(), 2, 0.5, Particle.SONIC_BOOM, null);
				target.setVelocity(target.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(7));
				((LivingEntity) target).damage((((LivingEntity) target).getHealth()*(10d/100d)));
				NexusPrintUtils.Print(p,"&r&7&oThe crystal bursts forwad, disintegrating..");
				NexusItemCollector.remove(e);
				return true;
			}
		}
		
		NexusPrintUtils.Print(p,"&r&7&oThe crystal resonates, but nothing happens..");
		return false;
	}

}
