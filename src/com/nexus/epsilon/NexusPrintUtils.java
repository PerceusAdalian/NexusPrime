package com.nexus.epsilon;

public class NexusPrintUtils
{
	public static String NexusPrint(String msg) 
	{
		return PrintUtils.ColorParser("&f{&e&lν&r&f} "+msg+" &r&f/&a$&f//");
	}
	
	public static String NexusError(String msg) 
	{
		return PrintUtils.ColorParser("&f{&c&lν&r&f} "+msg+" &r&f/&c!&f//");
	}
	
	public static String NexusDebug(String msg) 
	{
		return PrintUtils.ColorParser("&f{&b&lν&r&f} "+msg+ "&r&f/&b?&f//");
	}
	
}
