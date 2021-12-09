package source.suprememc.blocks.redstone;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class RedstoneBlockSMC extends Block
{
	private String name;
	public RedstoneBlockSMC(String name, Properties properties) 
	{
		super(properties);
		this.name = name;
		RegUtil.registerBlock(this, this.name, SMCTabs.REDSTONE);
	}
	
	public String getBlockName() 
	{
		return this.name;
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
