package source.suprememc.blocks.plant;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.NetherVegetationFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;
import source.suprememc.init.generation.SMCVegetation;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.util.RegUtil;

@SuppressWarnings("deprecation")
public class DimensionalGrassBlockSMC extends Block implements IGrowable
{
	private Block dirtBlock;
	public DimensionalGrassBlockSMC(String name, Block dirtBlock, Properties properties) 
	{
		super(properties);
		RegUtil.registerBlock(this, name, SMCTabs.BUILDING_BLOCKS);
		this.dirtBlock = dirtBlock;
	}

	private static boolean isDarkEnough(BlockState state, IWorldReader reader, BlockPos pos) 
	{
		BlockPos blockpos = pos.above();
		BlockState blockstate = reader.getBlockState(blockpos);
		int i = LightEngine.getLightBlockInto(reader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(reader, blockpos));
		return i < reader.getMaxLightLevel();
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) 
	{
		if(!isDarkEnough(state, worldIn, pos)) worldIn.setBlockAndUpdate(pos, this.dirtBlock.defaultBlockState());
	}
	
	
	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) 
	{
		return worldIn.getBlockState(pos.above()).isAir();
	}

	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) 
	{
		return true;
	}
	
	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) 
	{
		BlockState blockstate = worldIn.getBlockState(pos);
		BlockPos blockpos = pos.above();
		if (blockstate.is(SMCBlocks.MUSTARD_NYLIUM)) 
		{
			NetherVegetationFeature.place(worldIn, rand, blockpos, SMCVegetation.MUSTARD_FOREST_SPREAD, 3, 1);
		} 
		else if (blockstate.is(SMCBlocks.LAVENDER_ENDSPAR)) 
		{
			NetherVegetationFeature.place(worldIn, rand, blockpos, SMCVegetation.LAVENDER_FOREST_SPREAD, 3, 1);
		} 
		

	}
}
