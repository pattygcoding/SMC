package source.suprememc.world.generation.structure.mineshaft;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.mojang.serialization.Codec;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class MineshaftStructureSMC extends Structure<MineshaftConfigSMC> {
	public MineshaftStructureSMC(Codec<MineshaftConfigSMC> p_i231969_1_) {
		super(p_i231969_1_);
	}

	protected boolean isFeatureChunk(ChunkGenerator p_230363_1_, BiomeProvider p_230363_2_, long p_230363_3_, SharedSeedRandom p_230363_5_, int p_230363_6_, int p_230363_7_, Biome p_230363_8_, ChunkPos p_230363_9_, MineshaftConfigSMC p_230363_10_) {
		p_230363_5_.setLargeFeatureSeed(p_230363_3_, p_230363_6_, p_230363_7_);
		double d0 = (double)p_230363_10_.probability;
		return p_230363_5_.nextDouble() < d0;
	}

	public Structure.IStartFactory<MineshaftConfigSMC> getStartFactory() {
		return MineshaftStructureSMC.Start::new;
	}

	public static class Start extends StructureStart<MineshaftConfigSMC> 
	{
		public Start(Structure<MineshaftConfigSMC> structure, int p_i225811_2_, int p_i225811_3_, MutableBoundingBox p_i225811_4_, int p_i225811_5_, long p_i225811_6_) {
			super(structure, p_i225811_2_, p_i225811_3_, p_i225811_4_, p_i225811_5_, p_i225811_6_);
		}

		public void generatePieces(DynamicRegistries reg, ChunkGenerator chunkGen, TemplateManager tempManager, int x, int z, Biome biome, MineshaftConfigSMC config) 
		{
			MineshaftPiecesSMC.Room mineshaftpieces$room = new MineshaftPiecesSMC.Room(0, this.random, (x << 4) + 2, (z << 4) + 2, config.type);
			this.pieces.add(mineshaftpieces$room);
			mineshaftpieces$room.addChildren(mineshaftpieces$room, this.pieces, this.random);
			this.calculateBoundingBox();
			this.moveBelowSeaLevel(127, this.random, 10);
		}
	}

	public static enum Type implements IStringSerializable 
	{
		CRIMSON("crimson", 0),
		WARPED("warped", 1),
		MUSTARD("mustard", 2),
		NETHER("nether", 3),
		CAVE("cave", 4);

		private int id;
		public static final Codec<MineshaftStructureSMC.Type> CODEC = IStringSerializable.fromEnum(MineshaftStructureSMC.Type::values, MineshaftStructureSMC.Type::byName);
		private static final Map<String, MineshaftStructureSMC.Type> BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(MineshaftStructureSMC.Type::getName, (p_214716_0_) -> {
			return p_214716_0_;
		}));
		private static final Map<Integer, MineshaftStructureSMC.Type> BY_ID = Arrays.stream(values()).collect(Collectors.toMap(MineshaftStructureSMC.Type::getId, (p_214716_0_) -> {
			return p_214716_0_;
		}));
		private final String name;

		private Type(String p_i50444_3_, int id) {
			this.name = p_i50444_3_;
			this.id = id;
		}

		public String getName() {
			return this.name;
		}
		
		public int getId() {
			return this.id;
		}

		private static MineshaftStructureSMC.Type byName(String name) 
		{
			return BY_NAME.get(name);
		}

		public static MineshaftStructureSMC.Type byId(int id) 
		{
			return BY_ID.get(id);
		}

		public String getSerializedName() {
			return this.name;
		}
	}
}
