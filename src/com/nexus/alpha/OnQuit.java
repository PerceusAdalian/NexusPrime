package com.nexus.alpha;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.nexus.chi.NexusDisplayManager;

public class OnQuit implements Listener
{
	@EventHandler
	public void onQuit(PlayerQuitEvent e) 
	{
		NexusDisplayManager.clearHud(e.getPlayer());
	}
}
