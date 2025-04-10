package com.nexus.chi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.nexus.epsilon.NexusPrintUtils;

public class NexusDisplayManager
{
		
    public static void createHud(Player p) 
    {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective nexusHud = board.registerNewObjective("nexus_hud", Criteria.DUMMY, 
        		NexusPrintUtils.ColorParser("&r&e&l⋊ "+"&r&3&lNexus Hud"+" &r&e&l⋉"));
        nexusHud.setDisplaySlot(DisplaySlot.SIDEBAR);
        
        nexusHud.getScore(NexusPrintUtils.ColorParser("&r&f&lUser&r&f: " + p.getDisplayName())).setScore(0);
        nexusHud.getScore(NexusPrintUtils.ColorParser("&r&7⋖&e₪&r&7⋗&r&f")).setScore(NexusPlayerMoney.getMoney(p));
        nexusHud.getScore(NexusPrintUtils.ColorParser("&r&7⋖&cЖ&r&7⋗&r&f")).setScore(NexusPlayerMoney.getDebt(p)*(-1));
       
        p.setScoreboard(board);
    }

    public static void updateHud(Player p) 
    {
    	Scoreboard board = p.getScoreboard();
   	 	Objective obj = board.getObjective(DisplaySlot.SIDEBAR);
   	 	
   	 	obj.getScore(NexusPrintUtils.ColorParser("&r&f&lUser&r&f: " + p.getDisplayName())).setScore(0);
   	 	obj.getScore(NexusPrintUtils.ColorParser("&r&7⋖&e₪&r&7⋗&r&f")).setScore(NexusPlayerMoney.getMoney(p));
   	 	obj.getScore(NexusPrintUtils.ColorParser("&r&7⋖&cЖ&r&7⋗&r&f")).setScore(NexusPlayerMoney.getDebt(p)*(-1));
    }
    
    //redundant?: 
    public static void clearHud(Player p) 
    {
    	Scoreboard board = p.getScoreboard();
        board.resetScores(p.getUniqueId().toString());
        createHud(p);
    }
}
