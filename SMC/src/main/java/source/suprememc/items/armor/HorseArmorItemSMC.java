package source.suprememc.items.armor;

import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class HorseArmorItemSMC extends HorseArmorItem
{
	public HorseArmorItemSMC(int armorValue, String texture) 
	{
		super(armorValue, texture, (new Item.Properties()).stacksTo(1).tab(SMCTabs.MISCELLANEOUS));
		RegUtil.registerItem(this, texture + "_horse_armor");
	}
}
