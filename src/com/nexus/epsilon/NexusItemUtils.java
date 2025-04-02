package com.nexus.epsilon;

import java.util.Arrays;
import java.util.function.Consumer;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ColorableArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

public class NexusItemUtils 
{	
	@SuppressWarnings("unchecked")
	public static <T extends ItemMeta> ItemStack buildGenericItem(Material material, String name, int amount, Class<T> metaClass, Consumer<T> metaSetter, String... lore) 
	{
		ItemStack item = new ItemStack(material, amount);
		
		T meta = (T) item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		
		if (metaSetter != null) 
		{
			metaSetter.accept(meta);
		}
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack buildArmorItem(Material material, String name, Color color, TrimMaterial trimMaterial, TrimPattern pattern, String... lore) 
	{
		return buildGenericItem(material, name, 1, ColorableArmorMeta.class, (meta)-> 
		{
			meta.setColor(color);
			meta.setTrim(new ArmorTrim(trimMaterial ,pattern));
		}, lore);
		
	}
}
