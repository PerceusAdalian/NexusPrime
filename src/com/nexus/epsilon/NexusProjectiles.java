package com.nexus.epsilon;

import org.bukkit.Color;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.projectiles.ProjectileSource;

public class NexusProjectiles 
{
	public static void launchArrow(ProjectileSource source, double damage, boolean hasGravity, boolean isGlowing, boolean isCritical, Color color, PickupStatus status) 
	{
		Arrow obj = source.launchProjectile(Arrow.class);
		obj.setDamage(damage);
		obj.setGravity(hasGravity);
		obj.setGlowing(isGlowing);
		obj.setColor(color);
		obj.setPickupStatus(status);
		obj.setCritical(isCritical);
	}
}
