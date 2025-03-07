package com.nexus.io.EchoTag;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EchoBindableFilter implements EchoBindable
{
	private static final Set<Material> BINDABLE_ITEMS = new HashSet<>();
	
	static 
	{
		BINDABLE_ITEMS.add(Material.WOODEN_SWORD);
		BINDABLE_ITEMS.add(Material.STONE_SWORD);
		BINDABLE_ITEMS.add(Material.IRON_SWORD);
		BINDABLE_ITEMS.add(Material.GOLDEN_SWORD);
		BINDABLE_ITEMS.add(Material.DIAMOND_SWORD);
		BINDABLE_ITEMS.add(Material.NETHERITE_SWORD);
		
		BINDABLE_ITEMS.add(Material.WOODEN_AXE);
		BINDABLE_ITEMS.add(Material.STONE_AXE);
		BINDABLE_ITEMS.add(Material.IRON_AXE);
		BINDABLE_ITEMS.add(Material.GOLDEN_AXE);
		BINDABLE_ITEMS.add(Material.DIAMOND_AXE);
		BINDABLE_ITEMS.add(Material.NETHERITE_AXE);
		
		BINDABLE_ITEMS.add(Material.BOW);
		
		BINDABLE_ITEMS.add(Material.TOTEM_OF_UNDYING);
	}
	
	@Override
    public boolean isBindable(ItemStack item) 
	{
        return item != null && BINDABLE_ITEMS.contains(item.getType());
    }
	
}
