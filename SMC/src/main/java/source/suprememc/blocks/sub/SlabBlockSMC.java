package source.suprememc.blocks.sub;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import source.suprememc.blocks.BlockSMC;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class SlabBlockSMC extends SlabBlock
{
	public SlabBlockSMC(Block block) 
	{
		super(Properties.copy(block));
		RegUtil.registerBlock(this, ((BlockSMC)block).getBlockName().replace("_block", "").replace("_planks", "").replace("tiles", "tile").replace("bricks", "brick") + "_slab", SMCTabs.BUILDING_BLOCKS);	}
	
	public SlabBlockSMC(String name, Block block) 
	{
		super(Properties.copy(block));
		RegUtil.registerBlock(this, name, SMCTabs.BUILDING_BLOCKS);
	}
}
