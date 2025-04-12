package com.nexus.chi.objects;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nexus.chi.NexusGuiHandler;
import com.nexus.chi.guis.NexusMainPageGui;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.io.NexusObject.AbstractNexusObject;

public class NexusSlateObject extends AbstractNexusObject
{

	public NexusSlateObject() 
	{
		super("Nexus Slate", "nexus_slate", Material.KNOWLEDGE_BOOK, true, 
				"&r&fA tome infused with &e&oresonance energy&r&f.",
				"&r&f&lRight-Click&r&f to interface into the &e&l⋊&r&3&lNexus Menu&r&e&l⋉&r&f.",
				"\n","&r&7&oThis item may be reused indefinitely.");
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		if (NexusPlayerActions.rightClickAir(e)) 
		{
			NexusParticles.drawDisc(p.getLocation(), p.getWidth(), 1, 10, 1.0, Particle.GLOW_SQUID_INK, null);
			p.playSound(p.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			NexusGuiHandler.open(p, new NexusMainPageGui(p));
			return true;
		}
		return false;
	}

}
