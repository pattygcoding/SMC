package source.suprememc.blocks.sub;

import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import source.suprememc.blocks.BlockSMC;
import source.suprememc.client.RenderBlocks;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class DoorBlockSMC extends DoorBlock
{
	public DoorBlockSMC(Block block) 
	{
		super(Properties.copy(block).noCollission());
		RegUtil.registerBlock(this, ((BlockSMC)block).getBlockName().replace("_planks", "") + "_door", SMCTabs.REDSTONE);
		RenderBlocks.RENDER_BLOCK_LIST_2.add(this);
	}
}
