package com.nexus.chi.guis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

import com.nexus.chi.AbstractNexusGui;
import com.nexus.chi.NexusGuiButton;
import com.nexus.chi.NexusGuiHandler;
import com.nexus.chi.ShopItemContainer;
import com.nexus.epsilon.NexusPrintUtils;

public class NexusShopGui extends AbstractNexusGui
{
	public static Map<UUID, Material> confirmBuyer = new HashMap<>();

	public NexusShopGui(Player player) 
	{
		super(player, "Nexus Shop", 54, Set.of(21,22,23,37,43));
	}

	@Override
	protected void build() 
	{
		
		NexusGuiButton.button(Material.DIRT).setLore(NexusPrintUtils.setCost(ShopItemContainer.itemTable.get(Material.DIRT))).place(this, 21, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.MASTER, 1, 1);
			confirmBuyer.put(p.getUniqueId(), Material.DIRT);
			NexusGuiHandler.changeMenu(p, new ShopGuiItemConfirm(p));
		});
		
		NexusGuiButton.button(Material.OAK_WOOD).setLore(NexusPrintUtils.setCost(ShopItemContainer.itemTable.get(Material.OAK_WOOD))).place(this, 22, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.MASTER, 1, 1);
			confirmBuyer.put(p.getUniqueId(), Material.OAK_WOOD);
			NexusGuiHandler.changeMenu(p, new ShopGuiItemConfirm(p));
		});
		
		NexusGuiButton.button(Material.BREAD).setLore(NexusPrintUtils.setCost(ShopItemContainer.itemTable.get(Material.BREAD))).place(this, 23, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.MASTER, 1, 1);
			confirmBuyer.put(p.getUniqueId(), Material.BREAD);
			NexusGuiHandler.changeMenu(p, new ShopGuiItemConfirm(p));
		});
		
		//Exits
		NexusGuiButton.button(Material.YELLOW_STAINED_GLASS_PANE).setName("<- &e&lGo Back").setLore("Click to return to the previous screen.").place(this, 37, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.MASTER, 1, 1);
			NexusGuiHandler.changeMenu(p, new NexusMainPageGui(p));
		});
		
		NexusGuiButton.button(Material.RED_STAINED_GLASS_PANE).setName("&c&lExit Menu").setLore("Click to exit.").place(this, 43, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
			NexusGuiHandler.close(p);
		});
		paint();
	}

}
