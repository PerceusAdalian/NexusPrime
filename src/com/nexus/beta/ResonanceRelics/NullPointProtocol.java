package com.nexus.beta.ResonanceRelics;

import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusObjectAbilityType;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.epsilon.OreValues;
import com.nexus.io.NexusObject.AbstractResonanceObject;

public class NullPointProtocol extends AbstractResonanceObject
{

	public NullPointProtocol() 
	{
		super("Null-Point Protocol", "mining_relic", Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE, true, false, true, 
		NexusPrintUtils.assignAbilityType(NexusObjectAbilityType.UTILITY),
		"&r&f&lRight-Click&r&f to breakdown target block and drop its resource.",
		"&r&f&lShift_Right-Click&r&f to breakdown target block and smelt if applicable.");
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		
		if (NexusPlayerActions.shiftRightClickBlock(e)) 
		{
			Block target = e.getClickedBlock();
			
			boolean hasBlockType = Optional.ofNullable(target)
			        .map(Block::getType)
			        .map(OreValues.validBlockTypes::containsKey)
			        .orElse(false);
			
			if (target.getType().isAir() || target.getType().equals(Material.BEDROCK)) 
			{
				return false;
			}

			if (hasBlockType) 
			{
				Material newMaterial = OreValues.validBlockTypes.get(target.getType());
				ItemStack newStack = new ItemStack(newMaterial);
				p.playSound(p.getLocation(), Sound.BLOCK_SCULK_VEIN_BREAK, SoundCategory.MASTER, 1, 1);		
				NexusParticles.drawPoint(target.getLocation(), Particle.EXPLOSION, 0, null);
				
				Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()->
				{
					target.getWorld().dropItemNaturally(target.getLocation(), newStack);			
					target.setType(Material.AIR);
				}, 1);				
				return true;
			}
			p.playSound(p.getLocation(), Sound.BLOCK_SCULK_VEIN_BREAK, SoundCategory.MASTER, 1, 1);
			NexusParticles.drawPoint(target.getLocation(), Particle.EXPLOSION, 0, null);
			
			Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()->
			{
				target.breakNaturally();				
			}, 1);
			return true;
		}
		
		if (NexusPlayerActions.rightClickBlock(e)) 
		{
			Block target = e.getClickedBlock();
			
			if (target.getType().isAir() || target.getType().equals(Material.BEDROCK)) 
			{
				return false;
			}
			p.playSound(p.getLocation(), Sound.BLOCK_SCULK_VEIN_BREAK, SoundCategory.MASTER, 1, 1);			
			NexusParticles.drawPoint(target.getLocation(), Particle.EXPLOSION, 0, null);
			Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()->
			{
				target.breakNaturally();				
			}, 1);
			return true;
		}
		
		return false;
	}
}
