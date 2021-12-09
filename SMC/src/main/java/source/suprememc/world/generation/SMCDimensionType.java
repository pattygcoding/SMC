package source.suprememc.world.generation;

import java.util.OptionalLong;

import com.mojang.serialization.Lifecycle;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.EndBiomeProvider;
import net.minecraft.world.biome.provider.NetherBiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;

public class SMCDimensionType extends DimensionType
{
    protected SMCDimensionType
    (
    		OptionalLong opLong, 
    		boolean hasSkylight, 
    		boolean hasCeiling, 
    		boolean ultraWarm, 
    		boolean natural, 
    		double coordinateScale, 
    		boolean piglinSafe, 
    		boolean bedWorks, 
    		boolean respawnAnchorWorks, 
    		boolean hasRaids,
    		int biomeZoomer, 
    		ResourceLocation infiniburn, 
    		ResourceLocation effectsLocation, 
    		float ambientLight)
    {
        super
        (
        		opLong, 
        		hasSkylight, 
        		hasCeiling, 
        		ultraWarm, 
        		natural, 
        		coordinateScale, 
        		piglinSafe, 
        		bedWorks, 
        		respawnAnchorWorks, 
        		hasRaids, 
        		biomeZoomer, 
        		infiniburn, 
        		effectsLocation, 
        		ambientLight
        );
    }

    private static ChunkGenerator defaultNetherGenerator(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimensionSettingsRegistry, long seed) 
    {
    	//return new NoiseChunkGenerator(new SMCNetherBiomeProvider(seed, biomeRegistry), seed, () -> dimensionSettingsRegistry.getOrThrow(DimensionSettings.NETHER));
    	return new NoiseChunkGenerator(NetherBiomeProvider.Preset.NETHER.biomeSource(biomeRegistry, seed), seed, () -> 
        {
           return dimensionSettingsRegistry.getOrThrow(DimensionSettings.NETHER);
        });
    }
    
    private static ChunkGenerator defaultEndGenerator(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimensionSettingsRegistry, long seed) 
    {
        return new NoiseChunkGenerator(new EndBiomeProvider(biomeRegistry, seed), seed, () -> 
        {
           return dimensionSettingsRegistry.getOrThrow(DimensionSettings.END);
        });
     }

    

    public static SimpleRegistry<Dimension> dimensions(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimensionSettingsRegistry, long seed)
    {
    	SimpleRegistry<Dimension> registry = new SimpleRegistry<>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.experimental());
        registry.register(Dimension.NETHER, new Dimension(() -> DEFAULT_NETHER, defaultNetherGenerator(biomeRegistry, dimensionSettingsRegistry, seed)), Lifecycle.stable());
        registry.register(Dimension.END, new Dimension(() -> DEFAULT_END, defaultEndGenerator(biomeRegistry, dimensionSettingsRegistry, seed)), Lifecycle.stable());
        return registry;
    }
}
