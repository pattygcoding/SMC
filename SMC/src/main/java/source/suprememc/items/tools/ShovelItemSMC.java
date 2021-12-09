package source.suprememc.items.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class ShovelItemSMC extends ShovelItem
{
	private boolean fire = false;
	private String name;
	public ShovelItemSMC(IItemTier tier) 
	{
		super(tier, 0.0F, -3.0F, new Properties().tab(SMCTabs.TOOLS));
		this.name = ((ToolMaterialSMC) tier).getName() + "_shovel";
		RegUtil.registerItem(this, this.name);
	}
	
	public ShovelItemSMC(IItemTier tier, String name) 
	{
		super(tier, 0.0F, -3.0F, new Properties().tab(SMCTabs.TOOLS));
		this.name = name;
		RegUtil.registerItem(this, this.name);
	}
	
	public ShovelItemSMC(IItemTier tier, boolean fire) 
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
