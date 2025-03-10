package com.nexus.io.objectbuilder;

import java.util.HashMap;
import java.util.Map;

import com.nexus.io.ResonanceCrystal.ResonanceCrystalObject;

public class NexusItemRegistry 
{
	public static Map<String, AbstractNexusObject> itemRegistry = new HashMap<>();
	public static void itemInit() 
	{
		//Nexus Items
		ResonanceCrystalObject shardobject = new ResonanceCrystalObject();
		itemRegistry.put(ResonanceCrystalObject.getInternalName(), shardobject);
	}
}
