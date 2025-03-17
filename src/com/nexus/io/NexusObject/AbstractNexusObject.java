package com.nexus.io.NexusObject;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusPrintUtils;

public abstract class AbstractNexusObject 
{
	public static final NamespacedKey nexusObject = new NamespacedKey(NexusProper.instance, "nexus_object");
	
	private String name;
	private String internalName;
	private String[] itemDescription;
	private Material material;
	private boolean isEnchanted = false;
	
	public AbstractNexusObject(String name, String internalName, Material material, boolean isEnchanted, String...itemDescription) 
	{
		this.name = name;
		this.internalName = internalName;
		this.material = material;
		this.isEnchanted = isEnchanted;
		this.itemDescription = itemDescription;
	}
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getInternalName() 
	{
		return internalName;
	}
	public void setInternalName(String internalName) 
	{
		this.internalName = internalName;
	}
	public String[] getItemDescription() 
	{
		return itemDescription;
	}
	public void setItemDescription(String[] itemDescription) 
	{
		this.itemDescription = itemDescription;
	}
	public Material getMaterial() 
	{
		return material;
	}
	public void setMaterial(Material material) 
	{
		this.material = material;
	}
	public boolean isEnchanted() 
	{
		return isEnchanted;
	}
	public void setEnchanted(boolean isEnchanted) 
	{
		this.isEnchanted = isEnchanted;
	}
	public static NamespacedKey getNexusobject() 
	{
		return nexusObject;
	}
	public static String getInternalNameAsID(String internalName) 
	{
		int internalNameID = 0;
		for (char ch : internalName.toCharArray()) 
		{
			internalNameID += (int) ch;
		}
		
		return Integer.toHexString(internalNameID).toUpperCase();
	}
	
	public abstract boolean Cast(PlayerInteractEvent e);
	
	public ItemStack bake() 
	{
		ItemStack stack = new ItemStack(material, 1);
		ItemMeta meta = stack.getItemMeta();
		List<String> lore = new ArrayList<>();
			
		if (this.isEnchanted() == true) 
		{
			meta.setEnchantmentGlintOverride(true);
		}
		
		lore.add("\n");
		
		for (String line : itemDescription) 
		{
			lore.add(NexusPrintUtils.ColorParser("&r&f" + line) + "\n");
		}
		
		lore.add("\n");
		
		lore.add(NexusPrintUtils.ColorParser("&r&7&oNexus Object ID: " + getInternalNameAsID(internalName)));
		
		meta.setDisplayName(NexusPrintUtils.ColorParser("&r&3&ko&r&f&l "+name+" &r&3&ko&r&f&l"));
		
		meta.setLore(lore);
		
		meta.getPersistentDataContainer().set(nexusObject, PersistentDataType.STRING, internalName.toString());
		
		stack.setItemMeta(meta);
		
		return stack;
	}
}
