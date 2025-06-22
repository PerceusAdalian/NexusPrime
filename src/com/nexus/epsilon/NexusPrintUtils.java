package com.nexus.epsilon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class NexusPrintUtils
{
	public static String ColorParser(String msg) 
	{
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static void Print(String msg) 
	{
		Bukkit.getServer().getConsoleSender().sendMessage(ColorParser(msg));
	}
	
	public static void Print(Player player, String msg) 
	{
		player.getPlayer().sendMessage(ColorParser(msg));
	}
	
	public static void Print(Player player, String...msg) 
	{
		for (String line : msg) 
		{			
			player.getPlayer().sendMessage(ColorParser(line));
		}
	}
	
	public static void PrintToActionBar(Player player, String msg) 
	{
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ColorParser(msg)));
	}
	
	public static String setCost(int cost) 
	{
		return ColorParser("&r&fCost: " + cost + "&e₪&f");
	}
	
	public static void NexusFormatPrint(Player player, String msg) 
	{
		Print(player, "&f{&eν&r&f} "+msg+" &r&f/&e$&f//");
	}
	
	public static void NexusFormatError(Player player, String msg) 
	{
		Print(player, "&f{&cν&r&f} "+msg+" &r&f/&c!&f//");
	}
	
	public static void NexusFormatDebug(Player player, String msg) 
	{
		Print(player.getPlayer(), "&f{&bν&r&f} "+msg+ " &r&f/&b?&f//");
	}
	
	public static void NexusConsolePrint(String msg) 
	{
		Print("&f{&eν&r&f} "+msg+" &r&f/&e$&f//");
	}
	
	public static void NexusConsoleError(String msg) 
	{
		Print("&f{&cν&r&f} "+msg+" &r&f/&c!&f//");
	}
	
	public static void NexusConsoleDebug(String msg) 
	{
		Print("&f{&bν&r&f} "+msg+ " &r&f/&b?&f//");
	}
	
	public static String assignAbilityType(NexusObjectAbilityType type, NexusObjectAbilityType type2) 
	{
		return ColorParser("&r&f&lAbility Type&r&f: {"+ type.getAbilityType() + "&r&f | " + type2.getAbilityType() + "&r&f}");
	}
	public static String assignAbilityType(NexusObjectAbilityType type) 
	{
		return ColorParser("&r&f&lAbility Type&r&f: {"+type.getAbilityType()+"&r&f}");
	}
}
