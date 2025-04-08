package com.nexus.chi;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import com.nexus.alpha.NexusProper;

public class NexusPlayerMoney 
{
	
	private static final int maxMoney = 99999999, minMoney = 0, maxDebt = -99999999;
	
	public static final NamespacedKey playerMoney = new NamespacedKey(NexusProper.instance, "player_money");
	public static final NamespacedKey playerBaseMaxMoney = new NamespacedKey(NexusProper.instance, "player_max_money");
	public static final NamespacedKey playerBaseMinMoney = new NamespacedKey(NexusProper.instance, "player_min_money");
	
	public static final NamespacedKey playerDebt = new NamespacedKey(NexusProper.instance, "player_debt");
	public static final NamespacedKey playerMaxDebt = new NamespacedKey(NexusProper.instance, "player_max_debt");
	
	//Initializing money
	
	public static void setMoney(Player p, int money) 
	{
		p.getPersistentDataContainer().set(playerMoney, PersistentDataType.INTEGER, money);
	}
	
	public static Integer getMoney(Player p) 
	{
		Integer val = p.getPlayer().getPersistentDataContainer().get(playerMoney, PersistentDataType.INTEGER);
		return val != null ? val : 0;
	}
	
	public static void setBaseMaxMoney(Player p) 
	{		
		p.getPersistentDataContainer().set(playerBaseMaxMoney, PersistentDataType.INTEGER, maxMoney);
		setMoney(p, getMoney(p));
	}
	
	public static Integer getBaseMaxMoney(Player p) 
	{
		Integer val = p.getPersistentDataContainer().get(playerBaseMaxMoney, PersistentDataType.INTEGER);
		return val != null ? val : maxMoney;
	}
	
	public static void setBaseMinMoney(Player p) 
	{
		p.getPersistentDataContainer().set(playerBaseMinMoney, PersistentDataType.INTEGER, minMoney);
		setMoney(p, getMoney(p));
	}
	
	public static Integer getBaseMinMoney(Player p) 
	{
		Integer val = p.getPersistentDataContainer().get(playerBaseMinMoney, PersistentDataType.INTEGER);
		return val != null ? val : 0;
	}
	
	//Initializing debt
	
	public static void setDebt(Player p, int money) 
	{
		p.getPersistentDataContainer().set(playerDebt, PersistentDataType.INTEGER, money);
	}
	
	public static Integer getDebt(Player p) 
	{
		Integer val = p.getPersistentDataContainer().get(playerDebt, PersistentDataType.INTEGER);
		return val != null ? val : 0;
	}
	
	public static void setMaxDebt(Player p) 
	{
		p.getPersistentDataContainer().set(playerMaxDebt, PersistentDataType.INTEGER, maxDebt);
		setDebt(p, getDebt(p));
	}
	
	public static Integer getMaxDebt(Player p) 
	{
		Integer val = p.getPersistentDataContainer().get(playerMaxDebt, PersistentDataType.INTEGER);
		return val != null ? val : maxDebt;
	}
	
	public static Boolean hasDebt(Player p) 
	{
		return getDebt(p) < 0;
	}
		
	
	public static void add(Player p, int money) 
	{
		if (hasDebt(p)) 
		{
			int currentDebt = getDebt(p);
			int remaining = currentDebt + money;

			if (remaining >= 0) 
			{
				setDebt(p, 0);
				add(p, remaining);
				NexusDisplayManager.updateHud(p);
			} 
			else 
			{
				setDebt(p, remaining);
				NexusDisplayManager.updateHud(p);
			}
		}
		else 
		{			
			int currentMoney = getMoney(p);
			int newMoney = currentMoney + money;			
			if (newMoney > getBaseMaxMoney(p)) 
			{
				setMoney(p, getBaseMaxMoney(p));
				NexusDisplayManager.updateHud(p);
			}
			else 
			{
				setMoney(p, newMoney);	
				NexusDisplayManager.updateHud(p);
			}
		}
		
	}
	
	public static void subtract(Player p, int money) 
	{
		int currentMoney = getMoney(p);
		int newMoney = currentMoney - money;
		if (newMoney < getBaseMinMoney(p)) 
		{
			setMoney(p, getBaseMinMoney(p));
			NexusDisplayManager.updateHud(p);
			
			int debt = getDebt(p);
			int newDebt = debt + (newMoney - getBaseMinMoney(p));
			if (newDebt < getMaxDebt(p)) 
			{
				setDebt(p, getMaxDebt(p)); 
				NexusDisplayManager.updateHud(p);
			} 
			else 
			{
				setDebt(p, newDebt);
				NexusDisplayManager.updateHud(p);
			}

		}
		else 
		{
			setMoney(p, newMoney);	
			NexusDisplayManager.updateHud(p);
		}
	}
	
	public static void resetValues(Player p) 
	{
		setDebt(p, 0);
		setMoney(p, 0);

		NexusDisplayManager.updateHud(p);
	}
}
