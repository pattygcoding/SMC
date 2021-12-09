package source.suprememc.biomes.util;

import source.suprememc.biomes.AbstractBiomeSMC;

public class BiomeInfoSMC 
{
	private AbstractBiomeSMC biome;
	private String name;
	
	public BiomeInfoSMC(AbstractBiomeSMC biome, String name) 
	{
		this.biome = biome;
		this.name = name;
	}
	
	public AbstractBiomeSMC getBiome()
	{
		return this.biome;
	}
	
	public String getName()
	{
		return this.name;
	}
}
