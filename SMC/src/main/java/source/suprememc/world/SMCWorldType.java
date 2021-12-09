package source.suprememc.world;

import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import net.minecraftforge.common.world.ForgeWorldType;
import source.suprememc.world.generation.SMCBiomeProvider;
import source.suprememc.world.generation.SMCDimensionType;

public class SMCWorldType extends ForgeWorldType
{
	public SMCWorldType() 
	{
		super(null);
	}
	
	@Override
	public ChunkGenerator createChunkGenerator(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimensionSettingsRegistry, long seed, String generatorSettings) 
	{
		return new NoiseChunkGenerator(new SMCBiomeProvider(seed, biomeRegistry), seed, () -> dimensionSettingsRegistry.getOrThrow(DimensionSettings.OVERWORLD));
	}
	
	@Override
	public DimensionGeneratorSettings createSettings(DynamicRegistries dynamicRegistries, long seed, boolean generateStructures, boolean generateLoot, String generatorSettings) 
	{
		Registry<Biome> biomeRegistry = dynamicRegistries.registryOrThrow(Registry.BIOME_REGISTRY);
        Registry<DimensionSettings> dimensionSettingsRegistry = dynamicRegistries.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);
        Registry<DimensionType> dimensionTypeRegistry = dynamicRegistries.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
        return new DimensionGeneratorSettings(seed, generateStructures, generateLoot, DimensionGeneratorSettings.withOverworld(dimensionTypeRegistry, SMCDimensionType.dimensions(biomeRegistry, dimensionSettingsRegistry, seed), createChunkGenerator(biomeRegistry, dimensionSettingsRegistry, seed, null)));
	}
}
