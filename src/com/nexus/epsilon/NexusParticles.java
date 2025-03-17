package com.nexus.epsilon;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class NexusParticles 
{
	public static <T> void drawLine(Location source, Location target, int interval, Particle p, T data) 
	{
		if (!source.getWorld().equals(target.getWorld())) 
		{
			return;
		}
		
		World w = source.getWorld();
		
		Vector v1 = source.toVector();
		Vector v2 = target.toVector();
		Vector vPrime = v2.subtract(v1).normalize().multiply(interval);
		
		double d1 = source.distance(target);
		double d2 = 0;
		
		for (;d2 < d1; v1.add(vPrime)) 
		{
			w.spawnParticle(p, v1.getX(), v1.getY(), v1.getZ(), 0, 0, 0, 0, data);
			d2 += interval;
		}
	}
}
