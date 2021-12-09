package source.suprememc.blocks.redstone;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class RedstoneSlabBlockSMC extends SlabBlock
{
	public RedstoneSlabBlockSMC(Block block) 
	{
		super(Properties.copy(block));
		RegUtil.registerBlock(this, ((RedstoneBlockSMC)block).getBlockName().replace("_block", "").replace("_planks", "").replace("bricks", "brick") + "_slab", SMCTabs.REDSTONE);
	}
	
	public RedstoneSlabBlockSMC(String name, Block block) 
	{
		super(Properties.copy(block));
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
