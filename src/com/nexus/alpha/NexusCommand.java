package com.nexus.alpha;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.nexus.epsilon.NexusPrintUtils;

public class NexusCommand implements CommandExecutor, TabCompleter
{
	
	public NexusCommand() 
	{
		Bukkit.getPluginCommand("sys").setTabCompleter(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) 
	{
		Player player = (Player) sender;
		if (!(sender instanceof Player)) 
		{
			return false;
		}
		
		if (!sender.isOp()) 
		{
			player.sendMessage(NexusPrintUtils.NexusError("&7Permission Denied"));
			return false;
		}
		
		if (args.length == 0) 
		{
			player.sendMessage(NexusPrintUtils.NexusError("&7Invalid Argument(s)"));
			return false;
		}
		
		if (args[0].equals("debug"))
		{
			if (NexusProper.debug == false) 
			{
				NexusProper.debug = true;
				player.sendMessage(NexusPrintUtils.NexusDebug("&7Console logging has been turned &a&lON"));
				return true;
			}
			NexusProper.debug = false;
			player.sendMessage(NexusPrintUtils.NexusDebug("&7Console logging has been turned &c&lOFF"));
			return true;
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) 
	{
		
		return switch(args.length) 
				{
					case 0 -> List.of("sys");
					case 1 -> List.of("debug");
					case 2 -> 
					{
						yield switch(args[0])
						{
							case "debug" -> List.of();
							default -> List.of();
						};
					}
//					case 3 -> 
//					{
//						yield switch(args[1]) 
//						{
//							default -> List.of();
//						};
//					}
					default -> List.of();
				};
	}
	
}
