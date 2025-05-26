package com.nexus.chi.guis;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

import com.nexus.chi.AbstractNexusGui;
import com.nexus.chi.NexusGuiButton;
import com.nexus.chi.NexusGuiHandler;

public class NexusRecipesMainGui extends AbstractNexusGui
{

	public NexusRecipesMainGui(Player player) 
	{
		super(player, "Nexus Recipes", 27, Set.of(10,11,12,13,14,15,16));
	}

	@Override
	protected void build() 
	{
		
		NexusGuiButton.button(Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE).setName("Null Point Protocol Recipe").place(this, 11, e->
		{
			e.setCancelled(true);
		});
		
		NexusGuiButton.button(Material.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE).setName("Reticle Protocol Recipe").place(this, 12, e->
		{
			e.setCancelled(true);
		});
		
		NexusGuiButton.button(Material.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE).setName("Ordinal Protocol Recipe").place(this, 13, e->
		{
			e.setCancelled(true);
		});
		
		NexusGuiButton.button(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE).setName("Reconfiguration Protocol Recipe").place(this, 14, e->
		{
			e.setCancelled(true);
		});
		
		NexusGuiButton.button(Material.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE).setName("Vox Mnemonis Protocol Recipe").place(this, 15, e->
		{
			e.setCancelled(true);
		});
		
		//Exits / Go Back
		NexusGuiButton.button(Material.GREEN_STAINED_GLASS_PANE).setName("<- &e&lGo Back").setLore("Click to return to the previous screen.").place(this, 10, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.MASTER, 1, 1);
			NexusGuiHandler.changeMenu(p, new NexusMainPageGui(p));
		});
		
		NexusGuiButton.button(Material.RED_STAINED_GLASS_PANE).setName("&c&lExit Menu").setLore("Click to exit.").place(this, 16, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
			NexusGuiHandler.close(p);
		});
		paint();
	}

}
