package com.perceus.beta.NexusObjectRecipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.nexus.alpha.NexusProper;
import com.nexus.beta.ResonanceRelics.NullPointProtocol;

public class NullPointProtocolRecipe 
{
	public static void register() 
	{
		NamespacedKey k = new NamespacedKey(NexusProper.instance, "crafted_null_point_protocol");
		ItemStack stack = new NullPointProtocol().bake();
		ShapelessRecipe r = new ShapelessRecipe(k, stack);
		
		r.addIngredient(Material.GOLDEN_PICKAXE);
		r.addIngredient(Material.BRICK);
		r.addIngredient(Material.ENDER_PEARL);
		
		Bukkit.addRecipe(r);
	}
}
