package com.nexus.beta.ResonanceCrystals;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusObjectAbilityType;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.io.NexusObject.AbstractResonanceObject;

public class PocketDimension extends AbstractResonanceObject
{

	public PocketDimension() 
	{
		super("Pocket Dimension", "pocket_dimension", Material.ECHO_SHARD, true, true, false,
				NexusPrintUtils.assignAbilityType(NexusObjectAbilityType.UTILITY),
				"&r&f&lRight-Click&r&f to open a small rift that stores items.");
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		if (!NexusPlayerActions.rightClickAir(e)) 
		{
			return false;
		}
		
		e.getPlayer().openInventory(e.getPlayer().getEnderChest());
		e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.MASTER, 1, 1);
		NexusParticles.drawDisc(e.getPlayer().getLocation(), e.getPlayer().getWidth()+0.5, 1, 20, 0.5, Particle.WARPED_SPORE, null);
		NexusPrintUtils.Print(e.getPlayer(), "&r&7&oThe crystal tears open a rift, disappearing..");
		NexusItemCollector.remove(e);
		return false;
	}
}
