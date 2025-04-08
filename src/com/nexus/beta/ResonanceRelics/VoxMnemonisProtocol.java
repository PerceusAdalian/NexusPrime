package com.nexus.beta.ResonanceRelics;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.nexus.epsilon.NexusObjectAbilityType;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.io.NexusObject.AbstractResonanceObject;

public class VoxMnemonisProtocol extends AbstractResonanceObject
{

	public VoxMnemonisProtocol() 
	{
		super("Vox Mnemonis Protocol", "enchant_relic", Material.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE, true, false, true, 
				NexusPrintUtils.assignAbilityType(NexusObjectAbilityType.UTILITY),
				"&r&f&lRight-Click&r&f to &b&oenchant&r&f offhand item with all applicable buffs.",
				"&r&f&lShift_Right-Click&r&f to &c&oremove&r&f curses from item in offhand.");
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		Collection<Enchantment> allEnchantments = Registry.ENCHANTMENT.stream().toList();
		List<Enchantment> curseEnchantment = Arrays.asList(Enchantment.BINDING_CURSE, Enchantment.VANISHING_CURSE);
		
		if (NexusPlayerActions.shiftRightClickAir(e)) 
		{
			ItemStack held = p.getInventory().getItem(EquipmentSlot.OFF_HAND);
			ItemMeta meta = held.getItemMeta();			
			
			if ((held == null || held.getType().isAir()) || !meta.hasEnchants()) 
			{
				return false;
			}
			
			NexusPrintUtils.NexusFormatDebug(p, "&r&7&oUninstalling object's curse enchantments (if any)..");
			int numCurses = 0;
			for (Enchantment enchantment : allEnchantments) 
			{
				if (curseEnchantment.contains(enchantment)) 
				{
					meta.removeEnchant(enchantment);
					numCurses+=1;
				}
			}
			
			p.playSound(p.getLocation(), Sound.BLOCK_VAULT_DEACTIVATE, SoundCategory.MASTER, 1, 1);
			NexusParticles.drawDisc(p.getLocation(), p.getWidth()+0.5, 1, 40, 0.5, Particle.WARPED_SPORE, null);
			NexusParticles.drawCylinder(p.getLocation(), p.getWidth()+0.5, 4, 25, 1, 0, Particle.ENCHANT, null);
			NexusParticles.drawWisps(p.getLocation(), p.getWidth(), p.getHeight(), 5, Particle.SCULK_SOUL, null);
			held.setItemMeta(meta);
			NexusPrintUtils.NexusFormatPrint(p, "&r&c&l" + numCurses+" &r&7&ocurse(s) removed..");
			return true;
		}
		
		if (NexusPlayerActions.rightClickAir(e)) 
		{
			ItemStack held = p.getInventory().getItem(EquipmentSlot.OFF_HAND);
			ItemMeta meta = held.getItemMeta();
			
			if (held == null || held.getType().isAir()) 
			{
				return false;
			}
			
			if (held.getItemMeta().hasEnchants()) 
			{
				NexusPrintUtils.NexusFormatError(p, "&r&7&oUninstalling object's current enchantments..");
				meta.removeEnchantments();
				held.setItemMeta(meta);
			}
			
			NexusPrintUtils.NexusFormatDebug(p, "&r&7&oSearching for object's ideal enchantments..");
			int numEnchants = 0;
			for (Enchantment enchantment : allEnchantments) 
			{
			    if (enchantment.canEnchantItem(held)) 
			    {
			    	if (curseEnchantment.contains(enchantment)) continue;
			    	meta.addEnchant(enchantment, enchantment.getMaxLevel(), true);
			    	numEnchants+=1;
			    }
			}

			p.playSound(p.getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, SoundCategory.MASTER, 1, 1);
			p.playSound(p.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER, 1, 1);
			NexusParticles.drawDisc(p.getLocation(), p.getWidth()+0.5, 1, 40, 0.5, Particle.WARPED_SPORE, null);
			NexusParticles.drawCylinder(p.getLocation(), p.getWidth()+0.5, 4, 25, 1, 0, Particle.ENCHANT, null);
			NexusParticles.drawWisps(p.getLocation(), p.getWidth(), p.getHeight(), 5, Particle.SCULK_SOUL, null);
			held.setItemMeta(meta);
			if (numEnchants == 0) 
			{
				NexusPrintUtils.NexusFormatError(p, "&r&c&l0 &r&7&oenchants applied. Concluding program..");
			}
			else 
			{
				NexusPrintUtils.NexusFormatPrint(p, "&r&b&l"+ numEnchants+ " &r&7&oenchants installed. Setting their max values.. Done.");				
			}
			return true;
		}
		
		return false;
	}

}
