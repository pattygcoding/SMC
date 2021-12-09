package source.suprememc.biomes.types.end;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import source.suprememc.biomes.BiomeSMC;
import source.suprememc.init.generation.SMCTrees;
import source.suprememc.init.generation.SMCVegetation;

public class LavenderForestBiomeSMC extends BiomeSMC
{	
	public LavenderForestBiomeSMC(String name)
    {
		super(name);
    }
	
	@Override
    protected void configureBiome(Biome.Builder builder)
	{
	     builder.precipitation(Biome.RainType.NONE);
	     builder.biomeCategory(Biome.Category.THEEND);
	     builder.depth(0.1F).scale(0.2F).temperature(0.5F).downfall(0.5F);
	     builder.specialEffects((new BiomeAmbience.Builder())
	    		 .waterColor(4159204)
	    		 .waterFogColor(329011)
	    		 .fogColor(10518688)
	    		 .skyColor(0)
	    		 .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build());
	   
	}
	
	
	
	@Override
    protected void configureGeneration(BiomeGenerationSettings.Builder builder)
    {
		 builder.surfaceBuilder(ConfiguredSurfaceBuilders.END);
		 builder.addStructureStart(StructureFeatures.END_CITY);
		 builder.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.END_GATEWAY);
		 builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, SMCTrees.LAVENDER_FUNGI);
		 builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, SMCVegetation.LAVENDER_FOREST);
    }

	@Override
    protected void configureMobSpawns(MobSpawnInfo.Builder builder)
    {
		DefaultBiomeFeatures.endSpawns(builder);
    }
}