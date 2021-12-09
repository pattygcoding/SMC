package source.suprememc.blocks.plant;

import net.minecraft.block.FlowerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Effect;
import source.suprememc.client.RenderBlocks;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class FlowerBlockSMC extends FlowerBlock
{
	public FlowerBlockSMC(String name, Effect effect, int duration)
	{
		super(effect, duration, Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));
		RegUtil.registerBlock(this, name, SMCTabs.DECORATION_BLOCKS);
		RenderBlocks.RENDER_BLOCK_LIST_2.add(this);
	}
}
