package source.suprememc.world.generation.layer;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;
import source.suprememc.biomes.util.BiomeWeightSMC;
import source.suprememc.util.BiomeUtil;

public class SMCBiomeLayer implements IC0Transformer 
{
	private enum Climate
	{
		DESERT, WARM, COOL, ICY
	}
	

	public SMCBiomeLayer() 
	{
	}

	@Override
	public int apply(INoiseRandom iNoiseRandom, int value) 
	{
		int i = (value & 3840) >> 8;
		value = value & -3841;
		if (!SMCLayerUtil.isOcean(value) && value != 14) 
		{
			switch(value) {
			case 1:
				if(i > 0) return iNoiseRandom.nextRandom(3) == 0 ? 39 : 38;
				return getBiomeId(Climate.DESERT, iNoiseRandom);
			case 2:
				if(i > 0) return 21;
				return getBiomeId(Climate.WARM, iNoiseRandom);
			case 3:
				if(i > 0) return 32;
				return getBiomeId(Climate.COOL, iNoiseRandom);
			case 4:
				return getBiomeId(Climate.ICY, iNoiseRandom);
			default:
				return 14;
			}
		} 
		else return value;
		
	}

	private int getBiomeId(Climate type, INoiseRandom context) 
	{
		return BiomeUtil.getBiomeId(getBiome(type, context));
	}
	
	protected RegistryKey<Biome> getBiome(Climate type, INoiseRandom context) 
	{
		int totalWeight = 0;
		if(type == Climate.DESERT) totalWeight = 60;
		else if(type == Climate.WARM) totalWeight = 60;
		else if(type == Climate.COOL) totalWeight = 40;
		else totalWeight = 40;
		int weight = context.nextRandom(totalWeight / 10) * 10;
		if(type == Climate.DESERT) return BiomeUtil.createKey(WeightedRandom.getWeightedItem(BiomeWeightSMC.DESERT_BIOMES, weight).getBiome());
		else if(type == Climate.WARM) return BiomeUtil.createKey(WeightedRandom.getWeightedItem(BiomeWeightSMC.WARM_BIOMES, weight).getBiome());
		else if(type == Climate.COOL) return BiomeUtil.createKey(WeightedRandom.getWeightedItem(BiomeWeightSMC.COOL_BIOMES, weight).getBiome());
		else return BiomeUtil.createKey(WeightedRandom.getWeightedItem(BiomeWeightSMC.ICY_BIOMES, weight).getBiome());
	}
}