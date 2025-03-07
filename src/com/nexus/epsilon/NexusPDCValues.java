package com.nexus.epsilon;

import java.util.UUID;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;

import com.nexus.alpha.NexusProper;
import com.nexus.epsilon.NexusPDCUtils.DType;

public class NexusPDCValues
{
	/**
	 * @Notes: Implemented child class of @NexusPDCUtils.java and should not be used until further evaluations of proper implementation are met. Please see the parent class for further details.
	 */
	
	private NamespacedKey key;
	private PersistentDataContainer container;
	
	public NexusPDCValues(String key,PersistentDataContainer container)
	{
		this.key = new NamespacedKey(NexusProper.instance,key);
		this.container = container;
	}
	
	public boolean asBoolean()
	{
		return (boolean) container.get(key,DType.BOOLEAN.type);
	}
	
	public byte asByte()
	{
		return (byte) container.get(key,DType.BYTE.type);
	}
	
	public byte[] asByteArray()
	{
		return (byte[]) container.get(key,DType.BYTE_ARRAY.type);
	}
	
	public double asDouble()
	{
		return (double) container.get(key,DType.DOUBLE.type);
	}
	
	public float asFloat()
	{
		return (float) container.get(key,DType.FLOAT.type);
	}
	
	public int asInt()
	{
		return (int) container.get(key,DType.INTEGER.type);
	}
	
	public int[] asIntArray()
	{
		return (int[]) container.get(key,DType.INTEGER_ARRAY.type);
	}
	
	public long asLong()
	{
		return (long) container.get(key,DType.LONG.type);
	}
	
	public long[] asLongArray()
	{
		return (long[]) container.get(key,DType.LONG_ARRAY.type);
	}
	
	public short asShort()
	{
		return (short) container.get(key,DType.SHORT.type);
	}
	
	public String asString()
	{
		return (String) container.get(key,DType.STRING.type);
	}
	
	public UUID asUUID()
	{
		return UUID.fromString(asString());
	}
	
	public PersistentDataContainer asTagContainer()
	{
		return (PersistentDataContainer) container.get(key,DType.TAG_CONTAINER.type);
	}
}
