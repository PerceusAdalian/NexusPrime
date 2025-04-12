package com.nexus.chi.guis;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.nexus.chi.AbstractNexusGui;
import com.nexus.chi.NexusGuiButton;
import com.nexus.chi.NexusGuiHandler;
import com.nexus.chi.NexusPlayerMoney;
import com.nexus.chi.ShopItemContainer;
import com.nexus.epsilon.NexusPrintUtils;

public class ShopGuiItemConfirm extends AbstractNexusGui
{

	public ShopGuiItemConfirm(Player player) 
	{
		super(player, "Confirm Purchase", 27, Set.of(4,10,12,13,14,16));
	}

	@Override
	protected void build() 
	{
		String itemName = NexusShopGui.confirmBuyer.get(player).toString().toLowerCase();
		String[] splitName = itemName.split("_");
		itemName = "";
		for (String s : splitName) 
		{
			char[] chars = s.toCharArray();
			chars[0] = Character.toUpperCase(chars[0]);
			itemName += new String(chars);
			itemName += " ";
		}
		itemName = itemName.substring(0, itemName.length() - 1);
		
		NexusGuiButton.button(Material.OAK_SIGN)
		.setName("&r&f[&e&li&r&f]&o Purchase Terms and Conditions")
		.setLore("&r&a&oConfirm&r&f your &e&opurchase&r&f below by choosing the &oammount&r&f you'd wish to buy.",
				"&r&fThe cost &7(&e₪&7)&r&f associated is listed in the &b&oitem's description&r&f.",
				"&r&f&l&oNecessary &r&e&o₪ &r&f&l&omust be present to complete the purchase.",
				"&r&fYou may continue to &omake multiple purchases of the same item&r&f.",
				"To close or return the current or previous screen,",
				"Click either &c&o'Exit'&r&f or &a&o'Go Back'&r&f buttons.",
				"&r&f&nThank you for choosing to shop with us&r&f.")
		.place(this, 4, e->
		{
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.MASTER, 1,1);
		});
		
		NexusGuiButton.button(NexusShopGui.confirmBuyer.get(player))
		.setName("&r&f" + itemName + " 1x")
		.setLore(NexusPrintUtils.setCost(ShopItemContainer.itemTable.get(NexusShopGui.confirmBuyer.get(player))))
		.place(this, 12, e->
		{
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			ItemStack stack = new ItemStack(NexusShopGui.confirmBuyer.get(player), 1);
			int cost = ShopItemContainer.itemTable.get(NexusShopGui.confirmBuyer.get(player));
			
			if (NexusPlayerMoney.getMoney(p) < cost) 
			{
				p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1,1);
				NexusPrintUtils.NexusFormatError(p, "&r&7&oYou do not have the funds to purchase this. Closing shop menu..");
				NexusShopGui.confirmBuyer.remove(player);
				NexusGuiHandler.close(p);
			}
			else 
			{				
				p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, SoundCategory.MASTER, 1,1);
				NexusPlayerMoney.subtract(p, cost);
				p.getInventory().addItem(stack);
			}
		});
		
		NexusGuiButton.button(NexusShopGui.confirmBuyer.get(player))
		.setName("&r&f" + itemName + " 16x")
		.setLore(NexusPrintUtils.setCost(ShopItemContainer.itemTable.get(NexusShopGui.confirmBuyer.get(player))*16))
		.place(this, 13, e->
		{
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			ItemStack stack = new ItemStack(NexusShopGui.confirmBuyer.get(player), 16);
			int cost = ShopItemContainer.itemTable.get(NexusShopGui.confirmBuyer.get(player))*16;
			
			if (NexusPlayerMoney.getMoney(p) < cost) 
			{
				p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1,1);
				NexusPrintUtils.NexusFormatError(p, "&r&7&oYou do not have the funds to purchase this. Closing shop menu..");
				NexusShopGui.confirmBuyer.remove(player);
				NexusGuiHandler.close(p);
			}
			else 
			{				
				p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, SoundCategory.MASTER, 1,1);
				NexusPlayerMoney.subtract(p, cost);
				p.getInventory().addItem(stack);
			}
		});
		
		NexusGuiButton.button(NexusShopGui.confirmBuyer.get(player))
		.setName("&r&f" + itemName + " 32x")
		.setLore(NexusPrintUtils.setCost(ShopItemContainer.itemTable.get(NexusShopGui.confirmBuyer.get(player))*32))
		.place(this, 14, e->
		{
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			ItemStack stack = new ItemStack(NexusShopGui.confirmBuyer.get(player), 32);
			int cost = ShopItemContainer.itemTable.get(NexusShopGui.confirmBuyer.get(player))*32;
			
			if (NexusPlayerMoney.getMoney(p) < cost) 
			{
				p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1,1);
				NexusPrintUtils.NexusFormatError(p, "&r&7&oYou do not have the funds to purchase this. Closing shop menu..");
				NexusShopGui.confirmBuyer.remove(player);
				NexusGuiHandler.close(p);
			}
			else 
			{				
				p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, SoundCategory.MASTER, 1,1);
				NexusPlayerMoney.subtract(p, cost);
				p.getInventory().addItem(stack);
			}
			
		});
		
		//Exits
		NexusGuiButton.button(Material.GREEN_STAINED_GLASS_PANE).setName("<- &a&lGo Back").setLore("Click to return to: Shop Main Page").place(this, 10, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.MASTER, 1, 1);
			NexusShopGui.confirmBuyer.remove(player);
			NexusGuiHandler.changeMenu(p, new NexusShopGui(p));
		});
		
		NexusGuiButton.button(Material.RED_STAINED_GLASS_PANE).setName("&c&lExit Menu").setLore("Click to exit").place(this, 16, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
			NexusShopGui.confirmBuyer.remove(player);
			NexusGuiHandler.close(p);
		});
		paint();
	}

}
