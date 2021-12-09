package source.suprememc.biomes.types.deepdark;

import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.biome.SoundAdditionsAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import source.suprememc.biomes.BiomeSMC;
import source.suprememc.init.generation.SMCFeatures;
import source.suprememc.init.generation.SMCStructures;
import source.suprememc.init.generation.SMCSurfaceBuilders;
import source.suprememc.util.FeatureUtil;

public class QuartzCaveBiomeSMC extends BiomeSMC
{
	/**
	 * @link net.minecraft.world.biome.BiomeMaker;
	 */
	public QuartzCaveBiomeSMC(String name) 
	{
		super(name);
	}
	
	
	@Override
	protected void configureBiome(Biome.Builder builder) 
	{
		builder.precipitation(Biome.RainType.NONE);
		builder.biomeCategory(Biome.Category.NETHER);
		builder.depth(0.1F);
		builder.scale(0.2F);
		builder.temperature(2.0F);
		builder.downfall(0.0F);
		builder.specialEffects((new BiomeAmbience.Builder())
		.waterColor(0)
		.waterFogColor(0)
		.fogColor(0)
		.skyColor(getSkyColorWithTemperatureModifier(2.0F))
		.ambientLoopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
		.ambientMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D))
		.ambientAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D))
		.backgroundMusic(BackgroundMusicTracks.createGameMusic(SoundEvents.MUSIC_BIOME_BASALT_DELTAS)).build());
		   
	}
	
	@Override
	protected void configureGeneration(net.minecraft.world.biome.BiomeGenerationSettings.Builder builder) 
	{
		builder.surfaceBuilder(SMCSurfaceBuilders.QUARTZ_CAVE_SURFACE);
		//builder.addStructureStart(SMCStructures.CAVE_MINESHAFT);
		builder.addCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.NETHER_CAVE);
		builder.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, SMCFeatures.QUARTZ_PILLAR);
		FeatureUtil.addCaveOres(builder);
	}
	
	@Override
	protected void configureMobSpawns(net.minecraft.world.biome.MobSpawnInfo.Builder builder) 
	{
		DefaultBiomeFeatures.ambientSpawns(builder);
		FeatureUtil.caveMonsters(builder);
	}

}
