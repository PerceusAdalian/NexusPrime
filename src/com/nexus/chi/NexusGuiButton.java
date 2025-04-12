package com.nexus.chi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.nexus.epsilon.NexusPrintUtils;

public class NexusGuiButton 
{
	private final ItemStack item;
    private final ItemMeta meta;
    private final List<String> lore = new ArrayList<>();
    
    public NexusGuiButton(Material material) 
    {
        item = new ItemStack(material);
        meta = item.getItemMeta();
    }
    
    public NexusGuiButton(ItemStack obj) 
    {
		this.item = new ItemStack(obj);
		this.meta = obj.getItemMeta();
    }
    
    public static NexusGuiButton button(Material material) 
    {
        return new NexusGuiButton(material);
    }
    
    public static NexusGuiButton button(ItemStack stack) 
    {
    	return new NexusGuiButton(stack);
    }
    
    public NexusGuiButton setName(String name) 
    {
        meta.setDisplayName(NexusPrintUtils.ColorParser(name));
        return this;
    }

    public NexusGuiButton setLore(String... lines) 
    {
        for (String line : lines) 
        {
            lore.add(NexusPrintUtils.ColorParser("&r&f" + line));
        }
        return this;
    }
    
    public void place(AbstractNexusGui gui, int slot, Consumer<InventoryClickEvent> action) 
    {
        meta.setLore(lore);
        item.setItemMeta(meta);
        gui.getInventory().setItem(slot, item);
        gui.clickActions.put(slot, action);
    }
    
}
