package source.suprememc.world.generation.ores;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import source.suprememc.init.objects.SMCBlocks;

@SuppressWarnings("unused")
public class SMCOreGeneration 
{
	private final BiomeLoadingEvent event;
	private final Biome.Category biomeCategory;
	public static final RuleTest END_STONE = new BlockMatchRuleTest(Blocks.END_STONE);
	
	public SMCOreGeneration(final BiomeLoadingEvent event) 
	{
		this.biomeCategory = event.getCategory();
		this.event = event;
		if(!(this.atBiome(Biome.Category.NETHER) || this.atBiome(Biome.Category.THEEND))) 
		{
			this.generateOverworldOre(SMCBlocks.LIGHTSTONE_ORE, 20, 0, 128, 20);
			this.generateOverworldOre(SMCBlocks.SALT_ORE, 20, 32, 256, 20);
			this.generateOverworldOre(SMCBlocks.EXPERIENCE_ORE, 8, 0, 128, 16);
			this.generateOverworldOre(SMCBlocks.LIGHTSTONE, 5, 0, 48, 4);
			/*if(this.atBiome(Biome.Category.FOREST))
			{
				this.generateOverworldOre(SMCBlocks.FOSSILIZED_OAK_ORE, 8, 32, 64, 20);
				this.generateOverworldOre(SMCBlocks.FOSSILIZED_BIRCH_ORE, 8, 32, 64, 20);
				this.generateOverworldOre(SMCBlocks.FOSSILIZED_DARK_OAK_ORE, 8, 32, 64, 20);
			}
			if(this.atBiome(Biome.Category.SAVANNA))
			{
				this.generateOverworldOre(SMCBlocks.FOSSILIZED_ACACIA_ORE, 8, 32, 64, 5);
			}
			if(this.atBiome(Biome.Category.TAIGA))
			{
				this.generateOverworldOre(SMCBlocks.FOSSILIZED_SPRUCE_ORE, 8, 32, 64, 20);
			}
			if(this.atBiome(Biome.Category.JUNGLE))
			{
				this.generateOverworldOre(SMCBlocks.FOSSILIZED_JUNGLE_ORE, 8, 32, 64, 30);
				this.generateOverworldOre(SMCBlocks.FOSSILIZED_OAK_ORE, 8, 32, 64, 5);
			}
			if(this.atBiome(Biome.Category.OCEAN))
			{
				this.generateOverworldOre(SMCBlocks.PRISMARINE_ORE, 16, 0, 64, 20);
				this.generateOverworldOre(SMCBlocks.AQUAMARINE_ORE, 8, 0, 16, 1);
			}
			if(this.atBiome(Biome.Category.DESERT))
			{
				this.generateOverworldOre(SMCBlocks.RUBY_ORE, 8, 0, 16, 1);
			}*/

		}
		else if(this.atBiome(Biome.Category.NETHER))
		{
			this.generateNetherOre(SMCBlocks.NETHER_SULFUR_ORE, 8, 0, 128, 10);
			this.generateNetherOre(SMCBlocks.BURNING_DIAMOND_ORE, 8, 0, 16, 1);
			this.generateNetherOre(SMCBlocks.RADIUM_ORE, 8, 0, 16, 1);
		}
		else if(this.atBiome(Biome.Category.THEEND))
		{
			this.generateEndOre(SMCBlocks.GLENDSTONE, 20, 0, 128, 40);
			this.generateEndOre(SMCBlocks.ENDERITE_ORE, 16, 0, 128, 16);
			this.generateEndOre(SMCBlocks.TIGERS_EYE_ORE, 8, 0, 32, 1);
		}
	}

	private void generateOverworldOre(Block ore, int veinSize, int minHeight, int maxHeight, int amount)
	{
		generateOre(this.event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, ore.defaultBlockState(), 5, 15, 30, 10);
	}
	
	private void generateNetherOre(Block ore, int veinSize, int minHeight, int maxHeight, int amount)
	{
		generateOre(this.event.getGeneration(), OreFeatureConfig.FillerBlockType.NETHERRACK, ore.defaultBlockState(), 5, 15, 30, 10);
	}
	
	
	private void generateEndOre(Block ore, int veinSize, int minHeight, int maxHeight, int amount)
	{
		generateOre(this.event.getGeneration(), END_STONE, ore.defaultBlockState(), 5, 15, 30, 10);
	}
	
	private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state, int veinSize, int minHeight, int maxHeight, int amount) 
	{
		settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreFeatureConfig(fillerType, state, veinSize)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight))).squared().count(amount));
	}
	
	private boolean atBiome(Biome.Category category)
	{
		return this.biomeCategory.equals(category);
	}
}
