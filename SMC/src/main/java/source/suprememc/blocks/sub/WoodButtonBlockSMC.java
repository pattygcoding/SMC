package source.suprememc.blocks.sub;

import net.minecraft.block.SoundType;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class WoodButtonBlockSMC extends WoodButtonBlock
{
	public WoodButtonBlockSMC(String name) 
	{
		super(Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD));
		RegUtil.registerBlock(this, name, SMCTabs.REDSTONE);
	}

}