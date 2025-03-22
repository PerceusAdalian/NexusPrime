package com.nexus.io.ResonanceCrystals;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.epsilon.RayCastEntity;

public class VectorProtocol extends AbstractResonanceCrystal
{

	public VectorProtocol() 
	{
		super("Resonance Crystal: Vector Protocol", "vector_ability_crystal", Material.ECHO_SHARD, true, true,
				"&r&f&lRight-Click&r&f to rush towards a target.",
				"&r&f&lShift_Right-Click&r&f to pull a target towards you.",
				"&r&fDeal &c&odamage&r&f based on distance traveled.",
				"&r&f&lRange&r&f: &b&o30 meters&r&f | &c&lDamage&r&f = (distance * 1.25)");
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		Entity target = RayCastEntity.getNearest(p, 30);

		if (!(target instanceof LivingEntity)) 
		{
			return false;
		}
		
		if (RayCastEntity.getNearest(p, 30) == null) 
		{
			NexusPrintUtils.NexusFormatError(p, "&r&7&oNo target found.. ");
			return false;
		}
		
		if (NexusPlayerActions.shiftRightClickAir(e)) //This doesn't work as planned..
		{
			NexusParticles.drawLine(p.getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			target.setVelocity(p.getLocation().toVector().subtract(target.getLocation().toVector()).normalize().multiply(10));
			double distanceDamage = target.getLocation().toVector().distance(p.getLocation().toVector()) * 1.25;
			((LivingEntity) target).damage(distanceDamage, p);
			NexusPrintUtils.Print(p, "&r&7&oThe crystal rips the target towards you, shattering..");
			NexusItemCollector.remove(e);
			return true;
		}

		if (NexusPlayerActions.rightClickAir(e)) 
		{
			NexusParticles.drawLine(p.getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			p.setVelocity(target.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(5));
			double distanceDamage = target.getLocation().toVector().distance(p.getLocation().toVector()) * 1.25;
			((LivingEntity) target).damage(distanceDamage, p);
			NexusPrintUtils.Print(p, "&r&7&oYou rush towards the target -- the crystal shatters..");
			NexusItemCollector.remove(e);
			return true;
		}
		
		return false;
	}

}
