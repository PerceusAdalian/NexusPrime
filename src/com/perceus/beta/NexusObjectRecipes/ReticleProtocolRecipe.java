package com.perceus.beta.NexusObjectRecipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.nexus.alpha.NexusProper;
import com.nexus.beta.ResonanceRelics.ReticleProtocol;

public class ReticleProtocolRecipe 
{
	public static void register() 
	{
		NamespacedKey k = new NamespacedKey(NexusProper.instance, "crafted_reticle_protocol");
		ItemStack stack = new ReticleProtocol().bake();
		ShapelessRecipe r = new ShapelessRecipe(k, stack);
		
		r.addIngredient(Material.SPECTRAL_ARROW);
		r.addIngredient(Material.WIND_CHARGE);
		r.addIngredient(Material.BRICK);
		r.addIngredient(Material.ENDER_PEARL);
		
		Bukkit.addRecipe(r);
	}
}
