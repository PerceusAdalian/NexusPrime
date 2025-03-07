package com.nexus.io.EchoTag;

import java.util.UUID;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.nexus.alpha.NexusProper;

public class EchoNamespace 
{
	static protected NamespacedKey echokey = new NamespacedKey(NexusProper.instance, "echo_owner");
	
	public static void setEchoNamespace(ItemStack stack, Player p) 
	{	
		UUID player = p.getUniqueId();
		ItemMeta meta = stack.getItemMeta();
		meta.getPersistentDataContainer().set(echokey, PersistentDataType.STRING, player.toString());
		stack.setItemMeta(meta);
	}
	
	public static void removeEchoNamespace(ItemStack stack, Player p) 
	{
		UUID player = p.getUniqueId();
		String StoredEchoID = stack.getItemMeta().getPersistentDataContainer().get(echokey, PersistentDataType.STRING);
		if (!StoredEchoID.equals(player.toString())) 
		{
			p.getPlayer().getInventory().getItem(stack.getAmount()).setAmount(-1);			
		}
	}
	
	public static String getEchoOwner(ItemStack stack) 
	{
		if (stack == null || !stack.hasItemMeta()) return null;
		return stack.getItemMeta().getPersistentDataContainer().get(echokey, PersistentDataType.STRING);
	}
	
	public static boolean isEchoBound(ItemStack stack) 
	{
		if (stack == null || !stack.hasItemMeta()) return false;
		return stack.getItemMeta().getPersistentDataContainer().has(echokey);
	}
	
}
