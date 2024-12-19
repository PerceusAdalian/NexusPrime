package com.nexus.epsilon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.EntityType;

public class NexusFromEntity
{
	
	public static List<String> getAsList() 
	{
		/**
		 * @Documentation: Returns the native Minecraft entities as an Enum[] to be parsed for command purposes.
		 */
		List<String> entityNames = new ArrayList<>(); // Start with an empty list
		for (EntityType type : EntityType.values()) // Iterate over all the enum variants
		{
		  entityNames.add(type.name()); // Get the variant's name and then add it to the list
		}
		return entityNames;	
	}
	
}
