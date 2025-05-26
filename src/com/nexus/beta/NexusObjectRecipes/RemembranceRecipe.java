package com.nexus.beta.NexusObjectRecipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.nexus.alpha.NexusProper;
import com.nexus.beta.ResonanceRelics.ReticleProtocol;

public class RemembranceRecipe 
{
	public static void register() 
	{
		NamespacedKey k = new NamespacedKey(NexusProper.instance, "crafted_remembrance");
		ItemStack stack = new ReticleProtocol().bake();
		ShapelessRecipe r = new ShapelessRecipe(k, stack);
		
		r.addIngredient(Material.NETHER_STAR);
		r.addIngredient(Material.GHAST_TEAR);
		
		Bukkit.addRecipe(r);
	}
}
