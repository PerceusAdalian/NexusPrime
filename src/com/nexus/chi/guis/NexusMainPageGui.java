package com.nexus.chi.guis;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

import com.nexus.chi.AbstractNexusGui;
import com.nexus.chi.NexusGuiButton;
import com.nexus.chi.NexusGuiHandler;

public class NexusMainPageGui extends AbstractNexusGui
{

	public NexusMainPageGui(Player player) 
	{
		super(player, "Nexus Main Menu", 27, Set.of(10, 12, 13, 16));
	}

	@Override
	protected void build() 
	{
		NexusGuiButton.button(Material.WRITABLE_BOOK).setName("&d&lRecipes").setLore("Click to view all &3&lNexus Object Recipes").place(this, 12, e->{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.MASTER, 1, 1);
			NexusGuiHandler.changeMenu(p, new NexusRecipesMainGui(p));
		});
		
		NexusGuiButton.button(Material.NAME_TAG).setName("&e&lShop").setLore("&r&fSpend &e₪&f to buy items").place(this, 13, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GOLD, SoundCategory.MASTER, 1, 1);
			NexusGuiHandler.changeMenu(p, new NexusShopGui(p));
		});
		
		//Exits
		NexusGuiButton.button(Material.RED_STAINED_GLASS_PANE).setName("&c&lExit Menu").setLore("Click to exit.").place(this, 10, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
			NexusGuiHandler.close(p);
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
