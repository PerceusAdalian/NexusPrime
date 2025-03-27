package com.nexus.epsilon;

public enum NexusObjectAbilityType 
{
	
	UTILITY("&r&b&oUtility"),
	OFFENSIVE("&r&c&lOffensive"),
	DEFENSIVE("&r&6&lDefensive"),
	SUPPORT("&r&e&oSupport");

	private final String abilityType; 
	
	NexusObjectAbilityType(String string) 
	{
		this.abilityType = string;
	}

	public String getAbilityType() 
	{
		return this.abilityType;
	}
	
}
