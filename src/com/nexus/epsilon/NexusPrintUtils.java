package com.nexus.epsilon;

public class NexusPrintUtils
{
	public static String NexusPrint(String msg) 
	{
		return PrintUtils.ColorParser("&f{&eν&r&f} "+msg+" &r&f/&a$&f//");
	}
	
	public static String NexusError(String msg) 
	{
		return PrintUtils.ColorParser("&f{&cν&r&f} "+msg+" &r&f/&c!&f//");
	}
	
	public static String NexusDebug(String msg) 
	{
		return PrintUtils.ColorParser("&f{&bν&r&f} "+msg+ " &r&f/&b?&f//");
	}
	
	public static void NexusConsolePrint(String msg) 
	{
		PrintUtils.Print("&f{&eν&r&f} "+msg+" &r&f/&a$&f//");
	}
	
	public static void NexusConsoleError(String msg) 
	{
		PrintUtils.Print("&f{&cν&r&f} "+msg+" &r&f/&c!&f//");
	}
	
	public static void NexusConsoleDebug(String msg) 
	{
		PrintUtils.Print("&f{&bν&r&f} "+msg+ " &r&f/&b?&f//");
	}
}
