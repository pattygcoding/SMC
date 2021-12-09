package source.suprememc.blocks.sub;

import net.minecraft.block.Block;
import net.minecraft.block.FenceGateBlock;
import source.suprememc.blocks.BlockSMC;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class FenceGateBlockSMC extends FenceGateBlock
{
	public FenceGateBlockSMC(Block block) 
	{
		super(Properties.copy(block));
		RegUtil.registerBlock(this, ((BlockSMC)block).getBlockName().replace("_block", "").replace("_planks", "").replace("bricks", "brick") + "_fence_gate", SMCTabs.REDSTONE);
		
	}
	
	// For the nether brick fence gate
	public FenceGateBlockSMC(String name, Block block) 
	{
		super(Properties.copy(block));
		RegUtil.registerBlock(this, name, SMCTabs.REDSTONE);
	}
}
