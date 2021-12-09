package source.suprememc.blocks.sub;

import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import source.suprememc.blocks.BlockSMC;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class StairsBlockSMC extends StairsBlock
{
	public StairsBlockSMC(Block block) 
	{
		super(() -> block.defaultBlockState(), Properties.copy(block));
		RegUtil.registerBlock(this, ((BlockSMC)block).getBlockName().replace("_block", "").replace("_planks", "").replace("tiles", "tile").replace("bricks", "brick") + "_stairs", SMCTabs.BUILDING_BLOCKS);	
	}
	
	public StairsBlockSMC(String name, Block block) 
	{
		super(() -> block.defaultBlockState(), Properties.copy(block));
		RegUtil.registerBlock(this, name, SMCTabs.BUILDING_BLOCKS);
	}
}