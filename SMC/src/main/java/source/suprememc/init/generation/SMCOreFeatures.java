package source.suprememc.init.generation;

import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import source.suprememc.SupremeMC;
import source.suprememc.data.SMCBlockTags;
import source.suprememc.init.objects.SMCBlocks;
public class SMCOreFeatures 
{
	private static int caveMin = 0, caveMid = 64, caveMax = 128;
	
	public static final ConfiguredFeature<?, ?> GILDED_ORE = register("deep_amethyst", Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.BLACKSTONE), Blocks.GILDED_BLACKSTONE.defaultBlockState(), 33)).range(caveMax).squared().count(20));
	public static final ConfiguredFeature<?, ?> AMETHYST = register("deep_amethyst", Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(SMCBlocks.CALCITE), SMCBlocks.AMETHYST_BLOCK.defaultBlockState(), 33)).range(caveMax).squared().count(20));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_TUFF = register("deep_ore_tuff", Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(SMCBlocks.DEEPSLATE), SMCBlocks.TUFF.defaultBlockState(), 33)).range(caveMax).squared().count(10));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_COAL1 = register("deep_ore_coal_batch1", Feature.ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_COAL_ORE.defaultBlockState(), 17)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(caveMid, caveMid))).squared().count(10));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_COAL2 = register("deep_ore_coal_batch2", Feature.NO_SURFACE_ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_COAL_ORE.defaultBlockState(), 17)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(caveMid, caveMid))).squared().count(10));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_COAL3 = register("deep_ore_coal_batch3", Feature.ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_COAL_ORE.defaultBlockState(), 17)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(caveMid, caveMid, caveMax)).squared().count(30)));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_IRON1 = register("deep_ore_iron_batch1", Feature.ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_IRON_ORE.defaultBlockState(), 5)).range(caveMid).squared().count(10));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_IRON2 = register("deep_ore_iron_batch2", Feature.ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_IRON_ORE.defaultBlockState(), 9)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(caveMid, caveMid))).squared().count(10));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_IRON3 = register("deep_ore_iron_batch3", Feature.ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_IRON_ORE.defaultBlockState(), 9)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(caveMid, caveMid, caveMax)).squared().count(30)));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_GOLD1 = register("deep_ore_gold_batch1", Feature.ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_GOLD_ORE.defaultBlockState(), 9)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(caveMin, 32))).squared().count(2));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_GOLD2 = register("deep_ore_gold_batch2", Feature.NO_SURFACE_ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_GOLD_ORE.defaultBlockState(), 9)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(caveMin, 32))).squared().count(2));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_COPPER = register("deep_ore_copper_batch", Feature.ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_COPPER_ORE.defaultBlockState(), 10)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(48, 48))).squared().count(6));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_REDSTONE1 = register("deep_ore_redstone_batch1", Feature.ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_REDSTONE_ORE.defaultBlockState(), 8)).range(48).squared().count(8));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_REDSTONE2 = register("deep_ore_redstone_batch2", Feature.ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_REDSTONE_ORE.defaultBlockState(), 8)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(caveMin, 32))).squared().count(4));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_LAPIS1 = register("deep_ore_lapis_batch1", Feature.ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_LAPIS_ORE.defaultBlockState(), 8)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(32, 32))).squared().count(2));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_LAPIS2 = register("deep_ore_lapis_batch2", Feature.NO_SURFACE_ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_LAPIS_ORE.defaultBlockState(), 8)).range(caveMid).squared().count(4));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_DIAMOND = register("deep_ore_diamond_batch", Feature.ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_DIAMOND_ORE.defaultBlockState(), 8)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(caveMin, 32))).squared().count(6));
	public static final ConfiguredFeature<?, ?> DEEP_ORE_EMERALD = register("deep_ore_emerald_batch", Feature.ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(SMCBlockTags.DEEPDARK_STONE), SMCBlocks.DEEPSLATE_EMERALD_ORE.defaultBlockState(), 8)).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(caveMax, caveMid))).squared().count(30));
	public static final ConfiguredFeature<?, ?> NETHER_ORE_SULFUR = register("ore_sulfur", Feature.ORE.configured(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.NETHERRACK), SMCBlocks.NETHER_SULFUR_ORE.defaultBlockState(), 17)).range(caveMax).squared().count(20));
	public static final ConfiguredFeature<?, ?> FOSSILIZED_CHERRY = register("fossil_cherry", Feature.ORE.configured(new OreFeatureConfig(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), SMCBlocks.FOSSILIZED_CHERRY_ORE.defaultBlockState(), 17)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(32, 32, 100))).squared().count(40));

	
	
	
	protected static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(SupremeMC.MOD_ID, key), feature);
    }
}
