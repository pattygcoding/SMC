package source.suprememc.world.generation.layer;

import java.util.function.LongFunction;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import net.minecraft.util.Util;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.AddBambooForestLayer;
import net.minecraft.world.gen.layer.AddIslandLayer;
import net.minecraft.world.gen.layer.AddMushroomIslandLayer;
import net.minecraft.world.gen.layer.AddSnowLayer;
import net.minecraft.world.gen.layer.DeepOceanLayer;
import net.minecraft.world.gen.layer.EdgeBiomeLayer;
import net.minecraft.world.gen.layer.EdgeLayer;
import net.minecraft.world.gen.layer.HillsLayer;
import net.minecraft.world.gen.layer.IslandLayer;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.MixOceansLayer;
import net.minecraft.world.gen.layer.MixRiverLayer;
import net.minecraft.world.gen.layer.OceanLayer;
import net.minecraft.world.gen.layer.RareBiomeLayer;
import net.minecraft.world.gen.layer.RemoveTooMuchOceanLayer;
import net.minecraft.world.gen.layer.RiverLayer;
import net.minecraft.world.gen.layer.SmoothLayer;
import net.minecraft.world.gen.layer.StartRiverLayer;
import net.minecraft.world.gen.layer.ZoomLayer;
import net.minecraft.world.gen.layer.traits.IAreaTransformer1;
import source.suprememc.init.generation.SMCBiomes;
import source.suprememc.util.BiomeUtil;

public class SMCLayerUtil 
{

	public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> zoom(long value, IAreaTransformer1 iAreaTransformer, IAreaFactory<T> iAreaFactory, int size, LongFunction<C> seedFunc) 
	{
		IAreaFactory<T> iareafactory = iAreaFactory;
		for(int i = 0; i < size; ++i) iareafactory = iAreaTransformer.run(seedFunc.apply(value + (long)i), iareafactory);
		return iareafactory;
	}

	private static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> getDefaultLayer(boolean legacyDesert, int biomeSize, int riverSize, LongFunction<C> seedFunc) 
	{
		IAreaFactory<T> iareafactory = IslandLayer.INSTANCE.run(seedFunc.apply(1L));
		iareafactory = ZoomLayer.FUZZY.run(seedFunc.apply(2000L), iareafactory);
		iareafactory = AddIslandLayer.INSTANCE.run(seedFunc.apply(1L), iareafactory);
		iareafactory = ZoomLayer.NORMAL.run(seedFunc.apply(2001L), iareafactory);
		iareafactory = AddIslandLayer.INSTANCE.run(seedFunc.apply(2L), iareafactory);
		iareafactory = AddIslandLayer.INSTANCE.run(seedFunc.apply(50L), iareafactory);
		iareafactory = AddIslandLayer.INSTANCE.run(seedFunc.apply(70L), iareafactory);
		iareafactory = RemoveTooMuchOceanLayer.INSTANCE.run(seedFunc.apply(2L), iareafactory);
		IAreaFactory<T> iareafactory1 = OceanLayer.INSTANCE.run(seedFunc.apply(2L));
		iareafactory1 = zoom(2001L, ZoomLayer.NORMAL, iareafactory1, 6, seedFunc);
		iareafactory = AddSnowLayer.INSTANCE.run(seedFunc.apply(2L), iareafactory);
		iareafactory = AddIslandLayer.INSTANCE.run(seedFunc.apply(3L), iareafactory);
		iareafactory = EdgeLayer.CoolWarm.INSTANCE.run(seedFunc.apply(2L), iareafactory);
		iareafactory = EdgeLayer.HeatIce.INSTANCE.run(seedFunc.apply(2L), iareafactory);
		iareafactory = EdgeLayer.Special.INSTANCE.run(seedFunc.apply(3L), iareafactory);
		iareafactory = ZoomLayer.NORMAL.run(seedFunc.apply(2002L), iareafactory);
		iareafactory = ZoomLayer.NORMAL.run(seedFunc.apply(2003L), iareafactory);
		iareafactory = AddIslandLayer.INSTANCE.run(seedFunc.apply(4L), iareafactory);
		iareafactory = AddMushroomIslandLayer.INSTANCE.run(seedFunc.apply(5L), iareafactory);
		iareafactory = DeepOceanLayer.INSTANCE.run(seedFunc.apply(4L), iareafactory);
		iareafactory = zoom(1000L, ZoomLayer.NORMAL, iareafactory, 0, seedFunc);
		IAreaFactory<T> iriverfactory = zoom(1000L, ZoomLayer.NORMAL, iareafactory, 0, seedFunc);
		iriverfactory = StartRiverLayer.INSTANCE.run(seedFunc.apply(100L), iriverfactory);
		IAreaFactory<T> iSecondFactory = (new SMCBiomeLayer()).run(seedFunc.apply(200L), iareafactory);
		iSecondFactory = AddBambooForestLayer.INSTANCE.run(seedFunc.apply(1001L), iSecondFactory);
		iSecondFactory = zoom(1000L, ZoomLayer.NORMAL, iSecondFactory, 2, seedFunc);
		iSecondFactory = EdgeBiomeLayer.INSTANCE.run(seedFunc.apply(1000L), iSecondFactory);
		IAreaFactory<T> lvt_8_1_ = zoom(1000L, ZoomLayer.NORMAL, iriverfactory, 2, seedFunc);
		iSecondFactory = HillsLayer.INSTANCE.run(seedFunc.apply(1000L), iSecondFactory, lvt_8_1_);
		iriverfactory = zoom(1000L, ZoomLayer.NORMAL, iriverfactory, 2, seedFunc);
		iriverfactory = zoom(1000L, ZoomLayer.NORMAL, iriverfactory, riverSize, seedFunc);
		iriverfactory = RiverLayer.INSTANCE.run(seedFunc.apply(1L), iriverfactory);
		iriverfactory = SmoothLayer.INSTANCE.run(seedFunc.apply(1000L), iriverfactory);
		iSecondFactory = RareBiomeLayer.INSTANCE.run(seedFunc.apply(1001L), iSecondFactory);

		for(int i = 0; i < biomeSize; ++i) {
			iSecondFactory = ZoomLayer.NORMAL.run(seedFunc.apply((long)(1000 + i)), iSecondFactory);
			if (i == 0) {
				iSecondFactory = AddIslandLayer.INSTANCE.run(seedFunc.apply(3L), iSecondFactory);
			}

			if (i == 1 || biomeSize == 1) {
				iSecondFactory = SMCShoreLayer.INSTANCE.run(seedFunc.apply(1000L), iSecondFactory);
			}
		}

		iSecondFactory = SmoothLayer.INSTANCE.run(seedFunc.apply(1000L), iSecondFactory);
		iSecondFactory = MixRiverLayer.INSTANCE.run(seedFunc.apply(100L), iSecondFactory, iriverfactory);
		return MixOceansLayer.INSTANCE.run(seedFunc.apply(100L), iSecondFactory, iareafactory1);
	}

	public static Layer getDefaultLayer(long seed, boolean legacyDesert, int biomeSize, int riverSize) 
	{
		int maxCache = 25;
		IAreaFactory<LazyArea> iareafactory = getDefaultLayer(legacyDesert, biomeSize, riverSize, (seedFunc) -> 
		{
			return new LazyAreaLayerContext(maxCache, seed, seedFunc);
		});
		return new Layer(iareafactory);
	}

	public static boolean isSame(int biomeId1, int biomeId2)
	{
		if (biomeId1 == biomeId2) return true;
		return SIMILAR_BIOME_MAP.get(biomeId2) == SIMILAR_BIOME_MAP.get(biomeId2);
	}

	
	protected static boolean isOcean(int biomeId) 
	{
		return biomeId == BiomeUtil.getBiomeId(Biomes.COLD_OCEAN) || 
				biomeId == BiomeUtil.getBiomeId(Biomes.FROZEN_OCEAN) || 
				biomeId == BiomeUtil.getBiomeId(Biomes.OCEAN) || 
				biomeId == BiomeUtil.getBiomeId(Biomes.LUKEWARM_OCEAN) || 
				biomeId == BiomeUtil.getBiomeId(Biomes.WARM_OCEAN) ||
				biomeId == BiomeUtil.getBiomeId(Biomes.DEEP_COLD_OCEAN) || 
				biomeId == BiomeUtil.getBiomeId(Biomes.DEEP_FROZEN_OCEAN) || 
				biomeId == BiomeUtil.getBiomeId(Biomes.DEEP_OCEAN) || 
				biomeId == BiomeUtil.getBiomeId(Biomes.DEEP_LUKEWARM_OCEAN) || 
				biomeId == BiomeUtil.getBiomeId(Biomes.DEEP_WARM_OCEAN);
	}

	protected static boolean isShallowOcean(int biomeId) 
	{
		return biomeId == BiomeUtil.getBiomeId(Biomes.COLD_OCEAN) || 
				biomeId == BiomeUtil.getBiomeId(Biomes.FROZEN_OCEAN) || 
				biomeId == BiomeUtil.getBiomeId(Biomes.OCEAN) || 
				biomeId == BiomeUtil.getBiomeId(Biomes.LUKEWARM_OCEAN) || 
				biomeId == BiomeUtil.getBiomeId(Biomes.WARM_OCEAN);
	}

	
	public static enum Type 
 	{
 		NONE, TAIGA, EXTREME_HILLS, JUNGLE, MESA, BADLANDS_PLATEAU, PLAINS, SAVANNA, ICY, BEACH, FOREST, OCEAN, DESERT, RIVER, SWAMP, MUSHROOM;
  	}
     
	static final Int2IntMap SIMILAR_BIOME_MAP = Util.make(new Int2IntOpenHashMap(), (map) -> 
	{
		putInMap(map, Type.BEACH, BiomeUtil.getBiomeId(Biomes.BEACH));
		putInMap(map, Type.BEACH, BiomeUtil.getBiomeId(Biomes.SNOWY_BEACH));
		putInMap(map, Type.DESERT, BiomeUtil.getBiomeId(Biomes.DESERT));
		putInMap(map, Type.DESERT, BiomeUtil.getBiomeId(Biomes.DESERT_HILLS));
		putInMap(map, Type.DESERT, BiomeUtil.getBiomeId(Biomes.DESERT_LAKES));
		putInMap(map, Type.DESERT, BiomeUtil.getBiomeId(SMCBiomes.SAHARA_DESERT.getBiomeKey()));
		putInMap(map, Type.DESERT, BiomeUtil.getBiomeId(SMCBiomes.SALT_FLAT.getBiomeKey()));
		putInMap(map, Type.EXTREME_HILLS, BiomeUtil.getBiomeId(Biomes.GRAVELLY_MOUNTAINS));
		putInMap(map, Type.EXTREME_HILLS, BiomeUtil.getBiomeId(Biomes.MODIFIED_GRAVELLY_MOUNTAINS));
		putInMap(map, Type.EXTREME_HILLS, BiomeUtil.getBiomeId(Biomes.MOUNTAIN_EDGE));
		putInMap(map, Type.EXTREME_HILLS, BiomeUtil.getBiomeId(Biomes.MOUNTAINS));
		putInMap(map, Type.EXTREME_HILLS, BiomeUtil.getBiomeId(Biomes.WOODED_MOUNTAINS));
		putInMap(map, Type.FOREST, BiomeUtil.getBiomeId(Biomes.BIRCH_FOREST));
		putInMap(map, Type.FOREST, BiomeUtil.getBiomeId(Biomes.BIRCH_FOREST_HILLS));
		putInMap(map, Type.FOREST, BiomeUtil.getBiomeId(Biomes.DARK_FOREST));
		putInMap(map, Type.FOREST, BiomeUtil.getBiomeId(Biomes.DARK_FOREST_HILLS));
		putInMap(map, Type.FOREST, BiomeUtil.getBiomeId(Biomes.FLOWER_FOREST));
		putInMap(map, Type.FOREST, BiomeUtil.getBiomeId(Biomes.FOREST));
		putInMap(map, Type.FOREST, BiomeUtil.getBiomeId(Biomes.TALL_BIRCH_FOREST));
		putInMap(map, Type.FOREST, BiomeUtil.getBiomeId(Biomes.TALL_BIRCH_HILLS));
		putInMap(map, Type.FOREST, BiomeUtil.getBiomeId(Biomes.WOODED_HILLS));
		putInMap(map, Type.FOREST, BiomeUtil.getBiomeId(SMCBiomes.CHERRY_BLOSSOM_FOREST.getBiomeKey()));
		putInMap(map, Type.ICY, BiomeUtil.getBiomeId(Biomes.ICE_SPIKES));
		putInMap(map, Type.ICY, BiomeUtil.getBiomeId(Biomes.SNOWY_MOUNTAINS));
		putInMap(map, Type.ICY, BiomeUtil.getBiomeId(Biomes.SNOWY_TUNDRA));
		putInMap(map, Type.JUNGLE, BiomeUtil.getBiomeId(Biomes.BAMBOO_JUNGLE));
		putInMap(map, Type.JUNGLE, BiomeUtil.getBiomeId(Biomes.BAMBOO_JUNGLE_HILLS));
		putInMap(map, Type.JUNGLE, BiomeUtil.getBiomeId(Biomes.JUNGLE));
		putInMap(map, Type.JUNGLE, BiomeUtil.getBiomeId(Biomes.JUNGLE_EDGE));
		putInMap(map, Type.JUNGLE, BiomeUtil.getBiomeId(Biomes.JUNGLE_HILLS));
		putInMap(map, Type.JUNGLE, BiomeUtil.getBiomeId(Biomes.MODIFIED_JUNGLE));
		putInMap(map, Type.JUNGLE, BiomeUtil.getBiomeId(Biomes.MODIFIED_JUNGLE_EDGE));
		putInMap(map, Type.MESA, BiomeUtil.getBiomeId(Biomes.BADLANDS));
		putInMap(map, Type.MESA, BiomeUtil.getBiomeId(Biomes.ERODED_BADLANDS));
		putInMap(map, Type.MESA, BiomeUtil.getBiomeId(Biomes.MODIFIED_BADLANDS_PLATEAU));
		putInMap(map, Type.MESA, BiomeUtil.getBiomeId(Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU));
		putInMap(map, Type.BADLANDS_PLATEAU, BiomeUtil.getBiomeId(Biomes.BADLANDS_PLATEAU));
		putInMap(map, Type.BADLANDS_PLATEAU, BiomeUtil.getBiomeId(Biomes.WOODED_BADLANDS_PLATEAU));
		putInMap(map, Type.MUSHROOM, BiomeUtil.getBiomeId(Biomes.MUSHROOM_FIELDS));
		putInMap(map, Type.MUSHROOM, BiomeUtil.getBiomeId(Biomes.MUSHROOM_FIELD_SHORE));
		putInMap(map, Type.NONE, BiomeUtil.getBiomeId(Biomes.STONE_SHORE));
 		putInMap(map, Type.OCEAN, BiomeUtil.getBiomeId(Biomes.COLD_OCEAN));
 		putInMap(map, Type.OCEAN, BiomeUtil.getBiomeId(Biomes.DEEP_COLD_OCEAN));
 		putInMap(map, Type.OCEAN, BiomeUtil.getBiomeId(Biomes.DEEP_FROZEN_OCEAN));
 		putInMap(map, Type.OCEAN, BiomeUtil.getBiomeId(Biomes.DEEP_LUKEWARM_OCEAN));
 		putInMap(map, Type.OCEAN, BiomeUtil.getBiomeId(Biomes.DEEP_OCEAN));
 		putInMap(map, Type.OCEAN, BiomeUtil.getBiomeId(Biomes.DEEP_WARM_OCEAN));
 		putInMap(map, Type.OCEAN, BiomeUtil.getBiomeId(Biomes.FROZEN_OCEAN));
 		putInMap(map, Type.OCEAN, BiomeUtil.getBiomeId(Biomes.LUKEWARM_OCEAN));
 		putInMap(map, Type.OCEAN, BiomeUtil.getBiomeId(Biomes.OCEAN));
 		putInMap(map, Type.OCEAN, BiomeUtil.getBiomeId(Biomes.WARM_OCEAN));
 		putInMap(map, Type.PLAINS, BiomeUtil.getBiomeId(Biomes.PLAINS));
 		putInMap(map, Type.PLAINS, BiomeUtil.getBiomeId(Biomes.SUNFLOWER_PLAINS));
 		putInMap(map, Type.RIVER, BiomeUtil.getBiomeId(Biomes.FROZEN_RIVER));
 		putInMap(map, Type.RIVER, BiomeUtil.getBiomeId(Biomes.RIVER));
 		putInMap(map, Type.SAVANNA, BiomeUtil.getBiomeId(Biomes.SAVANNA));
 		putInMap(map, Type.SAVANNA, BiomeUtil.getBiomeId(Biomes.SAVANNA_PLATEAU));
 		putInMap(map, Type.SAVANNA, BiomeUtil.getBiomeId(Biomes.SHATTERED_SAVANNA));
 		putInMap(map, Type.SAVANNA, BiomeUtil.getBiomeId(Biomes.SHATTERED_SAVANNA_PLATEAU));
 		putInMap(map, Type.SWAMP, BiomeUtil.getBiomeId(Biomes.SWAMP));
 		putInMap(map, Type.SWAMP, BiomeUtil.getBiomeId(Biomes.SWAMP_HILLS));
 		putInMap(map, Type.TAIGA, BiomeUtil.getBiomeId(Biomes.GIANT_SPRUCE_TAIGA));
 		putInMap(map, Type.TAIGA, BiomeUtil.getBiomeId(Biomes.GIANT_SPRUCE_TAIGA_HILLS));
 		putInMap(map, Type.TAIGA, BiomeUtil.getBiomeId(Biomes.GIANT_TREE_TAIGA));
 		putInMap(map, Type.TAIGA, BiomeUtil.getBiomeId(Biomes.GIANT_TREE_TAIGA_HILLS));
 		putInMap(map, Type.TAIGA, BiomeUtil.getBiomeId(Biomes.SNOWY_TAIGA));
 		putInMap(map, Type.TAIGA, BiomeUtil.getBiomeId(Biomes.SNOWY_TAIGA_HILLS));
 		putInMap(map, Type.TAIGA, BiomeUtil.getBiomeId(Biomes.SNOWY_TAIGA_MOUNTAINS));
 		putInMap(map, Type.TAIGA, BiomeUtil.getBiomeId(Biomes.TAIGA));
 		putInMap(map, Type.TAIGA, BiomeUtil.getBiomeId(Biomes.TAIGA_HILLS));
 		putInMap(map, Type.TAIGA, BiomeUtil.getBiomeId(Biomes.TAIGA_MOUNTAINS));
 	});
	
	private static void putInMap(Int2IntOpenHashMap hashMap, Type type, int biomeId) 
	{
		hashMap.put(biomeId, type.ordinal());
	}
}
