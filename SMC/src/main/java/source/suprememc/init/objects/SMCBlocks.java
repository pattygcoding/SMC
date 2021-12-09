package source.suprememc.init.objects;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WoodType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.trees.Tree;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusConfig;
import source.suprememc.blocks.BlockSMC;
import source.suprememc.blocks.BookshelfBlockSMC;
import source.suprememc.blocks.FallingBlockSMC;
import source.suprememc.blocks.FlowingFluidBlockSMC;
import source.suprememc.blocks.OreBlockSMC;
import source.suprememc.blocks.RotatedPillarBlockSMC;
import source.suprememc.blocks.interactive.CraftingTableBlockSMC;
import source.suprememc.blocks.interactive.FreezerBlockSMC;
import source.suprememc.blocks.interactive.FurnaceBlockSMC;
import source.suprememc.blocks.interactive.PhonographBlockSMC;
import source.suprememc.blocks.interactive.SoulBrewingStandSMC;
import source.suprememc.blocks.plant.BushBlockSMC;
import source.suprememc.blocks.plant.CornStalkBlockSMC;
import source.suprememc.blocks.plant.CornStalkTopBlockSMC;
import source.suprememc.blocks.plant.CropsBlockSMC;
import source.suprememc.blocks.plant.DimensionalGrassBlockSMC;
import source.suprememc.blocks.plant.FlowerBlockSMC;
import source.suprememc.blocks.plant.FungusBlockSMC;
import source.suprememc.blocks.plant.GrapeVineBlockSMC;
import source.suprememc.blocks.plant.GrapeVineTopBlockSMC;
import source.suprememc.blocks.plant.LeavesBlockSMC;
import source.suprememc.blocks.plant.RootsBlockSMC;
import source.suprememc.blocks.plant.SaplingBlockSMC;
import source.suprememc.blocks.portal.PortalBlockSMC;
import source.suprememc.blocks.redstone.RedstoneBlockSMC;
import source.suprememc.blocks.redstone.RedstoneOreBlockSMC;
import source.suprememc.blocks.redstone.RedstoneSlabBlockSMC;
import source.suprememc.blocks.redstone.RedstoneStairsBlockSMC;
import source.suprememc.blocks.redstone.RedstoneWallBlockSMC;
import source.suprememc.blocks.redstone.WirelessReceiverBlockSMC;
import source.suprememc.blocks.redstone.WirelessSenderBlockSMC;
import source.suprememc.blocks.sub.CopperBlockSMC;
import source.suprememc.blocks.sub.CopperSlabBlockSMC;
import source.suprememc.blocks.sub.CopperStairsBlockSMC;
import source.suprememc.blocks.sub.DoorBlockSMC;
import source.suprememc.blocks.sub.FenceBlockSMC;
import source.suprememc.blocks.sub.FenceGateBlockSMC;
import source.suprememc.blocks.sub.FoodBlockSMC;
import source.suprememc.blocks.sub.PressurePlateBlockSMC;
import source.suprememc.blocks.sub.SignBlockSMC;
import source.suprememc.blocks.sub.SignWallBlockSMC;
import source.suprememc.blocks.sub.SlabBlockSMC;
import source.suprememc.blocks.sub.SlimeBlockSMC;
import source.suprememc.blocks.sub.StairsBlockSMC;
import source.suprememc.blocks.sub.StoneButtonBlockSMC;
import source.suprememc.blocks.sub.TrapDoorBlockSMC;
import source.suprememc.blocks.sub.UnstrippedLogBlockSMC;
import source.suprememc.blocks.sub.WallBlockSMC;
import source.suprememc.blocks.sub.WoodButtonBlockSMC;
import source.suprememc.init.generation.SMCDimensions;
import source.suprememc.init.generation.SMCTrees;
import source.suprememc.init.objects.blockspecifics.SMCBlockColors;
import source.suprememc.init.objects.blockspecifics.SMCBlockProperties;
import source.suprememc.init.objects.blockspecifics.SMCWoodTypes;
import source.suprememc.util.SMCColors;
import source.suprememc.world.generation.trees.CherryTreeSMC;
import source.suprememc.world.generation.trees.PalmTreeSMC;

public class SMCBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	public static final Block COBBLED_ANDESITE = block("cobbled_andesite", SMCBlockColors.STONE.COBBLE);
	public static final Block COBBLED_DIORITE = block("cobbled_diorite", SMCBlockColors.STONE.COBBLE);
	public static final Block COBBLED_GRANITE = block("cobbled_granite", SMCBlockColors.STONE.COBBLE);
	public static final Block CALCITE = block("calcite", SMCBlockColors.WHITE.CALCITE);
	public static final Block DEEPSLATE = pillar("deepslate", SMCBlockColors.STONE.DEEPSLATE);
	public static final Block COBBLED_DEEPSLATE = block("cobbled_deepslate", SMCBlockColors.STONE.DEEPSLATE);
	public static final Block DEEPSLATE_TILES = block("deepslate_tiles", SMCBlockColors.STONE.DEEPSLATE);
	public static final Block POLISHED_DEEPSLATE = block("polished_deepslate", SMCBlockColors.STONE.DEEPSLATE);
	public static final Block DEEPSLATE_BRICKS = block("deepslate_bricks", SMCBlockColors.STONE.DEEPSLATE);
	public static final Block TUFF = block("tuff", SMCBlockColors.STONE.TUFF);
	public static final Block SALT_STONE = block("salt_stone", SMCBlockColors.WHITE.STONE);
	// Planks
	public static final Block CHERRY_PLANKS = block("cherry_planks", SMCBlockColors.PINK.PLANKS);
	public static final Block PALM_PLANKS = block("palm_planks", SMCBlockColors.DARK_ORANGE.PLANKS);
	public static final Block MUSTARD_PLANKS = block("mustard_planks", SMCBlockColors.YELLOW.NETHER_PLANKS);
	public static final Block LAVENDER_PLANKS = block("lavender_planks", SMCBlockColors.PURPLE.NETHER_PLANKS);
	// Upper Terrain Blocks
	public static final Block MUSTARD_NYLIUM = grass("mustard_nylium", Blocks.NETHERRACK, SMCBlockColors.YELLOW.NYLIUM);
	public static final Block LAVENDER_ENDSPAR = grass("lavender_endspar", Blocks.END_STONE, SMCBlockColors.PURPLE.END_STONE);
	public static final Block ARABLE_SAND = fallingBlock("arable_sand", SMCBlockColors.SAND.SAND);
	public static final Block COARSE_SAND = fallingBlock("coarse_sand", SMCBlockColors.SAND.SAND);
	public static final Block SALT_BLOCK = fallingBlock("salt_block", SMCBlockColors.WHITE.SAND);
	// Logs
	public static final Block CHERRY_LOG = unstrippedLog("cherry_log", SMCBlockColors.PINK.WOOD);
	public static final Block PALM_LOG = unstrippedLog("palm_log", SMCBlockColors.DARK_ORANGE.WOOD);
	public static final Block MUSTARD_STEM = unstrippedLog("mustard_stem", SMCBlockColors.YELLOW.NETHER_WOOD);
	public static final Block LAVENDER_STEM = unstrippedLog("lavender_stem", SMCBlockColors.PURPLE.NETHER_WOOD);
	// Stripped Logs
	public static final Block STRIPPED_CHERRY_LOG = pillar("stripped_cherry_log", SMCBlockColors.PINK.WOOD);
	public static final Block STRIPPED_PALM_LOG = pillar("stripped_palm_log", SMCBlockColors.DARK_ORANGE.WOOD);
	public static final Block STRIPPED_MUSTARD_STEM = pillar("stripped_mustard_stem", SMCBlockColors.YELLOW.NETHER_WOOD);
	public static final Block STRIPPED_LAVENDER_STEM = pillar("stripped_lavender_stem", SMCBlockColors.PURPLE.NETHER_WOOD);
	// Wood
	public static final Block CHERRY_WOOD = unstrippedLog("cherry_wood", SMCBlockColors.PINK.WOOD);
	public static final Block PALM_WOOD = unstrippedLog("palm_wood", SMCBlockColors.DARK_ORANGE.WOOD);
	public static final Block MUSTARD_HYPHAE = unstrippedLog("mustard_hyphae", SMCBlockColors.YELLOW.NETHER_WOOD);
	public static final Block LAVENDER_HYPHAE = unstrippedLog("lavender_hyphae", SMCBlockColors.PURPLE.NETHER_WOOD);
	// Stripped Wood
	public static final Block STRIPPED_CHERRY_WOOD = pillar("stripped_cherry_wood", SMCBlockColors.PINK.WOOD);
	public static final Block STRIPPED_PALM_WOOD = pillar("stripped_palm_wood", SMCBlockColors.DARK_ORANGE.WOOD);
	public static final Block STRIPPED_MUSTARD_HYPHAE = pillar("stripped_mustard_hyphae", SMCBlockColors.YELLOW.NETHER_WOOD);
	public static final Block STRIPPED_LAVENDER_HYPHAE = pillar("stripped_lavender_hyphae", SMCBlockColors.PURPLE.NETHER_WOOD);
	// Flowers
	public static final Block BUTTERCUP = flower("buttercup", Effects.UNLUCK, 10);
	public static final Block CLOVER = flower("clover", Effects.GLOWING, 10);
	// Saplings
	public static final Block CHERRY_SAPLING = sapling("cherry_sapling", new CherryTreeSMC());
	public static final Block PALM_SAPLING = sapling("palm_sapling", new PalmTreeSMC());
	// Leaves & Wart Blocks
	public static final Block CHERRY_LEAVES = leaves("cherry_leaves", MaterialColor.COLOR_PINK);
	public static final Block PALM_LEAVES = leaves("palm_leaves", MaterialColor.PLANT);
	public static final Block MUSTARD_WART_BLOCK = block("mustard_wart_block", SMCBlockColors.YELLOW.WART);
	public static final Block LAVENDER_WART_BLOCK = block("lavender_wart_block", SMCBlockColors.PURPLE.WART);
	// Fungi
	public static final Block MUSTARD_FUNGUS = fungus("mustard_fungus", SMCTrees.MUSTARD_FUNGI_PLANTED);
	public static final Block LAVENDER_FUNGUS = fungus("lavender_fungus", SMCTrees.LAVENDER_FUNGI_PLANTED);
	// Nether Vegatation
	public static final Block MUSTARD_ROOTS = roots("mustard_roots");
	public static final Block LAVENDER_ROOTS = roots("lavender_roots");
	// Bricks
	// Andesite
	public static final Block POLISHED_ANDESITE_BRICKS = block("polished_andesite_bricks", SMCBlockColors.STONE.STONE);
	public static final Block MOSSY_POLISHED_ANDESITE_BRICKS = block("mossy_polished_andesite_bricks", SMCBlockColors.STONE.STONE);
	public static final Block CRACKED_POLISHED_ANDESITE_BRICKS = block("cracked_polished_andesite_bricks", SMCBlockColors.STONE.STONE);
	public static final Block CHISELED_POLISHED_ANDESITE_BRICKS = block("chiseled_polished_andesite_bricks", SMCBlockColors.STONE.STONE);
	// Diorite
	public static final Block POLISHED_DIORITE_BRICKS = block("polished_diorite_bricks", SMCBlockColors.QUARTZ.STONE);
	public static final Block MOSSY_POLISHED_DIORITE_BRICKS = block("mossy_polished_diorite_bricks", SMCBlockColors.QUARTZ.STONE);
	public static final Block CRACKED_POLISHED_DIORITE_BRICKS = block("cracked_polished_diorite_bricks", SMCBlockColors.QUARTZ.STONE);
	public static final Block CHISELED_POLISHED_DIORITE_BRICKS = block("chiseled_polished_diorite_bricks", SMCBlockColors.QUARTZ.STONE);
	// Granite
	public static final Block POLISHED_GRANITE_BRICKS = block("polished_granite_bricks", SMCBlockColors.DIRT.STONE);
	public static final Block MOSSY_POLISHED_GRANITE_BRICKS = block("mossy_polished_granite_bricks", SMCBlockColors.DIRT.STONE);
	public static final Block CRACKED_POLISHED_GRANITE_BRICKS = block("cracked_polished_granite_bricks", SMCBlockColors.DIRT.STONE);
	public static final Block CHISELED_POLISHED_GRANITE_BRICKS = block("chiseled_polished_granite_bricks", SMCBlockColors.DIRT.STONE);
	// Others
	public static final Block CRACKED_QUARTZ_BRICKS = block("cracked_quartz_bricks", SMCBlockColors.QUARTZ.QUARTZ);
	public static final Block CHISELED_END_STONE_BRICKS = block("chiseled_end_stone_bricks", SMCBlockColors.SAND.END_STONE);
	public static final Block CRACKED_END_STONE_BRICKS = block("cracked_end_stone_bricks", SMCBlockColors.SAND.END_STONE);
	// Ores
	public static final Block AQUAMARINE_ORE = ore("aquamarine_ore", 2, SMCBlockColors.STONE, 3, 7);
	public static final Block COPPER_ORE = block("copper_ore", SMCBlockColors.STONE.ORE_0);
	public static final Block EXPERIENCE_ORE = ore("experience_ore", 1, SMCBlockColors.STONE, 1, 3);
	public static final Block LIGHTSTONE_ORE = ore("lightstone_ore", 1, SMCBlockColors.STONE, 1, 3);
	public static final Block QUARTZ_ORE = ore("quartz_ore", 0, SMCBlockColors.STONE, 1, 4);
	public static final Block RUBY_ORE = ore("ruby_ore", 2, SMCBlockColors.STONE, 3, 7);
	public static final Block SALT_ORE = ore("salt_ore", 0, SMCBlockColors.STONE, 0, 2);
	public static final Block SULFUR_ORE = ore("sulfur_ore", 1, SMCBlockColors.STONE, 1, 4);
	public static final Block PRISMARINE_ORE = ore("prismarine_ore", 1, SMCBlockColors.STONE, 1, 3);
	// Fossilized Wood
	public static final Block FOSSILIZED_OAK_ORE = block("fossilized_oak_ore", SMCBlockColors.STONE.ORE_0);
	public static final Block FOSSILIZED_SPRUCE_ORE = block("fossilized_spruce_ore", SMCBlockColors.STONE.ORE_0);
	public static final Block FOSSILIZED_BIRCH_ORE = block("fossilized_birch_ore", SMCBlockColors.STONE.ORE_0);
	public static final Block FOSSILIZED_JUNGLE_ORE = block("fossilized_jungle_ore", SMCBlockColors.STONE.ORE_0);
	public static final Block FOSSILIZED_ACACIA_ORE = block("fossilized_acacia_ore", SMCBlockColors.STONE.ORE_0);
	public static final Block FOSSILIZED_DARK_OAK_ORE = block("fossilized_dark_oak_ore", SMCBlockColors.STONE.ORE_0);
	public static final Block FOSSILIZED_PALM_ORE = block("fossilized_palm_ore", SMCBlockColors.STONE.ORE_0);
	public static final Block FOSSILIZED_CHERRY_ORE = block("fossilized_cherry_ore", SMCBlockColors.STONE.ORE_0);
	// Deepslate
	public static final Block DEEPSLATE_COAL_ORE = ore("deepslate_coal_ore", 0, SMCBlockColors.GRAY, 0, 2);
	public static final Block DEEPSLATE_IRON_ORE = block("deepslate_iron_ore", SMCBlockColors.GRAY.ORE_1);
	public static final Block DEEPSLATE_COPPER_ORE = block("deepslate_copper_ore", SMCBlockColors.GRAY.ORE_1);
	public static final Block DEEPSLATE_GOLD_ORE = block("deepslate_gold_ore", SMCBlockColors.GRAY.ORE_2);
	public static final Block DEEPSLATE_DIAMOND_ORE = ore("deepslate_diamond_ore", 2, SMCBlockColors.GRAY, 3, 7);
	public static final Block DEEPSLATE_EMERALD_ORE = ore("deepslate_emerald_ore", 2, SMCBlockColors.GRAY, 3, 7);
	public static final Block DEEPSLATE_REDSTONE_ORE = redstoneOre("deepslate_redstone_ore", 2, SMCBlockColors.GRAY, 1, 5);
	public static final Block DEEPSLATE_LAPIS_ORE = ore("deepslate_lapis_ore", 2, SMCBlockColors.GRAY, 2, 5);
	// Nether
	public static final Block BURNING_DIAMOND_ORE = ore("burning_diamond_ore", 2, SMCBlockColors.NETHERRACK, 3, 7);
	public static final Block NETHER_SULFUR_ORE = ore("nether_sulfur_ore", 1, SMCBlockColors.NETHERRACK, 1, 4);
	public static final Block RADIUM_ORE = block("radium_ore",SMCBlockColors.NETHERRACK.ORE_2);
	// End
	public static final Block ENDERITE_ORE = ore("enderite_ore", 1, SMCBlockColors.SAND, 1, 5);
	public static final Block TIGERS_EYE_ORE = ore("tigers_eye_ore", 2, SMCBlockColors.SAND, 4, 8);

	// Compact Blocks
	public static final Block AMETHYST_BLOCK = block("amethyst_block", SMCBlockColors.PURPLE.AMETHYST);
	public static final Block AQUAMARINE_BLOCK = block("aquamarine_block", SMCBlockColors.LIGHT_BLUE.DIAMOND);
	public static final Block BURNING_DIAMOND_BLOCK = block("burning_diamond_block", SMCBlockColors.DARK_ORANGE.DIAMOND);
	public static final Block ENDERITE_BLOCK = block("enderite_block", SMCBlockColors.PURPLE.DIAMOND);
	public static final Block EXPERIENCE_BLOCK = block("experience_block", SMCBlockColors.LIME.EXPERIENCE);
	public static final Block RADIUM_BLOCK = block("radium_block", SMCBlockColors.GREEN.DIAMOND);
	public static final Block RUBY_BLOCK = block("ruby_block", SMCBlockColors.PINK.DIAMOND);
	public static final Block TIGERS_EYE_BLOCK = block("tigers_eye_block", SMCBlockColors.BROWN.DIAMOND);
	public static final Block SMOOTH_REDSTONE_BLOCK = redstoneBlock("smooth_redstone_block", Properties.copy(Blocks.REDSTONE_BLOCK));
	public static final Block SMOOTH_IRON_BLOCK = block("smooth_iron_block", Properties.copy(Blocks.IRON_BLOCK));
	public static final Block SMOOTH_LAPIS_BLOCK = block("smooth_lapis_block", Properties.copy(Blocks.LAPIS_BLOCK));
	public static final Block SMOOTH_GOLD_BLOCK = block("smooth_gold_block", Properties.copy(Blocks.GOLD_BLOCK));
	public static final Block SMOOTH_DIAMOND_BLOCK = block("smooth_diamond_block", Properties.copy(Blocks.DIAMOND_BLOCK));
	public static final Block SMOOTH_EMERALD_BLOCK = block("smooth_emerald_block", Properties.copy(Blocks.EMERALD_BLOCK));
	public static final Block SMOOTH_NETHERITE_BLOCK = block("smooth_netherite_block", Properties.copy(Blocks.NETHERITE_BLOCK));
	public static final Block SMOOTH_AQUAMARINE_BLOCK = block("smooth_aquamarine_block", SMCBlockColors.LIGHT_BLUE.DIAMOND);
	public static final Block SMOOTH_BURNING_DIAMOND_BLOCK = block("smooth_burning_diamond_block", SMCBlockColors.DARK_ORANGE.DIAMOND);
	public static final Block SMOOTH_ENDERITE_BLOCK = block("smooth_enderite_block", SMCBlockColors.PURPLE.DIAMOND);
	public static final Block SMOOTH_EXPERIENCE_BLOCK = block("smooth_experience_block", SMCBlockColors.LIME.EXPERIENCE);
	public static final Block SMOOTH_RADIUM_BLOCK = block("smooth_radium_block", SMCBlockColors.GREEN.DIAMOND);
	public static final Block SMOOTH_RUBY_BLOCK = block("smooth_ruby_block", SMCBlockColors.PINK.DIAMOND);
	public static final Block SMOOTH_TIGERS_EYE_BLOCK = block("smooth_tigers_eye_block", SMCBlockColors.BROWN.DIAMOND);
	public static final Block RAW_COPPER_BLOCK = block("raw_copper_block", SMCBlockColors.DARK_ORANGE.COPPER);
	public static final Block RAW_IRON_BLOCK = block("raw_iron_block", SMCBlockColors.GRAY.COPPER);
	public static final Block RAW_GOLD_BLOCK = block("raw_gold_block", SMCBlockColors.YELLOW.DIAMOND);
	public static final Block RAW_RADIUM_BLOCK = block("raw_radium_block", SMCBlockColors.GREEN.DIAMOND);


	// Copper
	public static final Block COPPER_BLOCK = copper("copper_block", SMCBlockColors.DIRT.COPPER);
	public static final Block EXPOSED_COPPER = copper("exposed_copper", SMCBlockColors.DIRT.COPPER);
	public static final Block WEATHERED_COPPER = copper("weathered_copper", SMCBlockColors.GREEN.COPPER);
	public static final Block OXIDIZED_COPPER = copper("oxidized_copper", SMCBlockColors.GREEN.COPPER);
	public static final Block CUT_COPPER = copper("cut_copper", SMCBlockColors.DIRT.COPPER);
	public static final Block EXPOSED_CUT_COPPER = copper("exposed_cut_copper", SMCBlockColors.DIRT.COPPER);
	public static final Block WEATHERED_CUT_COPPER = copper("weathered_cut_copper", SMCBlockColors.GREEN.COPPER);
	public static final Block OXIDIZED_CUT_COPPER = copper("oxidized_cut_copper", SMCBlockColors.GREEN.COPPER);
	public static final Block WAXED_COPPER_BLOCK = copper("waxed_copper_block", SMCBlockColors.DIRT.COPPER);
	public static final Block WAXED_EXPOSED_COPPER_BLOCK = copper("waxed_exposed_copper", SMCBlockColors.DIRT.COPPER);
	public static final Block WAXED_WEATHERED_COPPER_BLOCK = copper("waxed_weathered_copper", SMCBlockColors.GREEN.COPPER);
	public static final Block WAXED_OXIDIZED_COPPER_BLOCK = copper("waxed_oxidized_copper", SMCBlockColors.GREEN.COPPER);
	public static final Block WAXED_CUT_COPPER = copper("waxed_cut_copper", SMCBlockColors.DIRT.COPPER);
	public static final Block WAXED_EXPOSED_CUT_COPPER = copper("waxed_exposed_cut_copper", SMCBlockColors.DIRT.COPPER);
	public static final Block WAXED_WEATHERED_CUT_COPPER = copper("waxed_weathered_cut_copper", SMCBlockColors.GREEN.COPPER);
	public static final Block WAXED_OXIDIZED_CUT_COPPER = copper("waxed_oxidized_cut_copper", SMCBlockColors.GREEN.COPPER);

	public static final Block GLENDSTONE = block("glendstone", SMCBlockColors.PURPLE.GLOWSTONE);
	public static final Block LIGHTSTONE = block("lightstone", SMCBlockColors.WHITE.GLOWSTONE);
	public static final Block GLOWING_OBSIDIAN = block("glowing_obsidian", SMCBlockColors.MAGENTA.GLOWING_OBSIDIAN);

	// Bookshelves
	public static final Block SPRUCE_BOOKSHELF = bookshelf("spruce_bookshelf", SMCBlockColors.OBSIDIAN.BOOKSHELF);
	public static final Block BIRCH_BOOKSHELF = bookshelf("birch_bookshelf", SMCBlockColors.SAND.BOOKSHELF);
	public static final Block JUNGLE_BOOKSHELF = bookshelf("jungle_bookshelf", SMCBlockColors.DIRT.BOOKSHELF);
	public static final Block ACACIA_BOOKSHELF = bookshelf("acacia_bookshelf", SMCBlockColors.ORANGE.BOOKSHELF);
	public static final Block DARK_OAK_BOOKSHELF = bookshelf("dark_oak_bookshelf", SMCBlockColors.BROWN.BOOKSHELF);
	public static final Block CRIMSON_BOOKSHELF = bookshelf("crimson_bookshelf", SMCBlockColors.CRIMSON.BOOKSHELF);
	public static final Block WARPED_BOOKSHELF = bookshelf("warped_bookshelf", SMCBlockColors.WARPED.BOOKSHELF);
	public static final Block CHERRY_BOOKSHELF = bookshelf("cherry_bookshelf", SMCBlockColors.PINK.BOOKSHELF);
	public static final Block PALM_BOOKSHELF = bookshelf("palm_bookshelf", SMCBlockColors.DARK_ORANGE.BOOKSHELF);
	public static final Block MUSTARD_BOOKSHELF = bookshelf("mustard_bookshelf", SMCBlockColors.YELLOW.BOOKSHELF);
	public static final Block LAVENDER_BOOKSHELF = bookshelf("lavender_bookshelf", SMCBlockColors.PURPLE.BOOKSHELF);

	// Glowblocks
	public static final Block WHITE_GLOWBLOCK = block("white_glowblock", SMCBlockColors.WHITE.LIGHT);
	public static final Block ORANGE_GLOWBLOCK = block("orange_glowblock", SMCBlockColors.ORANGE.LIGHT);
	public static final Block MAGENTA_GLOWBLOCK = block("magenta_glowblock", SMCBlockColors.MAGENTA.LIGHT);
	public static final Block LIGHT_BLUE_GLOWBLOCK = block("light_blue_glowblock", SMCBlockColors.LIGHT_BLUE.LIGHT);
	public static final Block YELLOW_GLOWBLOCK = block("yellow_glowblock", SMCBlockColors.YELLOW.LIGHT);
	public static final Block LIME_GLOWBLOCK = block("lime_glowblock", SMCBlockColors.LIME.LIGHT);
	public static final Block PINK_GLOWBLOCK = block("pink_glowblock", SMCBlockColors.PINK.LIGHT);
	public static final Block GRAY_GLOWBLOCK = block("gray_glowblock", SMCBlockColors.GRAY.LIGHT);
	public static final Block LIGHT_GRAY_GLOWBLOCK = block("light_gray_glowblock", SMCBlockColors.LIGHT_GRAY.LIGHT);
	public static final Block CYAN_GLOWBLOCK = block("cyan_glowblock", SMCBlockColors.CYAN.LIGHT);
	public static final Block PURPLE_GLOWBLOCK = block("purple_glowblock", SMCBlockColors.PURPLE.LIGHT);
	public static final Block BLUE_GLOWBLOCK = block("blue_glowblock", SMCBlockColors.BLUE.LIGHT);
	public static final Block BROWN_GLOWBLOCK = block("brown_glowblock", SMCBlockColors.BROWN.LIGHT);
	public static final Block GREEN_GLOWBLOCK = block("green_glowblock", SMCBlockColors.GREEN.LIGHT);
	public static final Block RED_GLOWBLOCK = block("red_glowblock", SMCBlockColors.RED.LIGHT);
	public static final Block BLACK_GLOWBLOCK = block("black_glowblock", SMCBlockColors.BLACK.LIGHT);
	// Slime Blocks
	public static final Block WHITE_SLIME_BLOCK = slime(SMCColors.WHITE);
	public static final Block ORANGE_SLIME_BLOCK = slime(SMCColors.ORANGE);
	public static final Block MAGENTA_SLIME_BLOCK = slime(SMCColors.MAGENTA);
	public static final Block LIGHT_BLUE_SLIME_BLOCK = slime(SMCColors.LIGHT_BLUE);
	public static final Block YELLOW_SLIME_BLOCK = slime(SMCColors.YELLOW);
	public static final Block LIME_SLIME_BLOCK = slime(SMCColors.LIME);
	public static final Block PINK_SLIME_BLOCK = slime(SMCColors.PINK);
	public static final Block GRAY_SLIME_BLOCK = slime(SMCColors.GRAY);
	public static final Block LIGHT_GRAY_SLIME_BLOCK = slime(SMCColors.LIGHT_GRAY);
	public static final Block CYAN_SLIME_BLOCK = slime(SMCColors.CYAN);
	public static final Block PURPLE_SLIME_BLOCK = slime(SMCColors.PURPLE);
	public static final Block BLUE_SLIME_BLOCK = slime(SMCColors.BLUE);
	public static final Block BROWN_SLIME_BLOCK = slime(SMCColors.BROWN);
	public static final Block GREEN_SLIME_BLOCK = slime(SMCColors.GREEN);
	public static final Block RED_SLIME_BLOCK = slime(SMCColors.RED);
	public static final Block BLACK_SLIME_BLOCK = slime(SMCColors.BLACK);
	// Wireless Senders
	public static final Block WHITE_WIRELESS_SENDER = sender(SMCColors.WHITE);
	public static final Block ORANGE_WIRELESS_SENDER = sender(SMCColors.ORANGE);
	public static final Block MAGENTA_WIRELESS_SENDER = sender(SMCColors.MAGENTA);
	public static final Block LIGHT_BLUE_WIRELESS_SENDER = sender(SMCColors.LIGHT_BLUE);
	public static final Block YELLOW_WIRELESS_SENDER = sender(SMCColors.YELLOW);
	public static final Block LIME_WIRELESS_SENDER = sender(SMCColors.LIME);
	public static final Block PINK_WIRELESS_SENDER = sender(SMCColors.PINK);
	public static final Block GRAY_WIRELESS_SENDER = sender(SMCColors.GRAY);
	public static final Block LIGHT_GRAY_WIRELESS_SENDER = sender(SMCColors.LIGHT_GRAY);
	public static final Block CYAN_WIRELESS_SENDER = sender(SMCColors.CYAN);
	public static final Block PURPLE_WIRELESS_SENDER = sender(SMCColors.PURPLE);
	public static final Block BLUE_WIRELESS_SENDER = sender(SMCColors.BLUE);
	public static final Block BROWN_WIRELESS_SENDER = sender(SMCColors.BROWN);
	public static final Block GREEN_WIRELESS_SENDER = sender(SMCColors.GREEN);
	public static final Block RED_WIRELESS_SENDER = sender(SMCColors.RED);
	public static final Block BLACK_WIRELESS_SENDER = sender(SMCColors.BLACK);
	// Wireless Receivers
	public static final Block WHITE_WIRELESS_RECEIVER = receiver(SMCColors.WHITE, WHITE_WIRELESS_SENDER);
	public static final Block ORANGE_WIRELESS_RECEIVER = receiver(SMCColors.ORANGE, ORANGE_WIRELESS_SENDER);
	public static final Block MAGENTA_WIRELESS_RECEIVER = receiver(SMCColors.MAGENTA, MAGENTA_WIRELESS_SENDER);
	public static final Block LIGHT_BLUE_WIRELESS_RECEIVER = receiver(SMCColors.LIGHT_BLUE, LIGHT_BLUE_WIRELESS_SENDER);
	public static final Block YELLOW_WIRELESS_RECEIVER = receiver(SMCColors.YELLOW, YELLOW_WIRELESS_SENDER);
	public static final Block LIME_WIRELESS_RECEIVER = receiver(SMCColors.LIME, LIME_WIRELESS_SENDER);
	public static final Block PINK_WIRELESS_RECEIVER = receiver(SMCColors.PINK, PINK_WIRELESS_SENDER);
	public static final Block GRAY_WIRELESS_RECEIVER = receiver(SMCColors.GRAY, GRAY_WIRELESS_SENDER);
	public static final Block LIGHT_GRAY_WIRELESS_RECEIVER = receiver(SMCColors.LIGHT_GRAY, LIGHT_GRAY_WIRELESS_SENDER);
	public static final Block CYAN_WIRELESS_RECEIVER = receiver(SMCColors.CYAN, CYAN_WIRELESS_SENDER);
	public static final Block PURPLE_WIRELESS_RECEIVER = receiver(SMCColors.PURPLE, PURPLE_WIRELESS_SENDER);
	public static final Block BLUE_WIRELESS_RECEIVER = receiver(SMCColors.BLUE, BLUE_WIRELESS_SENDER);
	public static final Block BROWN_WIRELESS_RECEIVER = receiver(SMCColors.BROWN, BROWN_WIRELESS_SENDER);
	public static final Block GREEN_WIRELESS_RECEIVER = receiver(SMCColors.GREEN, GREEN_WIRELESS_SENDER);
	public static final Block RED_WIRELESS_RECEIVER = receiver(SMCColors.RED, RED_WIRELESS_SENDER);
	public static final Block BLACK_WIRELESS_RECEIVER = receiver(SMCColors.BLACK, BLACK_WIRELESS_SENDER);
	// Stairs
	// Wood
	public static final Block CHERRY_STAIRS = stairs(CHERRY_PLANKS);
	public static final Block PALM_STAIRS = stairs(PALM_PLANKS);
	public static final Block MUSTARD_STAIRS = stairs(MUSTARD_PLANKS);
	public static final Block LAVENDER_STAIRS = stairs(LAVENDER_PLANKS);
	// Stone
	public static final Block COBBLED_ANDESITE_STAIRS = stairs(COBBLED_ANDESITE);
	public static final Block COBBLED_DIORITE_STAIRS = stairs(COBBLED_DIORITE);
	public static final Block COBBLED_GRANITE_STAIRS = stairs(COBBLED_GRANITE);
	public static final Block COBBLED_DEEPSLATE_STAIRS = stairs(COBBLED_DEEPSLATE);
	public static final Block POLISHED_DEEPSLATE_STAIRS = stairs(POLISHED_DEEPSLATE);
	public static final Block POLISHED_ANDESITE_BRICK_STAIRS = stairs(POLISHED_ANDESITE_BRICKS);
	public static final Block POLISHED_DIORITE_BRICK_STAIRS = stairs(POLISHED_DIORITE_BRICKS);
	public static final Block POLISHED_GRANITE_BRICK_STAIRS = stairs(POLISHED_GRANITE_BRICKS);
	public static final Block DEEPSLATE_BRICK_STAIRS = stairs(DEEPSLATE_BRICKS);
	public static final Block MOSSY_POLISHED_ANDESITE_BRICK_STAIRS = stairs(MOSSY_POLISHED_ANDESITE_BRICKS);
	public static final Block MOSSY_POLISHED_DIORITE_BRICK_STAIRS = stairs(MOSSY_POLISHED_DIORITE_BRICKS);
	public static final Block MOSSY_POLISHED_GRANITE_BRICK_STAIRS = stairs(MOSSY_POLISHED_GRANITE_BRICKS);
	public static final Block DEEPSLATE_TILE_STAIRS = stairs(DEEPSLATE_TILES);

	// Vanilla
	public static final Block COAL_STAIRS = stairs("coal_stairs", Blocks.COAL_BLOCK);
	public static final Block REDSTONE_STAIRS = redstoneStairs("redstone_stairs", Blocks.REDSTONE_BLOCK);
	public static final Block IRON_STAIRS = stairs("iron_stairs", Blocks.IRON_BLOCK);
	public static final Block LAPIS_STAIRS = stairs("lapis_stairs", Blocks.LAPIS_BLOCK);
	public static final Block GOLD_STAIRS = stairs("gold_stairs", Blocks.GOLD_BLOCK);
	public static final Block DIAMOND_STAIRS = stairs("diamond_stairs", Blocks.DIAMOND_BLOCK);
	public static final Block EMERALD_STAIRS = stairs("emerald_stairs", Blocks.EMERALD_BLOCK);
	public static final Block NETHERITE_STAIRS = stairs("netherite_stairs", Blocks.NETHERITE_BLOCK);
	public static final Block AQUAMARINE_STAIRS = stairs(AQUAMARINE_BLOCK);
	public static final Block BURNING_DIAMOND_STAIRS = stairs(BURNING_DIAMOND_BLOCK);
	public static final Block ENDERITE_STAIRS = stairs(ENDERITE_BLOCK);
	public static final Block EXPERIENCE_STAIRS = stairs(EXPERIENCE_BLOCK);
	public static final Block RADIUM_STAIRS = stairs(RADIUM_BLOCK);
	public static final Block RUBY_STAIRS = stairs(RUBY_BLOCK);
	public static final Block TIGERS_EYE_STAIRS = stairs(TIGERS_EYE_BLOCK);
	public static final Block SMOOTH_REDSTONE_STAIRS = redstoneStairs(SMOOTH_REDSTONE_BLOCK);
	public static final Block SMOOTH_IRON_STAIRS = stairs(SMOOTH_IRON_BLOCK);
	public static final Block SMOOTH_LAPIS_STAIRS = stairs(SMOOTH_LAPIS_BLOCK);
	public static final Block SMOOTH_GOLD_STAIRS = stairs(SMOOTH_GOLD_BLOCK);
	public static final Block SMOOTH_DIAMOND_STAIRS = stairs(SMOOTH_DIAMOND_BLOCK);
	public static final Block SMOOTH_EMERALD_STAIRS = stairs(SMOOTH_EMERALD_BLOCK);
	public static final Block SMOOTH_NETHERITE_STAIRS = stairs(SMOOTH_NETHERITE_BLOCK);
	public static final Block SMOOTH_AQUAMARINE_STAIRS = stairs(SMOOTH_AQUAMARINE_BLOCK);
	public static final Block SMOOTH_BURNING_DIAMOND_STAIRS = stairs(SMOOTH_BURNING_DIAMOND_BLOCK);
	public static final Block SMOOTH_ENDERITE_STAIRS = stairs(SMOOTH_ENDERITE_BLOCK);
	public static final Block SMOOTH_EXPERIENCE_STAIRS = stairs(SMOOTH_EXPERIENCE_BLOCK);
	public static final Block SMOOTH_RADIUM_STAIRS = stairs(SMOOTH_RADIUM_BLOCK);
	public static final Block SMOOTH_RUBY_STAIRS = stairs(SMOOTH_RUBY_BLOCK);
	public static final Block SMOOTH_TIGERS_EYE_STAIRS = stairs(SMOOTH_TIGERS_EYE_BLOCK);
	public static final Block OBSIDIAN_STAIRS = stairs("obsidian_stairs", Blocks.OBSIDIAN);
	public static final Block END_STONE_STAIRS = stairs("end_stone_stairs", Blocks.END_STONE);

	// Metal
	public static final Block CUT_COPPER_STAIRS = copperStairs(CUT_COPPER);
	public static final Block EXPOSED_CUT_COPPER_STAIRS = copperStairs(EXPOSED_CUT_COPPER);
	public static final Block WEATHERED_CUT_COPPER_STAIRS = copperStairs(WEATHERED_CUT_COPPER);
	public static final Block OXIDIZED_CUT_COPPER_STAIRS = copperStairs(OXIDIZED_CUT_COPPER);
	public static final Block WAXED_CUT_COPPER_STAIRS = copperStairs(WAXED_CUT_COPPER);
	public static final Block WAXED_EXPOSED_CUT_COPPER_STAIRS = copperStairs(WAXED_EXPOSED_CUT_COPPER);
	public static final Block WAXED_WEATHERED_CUT_COPPER_STAIRS = copperStairs(WAXED_WEATHERED_CUT_COPPER);
	public static final Block WAXED_OXIDIZED_CUT_COPPER_STAIRS = copperStairs(WAXED_OXIDIZED_CUT_COPPER);

	// Slabs
	// Wood
	public static final Block CHERRY_SLAB = slab(CHERRY_PLANKS);
	public static final Block PALM_SLAB = slab(PALM_PLANKS);
	public static final Block MUSTARD_SLAB = slab(MUSTARD_PLANKS);
	public static final Block LAVENDER_SLAB = slab(LAVENDER_PLANKS);
	// Stone
	public static final Block COBBLED_ANDESITE_SLAB = slab(COBBLED_ANDESITE);
	public static final Block COBBLED_DIORITE_SLAB = slab(COBBLED_DIORITE);
	public static final Block COBBLED_GRANITE_SLAB = slab(COBBLED_GRANITE);
	public static final Block COBBLED_DEEPSLATE_SLAB = slab(COBBLED_DEEPSLATE);
	public static final Block POLISHED_DEEPSLATE_SLAB = slab(POLISHED_DEEPSLATE);
	public static final Block POLISHED_ANDESITE_BRICK_SLAB = slab(POLISHED_ANDESITE_BRICKS);
	public static final Block POLISHED_DIORITE_BRICK_SLAB = slab(POLISHED_DIORITE_BRICKS);
	public static final Block POLISHED_GRANITE_BRICK_SLAB = slab(POLISHED_GRANITE_BRICKS);
	public static final Block DEEPSLATE_BRICK_SLAB = slab(DEEPSLATE_BRICKS);
	public static final Block MOSSY_ANDESITE_BRICK_SLAB = slab(MOSSY_POLISHED_ANDESITE_BRICKS);
	public static final Block MOSSY_DIORITE_BRICK_SLAB = slab(MOSSY_POLISHED_DIORITE_BRICKS);
	public static final Block MOSSY_GRANITE_BRICK_SLAB = slab(MOSSY_POLISHED_GRANITE_BRICKS);
	public static final Block DEEPSLATE_TILE_SLAB = slab(DEEPSLATE_TILES);

	// Vanilla
	public static final Block COAL_SLAB = slab("coal_slab", Blocks.COAL_BLOCK);
	public static final Block REDSTONE_SLAB = redstoneSlab("redstone_slab", Blocks.REDSTONE_BLOCK);
	public static final Block IRON_SLAB = slab("iron_slab", Blocks.IRON_BLOCK);
	public static final Block LAPIS_SLAB = slab("lapis_slab", Blocks.LAPIS_BLOCK);
	public static final Block GOLD_SLAB = slab("gold_slab", Blocks.GOLD_BLOCK);
	public static final Block DIAMOND_SLAB = slab("diamond_slab", Blocks.DIAMOND_BLOCK);
	public static final Block EMERALD_SLAB = slab("emerald_slab", Blocks.EMERALD_BLOCK);
	public static final Block NETHERITE_SLAB = slab("netherite_slab", Blocks.NETHERITE_BLOCK);
	public static final Block AQUAMARINE_SLAB = slab(AQUAMARINE_BLOCK);
	public static final Block BURNING_DIAMOND_SLAB = slab(BURNING_DIAMOND_BLOCK);
	public static final Block ENDERITE_SLAB = slab(ENDERITE_BLOCK);
	public static final Block EXPERIENCE_SLAB = slab(EXPERIENCE_BLOCK);
	public static final Block RADIUM_SLAB = slab(RADIUM_BLOCK);
	public static final Block RUBY_SLAB = slab(RUBY_BLOCK);
	public static final Block SMOOTH_REDSTONE_SLAB = redstoneSlab(SMOOTH_REDSTONE_BLOCK);
	public static final Block TIGERS_EYE_SLAB = slab(TIGERS_EYE_BLOCK);
	public static final Block SMOOTH_IRON_SLAB = slab(SMOOTH_IRON_BLOCK);
	public static final Block SMOOTH_LAPIS_SLAB = slab(SMOOTH_LAPIS_BLOCK);
	public static final Block SMOOTH_GOLD_SLAB = slab(SMOOTH_GOLD_BLOCK);
	public static final Block SMOOTH_DIAMOND_SLAB = slab(SMOOTH_DIAMOND_BLOCK);
	public static final Block SMOOTH_EMERALD_SLAB = slab(SMOOTH_EMERALD_BLOCK);
	public static final Block SMOOTH_NETHERITE_SLAB = slab(SMOOTH_NETHERITE_BLOCK);
	public static final Block SMOOTH_AQUAMARINE_SLAB = slab(SMOOTH_AQUAMARINE_BLOCK);
	public static final Block SMOOTH_BURNING_DIAMOND_SLAB = slab(SMOOTH_BURNING_DIAMOND_BLOCK);
	public static final Block SMOOTH_ENDERITE_SLAB = slab(SMOOTH_ENDERITE_BLOCK);
	public static final Block SMOOTH_EXPERIENCE_SLAB = slab(SMOOTH_EXPERIENCE_BLOCK);
	public static final Block SMOOTH_RADIUM_SLAB = slab(SMOOTH_RADIUM_BLOCK);
	public static final Block SMOOTH_RUBY_SLAB = slab(SMOOTH_RUBY_BLOCK);
	public static final Block SMOOTH_TIGERS_EYE_SLAB = slab(SMOOTH_TIGERS_EYE_BLOCK);
	public static final Block OBSIDIAN_SLAB = slab("obsidian_slab", Blocks.OBSIDIAN);
	public static final Block END_STONE_SLAB = slab("end_stone_slab", Blocks.END_STONE);
	// Metal
	public static final Block CUT_COPPER_SLAB = copperSlab(CUT_COPPER);
	public static final Block EXPOSED_CUT_COPPER_SLAB = copperSlab(EXPOSED_CUT_COPPER);
	public static final Block WEATHERED_CUT_COPPER_SLAB = copperSlab(WEATHERED_CUT_COPPER);
	public static final Block OXIDIZED_CUT_COPPER_SLAB = copperSlab(OXIDIZED_CUT_COPPER);
	public static final Block WAXED_CUT_COPPER_SLAB = copperSlab(WAXED_CUT_COPPER);
	public static final Block WAXED_EXPOSED_CUT_COPPER_SLAB = copperSlab(WAXED_EXPOSED_CUT_COPPER);
	public static final Block WAXED_WEATHERED_CUT_COPPER_SLAB = copperSlab(WAXED_WEATHERED_CUT_COPPER);
	public static final Block WAXED_OXIDIZED_CUT_COPPER_SLAB = copperSlab(WAXED_OXIDIZED_CUT_COPPER);

	// Walls
	public static final Block STONE_WALL = wall("stone_wall", Blocks.STONE);

	public static final Block COBBLED_ANDESITE_WALL = wall(COBBLED_ANDESITE);
	public static final Block COBBLED_DIORITE_WALL = wall(COBBLED_DIORITE);
	public static final Block COBBLED_GRANITE_WALL = wall(COBBLED_GRANITE);
	public static final Block COBBLED_DEEPSLATE_WALL = wall(COBBLED_DEEPSLATE);
	public static final Block POLISHED_ANDESITE_WALL = wall("polished_andesite_wall", Blocks.POLISHED_ANDESITE);
	public static final Block POLISHED_DIORITE_WALL = wall("polished_diorite_wall", Blocks.POLISHED_DIORITE);
	public static final Block POLISHED_GRANITE_WALL = wall("polished_granite_wall", Blocks.POLISHED_GRANITE);
	public static final Block POLISHED_DEEPSLATE_WALL = wall(POLISHED_DEEPSLATE);
	public static final Block POLISHED_ANDESITE_BRICK_WALL = wall(POLISHED_ANDESITE_BRICKS);
	public static final Block POLISHED_DIORITE_BRICK_WALL = wall(POLISHED_DIORITE_BRICKS);
	public static final Block POLISHED_GRANITE_BRICK_WALL = wall(POLISHED_GRANITE_BRICKS);
	public static final Block DEEPSLATE_BRICK_WALL = wall(DEEPSLATE_BRICKS);
	public static final Block MOSSY_POLISHED_ANDESITE_BRICK_WALL = wall(MOSSY_POLISHED_ANDESITE_BRICKS);
	public static final Block MOSSY_POLISHED_DIORITE_BRICK_WALL = wall(MOSSY_POLISHED_DIORITE_BRICKS);
	public static final Block MOSSY_POLISHED_GRANITE_BRICK_WALL = wall(MOSSY_POLISHED_GRANITE_BRICKS);
	public static final Block DEEPSLATE_TILE_WALL = wall(DEEPSLATE_TILES);

	public static final Block COAL_WALL = wall("coal_wall", Blocks.COAL_BLOCK);
	public static final Block REDSTONE_WALL = redstoneWall("redstone_wall", Blocks.REDSTONE_BLOCK);
	public static final Block IRON_WALL = wall("iron_wall", Blocks.IRON_BLOCK);
	public static final Block LAPIS_WALL = wall("lapis_wall", Blocks.LAPIS_BLOCK);
	public static final Block GOLD_WALL = wall("gold_wall", Blocks.GOLD_BLOCK);
	public static final Block DIAMOND_WALL = wall("diamond_wall", Blocks.DIAMOND_BLOCK);
	public static final Block EMERALD_WALL = wall("emerald_wall", Blocks.EMERALD_BLOCK);
	public static final Block NETHERITE_WALL = wall("netherite_wall", Blocks.NETHERITE_BLOCK);
	public static final Block AQUAMARINE_WALL = wall(AQUAMARINE_BLOCK);
	public static final Block BURNING_DIAMOND_WALL = wall(BURNING_DIAMOND_BLOCK);
	public static final Block ENDERITE_WALL = wall(ENDERITE_BLOCK);
	public static final Block EXPERIENCE_WALL = wall(EXPERIENCE_BLOCK);
	public static final Block RADIUM_WALL = wall(RADIUM_BLOCK);
	public static final Block RUBY_WALL = wall(RUBY_BLOCK);
	public static final Block SMOOTH_RUBY_WALL = wall(SMOOTH_RUBY_BLOCK);
	public static final Block TIGERS_EYE_WALL = wall(TIGERS_EYE_BLOCK);
	public static final Block SMOOTH_REDSTONE_WALL = redstoneWall(SMOOTH_REDSTONE_BLOCK);
	public static final Block SMOOTH_IRON_WALL = wall(SMOOTH_IRON_BLOCK);
	public static final Block SMOOTH_LAPIS_WALL = wall(SMOOTH_LAPIS_BLOCK);
	public static final Block SMOOTH_GOLD_WALL = wall(SMOOTH_GOLD_BLOCK);
	public static final Block SMOOTH_DIAMOND_WALL = wall(SMOOTH_DIAMOND_BLOCK);
	public static final Block SMOOTH_EMERALD_WALL = wall(SMOOTH_EMERALD_BLOCK);
	public static final Block SMOOTH_NETHERITE_WALL = wall(SMOOTH_NETHERITE_BLOCK);
	public static final Block SMOOTH_AQUAMARINE_WALL = wall(SMOOTH_AQUAMARINE_BLOCK);
	public static final Block SMOOTH_BURNING_DIAMOND_WALL = wall(SMOOTH_BURNING_DIAMOND_BLOCK);
	public static final Block SMOOTH_ENDERITE_WALL = wall(SMOOTH_ENDERITE_BLOCK);
	public static final Block SMOOTH_EXPERIENCE_WALL = wall(SMOOTH_EXPERIENCE_BLOCK);
	
	public static final Block SMOOTH_RADIUM_WALL = wall(SMOOTH_RADIUM_BLOCK);
	public static final Block SMOOTH_TIGERS_EYE_WALL = wall(SMOOTH_TIGERS_EYE_BLOCK);
	public static final Block OBSIDIAN_WALL = wall("obsidian_wall", Blocks.OBSIDIAN);
	public static final Block END_STONE_WALL = wall("end_stone_wall", Blocks.END_STONE);
	// Pressure Plates
	public static final Block CHERRY_PRESSURE_PLATE = pressurePlate(CHERRY_PLANKS);
	public static final Block PALM_PRESSURE_PLATE = pressurePlate(PALM_PLANKS);
	public static final Block MUSTARD_PRESSURE_PLATE = pressurePlate(MUSTARD_PLANKS);
	public static final Block LAVENDER_PRESSURE_PLATE = pressurePlate(LAVENDER_PLANKS);
	public static final Block POLISHED_ANDESITE_PRESSURE_PLATE = pressurePlate("polished_andesite_pressure_plate", Blocks.POLISHED_ANDESITE);
	public static final Block POLISHED_DIORITE_PRESSURE_PLATE = pressurePlate("polished_diorite_pressure_plate", Blocks.POLISHED_DIORITE);
	public static final Block POLISHED_GRANITE_PRESSURE_PLATE = pressurePlate("polished_granite_pressure_plate", Blocks.POLISHED_GRANITE);
	// Trapdoors
	public static final Block CHERRY_TRAPDOOR = trapdoor(CHERRY_PLANKS);
	public static final Block PALM_TRAPDOOR = trapdoor(PALM_PLANKS);
	public static final Block MUSTARD_TRAPDOOR = trapdoor(MUSTARD_PLANKS);
	public static final Block LAVENDER_TRAPDOOR = trapdoor(LAVENDER_PLANKS);
	// Fences
	public static final Block CHERRY_FENCE = fence(CHERRY_PLANKS);
	public static final Block PALM_FENCE = fence(PALM_PLANKS);
	public static final Block MUSTARD_FENCE = fence(MUSTARD_PLANKS);
	public static final Block LAVENDER_FENCE = fence(LAVENDER_PLANKS);
	// Fence Gates
	public static final Block CHERRY_FENCE_GATE = fenceGate(CHERRY_PLANKS);
	public static final Block PALM_FENCE_GATE = fenceGate(PALM_PLANKS);
	public static final Block MUSTARD_FENCE_GATE = fenceGate(MUSTARD_PLANKS);
	public static final Block LAVENDER_FENCE_GATE = fenceGate(LAVENDER_PLANKS);
	public static final Block NETHER_BRICK_FENCE_GATE = fenceGate("nether_brick_fence_gate", Blocks.NETHER_BRICKS);
	// Buttons
	public static final Block POLISHED_ANDESITE_BUTTON = button("polished_andesite_button", false);
	public static final Block POLISHED_DIORITE_BUTTON = button("polished_diorite_button", false);
	public static final Block POLISHED_GRANITE_BUTTON = button("polished_granite_button", false);
	public static final Block CHERRY_BUTTON = button("cherry_button", true);
	public static final Block PALM_BUTTON = button("palm_button", true);
	public static final Block MUSTARD_BUTTON = button("mustard_button", true);
	public static final Block LAVENDER_BUTTON = button("lavender_button", true);
	// Doors
	public static final Block CHERRY_DOOR = door(CHERRY_PLANKS);
	public static final Block PALM_DOOR = door(PALM_PLANKS);
	public static final Block MUSTARD_DOOR = door(MUSTARD_PLANKS);
	public static final Block LAVENDER_DOOR = door(LAVENDER_PLANKS);
	// Signs
	public static final Block CHERRY_SIGN = sign(SMCWoodTypes.CHERRY, "cherry_sign");
	public static final Block PALM_SIGN = sign(SMCWoodTypes.PALM, "palm_sign");
	public static final Block MUSTARD_SIGN = sign(SMCWoodTypes.MUSTARD, "mustard_sign");
	public static final Block LAVENDER_SIGN = sign(SMCWoodTypes.LAVENDER, "lavender_sign");
	// Utility
	public static final Block SPRUCE_CRAFTING_TABLE = craftingTable("spruce_crafting_table");
	public static final Block BIRCH_CRAFTING_TABLE = craftingTable("birch_crafting_table");
	public static final Block JUNGLE_CRAFTING_TABLE = craftingTable("jungle_crafting_table");
	public static final Block ACACIA_CRAFTING_TABLE = craftingTable("acacia_crafting_table");
	public static final Block DARK_OAK_CRAFTING_TABLE = craftingTable("dark_oak_crafting_table");
	public static final Block CHERRY_CRAFTING_TABLE = craftingTable("cherry_crafting_table");
	public static final Block PALM_CRAFTING_TABLE = craftingTable("palm_crafting_table");
	public static final Block CRIMSON_CRAFTING_TABLE = craftingTable("crimson_crafting_table");
	public static final Block WARPED_CRAFTING_TABLE = craftingTable("warped_crafting_table");
	public static final Block MUSTARD_CRAFTING_TABLE = craftingTable("mustard_crafting_table");
	public static final Block LAVENDER_CRAFTING_TABLE = craftingTable("lavender_crafting_table");

	public static final Block ANDESITE_FURNACE = furnace("andesite_furnace");
	public static final Block DIORITE_FURNACE = furnace("diorite_furnace");
	public static final Block GRANITE_FURNACE = furnace("granite_furnace");
	public static final Block BLACKSTONE_FURNACE = furnace("blackstone_furnace");
	public static final Block DEEPSLATE_FURNACE = furnace("deepslate_furnace");

	public static final Block FREEZER = new FreezerBlockSMC("freezer");
	public static final Block PHONOGRAPH = new PhonographBlockSMC("phonograph");
	public static final Block SOUL_BREWING_STAND = new SoulBrewingStandSMC("soul_brewing_stand");
	// Food Blocks
	public static final Block CHOCOLATE_CAKE = food("chocolate_cake", 3, 0.13F);
	public static final Block CHEESE_PIZZA = food("cheese_pizza", 2, 0.125F);
	public static final Block PEPPERONI_PIZZA = food("pepperoni_pizza", 3, 0.14F);
	// Plants
	public static final Block COTTON_BUSH = bush("cotton_bush", SMCItems.COTTON);
	public static final Block TOMATO_BUSH = bush("tomato_bush", SMCItems.TOMATO);
	public static final Block CORN_STALK = new CornStalkTopBlockSMC("corn_stalk");
	public static final Block CORN_STALK_PLANT = new CornStalkBlockSMC("corn_stalk_plant");
	public static final Block GRAPE_VINE = new GrapeVineTopBlockSMC("grape_vine");
	public static final Block GRAPE_VINE_PLANT = new GrapeVineBlockSMC("grape_vine_plant");
	
	public static final Block VANILLA_CROPS = crops("vanilla_crops", SMCItems.VANILLA_SEEDS);
	// Liquid Blocks
	public static final Block RADIATED_WATER = fluid("radiated_water", SMCFluids.RADIATED_WATER);
	// Portal
	public static final Block DEEPDARK_PORTAL = portal("deepdark_portal", SMCDimensions.DEEPDARK, GLOWING_OBSIDIAN);


	
	private static Block block(String name, Block.Properties properties) {return new BlockSMC(name, properties);}
	private static Block bookshelf(String name, Block.Properties properties) {return new BookshelfBlockSMC(name, properties);}
	private static Block button(String name, Boolean isWood) {return isWood ? new WoodButtonBlockSMC(name) : new StoneButtonBlockSMC(name);}
	private static Block bush(String name, Item item) {return new BushBlockSMC(name, item);}
	private static Block copper(String name, Block.Properties properties) {return new CopperBlockSMC(name, properties);}
	private static Block copperSlab(Block block) {return new CopperSlabBlockSMC(block);}
	private static Block copperStairs(Block block) {return new CopperStairsBlockSMC(block);}
	private static Block craftingTable(String name) {return new CraftingTableBlockSMC(name);}
	private static Block crops(String name, Item item) {return new CropsBlockSMC(name, item);}	
	private static Block door(Block block) {return new DoorBlockSMC(block);}
	private static Block fallingBlock(String name, Block.Properties properties) {return new FallingBlockSMC(name, properties);}
	private static Block fence(Block block) {return new FenceBlockSMC(block);}
	private static Block fenceGate(Block block) {return new FenceGateBlockSMC(block);}
	private static Block fenceGate(String name, Block block) {return new FenceGateBlockSMC(name, block);}
	private static Block flower(String name, Effect effect, int duration) {return new FlowerBlockSMC(name, effect, duration);}	
	private static Block fluid(String name, FlowingFluid fluid) {return new FlowingFluidBlockSMC(name, () -> fluid);}
	private static Block food(String name, int hunger, float saturation) {return new FoodBlockSMC(name, hunger, saturation);}
	private static Block fungus(String name, ConfiguredFeature<HugeFungusConfig, ?> fungusFeature) {return new FungusBlockSMC(name, () -> {return fungusFeature;});}
	private static Block furnace(String name) {return new FurnaceBlockSMC(name);}
	private static Block grass(String name, Block dirtBlock, Block.Properties properties) {return new DimensionalGrassBlockSMC(name, dirtBlock, properties);} 
	private static Block leaves(String name, MaterialColor color) {return new LeavesBlockSMC(name, color);}	
	private static Block pillar(String name, Block.Properties properties) {return new RotatedPillarBlockSMC(name, properties);}
	private static Block portal(String name, RegistryKey<World> dimension, Block frame) {return new PortalBlockSMC(name, dimension, frame);}
	private static Block pressurePlate(Block block) {return new PressurePlateBlockSMC(block);}
	private static Block pressurePlate(String name, Block block) {return new PressurePlateBlockSMC(name, block);}
	private static Block receiver(SMCColors color, Block sender) {return new WirelessReceiverBlockSMC(color, sender);}
	private static Block redstoneBlock(String name, Block.Properties properties) {return new RedstoneBlockSMC(name, properties);}
	private static Block redstoneSlab(Block block) {return new RedstoneSlabBlockSMC(block);}
	private static Block redstoneSlab(String name, Block block) {return new RedstoneSlabBlockSMC(name, block);}
	private static Block redstoneStairs(Block block) {return new RedstoneStairsBlockSMC(block);}
	private static Block redstoneStairs(String name, Block block) {return new RedstoneStairsBlockSMC(name, block);}
	private static Block redstoneWall(Block block) {return new RedstoneWallBlockSMC(block);}
	private static Block redstoneWall(String name, Block block) {return new RedstoneWallBlockSMC(name, block);}
	private static Block roots(String name) {return new RootsBlockSMC(name);}
	private static Block sapling(String name, Tree tree) {return new SaplingBlockSMC(name, tree);}
	private static Block sender(SMCColors color) {return new WirelessSenderBlockSMC(color);}
	private static Block sign(WoodType woodType, String name) {return new SignBlockSMC(woodType, name, new SignWallBlockSMC(woodType, name + "_wall"));}
	private static Block slab(Block block) {return new SlabBlockSMC(block);}
	private static Block slab(String name, Block block) {return new SlabBlockSMC(name, block);}
	private static Block slime(SMCColors color) {return new SlimeBlockSMC(color);}
	private static Block stairs(Block block) {return new StairsBlockSMC(block);}
	private static Block stairs(String name, Block block) {return new StairsBlockSMC(name, block);}
	private static Block trapdoor(Block block) {return new TrapDoorBlockSMC(block);}
	private static Block unstrippedLog(String name, Block.Properties properties) {return new UnstrippedLogBlockSMC(name, properties);}
	private static Block wall(Block block) {return new WallBlockSMC(block);}
	private static Block wall(String name, Block block) {return new WallBlockSMC(name, block);}	
	private static Block ore(String name, int oreLevel, SMCBlockProperties stone, int minXP, int maxXP) 
	{
		Properties properties;
		if(oreLevel == 1) properties = stone.ORE_1;
		else if(oreLevel == 2) properties = stone.ORE_2;
		else properties = stone.ORE_0;
		return new OreBlockSMC(name, minXP, maxXP, properties);
	}
	private static Block redstoneOre(String name, int oreLevel, SMCBlockProperties stone, int minXP, int maxXP) 
	{
		Properties properties;
		if(oreLevel == 1) properties = stone.ORE_1;
		else if(oreLevel == 2) properties = stone.ORE_2;
		else properties = stone.ORE_0;
		return new RedstoneOreBlockSMC(name, minXP, maxXP, properties);
	}
	
	

}
