package com.nexus.chi.objects;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.nexus.chi.NexusPlayerMoney;
import com.nexus.epsilon.NexusItemCollector;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.io.NexusObject.AbstractNexusObject;

public class LuminiteFragment extends AbstractNexusObject
{

	public LuminiteFragment() 
	{
		super("Luminite Fragment", "money_tier3", Material.RAW_GOLD, true, 
				"&r&fA medium concentration of &e&l&oLuminite&r&f.",
				"&r&fIt is a semi-coherent chunk with a whirring soft glow.",
				"&r&f&lRight-Click&r&f to harness into &l250&r&e₪&f.",
				"\n","&r&fThis item is &d&ostackable&r&f and &c&l&odestroyed&r&f upon use.");
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		if (NexusPlayerActions.rightClickAir(e)) 
		{
			NexusParticles.drawDisc(p.getLocation(), p.getWidth()+0.5, 1, 10, 1.0, Particle.CLOUD, null);
			NexusParticles.drawDisc(p.getLocation(), p.getWidth()+0.5, 1, 10, 1.0, Particle.WAX_ON, null);
			p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, SoundCategory.MASTER, 1, 1);
			NexusPlayerMoney.add(p, 250);
			NexusPrintUtils.Print(p, "&r&f&l250&r&e₪&f has been added to your account.");
			NexusItemCollector.remove(e);
			return true;
		}
		return false;
	}

}
