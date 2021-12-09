package source.suprememc.items.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class PickaxeItemSMC extends PickaxeItem
{
	private boolean fire = false;
	private String name;
	public PickaxeItemSMC(IItemTier tier) 
	{
		super(tier, 1, -2.8F, new Properties().tab(SMCTabs.TOOLS));
		this.name = ((ToolMaterialSMC) tier).getName() + "_pickaxe";
		RegUtil.registerItem(this, this.name);
	}
	public PickaxeItemSMC(IItemTier tier, String name) 
	{
		super(tier, 1, -2.8F, new Properties().tab(SMCTabs.TOOLS));
		this.name = name;
		RegUtil.registerItem(this, this.name);
	}
	public PickaxeItemSMC(IItemTier tier, boolean fire) 
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