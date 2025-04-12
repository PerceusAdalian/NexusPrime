package com.nexus.alpha;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.nexus.chi.NexusDisplayManager;
import com.nexus.chi.NexusPlayerMoney;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.io.NexusObject.AbstractNexusObject;
import com.nexus.io.NexusObject.NexusItemRegistry;

public class OnJoin implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent e) 
	{
		Player p = e.getPlayer();
		NexusPlayerMoney.setBaseMinMoney(p);
        NexusPlayerMoney.setBaseMaxMoney(p);
        NexusPlayerMoney.setMaxDebt(p);
		NexusDisplayManager.createHud(p);
		p.setCanPickupItems(true);
		if (p.isOp()) 
		{
			p.setGameMode(GameMode.CREATIVE);
			p.setAllowFlight(true);
			return;
		}

		if (!p.hasPlayedBefore()) 
		{
			AbstractNexusObject crystal = NexusItemRegistry.itemRegistry.values().iterator().next();
			p.getInventory().addItem(crystal.bake());
			NexusPrintUtils.NexusFormatPrint(p, "Welcome to the server, " + p.getName() + "&r&f!");
			NexusPrintUtils.NexusFormatPrint(p, "An item has been added to your inventory: [" + crystal.getName() + "] | Enjoy! :)");
			return;
		}

		NexusPrintUtils.NexusFormatPrint(p, "&r&fWelcome back, " + p.getName());
		p.setGameMode(GameMode.SURVIVAL);
		p.setAllowFlight(false);
		p.setFlying(false);
		return;
	}
}
