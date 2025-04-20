package com.nexus.beta.ResonanceRelics;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusObjectAbilityType;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.epsilon.NexusWorldEvents;
import com.nexus.epsilon.RayCastEntity;
import com.nexus.io.NexusObject.AbstractResonanceObject;

public class VectorDisplacementProtocol extends AbstractResonanceObject
{

	public VectorDisplacementProtocol() 
	{
		super("Vector Displacement Protocol", "grapple_relic", Material.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE, true, false, true, 
				NexusPrintUtils.assignAbilityType(NexusObjectAbilityType.UTILITY),
				"&r&f&lRight-Click&r&f target &b&oblock&r&f or &d&oentity&r&f to &ograpple&r&f towards it.",
				"&r&3&lVector Range&r&f: 80 meters.");
	}

	protected static Map<UUID, Boolean> cooldownCheck = new HashMap<>();
	
	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		
		if (NexusPlayerActions.rightClickAir(e)) 
		{
			Player p = e.getPlayer();
			Block bTarget = NexusWorldEvents.rayTraceBlock(p, 80);
			Entity eTarget = RayCastEntity.getNearest(p, 80);
			
			if (bTarget != null) 
			{
				if (cooldownCheck.containsKey(p.getUniqueId())) 
				{
					return false;
				}
				
				cooldownCheck.put(p.getUniqueId(), true);
				
				p.playSound(p.getLocation(), Sound.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.MASTER, 1, 1);
				NexusParticles.drawDisc(p.getLocation(), p.getWidth()+0.5, 1, 20, 0, Particle.WARPED_SPORE, null);				
				NexusParticles.drawLine(p.getLocation(), bTarget.getLocation().clone(), 0.5, 0.5, Particle.WARPED_SPORE, null);
				NexusParticles.drawLine(p.getLocation(), bTarget.getLocation().clone(), 0.5, 0.5, Particle.ASH, null);
				
				new BukkitRunnable() 
				{
					public void run()
					{
						if (!p.isOnline() || p.isDead()) 
						{
							cancel();
							cooldownCheck.remove(p.getUniqueId());
							return;
						}
						
						Vector direction = bTarget.getLocation().add(0.5,1,0.5).toVector().subtract(p.getLocation().toVector());
						double distance = direction.length();
						
						if (distance < 1.5) 
						{
							cancel();
							cooldownCheck.remove(p.getUniqueId());
							return;
						}
						
						Block cLocYLower = p.getLocation().subtract(0.75,0.1,0.75).getBlock();
						Block cLocYUpper = p.getLocation().add(1.5,2,1.5).getBlock();
						if (cLocYLower.getType().isSolid() || cLocYUpper.getType().isSolid()) 
						{
							cancel();
							cooldownCheck.remove(p.getUniqueId());
							return;
						}
						
						if (!p.getLocation().getBlock().isPassable()) 
						{
							cancel();
							cooldownCheck.remove(p.getUniqueId());
							return;
						}
						
						direction.normalize().multiply(1.5);
						NexusParticles.drawLine(bTarget.getLocation().clone(), p.getLocation(), 3, 0.5, Particle.END_ROD, null);
						p.setVelocity(direction);
					}
				}.runTaskTimer(NexusProper.instance, 0L, 1L);
				
				return true;
			}
			
			if (eTarget != null) 
			{
				if (cooldownCheck.containsKey(p.getUniqueId())) 
				{
					return false;
				}

				cooldownCheck.put(p.getUniqueId(), true);
				
				p.playSound(p.getLocation(), Sound.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.MASTER, 1, 1);
				NexusParticles.drawDisc(p.getLocation(), p.getWidth()+0.5, 1, 20, 0, Particle.WARPED_SPORE, null);				
				NexusParticles.drawLine(p.getLocation(), eTarget.getLocation(), 0.5, 0.5, Particle.WARPED_SPORE, null);
				NexusParticles.drawLine(p.getLocation(), eTarget.getLocation(), 0.5, 0.5, Particle.ASH, null);
				
				
				new BukkitRunnable() 
				{
					public void run()
					{
						if (!p.isOnline() || p.isDead()) 
						{
							cancel();
							cooldownCheck.remove(p.getUniqueId());
							return;
						}
						
						Vector direction = eTarget.getLocation().add(0.5,1,0.5).toVector().subtract(p.getLocation().toVector());
						double distance = direction.length();
						
						if (distance < 1.5) 
						{
							cancel();
							cooldownCheck.remove(p.getUniqueId());
							return;
						}
						
						Block cLoc = p.getLocation().subtract(0.75,0.1,0.75).getBlock();
						if (cLoc.getType().isSolid()) 
						{
							cancel();
							cooldownCheck.remove(p.getUniqueId());
							return;
						}
						
						if (!p.getLocation().getBlock().isPassable()) 
						{
							cancel();
							cooldownCheck.remove(p.getUniqueId());
							return;
						}
						
						direction.normalize().multiply(1.5);
						NexusParticles.drawLine(eTarget.getLocation(), p.getLocation(), 3, 0.5, Particle.END_ROD, null);
						p.setVelocity(direction);
					}
				}.runTaskTimer(NexusProper.instance, 0L, 1L);
				return true;
			}
			return false;
		}
		return false;
	}

}
