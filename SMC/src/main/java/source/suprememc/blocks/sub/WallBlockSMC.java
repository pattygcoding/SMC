package source.suprememc.blocks.sub;

import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;
import source.suprememc.blocks.BlockSMC;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class WallBlockSMC extends WallBlock
{
	public WallBlockSMC(Block block) 
	{
		super(Properties.copy(block));
		RegUtil.registerBlock(this, ((BlockSMC)block).getBlockName().replace("_block", "").replace("_planks", "").replace("tiles", "tile").replace("bricks", "brick") + "_wall", SMCTabs.DECORATION_BLOCKS);	
	}
	
	public WallBlockSMC(String name, Block block) 
	{
		super(Properties.copy(block));
		RegUtil.registerBlock(this, name, SMCTabs.DECORATION_BLOCKS);
	}
}
