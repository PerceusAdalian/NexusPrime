package com.nexus.io.ResonanceCrystals;

import org.bukkit.Material;

import com.nexus.io.NexusObject.AbstractNexusObject;

public abstract class AbstractResonanceCrystal extends AbstractNexusObject
{
	private boolean isCrystal;
	
	public AbstractResonanceCrystal(String name, String internalName, Material material, boolean isEnchanted, String[] itemDescription) 
	{
		super(name, internalName, material, isEnchanted, itemDescription);
	}
	
	public AbstractResonanceCrystal(String name, String internalName, Material material, boolean isEnchanted, boolean isCrystal, String...itemDescription) 
	{
		super(name, internalName, material, isEnchanted, itemDescription);
		this.isCrystal = isCrystal;
	}

	public boolean isCrystal() 
	{
		return isCrystal;
	}
}
