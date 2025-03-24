package com.nexus.io.ResonanceCrystals;

import java.util.Arrays;
import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.epsilon.OreValues;

public class ThermalDisruption extends AbstractResonanceCrystal
{

	public ThermalDisruption() 
	{
		super("Resonance Crystal: Thermal Disruption", "thermal_disruption_crystal", Material.ECHO_SHARD, true, true,
				"&r&f&lRight-Click&r&f to smelt the ore in one's offhand.",
				"&r&f&lShift_Right-Click&r&f to smelt the ores in one's inventory.");
	}
	
	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		if (NexusPlayerActions.shiftRightClickAir(e)) 
		{
			boolean hasTargetMaterial = Arrays.stream(p.getInventory().getContents())
					.filter(Objects::nonNull)
					.map(ItemStack::getType)
					.anyMatch(OreValues.validMaterials::containsKey);
			
			if (hasTargetMaterial) 
			{
				for (int i = 0; i < 36; i++) 
				{
					ItemStack stack = p.getInventory().getItem(i);
					if (stack != null && OreValues.validMaterials.containsKey(stack.getType())) 
					{
						Material newMaterial = OreValues.validMaterials.get(stack.getType());
						ItemStack newStack = new ItemStack(newMaterial, stack.getAmount());
						
						if (stack.hasItemMeta()) 
						{
							newStack.setItemMeta(stack.getItemMeta());
						}
						
						p.getInventory().setItem(i, newStack);
					}
				}
				
				p.playSound(p.getLocation(), Sound.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.MASTER, 1, 1);
				NexusPrintUtils.Print(p, "&r&7&oThe crystal lights ablaze and fizzles to ash..");
				NexusItemCollector.remove(e);
				return true;
			}
			
			NexusPrintUtils.Print(p, "&r&7&oThe crystal resonates, but nothing happens..");	
			return false;
		}
		
		if (NexusPlayerActions.rightClickAir(e)) 
		{
			ItemStack stack = p.getInventory().getItemInOffHand();
			if ((stack != null && !stack.getType().equals(Material.AIR)) && OreValues.validMaterials.containsKey(stack.getType())) 
			{
				Material newMaterial = OreValues.validMaterials.get(stack.getType());
				ItemStack newStack = new ItemStack(newMaterial, stack.getAmount());
				
				if (stack.hasItemMeta()) 
				{
					newStack.setItemMeta(stack.getItemMeta());
				}
				p.getInventory().setItemInOffHand(newStack);
				p.playSound(p.getLocation(), Sound.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.MASTER, 1, 1);
				NexusPrintUtils.Print(p, "&r&7&oThe crystal lights ablaze and fizzles to ash..");
				NexusItemCollector.remove(e);
				return true;
			}
			
			NexusPrintUtils.Print(p, "&r&7&oThe crystal resonates, but nothing happens..");	
			return false;
		}
		return false;
	}
}
