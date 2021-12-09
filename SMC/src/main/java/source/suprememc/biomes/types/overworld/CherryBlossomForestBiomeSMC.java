package source.suprememc.biomes.types.overworld;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import source.suprememc.biomes.BiomeSMC;
import source.suprememc.init.generation.SMCOreFeatures;
import source.suprememc.init.generation.SMCTrees;
import source.suprememc.init.generation.SMCVegetation;

public class CherryBlossomForestBiomeSMC extends BiomeSMC
{	
	public CherryBlossomForestBiomeSMC(String name)
	{
		super(name);
	}

	@Override
	protected void configureBiome(Biome.Builder builder)
	{
	      builder.precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(0.1F).scale(0.2F)
	      .temperature(0.6F).downfall(0.6F).specialEffects((new BiomeAmbience.Builder()).waterColor(4159204)
	    .waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(0.6F))
	    .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build());
	}



	@Override
	protected void configureGeneration(BiomeGenerationSettings.Builder builder)
	{
		builder.surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
		 DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
	      builder.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
	      DefaultBiomeFeatures.addDefaultCarvers(builder);
	      DefaultBiomeFeatures.addDefaultLakes(builder);
	      DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
	      DefaultBiomeFeatures.addForestFlowers(builder);
	      DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
	      DefaultBiomeFeatures.addDefaultOres(builder);
	      builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, SMCOreFeatures.FOSSILIZED_CHERRY);
	      DefaultBiomeFeatures.addDefaultSoftDisks(builder);
	      builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, SMCTrees.CHERRY_TREES);
	      DefaultBiomeFeatures.addDefaultFlowers(builder);
	      builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, SMCVegetation.PATCH_CLOVER);
	      DefaultBiomeFeatures.addDefaultMushrooms(builder);
	      DefaultBiomeFeatures.addDefaultExtraVegetation(builder);
	      DefaultBiomeFeatures.addDefaultSprings(builder);
	      DefaultBiomeFeatures.addSurfaceFreezing(builder);

	}

	@Override
	protected void configureMobSpawns(MobSpawnInfo.Builder builder)
	{
		DefaultBiomeFeatures.farmAnimals(builder);
		DefaultBiomeFeatures.commonSpawns(builder);
	}
}