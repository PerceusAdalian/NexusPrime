package com.nexus.io.NexusObject;

import org.bukkit.Material;

public abstract class AbstractResonanceObject extends AbstractNexusObject
{
	private boolean isCrystal;
	private boolean isRelic;
	
	public AbstractResonanceObject(String name, String internalName, Material material, boolean isEnchanted, String[] itemDescription) 
	{
		super(name, internalName, material, isEnchanted, itemDescription);
	}
	
	public AbstractResonanceObject(String name, String internalName, Material material, boolean isEnchanted, boolean isCrystal, boolean isRelic, String...itemDescription) 
	{
		super(name, internalName, material, isEnchanted, itemDescription);
		this.isCrystal = isCrystal;
		this.isRelic = isRelic;
	}

	public boolean isCrystal() 
	{
		return isCrystal;
	}
	
	public boolean isRelic() 
	{
		return isRelic;
	}
}
