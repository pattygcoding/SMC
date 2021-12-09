package source.suprememc.blocks.sub;

import net.minecraft.block.SoundType;
import net.minecraft.block.StoneButtonBlock;
import net.minecraft.block.material.Material;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class StoneButtonBlockSMC extends StoneButtonBlock
{
	public StoneButtonBlockSMC(String name) 
	{
		super(Properties.of(Material.STONE).noCollission().strength(0.5F).sound(SoundType.WOOD));
		RegUtil.registerBlock(this, name, SMCTabs.REDSTONE);
	}

}