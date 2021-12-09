package source.suprememc.items.tools;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class MobRemoverSMC extends SwordItem
{
	public MobRemoverSMC(String name, IItemTier tier) 
	{
		super(tier, 3, 0.0F, new Properties().tab(SMCTabs.COMBAT));
		RegUtil.registerItem(this, name);
	}
}
