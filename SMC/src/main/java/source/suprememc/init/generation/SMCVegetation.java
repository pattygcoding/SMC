package source.suprememc.init.generation;


import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateProvidingFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import source.suprememc.SupremeMC;
import source.suprememc.init.objects.SMCBlocks;

/**
 * 
 * @author phgoodw
 * @link net.minecraft.world.gen.Features
 */

public class SMCVegetation 
{
    public static final BlockClusterFeatureConfig CLOVER_SPREAD = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(SMCBlocks.CLOVER.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(32).build();

	public static final BlockStateProvidingFeatureConfig MUSTARD_FOREST_SPREAD = netherVeg(SMCBlocks.MUSTARD_ROOTS, 87, SMCBlocks.MUSTARD_FUNGUS, 11, Blocks.CRIMSON_FUNGUS, 1, Blocks.WARPED_FUNGUS, 1);
	public static final BlockStateProvidingFeatureConfig LAVENDER_FOREST_SPREAD = netherVeg(SMCBlocks.LAVENDER_ROOTS, 87, SMCBlocks.MUSTARD_FUNGUS, 13);
	
	public static final ConfiguredFeature<?, ?> PATCH_CLOVER = register("clover_patches", Feature.RANDOM_PATCH.configured(CLOVER_SPREAD).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(Placement.COUNT_NOISE.configured(new NoiseDependant(-0.8D, 5, 10))));

	public static final ConfiguredFeature<?, ?> LAVENDER_FOREST = register("lavender_forest", Feature.NETHER_FOREST_VEGETATION.configured(LAVENDER_FOREST_SPREAD).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(6))));
	public static final ConfiguredFeature<?, ?> MUSTARD_FOREST = register("mustard_forest", Feature.NETHER_FOREST_VEGETATION.configured(MUSTARD_FOREST_SPREAD).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(6))));

	protected static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(SupremeMC.MOD_ID, key + "_veg_feature"), feature);
    }

	private static BlockStateProvidingFeatureConfig netherVeg(Block blockA, int intA, Block blockB, int intB)
	{
		return new BlockStateProvidingFeatureConfig((new WeightedBlockStateProvider()).add
		(
			blockA.defaultBlockState(), intA)
			.add(blockB.defaultBlockState(), intB)
		);
	}
	
	private static BlockStateProvidingFeatureConfig netherVeg(Block blockA, int intA, Block blockB, int intB, Block blockC, int intC, Block blockD, int intD)
	{
		return new BlockStateProvidingFeatureConfig((new WeightedBlockStateProvider()).add
		(
			blockA.defaultBlockState(), intA)
			.add(blockB.defaultBlockState(), intB)
			.add(blockC.defaultBlockState(), intC)
			.add(blockD.defaultBlockState(), intD)
		);
	}

	
}
