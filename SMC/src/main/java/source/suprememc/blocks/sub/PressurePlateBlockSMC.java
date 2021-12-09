package source.suprememc.blocks.sub;

import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SoundType;
import source.suprememc.blocks.BlockSMC;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

@SuppressWarnings("deprecation")
public class PressurePlateBlockSMC extends PressurePlateBlock
{
	public PressurePlateBlockSMC(Block block) 
	{
		super(((block.getSoundType(block.defaultBlockState()) == SoundType.WOOD) ? Sensitivity.EVERYTHING : Sensitivity.MOBS), Properties.copy(block).noCollission());
		RegUtil.registerBlock(this, ((BlockSMC)block).getBlockName().replace("_block", "").replace("_planks", "").replace("bricks", "brick") + "_pressure_plate", SMCTabs.REDSTONE);
	}
	
	public PressurePlateBlockSMC(String name, Block block) 
	{
		super(((block.getSoundType(block.defaultBlockState()) == SoundType.WOOD) ? Sensitivity.EVERYTHING : Sensitivity.MOBS), Properties.copy(block).noCollission());
		RegUtil.registerBlock(this, name, SMCTabs.REDSTONE);
	}
}