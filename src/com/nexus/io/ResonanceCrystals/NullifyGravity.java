package com.nexus.io.ResonanceCrystals;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
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
import com.nexus.epsilon.RayCastEntity;

//Not yet added

public class NullifyGravity extends AbstractResonanceCrystal
{

	public NullifyGravity() 
	{
		super("Resonance Crystal: Nullify Gravity", "nullify_gravity_crystal", Material.ECHO_SHARD, true, true,
				NexusPrintUtils.assignAbilityType(NexusObjectAbilityType.OFFENSIVE, NexusObjectAbilityType.UTILITY),
				"&r&f&lRight-Click&r&f to distort gravity for you or others.",
				"&r&f&lTarget Range&r&f: &b&o25 meters&r&f | &lDuration&r&f: &b&o15 Seconds&r&f",
				"&r&f&lShift_Right-Click&r&f to allow temporary flight.",
				"&r&f&lDuration&r&f: &b&o35 Seconds&r&f");
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		if (NexusPlayerActions.shiftRightClickAir(e)) 
		{
			if (!p.getGameMode().equals(GameMode.SURVIVAL)) 
			{
				NexusPrintUtils.NexusFormatError(p, "&r&4This ability does not apply to you.. Try again in Survival Mode.");
				return false;
			}
			if (p.getAllowFlight() || p.isFlying()) 
			{
				NexusPrintUtils.Print(p, "&r&7&oThe crystal resonates, but nothing happens..");
				return false;
			}
			p.playSound(p.getLocation(), Sound.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.MASTER, 1, 1);
			NexusParticles.drawDisc(p.getLocation(), p.getWidth()+0.5, 1, 20, 0, Particle.WARPED_SPORE, null);
			NexusParticles.drawVerticalVortex(p.getLocation(), p.getWidth()+0.5, p.getHeight()+1, 1, 4, 20, 1, Particle.SCULK_SOUL, null);
			NexusEffects.add(e, PotionEffectType.JUMP_BOOST, 700, 4);
			p.setAllowFlight(true);
			NexusItemCollector.remove(e);
			NexusPrintUtils.Print(p, "&r&7&oThe crystal hums and shatters; gravity dissipates..");
			NexusStandardTimer.runWithCancel(NexusProper.instance, (r)->
			{
				NexusParticles.drawWisps(p.getLocation(), p.getWidth(), p.getHeight(), 4, Particle.SCULK_SOUL, null);
				NexusParticles.drawPoint(p.getLocation(), Particle.WHITE_ASH, 0, null);
				NexusParticles.drawPoint(p.getLocation(), Particle.END_ROD, 0, null);
			}, 20, 700);
			Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()-> 
			{
				p.setAllowFlight(false);
				
			}, 700);
			return true;
		}
		
		if (NexusPlayerActions.rightClickAir(e)) 
		{
			Entity target = RayCastEntity.getNearest(p, 25);
			
			if (target == null) 
			{
				NexusEffects.add(e, PotionEffectType.SLOW_FALLING, 300, 0);
				NexusEffects.add(e, PotionEffectType.JUMP_BOOST, 300, 2);
				p.playSound(p.getLocation(), Sound.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.MASTER, 1, 1);
				NexusStandardTimer.runWithCancel(NexusProper.instance, (task)->
				{
					NexusParticles.drawWisps(p.getLocation(), p.getWidth(), p.getHeight(), 4, Particle.SCULK_SOUL, null);
				}, 20, 300);
				NexusPrintUtils.Print(p,"&r&7&oGravity has been decreased; the crystal returns to dust..");
				NexusItemCollector.remove(e);
				return true;
			}
			
			NexusParticles.drawLine(p.getLocation(), target.getLocation(), 0.5, 0.5, Particle.WARPED_SPORE, null);
			p.playSound(target.getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.MASTER, 1, 1);
			Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()->
			{
				NexusParticles.drawVerticalVortex(target.getLocation(), target.getWidth()+1, target.getHeight()+1, 1, 2, 5, 0, Particle.SCULK_SOUL, null);				
				NexusEffects.add(target, PotionEffectType.LEVITATION, 300, 0);
				p.playSound(p.getLocation(), Sound.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.MASTER, 1, 1);
				NexusStandardTimer.runWithCancel(NexusProper.instance, (task)->
				{
					NexusParticles.drawWisps(target.getLocation(), target.getWidth(), target.getHeight(), 4, Particle.SCULK_SOUL, null);
				}, 20, 300);
			}, 20);
			NexusPrintUtils.Print(p, "&r&7&oThe crystal returns to dust..");
			NexusItemCollector.remove(e);
			return true;
		}
		return false;
	}
	
}
