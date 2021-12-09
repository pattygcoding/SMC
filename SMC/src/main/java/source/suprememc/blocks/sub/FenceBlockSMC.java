package source.suprememc.blocks.sub;

import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import source.suprememc.blocks.BlockSMC;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class FenceBlockSMC extends FenceBlock
{
	public FenceBlockSMC(Block block) 
	{
		super(Properties.copy(block));
		RegUtil.registerBlock(this, ((BlockSMC)block).getBlockName().replace("_planks", "") + "_fence", SMCTabs.DECORATION_BLOCKS);
	}
}
