package source.suprememc.biomes.types.overworld;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import source.suprememc.biomes.BiomeSMC;
import source.suprememc.init.generation.SMCSurfaceBuilders;
import source.suprememc.init.generation.SMCTrees;
import source.suprememc.init.objects.SMCEntities;

public class PalmBeachBiomeSMC extends BiomeSMC
{	
	public PalmBeachBiomeSMC(String name)
    {
		super(name);
    }
	
	@Override
    protected void configureBiome(Biome.Builder builder)
	{
		builder.precipitation(Biome.RainType.RAIN);
		builder.biomeCategory(Biome.Category.BEACH);
		builder.depth(0.0F);
		builder.scale(0.025F);
		builder.temperature(0.8F);
		builder.downfall(0.4F);
		builder.specialEffects((new BiomeAmbience.Builder())
		.waterColor(4159204)
		.waterFogColor(329011)
		.fogColor(12638463)
		.skyColor(BiomeSMC.getSkyColorWithTemperatureModifier(0.8F))
		.ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build());
	}
	
	
	
	@Override
    protected void configureGeneration(BiomeGenerationSettings.Builder builder)
    {
		builder.surfaceBuilder(SMCSurfaceBuilders.PALM_SURFACE);
		builder.addStructureStart(StructureFeatures.MINESHAFT);
		builder.addStructureStart(StructureFeatures.BURIED_TREASURE);
		builder.addStructureStart(StructureFeatures.SHIPWRECH_BEACHED);
		builder.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);
		DefaultBiomeFeatures.addDefaultCarvers(builder);
		DefaultBiomeFeatures.addDefaultLakes(builder);
		DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
		DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultSoftDisks(builder);
		
		builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, SMCTrees.PALM_TREES);
		DefaultBiomeFeatures.addDefaultExtraVegetation(builder);
		DefaultBiomeFeatures.addDefaultFlowers(builder);
		DefaultBiomeFeatures.addBadlandGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
	  
    }

	@Override
    protected void configureMobSpawns(MobSpawnInfo.Builder builder)
    {
		builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.TURTLE, 5, 2, 5));
		DefaultBiomeFeatures.ambientSpawns(builder);
		builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
		builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(SMCEntities.TROPICAL_DRUDGE, 80, 4, 4));
		builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 20, 4, 4));
		builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, 25, 1, 1));
		builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 100, 4, 4));
		builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.CREEPER, 100, 4, 4));
		builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
		builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
		builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
    }
}