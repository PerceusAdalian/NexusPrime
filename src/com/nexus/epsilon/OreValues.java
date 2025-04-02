package com.nexus.epsilon;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;

public class OreValues 
{
	public static Map<Material, Material> validMaterials = new HashMap<>();
	public static Map<Material, Material> validBlockTypes = new HashMap<>();
	
	public static void initMaterials() 
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
	
	public static void initBlockTypes() 
	{
		validBlockTypes.put(Material.COAL_ORE, Material.CHARCOAL);
		validBlockTypes.put(Material.IRON_ORE, Material.IRON_INGOT);
		validBlockTypes.put(Material.GOLD_ORE, Material.GOLD_INGOT);
		validBlockTypes.put(Material.DIAMOND_ORE, Material.DIAMOND);
		validBlockTypes.put(Material.COPPER_ORE, Material.COPPER_INGOT);
		validBlockTypes.put(Material.LAPIS_ORE, Material.LAPIS_LAZULI);
		validBlockTypes.put(Material.EMERALD_ORE, Material.EMERALD);
		validBlockTypes.put(Material.REDSTONE_ORE, Material.REDSTONE);
		
		validBlockTypes.put(Material.ANCIENT_DEBRIS, Material.NETHERITE_SCRAP);

		validBlockTypes.put(Material.DEEPSLATE_COAL_ORE, Material.CHARCOAL);
		validBlockTypes.put(Material.DEEPSLATE_IRON_ORE, Material.IRON_INGOT);
		validBlockTypes.put(Material.DEEPSLATE_GOLD_ORE, Material.GOLD_INGOT);
		validBlockTypes.put(Material.DEEPSLATE_DIAMOND_ORE, Material.DIAMOND);
		validBlockTypes.put(Material.DEEPSLATE_COPPER_ORE, Material.COPPER_INGOT);
		validBlockTypes.put(Material.DEEPSLATE_LAPIS_ORE, Material.LAPIS_LAZULI);
		validBlockTypes.put(Material.DEEPSLATE_EMERALD_ORE, Material.EMERALD);
		validBlockTypes.put(Material.DEEPSLATE_REDSTONE_ORE, Material.REDSTONE);
	}
}
