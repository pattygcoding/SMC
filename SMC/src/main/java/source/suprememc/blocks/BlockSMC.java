package source.suprememc.blocks;

import net.minecraft.block.Block;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class BlockSMC extends Block
{
	private String name;
	
	public BlockSMC(String name, Properties properties) 
	{
		// Pass the properties to the constructor in Block.class
		super(properties);
		
		// Assign the name to the private variable used in getBlockName()
		this.name = name;
		
		// Register the block to the game
		RegUtil.registerBlock(this, this.name, SMCTabs.BUILDING_BLOCKS);
	}
	
	// This function is used for sub-blocks such as stairs and slabs to make naming easier in SMCBlocks.java
	public String getBlockName() 
	{
		return this.name;
	}
}
