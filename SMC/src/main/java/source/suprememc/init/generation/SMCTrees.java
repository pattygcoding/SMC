package source.suprememc.init.generation;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.HugeFungusConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import source.suprememc.SupremeMC;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.world.generation.trees.foliage.PalmFoliagePlacerSMC;

/**
 * 
 * @author phgoodw
 * @link net.minecraft.world.gen.Features
 */
public class SMCTrees 
{
	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CHERRY_TREE = cherryTree("cherry_tree", SMCBlocks.CHERRY_LOG, SMCBlocks.CHERRY_LEAVES);
	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> PALM_TREE = palmTree("palm_tree", SMCBlocks.PALM_LOG, SMCBlocks.PALM_LEAVES);
	
	public static final ConfiguredFeature<?, ?> CHERRY_TREES = register("cherry_trees", Feature.TREE.configured(CHERRY_TREE.config()).decorated(Features.Placements.HEIGHTMAP).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(6, 0.1F, 1))));
	public static final ConfiguredFeature<?, ?> PALM_TREES = register("palm_trees", Feature.TREE.configured(PALM_TREE.config()).decorated(Features.Placements.HEIGHTMAP).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(4, 0.1F, 1))));

	public static final HugeFungusConfig MUSTARD_FUNGUS = new HugeFungusConfig(SMCBlocks.MUSTARD_NYLIUM.defaultBlockState(), SMCBlocks.MUSTARD_STEM.defaultBlockState(), SMCBlocks.MUSTARD_WART_BLOCK.defaultBlockState(), Blocks.SHROOMLIGHT.defaultBlockState(), true);
	public static final HugeFungusConfig LAVENDER_FUNGUS = new HugeFungusConfig(SMCBlocks.LAVENDER_ENDSPAR.defaultBlockState(), SMCBlocks.LAVENDER_STEM.defaultBlockState(), SMCBlocks.LAVENDER_WART_BLOCK.defaultBlockState(), Blocks.SHROOMLIGHT.defaultBlockState(), true);
	
	public static final ConfiguredFeature<?, ?> MUSTARD_FUNGI = register("mustard_fungi", Feature.HUGE_FUNGUS.configured(MUSTARD_FUNGUS).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(8))));
	public static final ConfiguredFeature<?, ?> LAVENDER_FUNGI = register("lavender_fungi", Feature.HUGE_FUNGUS.configured(LAVENDER_FUNGUS).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(8))));

	public static final ConfiguredFeature<HugeFungusConfig, ?> MUSTARD_FUNGI_PLANTED = register("mustard_fungi_planted", Feature.HUGE_FUNGUS.configured(MUSTARD_FUNGUS));
	public static final ConfiguredFeature<HugeFungusConfig, ?> LAVENDER_FUNGI_PLANTED = register("lavender_fungi_planted", Feature.HUGE_FUNGUS.configured(LAVENDER_FUNGUS));
	

	/**
	 * REGISTER FUNCTION
	 */
	
	protected static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(SupremeMC.MOD_ID, key), feature);
    }
	
	
	/**
	 * TREE HELPER FUNCTIONS
	 */
	
	
	protected static ConfiguredFeature<BaseTreeFeatureConfig, ?> cherryTree(String name, Block logBlock, Block leavesBlock)
	{
		return register(name + "_configured_feature", Feature.TREE.configured
		((
			new BaseTreeFeatureConfig.Builder
			(
				new SimpleBlockStateProvider(logBlock.defaultBlockState()), 
				new SimpleBlockStateProvider(leavesBlock.defaultBlockState()), 
				new BlobFoliagePlacer(FeatureSpread.fixed(2), 
				FeatureSpread.fixed(0), 3), 
				new StraightTrunkPlacer(4, 2, 0), 
				new TwoLayerFeature(1, 0, 1)
			)
		).ignoreVines().build()));	
	}
	   
	protected static ConfiguredFeature<BaseTreeFeatureConfig, ?> palmTree(String name, Block logBlock, Block leavesBlock)
	{
		return register(name + "_configured_feature", Feature.TREE.configured
		((
			new BaseTreeFeatureConfig.Builder
			(
				new SimpleBlockStateProvider(logBlock.defaultBlockState()), 
				new SimpleBlockStateProvider(leavesBlock.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)), 
				new PalmFoliagePlacerSMC(FeatureSpread.fixed(2), FeatureSpread.fixed(0)), 
				new ForkyTrunkPlacer(6, 3, 3), 
				new TwoLayerFeature(1, 0, 2)
			)
		).ignoreVines().build()));
	}
	
	
	
}
