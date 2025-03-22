package com.nexus.io.ResonanceCrystals;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;

public class RiftPostulate extends AbstractResonanceCrystal
{

	public RiftPostulate() 
	{
		super("Resonance Crystal: Rift Postulate", "rift_postulate_crystal", Material.ECHO_SHARD, true, true, "&r&f&lRight-Click&r&f to open a small rift that stores items.");
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		if (!NexusPlayerActions.rightClickAir(e)) 
		{
			return false;
		}
		
		Inventory i = e.getPlayer().getEnderChest();
		e.getPlayer().openInventory(i);
		e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, SoundCategory.MASTER, 1, 1);
		NexusPrintUtils.Print(e.getPlayer(), "&r&7&oThe crystal tears open a rift, disappearing..");
		NexusItemCollector.remove(e);
		return false;
	}
}
