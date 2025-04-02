package com.perceus.beta.NexusObjectRecipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.nexus.alpha.NexusProper;
import com.nexus.beta.ResonanceRelics.OrdinalProtocol;

public class OrdinalProtocolRecipe 
{
	public static void register() 
	{
		NamespacedKey k = new NamespacedKey(NexusProper.instance, "crafted_ordinal_protocol");
		ItemStack stack = new OrdinalProtocol().bake();
		ShapelessRecipe r = new ShapelessRecipe(k, stack);
		
		r.addIngredient(Material.GOLDEN_APPLE);
		r.addIngredient(Material.BRICK);
		r.addIngredient(Material.ENDER_PEARL);
		
		Bukkit.addRecipe(r);
	}
}
