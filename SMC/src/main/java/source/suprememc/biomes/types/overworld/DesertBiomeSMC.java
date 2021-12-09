package source.suprememc.biomes.types.overworld;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import source.suprememc.biomes.BiomeSMC;

public class DesertBiomeSMC extends BiomeSMC
{	
	private float depth;
	private float scale;
	private boolean hasFossils, hasVillageAndOutpost, hasDesertPyramid;
	private ConfiguredSurfaceBuilder<?> surface;
	public DesertBiomeSMC(String name, float depth, float scale, ConfiguredSurfaceBuilder<?> surface, boolean hasVillageAndOutpost, boolean hasDesertPyramid, boolean hasFossils)
	{
		super(name);
		this.depth = depth;
		this.scale = scale;
		this.hasVillageAndOutpost = hasVillageAndOutpost;
		this.hasDesertPyramid = hasDesertPyramid;
		this.hasFossils = hasFossils;
		this.surface = surface;
	}

	@Override
	protected void configureBiome(Biome.Builder builder)
	{
		builder.precipitation(Biome.RainType.NONE);
		builder.biomeCategory(Biome.Category.DESERT);
		builder.depth(this.depth);
		builder.scale(this.scale);
		builder.temperature(2.0F);
		builder.downfall(0.0F);
		builder.specialEffects((new BiomeAmbience.Builder())
				.waterColor(4159204)
				.waterFogColor(329011)
				.fogColor(12638463)
				.skyColor(BiomeSMC.getSkyColorWithTemperatureModifier(2.0F))
				.ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build());
	}



	@Override
	protected void configureGeneration(BiomeGenerationSettings.Builder builder)
	{
		builder.surfaceBuilder(this.surface);
		 if (this.hasVillageAndOutpost) {
	         builder.addStructureStart(StructureFeatures.VILLAGE_DESERT);
	         builder.addStructureStart(StructureFeatures.PILLAGER_OUTPOST);
	      }

	      if (this.hasDesertPyramid) {
	         builder.addStructureStart(StructureFeatures.DESERT_PYRAMID);
	      }

	      if (this.hasFossils) {
	         DefaultBiomeFeatures.addFossilDecoration(builder);
	      }

	      DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
	      builder.addStructureStart(StructureFeatures.RUINED_PORTAL_DESERT);
	      DefaultBiomeFeatures.addDefaultCarvers(builder);
	      DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
	      DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
	      DefaultBiomeFeatures.addDefaultOres(builder);
	      DefaultBiomeFeatures.addDefaultSoftDisks(builder);
	      DefaultBiomeFeatures.addDesertExtraVegetation(builder);
	      DefaultBiomeFeatures.addDesertExtraDecoration(builder);
	      DefaultBiomeFeatures.addSurfaceFreezing(builder);

	}

	@Override
	protected void configureMobSpawns(MobSpawnInfo.Builder builder)
	{
		DefaultBiomeFeatures.desertSpawns(builder);
		builder.setPlayerCanSpawn();
	}
}