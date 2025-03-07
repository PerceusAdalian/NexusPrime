package com.nexus.epsilon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class PrintUtils
{
	public static String ColorParser(String msg) 
	{
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	public static void Print(String msg) 
	{
		Bukkit.getServer().getConsoleSender().sendMessage(ColorParser(msg));
	}
	public static void PrintError(String msg) 
	{
		Bukkit.getServer().getConsoleSender().sendMessage(ColorParser("&4"+ msg));
	}
}
