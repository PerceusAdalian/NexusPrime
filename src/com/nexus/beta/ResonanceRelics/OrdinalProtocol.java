package com.nexus.beta.ResonanceRelics;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;

import com.google.gson.Gson;
import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusEffects;
import com.nexus.epsilon.NexusObjectAbilityType;
import com.nexus.epsilon.NexusParticles;
import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusPrintUtils;
import com.nexus.io.NexusObject.AbstractResonanceObject;

public class OrdinalProtocol extends AbstractResonanceObject
{

	public OrdinalProtocol() 
	{
		super("Ordinal Protocol", "home_relic", Material.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE, true, false, true, 
				NexusPrintUtils.assignAbilityType(NexusObjectAbilityType.UTILITY),
				"&r&f&lRight-Click&r&f to teleport to your &d&orespawn location&r&f.",
				"&r&f&lShift_Right-Click&r&f to set your &d&orespawn location&r&f.");
	}
	
	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		Player p = e.getPlayer();
		
		if (NexusPlayerActions.shiftRightClickAir(e)) 
		{
			Location loc = new Location(p.getWorld(), 0.5, 0, 0.5, p.getLocation().getYaw(), p.getLocation().getPitch());
			p.playSound(p.getLocation(), Sound.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.MASTER, 1, 1);
			NexusParticles.drawCylinder(p.getLocation(), p.getWidth()+0.5, (int) p.getHeight(), 10, 1, 0.5, Particle.ENCHANT, null);
			NexusParticles.drawDisc(p.getLocation(), p.getWidth()+0.5, 1, 20, 0, Particle.WARPED_SPORE, null);
			setCustomRespawn(p, loc);
			p.setRespawnLocation(loc, true);
			NexusPrintUtils.NexusFormatPrint(p, "&r&7&oRespawn location set for: " + p.getName());
			return true;			
		}
		
		if (NexusPlayerActions.rightClickAir(e)) 
		{
			
			Location respawn = getCustomRespawn(p);
		    if (respawn == null) 
		    {
		        p.teleport(p.getWorld().getSpawnLocation());
		        Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()->
				{
					p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);
					p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.MASTER, 1, 1);
					NexusParticles.drawVerticalVortex(p.getLocation(), p.getWidth()+0.5, p.getHeight()+2, 0.5, 3, 10, 0, Particle.SCULK_SOUL, null);
					NexusEffects.add(p, PotionEffectType.NAUSEA, 100, 0);
					NexusEffects.add(p, PotionEffectType.DARKNESS, 100, 2);
				}, 10);
				return true;
		    } 
		    else 
		    {
		        p.teleport(respawn);
		        Bukkit.getScheduler().runTaskLater(NexusProper.instance, ()->
				{
					p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);
					p.playSound(p.getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.MASTER, 1, 1);
					NexusParticles.drawVerticalVortex(p.getLocation(), p.getWidth()+0.5, p.getHeight()+2, 0.5, 3, 10, 0, Particle.SCULK_SOUL, null);
					NexusEffects.add(p, PotionEffectType.NAUSEA, 100, 0);
					NexusEffects.add(p, PotionEffectType.DARKNESS, 100, 2);
				}, 10);
				return true;
		    }
		}
		return false;
	}
	
	private static final NamespacedKey RESPAWN_KEY = new NamespacedKey(NexusProper.instance, "respawn_location");

	public static void setCustomRespawn(Player p, Location loc) 
	{
		loc.setY(p.getWorld().getHighestBlockYAt(loc.getBlockX(), loc.getBlockZ()) + 1);
	    PersistentDataContainer data = p.getPersistentDataContainer();
	    data.set(RESPAWN_KEY, PersistentDataType.STRING, loc.serialize().toString());
	}

	@SuppressWarnings("unchecked")
	public static Location getCustomRespawn(Player p) 
	{
	    PersistentDataContainer data = p.getPersistentDataContainer();
	    
	    if (!data.has(RESPAWN_KEY, PersistentDataType.STRING)) return null;    
	    
	    try 
	    {
	        return Location.deserialize(new Gson().fromJson(data.get(RESPAWN_KEY, PersistentDataType.STRING), Map.class));
	    } 
	    catch (Exception e) 
	    {
	        return null;
	    }
	}
}
