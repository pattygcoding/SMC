package source.suprememc.world.generation.trees.foliage;

import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import source.suprememc.init.generation.SMCFoliagePlacerTypes;

public class PalmFoliagePlacerSMC extends FoliagePlacer 
{
	public static final Codec<PalmFoliagePlacerSMC> PALM_CODEC = RecordCodecBuilder.create((x) -> 
	{
		return foliagePlacerParts(x).apply(x, PalmFoliagePlacerSMC::new);
	});

	public PalmFoliagePlacerSMC(FeatureSpread featureSpread1, FeatureSpread featureSpread2) 
	{
		super(FeatureSpread.fixed(2), FeatureSpread.fixed(0));
	}

	
	@Override
	protected FoliagePlacerType<?> type() 
	{
		return SMCFoliagePlacerTypes.PALM;
	}

	@Override
	protected void createFoliage(IWorldGenerationReader iWorldGenReader, Random random, BaseTreeFeatureConfig baseTreeFeatureConfig, int trunkNum, FoliagePlacer.Foliage foliage, int foilageNum1, int featureSpread1Base, Set<BlockPos> setBlockPos, int featureSpread2Base, MutableBoundingBox mutableBoundingBox) 
	{
		// foilageNum1 = 0
		BlockPos blockpos = foliage.foliagePos();
		this.makeExtraLog(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, 0, 0), setBlockPos, mutableBoundingBox);
		
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(1, 0, 0), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(-1, 0, 0), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, 0, 1), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, 0, -1), setBlockPos, mutableBoundingBox);
		
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(2, 0, 2), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(2, 0, -2), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(-2, 0, 2), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(-2, 0, -2), setBlockPos, mutableBoundingBox);
		
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(2, -1, 0), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(-2, -1, 0), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, -1, 2), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, -1, -2), setBlockPos, mutableBoundingBox);
		
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, 1, 0), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(1, 1, 1), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(-1, 1, 1), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(1, 1, -1), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(-1, 1, -1), setBlockPos, mutableBoundingBox);
		
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, 2, 0), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(1, 2, 0), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(-1, 2, 0), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, 2, 1), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, 2, -1), setBlockPos, mutableBoundingBox);
		
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(2, 2, 2), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(2, 2, -2), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(-2, 2, 2), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(-2, 2, -2), setBlockPos, mutableBoundingBox);
		
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(2, 3, 0), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(-2, 3, 0), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, 3, 2), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, 3, -2), setBlockPos, mutableBoundingBox);
		
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(1, 1, 0), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(-1, 1, 0), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, 1, 1), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, 1, -1), setBlockPos, mutableBoundingBox);
		
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(2, 1, 0), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(-2, 1, 0), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, 1, 2), setBlockPos, mutableBoundingBox);
		this.make(iWorldGenReader, random, baseTreeFeatureConfig, blockpos.offset(0, 1, -2), setBlockPos, mutableBoundingBox);
		
		
	}
	
	private void placeLeaves(IWorldGenerationReader iWorldGenReader, Random random, BaseTreeFeatureConfig baseTreeFeatureConfig, Set<BlockPos> setBlockPos, BlockPos blockPos, MutableBoundingBox mutableBoundingBox)
	{
		
		iWorldGenReader.setBlock(blockPos, baseTreeFeatureConfig.leavesProvider.getState(random, blockPos), 19);
        mutableBoundingBox.expand(new MutableBoundingBox(blockPos, blockPos));
        setBlockPos.add(blockPos.immutable());
       
	}
	
	private void placeLog(IWorldGenerationReader iWorldGenReader, Random random, BaseTreeFeatureConfig baseTreeFeatureConfig, Set<BlockPos> setBlockPos, BlockPos blockPos, MutableBoundingBox mutableBoundingBox)
	{
		iWorldGenReader.setBlock(blockPos, baseTreeFeatureConfig.trunkProvider.getState(random, blockPos), 19);
        mutableBoundingBox.expand(new MutableBoundingBox(blockPos, blockPos));
        setBlockPos.add(blockPos.immutable());
       
	}
	protected void make(IWorldGenerationReader iWorldGenReader, Random random, BaseTreeFeatureConfig baseTreeFeatureConfig, BlockPos blockPos,Set<BlockPos> setBlockPos, MutableBoundingBox mutableBoundingBox) 
	{
	      this.placeLeaves(iWorldGenReader, random, baseTreeFeatureConfig, setBlockPos, blockPos, mutableBoundingBox);
	    	 
	}
	
	protected void makeExtraLog(IWorldGenerationReader iWorldGenReader, Random random, BaseTreeFeatureConfig baseTreeFeatureConfig, BlockPos blockPos,Set<BlockPos> setBlockPos, MutableBoundingBox mutableBoundingBox) 
	{
	      this.placeLog(iWorldGenReader, random, baseTreeFeatureConfig, setBlockPos, blockPos, mutableBoundingBox);
	    	 
	}

	
	@Override
	public int foliageHeight(Random random, int p_230374_2_, BaseTreeFeatureConfig p_230374_3_) 
	{
		return 0;
	}

	@Override
	protected boolean shouldSkipLocation(Random random, int i, int foilageNum1, int j, int modifiedFS1Base, boolean flag) 
	{
		if(foilageNum1 == 0) return (i > 1 || j > 1) && i != 0 && j != 0;
		
		else return i == modifiedFS1Base && j == modifiedFS1Base && modifiedFS1Base > 0;     
	}
}
