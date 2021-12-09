package source.suprememc.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;
import source.suprememc.util.SMCSustainability;

public class FallingBlockSMC extends FallingBlock
{
	public FallingBlockSMC(String name, Properties properties) 
	{
		// Pass the properties to the constructor in Block.class
		super(properties);
		
		// Register the block to the game
		RegUtil.registerBlock(this, name, SMCTabs.BUILDING_BLOCKS);
	}
	
	
	/**
	 * Arable sand is used to grow some plants in this mod and is a falling block. 
	 * As a result, this function is needed to support that.
	 */
	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, net.minecraft.util.Direction facing, IPlantable plantable) 
	{
		return SMCSustainability.canSustainPlant(this, world, pos, facing, plantable);
	}
}
