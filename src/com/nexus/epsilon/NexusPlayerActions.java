package com.nexus.epsilon;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class NexusPlayerActions 
{
	public static boolean rightClickAir(PlayerInteractEvent e) 
	{	
		return e.getAction().equals(Action.RIGHT_CLICK_AIR);
	}
	
	public static boolean leftClickAir(PlayerInteractEvent e) 
	{
		return e.getAction().equals(Action.LEFT_CLICK_AIR);
	}
	
	public static boolean rightClickBlock(PlayerInteractEvent e) 
	{
		return e.getAction().equals(Action.RIGHT_CLICK_BLOCK);
	}
	
	public static boolean shiftRightClickAir(PlayerInteractEvent e) 
	{	
		return e.getPlayer().isSneaking() && e.getAction().equals(Action.RIGHT_CLICK_AIR);
	}
	
	public static boolean shiftLeftClickAir(PlayerInteractEvent e) 
	{
		return e.getPlayer().isSneaking() && e.getAction().equals(Action.LEFT_CLICK_AIR);
	}
	
	public static boolean shiftRightClickBlock(PlayerInteractEvent e) 
	{
		return e.getPlayer().isSneaking() && e.getAction().equals(Action.RIGHT_CLICK_BLOCK);
	}
}

//	public enum PlayerAction
//	{
//		RIGHT_CLICK_AIR,
//		SNEAK_RIGHT_CLICK_AIR,
//		LEFT_CLICK_AIR,
//		SNEAK_LEFT_CLICK_AIR,
//		RIGHT_CLICK_BLOCK,
//		SNEAK_RIGHT_CLICK_BLOCK;
//
//		public static PlayerAction getPlayerAction(PlayerInteractEvent e) 
//		{
//			boolean isSneaking = e.getPlayer().isSneaking();
//			Action actionType = e.getAction();
//			
//			return switch(actionType)
//			{
//				case RIGHT_CLICK_AIR -> isSneaking ? SNEAK_RIGHT_CLICK_AIR : RIGHT_CLICK_AIR;
//				case LEFT_CLICK_AIR -> isSneaking ? SNEAK_LEFT_CLICK_AIR : LEFT_CLICK_AIR;
//				case RIGHT_CLICK_BLOCK -> isSneaking ? SNEAK_RIGHT_CLICK_BLOCK : RIGHT_CLICK_BLOCK;
//				default -> throw new IllegalArgumentException("Unexpected value: " + e.getAction());
//			};
//		}
//	}