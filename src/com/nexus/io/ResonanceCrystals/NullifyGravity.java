package com.nexus.io.ResonanceCrystals;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusEffects;
import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusStandardTimer;
import com.nexus.epsilon.RayCastEntity;

//Not yet added

public class NullifyGravity extends AbstractResonanceCrystal
{

	public NullifyGravity() 
	{
		super("Resonance Crystal: Nullify Gravity", "nullify_gravity_crystal", Material.ECHO_SHARD, true, true,
				"&r&f&lRight-Click&r&f to distort gravity for you or others.",
				"&r&f&lTarget Range&r&f: &b&o25 meters&r&f | &lDuration&r&f: &b&o15 Seconds&r&f");
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
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.MASTER, 1, 1);
				NexusStandardTimer.runWithCancel(NexusProper.instance, (task)->
				{
					NexusParticles.drawWisps(e.getPlayer().getLocation(), e.getPlayer().getWidth(), e.getPlayer().getHeight(), 4, Particle.SCULK_SOUL, null);
				}, 20, 300);
				e.getPlayer().sendMessage("Gravity has been nullified; the crystal returns to dust..");
				NexusItemCollector.remove(e);
				return true;
			}
			Entity target = RayCastEntity.getNearest(e.getPlayer(), 25);
			NexusParticles.drawLine(e.getPlayer().getLocation(), target.getLocation(), 3, 0.5, Particle.END_ROD, null);
			Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()->
			{
				NexusEffects.add(target, PotionEffectType.LEVITATION, 300, 0);
				e.getPlayer().playSound(target.getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.MASTER, 1, 1);
				NexusParticles.drawVerticalVortex(target.getLocation(), target.getWidth()+1, target.getHeight()+1, 1, 2, 5, 0, Particle.SCULK_SOUL, null);				
				NexusStandardTimer.runWithCancel(NexusProper.instance, (task)->
				{
					NexusParticles.drawWisps(target.getLocation(), target.getWidth(), target.getHeight(), 4, Particle.SCULK_SOUL, null);
				}, 20, 300);
			}, 20);
			e.getPlayer().sendMessage("The crystal returns to dust..");
			NexusItemCollector.remove(e);
			return true;
		}
		return false;
	}
	
}
