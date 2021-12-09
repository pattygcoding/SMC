package source.suprememc.biomes.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import source.suprememc.init.generation.SMCBiomes;
import source.suprememc.util.BiomeUtil;

public class BiomeWeightSMC extends WeightedRandom.Item
{
	private Biome biome;
	private int weight;
	
	public static final List<BiomeWeightSMC> DESERT_BIOMES = new ArrayList<BiomeWeightSMC>();
	public static final List<BiomeWeightSMC> WARM_BIOMES = new ArrayList<BiomeWeightSMC>();
	public static final List<BiomeWeightSMC> COOL_BIOMES = new ArrayList<BiomeWeightSMC>();
	public static final List<BiomeWeightSMC> ICY_BIOMES = new ArrayList<BiomeWeightSMC>();
	
	public BiomeWeightSMC(Biome biome, int weight)
	{
		super(weight);
		this.biome = biome;
		this.weight = weight;
	}
	public Biome getBiome()
	{
		return this.biome;
	}
	public int getWeight()
	{
		return this.weight;
	}
	
	
	static
	{
		DESERT_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.DESERT), 20));
		DESERT_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(SMCBiomes.SAHARA_DESERT.getBiomeKey()), 5));
		DESERT_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(SMCBiomes.SALT_FLAT.getBiomeKey()), 5));
		DESERT_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.SAVANNA), 20));
		DESERT_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.PLAINS), 10));
		
		WARM_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.FOREST), 10));
		WARM_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.DARK_FOREST), 10));
		WARM_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.MOUNTAINS), 10));
		WARM_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.PLAINS), 10));
		WARM_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.BIRCH_FOREST), 5));
		WARM_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(SMCBiomes.CHERRY_BLOSSOM_FOREST.getBiomeKey()), 5));
		WARM_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.SWAMP), 10));
		
		COOL_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.FOREST), 10));
		COOL_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.MOUNTAINS), 10));
		COOL_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.TAIGA), 10));
		COOL_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.PLAINS), 10));
		
		ICY_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.SNOWY_TUNDRA), 30));
		ICY_BIOMES.add(new BiomeWeightSMC(BiomeUtil.getBiome(Biomes.SNOWY_TAIGA), 10));
	}
}