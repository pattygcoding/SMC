package source.suprememc.items.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class HoeItemSMC extends HoeItem
{
	private boolean fire = false;
	private String name;
	public HoeItemSMC(IItemTier tier) 
	{
		super(tier, (int)(0.0F - tier.getAttackDamageBonus()), (float)(tier.getLevel() + 1), new Properties().tab(SMCTabs.TOOLS));
		this.name = ((ToolMaterialSMC) tier).getName() + "_hoe";
		RegUtil.registerItem(this, this.name);
	}
	public HoeItemSMC(IItemTier tier, String name) 
	{
		super(tier, (int)(0.0F - tier.getAttackDamageBonus()), (float)(tier.getLevel() + 1), new Properties().tab(SMCTabs.TOOLS));
		this.name = name;
		RegUtil.registerItem(this, this.name);
	}
	public HoeItemSMC(IItemTier tier, boolean fire) 
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
