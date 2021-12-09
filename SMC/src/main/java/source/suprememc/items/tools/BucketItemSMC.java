package source.suprememc.items.tools;

import java.util.function.Supplier;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class BucketItemSMC extends BucketItem
{
	public BucketItemSMC(Supplier<? extends Fluid> fluid, String name) 
	{
		super(fluid, new Properties().tab(SMCTabs.MISCELLANEOUS));
		RegUtil.registerItem(this, name);
	}
}
