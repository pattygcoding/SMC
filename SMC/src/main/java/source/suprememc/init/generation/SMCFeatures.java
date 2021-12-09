package source.suprememc.init.generation;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import source.suprememc.SupremeMC;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.world.generation.features.PillarFeature;

@Mod.EventBusSubscriber(modid = SupremeMC.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMCFeatures 
{
	/**
	 * @link net.minecraft.world.gen.feature.Features;
	 */
	private static final List<Feature<?>> FEATURES = new ArrayList<>();
	
	private static final Feature<NoFeatureConfig> ANDESITE_PILLAR_FEATURE = register("andesite_pillar", new PillarFeature(Blocks.ANDESITE, NoFeatureConfig.CODEC));
	private static final Feature<NoFeatureConfig> BLACKSTONE_PILLAR_FEATURE = register("blackstone_pillar", new PillarFeature(Blocks.BLACKSTONE, NoFeatureConfig.CODEC));
	private static final Feature<NoFeatureConfig> CALCITE_PILLAR_FEATURE = register("calcite_pillar", new PillarFeature(SMCBlocks.CALCITE, NoFeatureConfig.CODEC));
	private static final Feature<NoFeatureConfig> DEEPSLATE_PILLAR_EATURE = register("deepslate_pillar", new PillarFeature(SMCBlocks.DEEPSLATE, NoFeatureConfig.CODEC));
	private static final Feature<NoFeatureConfig> DIORITE_PILLAR_FEATURE = register("diorite_pillar", new PillarFeature(Blocks.DIORITE, NoFeatureConfig.CODEC));
	private static final Feature<NoFeatureConfig> GRANITE_PILLAR_FEATURE = register("granite_pillar", new PillarFeature(Blocks.GRANITE, NoFeatureConfig.CODEC));
	private static final Feature<NoFeatureConfig> MAGMA_PILLAR_FEATURE = register("magma_pillar", new PillarFeature(Blocks.MAGMA_BLOCK, NoFeatureConfig.CODEC));
	private static final Feature<NoFeatureConfig> QUARTZ_PILLAR_FEATURE = register("quartz_pillar", new PillarFeature(Blocks.SMOOTH_QUARTZ, NoFeatureConfig.CODEC));
	
	public static final ConfiguredFeature<?, ?> ANDESITE_PILLAR = feature("andesite_pillar", SMCFeatures.ANDESITE_PILLAR_FEATURE.configured(IFeatureConfig.NONE).range(255).squared().count(100));
	public static final ConfiguredFeature<?, ?> BLACKSTONE_PILLAR = feature("blackstone_pillar", SMCFeatures.BLACKSTONE_PILLAR_FEATURE.configured(IFeatureConfig.NONE).range(255).squared().count(100));
	public static final ConfiguredFeature<?, ?> CALCITE_PILLAR = feature("calcite_pillar", SMCFeatures.CALCITE_PILLAR_FEATURE.configured(IFeatureConfig.NONE).range(255).squared().count(100));
	public static final ConfiguredFeature<?, ?> DEEPSLATE_PILLAR = feature("deepslate_pillar", SMCFeatures.DEEPSLATE_PILLAR_EATURE.configured(IFeatureConfig.NONE).range(255).squared().count(100));
	public static final ConfiguredFeature<?, ?> DIORITE_PILLAR = feature("diorite_pillar", SMCFeatures.DIORITE_PILLAR_FEATURE.configured(IFeatureConfig.NONE).range(255).squared().count(100));
	public static final ConfiguredFeature<?, ?> GRANITE_PILLAR = feature("granite_pillar", SMCFeatures.GRANITE_PILLAR_FEATURE.configured(IFeatureConfig.NONE).range(255).squared().count(100));
	public static final ConfiguredFeature<?, ?> MAGMA_PILLAR = feature("magma_pillar", SMCFeatures.MAGMA_PILLAR_FEATURE.configured(IFeatureConfig.NONE).range(255).squared().count(100));
	public static final ConfiguredFeature<?, ?> QUARTZ_PILLAR = feature("quartz_pillar", SMCFeatures.QUARTZ_PILLAR_FEATURE.configured(IFeatureConfig.NONE).range(255).squared().count(100));


	
	private static <C extends IFeatureConfig, F extends Feature<C>> F register(String id, F featureType)
	{
		F type = featureType; 
		type.setRegistryName(SupremeMC.MOD_ID, id);
		FEATURES.add(type);
		return type;
	}

	@SubscribeEvent
	public static void registerTypes(final RegistryEvent.Register<Feature<?>> event)
	{
		FEATURES.forEach(type -> event.getRegistry().register(type));
		FEATURES.clear();
	}
	
	protected static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> feature(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(SupremeMC.MOD_ID, key + "_feature"), feature);
    }
}
