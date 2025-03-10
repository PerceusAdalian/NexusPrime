package com.nexus.io.ResonanceCrystal;

import org.bukkit.Material;

import com.nexus.io.objectbuilder.AbstractNexusObject;

public class ResonanceCrystalObject extends AbstractNexusObject
{
	public ResonanceCrystalObject() 
	{
		super("Resonance Crystal", "resonance_crystal", Material.ECHO_SHARD, true,
				"&r&fA highly unstable form of &e&l&oEchoic Energy&r&f.",
				"&r&f&lShift_Right-Click&r&f to set a &b&orecall&r&f.",
				"&r&f&lRight-Click&r&f to &b&orecall&r&f to set location.",
				"&r&cDestroys&r&f this item upon use. This item is stackable.");
	}
}
