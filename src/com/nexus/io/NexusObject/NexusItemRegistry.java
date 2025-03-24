package com.nexus.io.NexusObject;

import java.util.HashMap;
import java.util.Map;

import com.nexus.io.ResonanceCrystals.EchoicLocator;
import com.nexus.io.ResonanceCrystals.NullifyGravity;
import com.nexus.io.ResonanceCrystals.MemoryAnchor;
import com.nexus.io.ResonanceCrystals.EchoicDisruption;
import com.nexus.io.ResonanceCrystals.RiftPostulate;
import com.nexus.io.ResonanceCrystals.ThermalDisruption;
import com.nexus.io.ResonanceCrystals.VectorDisplacement;

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
		
		NullifyGravity gravityObject = new NullifyGravity();
		itemRegistry.put(gravityObject.getInternalName(), gravityObject);
		
		VectorDisplacement vectorObject = new VectorDisplacement();
		itemRegistry.put(vectorObject.getInternalName(), vectorObject);
		
		RiftPostulate riftObject = new RiftPostulate();
		itemRegistry.put(riftObject.getInternalName(), riftObject);
		
		ThermalDisruption smeltObject = new ThermalDisruption();
		itemRegistry.put(smeltObject.getInternalName(), smeltObject);
		
		EchoicDisruption damageObject = new EchoicDisruption();
		itemRegistry.put(damageObject.getInternalName(), damageObject);
	}
}
