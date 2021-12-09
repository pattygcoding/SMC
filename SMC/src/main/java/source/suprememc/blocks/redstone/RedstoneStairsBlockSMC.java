package source.suprememc.blocks.redstone;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class RedstoneStairsBlockSMC extends StairsBlock
{
	public RedstoneStairsBlockSMC(Block block) 
	{
		super(() -> block.defaultBlockState(), Properties.copy(block));
		RegUtil.registerBlock(this, ((RedstoneBlockSMC)block).getBlockName().replace("_block", "").replace("_planks", "").replace("bricks", "brick") + "_stairs", SMCTabs.REDSTONE);
	}
	
	public RedstoneStairsBlockSMC(String name, Block block) 
	{
		super(() -> block.defaultBlockState(), Properties.copy(block));
		RegUtil.registerBlock(this, name, SMCTabs.REDSTONE);
	}
	
	@Override
	public boolean isSignalSource(BlockState blockState) 
	{
		return true;
	}

	@Override
	public int getSignal(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos, Direction direction) 
	{
		return 15;
	}
	
}