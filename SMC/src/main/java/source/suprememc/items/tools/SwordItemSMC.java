package source.suprememc.items.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class SwordItemSMC extends SwordItem
{
	private boolean fire = false;
	private String name;
	public SwordItemSMC(IItemTier tier) 
	{
		super(tier, 3, -2.4F, new Properties().tab(SMCTabs.COMBAT));
		this.name = ((ToolMaterialSMC) tier).getName() + "_sword";
		RegUtil.registerItem(this, name);
	}
	public SwordItemSMC(IItemTier tier, String name) 
	{
		super(tier, 3, -2.4F, new Properties().tab(SMCTabs.COMBAT));
		this.name = name;
		RegUtil.registerItem(this, name);
	}
	public SwordItemSMC(IItemTier tier, boolean fire) 
	{
		this(tier);
		this.fire = fire;
	}
	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) 
	{
		if(fire) entity.setRemainingFireTicks(5);
		return super.onLeftClickEntity(stack, player, entity);
	}
}
