package com.nexus.beta.NexusObjectRecipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.nexus.alpha.NexusProper;
import com.nexus.beta.ResonanceRelics.ReconfigurationProtocol;

public class ReconfigurationProtocolRecipe 
{
	public static void register() 
	{
		NamespacedKey k = new NamespacedKey(NexusProper.instance, "crafted_reconfiguration_protocol");
		ItemStack stack = new ReconfigurationProtocol().bake();
		ShapelessRecipe r = new ShapelessRecipe(k, stack);
		
		r.addIngredient(Material.CRAFTING_TABLE);
		r.addIngredient(Material.BRICK);
		r.addIngredient(Material.ENDER_PEARL);
		
		Bukkit.addRecipe(r);
	}
}
