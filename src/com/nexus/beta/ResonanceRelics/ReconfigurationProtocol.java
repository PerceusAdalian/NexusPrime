package com.nexus.beta.ResonanceRelics;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nexus.epsilon.NexusObjectAbilityType;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.io.NexusObject.AbstractResonanceObject;

public class ReconfigurationProtocol extends AbstractResonanceObject
{

	public ReconfigurationProtocol() 
	{
		super("Reconfiguration Protocol", "crafting_relic", Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, true, false, true, 
		NexusPrintUtils.assignAbilityType(NexusObjectAbilityType.UTILITY),
		"&r&f&lRight-Click&r&f to open your own crafting grid.",
		"&r&f&lShift_Right-Click&r&f to open your personal ender chest.");
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		
		if (NexusPlayerActions.shiftRightClickAir(e)) 
		{
			NexusParticles.drawCylinder(p.getLocation(), p.getWidth()+0.5, (int) p.getHeight(), 10, 1, 0.5, Particle.SCULK_SOUL, null);
			p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, SoundCategory.MASTER, 1, 1);
			p.openInventory(p.getEnderChest());
			return true;
		}
		
		if (NexusPlayerActions.rightClickAir(e)) 
		{
			NexusParticles.drawCylinder(p.getLocation(), p.getWidth()+0.5, (int) p.getHeight(), 10, 1, 0.5, Particle.SCULK_SOUL, null);
			p.playSound(p.getLocation(), Sound.BLOCK_CRAFTER_CRAFT, SoundCategory.MASTER, 1, 1);
			p.openInventory(p.openWorkbench(p.getLocation(), true));
			return true;
		}
		
		return false;
	}

}
