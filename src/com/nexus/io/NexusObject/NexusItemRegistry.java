package com.nexus.io.NexusObject;

import java.util.HashMap;
import java.util.Map;

import com.nexus.io.ResonanceCrystals.EchoicLocator;
import com.nexus.io.ResonanceCrystals.MemoryAnchor;

public class NexusItemRegistry 
{
	public static Map<String, AbstractNexusObject> itemRegistry = new HashMap<>();
	public static void itemInit() 
	{
		//Nexus Items
		
		//Resonance Crystals
		MemoryAnchor shardObject = new MemoryAnchor();
		itemRegistry.put(shardObject.getInternalName(), shardObject);
		
		EchoicLocator locatorObject = new EchoicLocator();
		itemRegistry.put(locatorObject.getInternalName(), locatorObject);
	}
}
