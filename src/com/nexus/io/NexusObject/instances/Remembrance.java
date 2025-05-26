package com.nexus.io.NexusObject.instances;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nexus.epsilon.NexusEffects;
import com.nexus.epsilon.NexusObjectAbilityType;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.io.NexusObject.AbstractNexusObject;

public class Remembrance extends AbstractNexusObject
{

	public Remembrance() 
	{
		super("Remembrance", "remembrance_object", Material.NETHER_STAR, true, 
				"&r&e&lAction&r&f: &o&lSoothing light&r&f | " + NexusPrintUtils.assignAbilityType(NexusObjectAbilityType.SUPPORT),
				"&r&f&lRight-Click&r&f to &arestore&f &l3&r&c♥",
				"",
				"&r&7&oThis object holds no special power, save as a reminder",
				"&r&7&oof those lost in battle. Fragile as we are, we find strength",  
				"&r&7&oin unity -- when heart and soul serve a cause greater than self.", 
				"&r&7&oFor we are humanity, and no one is exempt from loss. I pray",
				"&r&7&owhatever guides you, let it be your anchor to those you cherish,",  
				"&r&7&oin life and in memory. Even as inner wars rage, remember this:",  
				"&r&7&oYou are never alone in this world we call Home.");
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		if (NexusPlayerActions.rightClickAir(e)) 
		{
			Player p = e.getPlayer();
			try 
			{
				if (p.getHealth() == p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) 
				{
					NexusPrintUtils.Print(p, "HP is already at full.");
					return true;
				}
				NexusParticles.drawDisc(p.getLocation(), p.getWidth(), 1, 15, 0.5, Particle.CLOUD, null);
				NexusEffects.playSound(p, p.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, SoundCategory.MASTER, 1, 1);
				p.setHealth(p.getHealth()+6);
				return true;
			}
			catch (IllegalArgumentException error)
			{
				NexusParticles.drawDisc(p.getLocation(), p.getWidth(), 1, 15, 0.5, Particle.CLOUD, null);
				NexusEffects.playSound(p, p.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, SoundCategory.MASTER, 1, 1);
				p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
				return true;
			}
		}
		return false;
	}

}
