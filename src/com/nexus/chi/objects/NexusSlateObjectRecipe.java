package com.nexus.chi.objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.nexus.alpha.NexusProper;

@Deprecated
public class NexusSlateObjectRecipe 
{
	public static void register() 
	{
		NamespacedKey k = new NamespacedKey(NexusProper.instance, "crafted_nexus_slate");
		ItemStack stack = new NexusSlateObject().bake();
		ShapelessRecipe r = new ShapelessRecipe(k, stack);
		
		r.addIngredient(Material.BOOK);
		r.addIngredient(Material.ENDER_PEARL);
		
		Bukkit.addRecipe(r);
	}
}
