package com.nexus.alpha;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.io.NexusObject.AbstractNexusObject;
import com.nexus.io.NexusObject.NexusItemRegistry;

public class NexusCommand implements CommandExecutor, TabCompleter
{
	
	public NexusCommand() 
	{
		Bukkit.getPluginCommand("nexus").setTabCompleter(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) 
	{
		if (!(sender instanceof Player)) 
		{
			return false;
		}
		Player player = (Player) sender;
		
		if (!sender.isOp()) 
		{
			NexusPrintUtils.NexusFormatError(player, "&7Permission Denied");
			return false;
		}
		
		if (args.length == 0) 
		{
			NexusPrintUtils.NexusFormatError(player, "&7Invalid Argument(s)");
			return false;
		}
		
		if (args[0].equals("debug"))
		{
			if (NexusProper.debug == false) 
			{
				NexusProper.debug = true;
				NexusPrintUtils.NexusFormatDebug(player, "&7Console logging has been turned &a&lON");
				NexusPrintUtils.NexusConsoleDebug("&7Console logging has been turned &a&lON");
				return true;
			}
			NexusProper.debug = false;
			NexusPrintUtils.NexusFormatDebug(player, "&7Console logging has been turned &c&lOFF");
			NexusPrintUtils.NexusConsoleDebug("&7Console logging has been turned &c&lOFF");
			return true;
		}
		
		if (args[0].equals("generate")) 
		{
			if (args[1].equals("item") && NexusItemRegistry.itemRegistry.containsKey(args[2])) 
			{
				AbstractNexusObject obj = NexusItemRegistry.itemRegistry.get(args[2]);
				ItemStack stack = obj.bake();
				player.getInventory().addItem(stack);
				
				if (NexusProper.debug == true) 
				{
					String internalName = null;
		            if (stack.getItemMeta() != null) 
		            {
		                internalName = stack.getItemMeta().getPersistentDataContainer().get(AbstractNexusObject.nexusObject, PersistentDataType.STRING);
		            }
		            
		            if (internalName == null) 
		            {
		                NexusPrintUtils.NexusConsoleError("Could not retrieve internal name from baked item.");
		                return true;
		            }
		            
		            AbstractNexusObject nexusObject = NexusItemRegistry.itemRegistry.get(internalName);
		            if (nexusObject == null) 
		            {
		                NexusPrintUtils.NexusConsoleError("Internal name exists, but item is not in the registry.");
		                return true;
		            }
		            
					NexusPrintUtils.NexusConsoleDebug("&7Summoned item: " + nexusObject.getName() + "&7 | ID: (" + AbstractNexusObject.getInternalNameAsID(internalName) + "&7) &a&oSuccessfully");
				}
				
				return true;
			}
		}
		
//		if (args[0].equals("nexusmenu")) 
//		{
//			NexusGuiHandler.open(player, new NexusMainPageGui(player));
//			return true;
//		}
//		
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) 
	{
		
		return switch(args.length) 
		{
			case 0 -> List.of("nexus");
			case 1 -> List.of("debug", "generate","money", "nexusmenu");
			case 2 -> 
			{
				yield switch(args[0])
				{
					case "debug" -> List.of();
					case "generate" -> List.of("item");
					case "money" -> List.of("add", "subtract", "reset", "setMaxMoney", "setMaxDebt");
					case "nexusmenu" -> List.of();
					default -> List.of();
				};
			}
			case 3 ->
			{
				yield switch(args[1]) 
				{
					case "item" -> new ArrayList<>(NexusItemRegistry.itemRegistry.keySet());
					default -> List.of();
				};
			}
			default -> List.of();
		};
	}
	
}