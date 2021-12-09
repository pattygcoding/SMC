package source.suprememc.blocks;

import net.minecraft.block.RotatedPillarBlock;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

/**
 * This class is similar to BlockSMC but it extends RotatedPillarBlock.class
 * instead of Block.class
 */
public class RotatedPillarBlockSMC extends RotatedPillarBlock
{
	public RotatedPillarBlockSMC(String name, Properties properties) 
	{
		// Pass the properties to the constructor in Block.class
		super(properties);
		
		// Register the block to the game
		RegUtil.registerBlock(this, name, SMCTabs.BUILDING_BLOCKS);
	}
}
