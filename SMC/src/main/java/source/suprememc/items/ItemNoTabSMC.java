package source.suprememc.items;

import net.minecraft.item.Item;
import source.suprememc.util.RegUtil;

public class ItemNoTabSMC extends Item
{
	public ItemNoTabSMC(String name) 
	{
		super(new Properties());
		RegUtil.registerItem(this, name);
	}
}