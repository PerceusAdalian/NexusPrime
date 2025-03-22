package com.nexus.epsilon;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;

public class OreValues 
{
	public static Map<Material, Material> validMaterials = new HashMap<>();
	
	public static void init() 
	{
		validMaterials.put(Material.COAL, Material.CHARCOAL);
		validMaterials.put(Material.CHARCOAL, Material.BLAZE_POWDER);
		validMaterials.put(Material.RAW_IRON, Material.IRON_INGOT);
		validMaterials.put(Material.RAW_GOLD, Material.GOLD_INGOT);
		validMaterials.put(Material.DIAMOND_ORE, Material.DIAMOND);
		validMaterials.put(Material.RAW_COPPER, Material.COPPER_INGOT);
		validMaterials.put(Material.ANCIENT_DEBRIS, Material.NETHERITE_SCRAP);
		validMaterials.put(Material.NETHERITE_SCRAP, Material.NETHERITE_INGOT);
		validMaterials.put(Material.EMERALD_ORE, Material.EMERALD);
	}
}
