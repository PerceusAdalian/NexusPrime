package com.nexus.epsilon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

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
	
	public static String NexusFormatPrint(String msg) 
	{
		return ColorParser("&f{&eν&r&f} "+msg+" &r&f/&e$&f//");
	}
	
	public static String NexusFormatError(String msg) 
	{
		return ColorParser("&f{&cν&r&f} "+msg+" &r&f/&c!&f//");
	}
	
	public static String NexusFormatDebug(String msg) 
	{
		return ColorParser("&f{&bν&r&f} "+msg+ " &r&f/&b?&f//");
	}
	
	public static void NexusConsolePrint(String msg) 
	{
		Bukkit.getServer().getConsoleSender().sendMessage(ColorParser("&f{&eν&r&f} "+msg+" &r&f/&e$&f//"));
	}
	
	public static void NexusConsoleError(String msg) 
	{
		Bukkit.getServer().getConsoleSender().sendMessage(ColorParser("&f{&cν&r&f} "+msg+" &r&f/&c!&f//"));
	}
	
	public static void NexusConsoleDebug(String msg) 
	{
		Print("&f{&bν&r&f} "+msg+ " &r&f/&b?&f//");
	}
}
