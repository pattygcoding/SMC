package source.suprememc.init.generation;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import source.suprememc.biomes.BiomeSMC;
import source.suprememc.biomes.types.deepdark.AndesiteCaveBiomeSMC;
import source.suprememc.biomes.types.deepdark.BlackstoneCaveBiomeSMC;
import source.suprememc.biomes.types.deepdark.DeepDarkBiomeSMC;
import source.suprememc.biomes.types.deepdark.DioriteCaveBiomeSMC;
import source.suprememc.biomes.types.deepdark.GeodeCaveBiomeSMC;
import source.suprememc.biomes.types.deepdark.GraniteCaveBiomeSMC;
import source.suprememc.biomes.types.deepdark.MagmaCaveBiomeSMC;
import source.suprememc.biomes.types.deepdark.QuartzCaveBiomeSMC;
import source.suprememc.biomes.types.end.LavenderForestBiomeSMC;
import source.suprememc.biomes.types.overworld.CherryBlossomForestBiomeSMC;
import source.suprememc.biomes.types.overworld.DesertBiomeSMC;
import source.suprememc.biomes.types.overworld.PalmBeachBiomeSMC;
import source.suprememc.biomes.util.BiomeInfoSMC;
import source.suprememc.util.BiomeUtil;

public class SMCBiomes 
{
	
	public static final List<BiomeInfoSMC> BIOMES = new ArrayList<BiomeInfoSMC>();
	
	public static final BiomeSMC CHERRY_BLOSSOM_FOREST = new CherryBlossomForestBiomeSMC("cherry_blossom_forest");
	public static final BiomeSMC SAHARA_DESERT = new DesertBiomeSMC("sahara_desert", 0.125F, 0.05F, SMCSurfaceBuilders.SAHARA_SURFACE, false, false, true);
	public static final BiomeSMC SALT_FLAT = new DesertBiomeSMC("salt_flat", 0.125F, 0.05F, SMCSurfaceBuilders.SALT_FLAT_SURFACE, false, false, true);
	public static final BiomeSMC PALM_BEACH = new PalmBeachBiomeSMC("palm_beach");
	
	
	public static final BiomeSMC ANDESITE_CAVE = new AndesiteCaveBiomeSMC("andesite_cave");
	public static final BiomeSMC BLACKSTONE_CAVE = new BlackstoneCaveBiomeSMC("blackstone_cave");
	public static final BiomeSMC DEEP_DARK = new DeepDarkBiomeSMC("deep_dark_cave");
	public static final BiomeSMC DIORITE_CAVE = new DioriteCaveBiomeSMC("diorite_cave");
	public static final BiomeSMC GEODE_CAVE = new GeodeCaveBiomeSMC("geode_cave");
	public static final BiomeSMC GRANITE_CAVE = new GraniteCaveBiomeSMC("granite_cave");
	public static final BiomeSMC MAGMA_CAVE = new MagmaCaveBiomeSMC("magma_cave");
	public static final BiomeSMC QUARTZ_CAVE = new QuartzCaveBiomeSMC("quartz_cave");
	
	public static final BiomeSMC LAVENDER_FOREST_HIGHLANDS = new LavenderForestBiomeSMC("lavender_forest_highlands");
	public static final BiomeSMC LAVENDER_FOREST_MIDLANDS = new LavenderForestBiomeSMC("lavender_forest_midlands");
	public static final BiomeSMC LAVENDER_FOREST_ISLANDS = new LavenderForestBiomeSMC("lavender_forest_islands");
	
	public static void registerBiomeDictionaryTags()
	{
		register(CHERRY_BLOSSOM_FOREST.getBiomeKey(), Type.FOREST, Type.OVERWORLD);
		register(SAHARA_DESERT.getBiomeKey(), Type.HOT, Type.DRY, Type.SANDY, Type.OVERWORLD);
		register(SALT_FLAT.getBiomeKey(), Type.HOT, Type.DRY, Type.SANDY, Type.OVERWORLD);
		register(PALM_BEACH.getBiomeKey(), Type.BEACH, Type.OVERWORLD);
	}
	
	private static void register(RegistryKey<Biome> key, Type...type) { if (BiomeUtil.exists(key)) BiomeDictionary.addTypes(key, type); }
}
