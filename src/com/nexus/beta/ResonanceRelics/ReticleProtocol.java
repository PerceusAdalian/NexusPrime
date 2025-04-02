package com.nexus.beta.ResonanceRelics;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusObjectAbilityType;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.epsilon.NexusStandardTimer;
import com.nexus.epsilon.RayCastEntity;
import com.nexus.io.NexusObject.AbstractResonanceObject;

public class ReticleProtocol extends AbstractResonanceObject
{

	public ReticleProtocol() 
	{
		super("Reticle Protocol", "projectile_relic", Material.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE, true, false, true, 
		NexusPrintUtils.assignAbilityType(NexusObjectAbilityType.OFFENSIVE),
		"&r&f&lRight-Click&r&f to fire a projectile of echoic energy.",
		"&r&c&lDamage&r&f: 4&r&c♥&r&f | &r&b&lCooldown&r&f: 2s",
		"&r&f&lShift_Right-Click&r&f to damage target entity.",
		"&r&f&lRange: 15 &b&ometers&r&f | &r&c&lDamage&r&f: 2.5&r&c♥&r&f");
	}
	
	public static Map<UUID, Boolean> projectileCooldownCheck = new HashMap<>();
	
	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		
		if(NexusPlayerActions.shiftRightClickAir(e)) 
		{
			Entity target = RayCastEntity.getNearest(p, 15);
			
			if (target == null) 
			{
				NexusPrintUtils.Print(p, "&r&7&oNo target found..");
				return false;
			}
			p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.MASTER, 1, 1);
			NexusParticles.drawAngledArcLine(p.getLocation(), target.getLocation(), 1, 4, 45, 5, Particle.ASH, Color.TEAL);
			NexusParticles.drawAngledArcLine(p.getLocation(), target.getLocation(), 3, 6, 60, 3, Particle.ASH, Color.TEAL);
			NexusParticles.drawAngledArcLine(p.getLocation(), target.getLocation(), 2, 5, 90, 7, Particle.ASH, Color.TEAL);
			((Damageable) target).damage(2.5);
			return true;
		}
		
		if (NexusPlayerActions.rightClickAir(e)) 
		{
			
			if (projectileCooldownCheck.containsKey(p.getUniqueId())) 
			{
				return false;
			}
			
			projectileCooldownCheck.put(p.getUniqueId(), true);
			
			Arrow arrow = p.launchProjectile(Arrow.class);
			arrow.setDamage(4);
			arrow.setGravity(false);
			arrow.setGlowing(true);
			arrow.setColor(Color.TEAL);
			arrow.setCritical(false);
			arrow.setPickupStatus(PickupStatus.DISALLOWED);
			
			p.playSound(p.getLocation(), Sound.ENTITY_SHULKER_SHOOT, SoundCategory.MASTER, 1, 1);
			
			NexusStandardTimer.runWithCancel(NexusProper.instance, (r)->
			{
				if (arrow.isDead() || arrow.isOnGround()) 
				{
					return;
				}
				NexusParticles.drawPoint(arrow.getLocation(), Particle.SCULK_CHARGE, 0, 1);
			}, 1, 40);
			
			Bukkit.getScheduler().runTaskLater(NexusProper.instance, () -> 
			{
				arrow.remove();
				projectileCooldownCheck.remove(p.getUniqueId());
			}, 40);
			return true;
		}
		
		return false;
	}
	
}
