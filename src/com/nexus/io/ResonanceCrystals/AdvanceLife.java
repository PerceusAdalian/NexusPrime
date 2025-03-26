package com.nexus.io.ResonanceCrystals;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.epsilon.NexusStandardTimer;
import com.nexus.epsilon.NexusWorldEvents;
import com.nexus.epsilon.RayCastEntity;

public class AdvanceLife extends AbstractResonanceCrystal

{

	public AdvanceLife() 
	{
		super("Resonance Crystal: Advance Life", "advance_life_crystal", Material.ECHO_SHARD, true, true,
				"&r&f&lRight-Click&r&f to rapidly grow nearby crops.",
				"&r&f&lEffect Radius&r&f: 30 &b&ometers&r&f",
				"&r&f&lShift_Right-Click&r&f to &c&odamage&r&f target and &a&oheal&r&f self over time.",
				"&r&f&lRange&r&f: 20 &bmeters&f | &r&c&lDamage&r&f: HP*10% | &r&a&lHealing&r&f: +0.5&c♥&f | &r&b&lDuration&r&f: 10s");
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		if (NexusPlayerActions.shiftRightClickAir(e)) 
		{			
			Entity target = RayCastEntity.getNearest(p, 20);
			if (target == null) 
			{
				NexusPrintUtils.Print(p, "&r&7&oThe crystal resonates, but nothing happens..");
				return false;
			}
			if (target instanceof LivingEntity || target instanceof Player) 
			{
				p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.MASTER, 1, 1);
				NexusParticles.drawLine(p.getLocation(), target.getLocation(), 0.5, 1, Particle.ASH, null);
				NexusStandardTimer.runWithCancel(NexusProper.instance, (r)->
				{
					((Damageable) target).damage(((Damageable) target).getHealth() * 0.1);
					NexusParticles.drawWisps(target.getLocation(), target.getWidth(), target.getHeight(), 3, Particle.SCULK_SOUL, null);
					NexusParticles.drawWisps(target.getLocation(), target.getWidth(), target.getHeight(), 5, Particle.WARPED_SPORE, null);
					try
					{
						p.setHealth(p.getHealth()+1);
					} catch (IllegalArgumentException error) 
					{
						p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
					}
				}, 20, 200);
				NexusItemCollector.remove(e);
				NexusPrintUtils.Print(p, "&r&7&oThe crystal burts forwad, shattering..");
				return true;
			}
			NexusPrintUtils.Print(p, "&r&7&oThe crystal resonates, but nothing happens..");
			return false;
		}
		
		if (NexusPlayerActions.rightClickAir(e)) 
		{
			List<Block> target = NexusWorldEvents.getNearbyBlocks(p.getLocation(), 30);
			
			boolean validTargets = target.stream().anyMatch(block -> block.getBlockData() instanceof Ageable);
			
			if (!validTargets) 
			{
				NexusPrintUtils.Print(p, "&r&7&oThe crystal resonates, but nothing happens..");
				return false;
			}
			
			p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.MASTER, 1, 1);
			for (Block block : target) 
			{
				if (block.getBlockData() != null && block.getBlockData() instanceof Ageable) 
				{
					NexusParticles.drawArcLine(p.getLocation(), block.getLocation(), 1, 6, Particle.ASH, null);
				}
				Bukkit.getScheduler().runTaskLater(NexusProper.instance, () -> 
				{
					
					if (block.getBlockData() != null && block.getBlockData() instanceof Ageable) 
					{
						NexusParticles.drawWisps(block.getLocation().add(0,0.5,0), 1, 1, 10, Particle.WARPED_SPORE, null);				
						NexusParticles.drawWisps(block.getLocation().add(0,0.5,0), 1, 1, 2, Particle.SCULK_SOUL, null);
						p.playSound(block.getLocation(), Sound.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.MASTER, 1, 1);
						Ageable data = (Ageable) block.getBlockData();
						data.setAge(data.getMaximumAge());
						block.setBlockData(data);
					}				
				}, 40);
			}
			NexusItemCollector.remove(e);
			NexusPrintUtils.Print(p, "&r&7&oNearby harmonic frequencies change, the crystal returns to dust..");
			return true;	
		}
		return false;
	}
}