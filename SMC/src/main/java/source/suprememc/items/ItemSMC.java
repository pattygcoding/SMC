package source.suprememc.items;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class ItemSMC extends Item
{
	public ItemSMC(String name) 
	{
		super(new Properties().tab(SMCTabs.MISCELLANEOUS));
		RegUtil.registerItem(this, name);
	}
	
	public ItemSMC(String name, ItemGroup tab) 
	{
		super(new Properties().tab(tab));
		RegUtil.registerItem(this, name);
	}
	@Override
	public int getBurnTime(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return super.getBurnTime(itemStack);
	}
	// For foods
	public ItemSMC(String name, Food food) 
	{
		super(new Properties().tab(SMCTabs.FOODS).food(food));
		RegUtil.registerItem(this, name);
	}
	
	// For inedibles
	public ItemSMC(String name, int num) 
	{
		super(new Properties().tab(SMCTabs.FOODS));
		RegUtil.registerItem(this, name);
	}
	
	// For holding objects
	public ItemSMC(Item container, int num, String name, ItemGroup tab) 
	{
		super(new Properties().craftRemainder(container).stacksTo(num).tab(tab));
		RegUtil.registerItem(this, name);
	}
}