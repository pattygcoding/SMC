package source.suprememc.items.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class AxeItemSMC extends AxeItem
{
	private boolean fire = false;
	private String name;
	
	public AxeItemSMC(IItemTier tier, float attackDamageIn, float attackSpeedIn) 
	{
		super(tier, attackDamageIn - tier.getAttackDamageBonus() - 1.0F, attackSpeedIn - 4.0F, new Properties().tab(SMCTabs.TOOLS));
		this.name = ((ToolMaterialSMC) tier).getName() + "_axe";
		RegUtil.registerItem(this, this.name);
	}
	
	public AxeItemSMC(IItemTier tier, float attackDamageIn, float attackSpeedIn, String name) 
	{
		super(tier, attackDamageIn - tier.getAttackDamageBonus() - 1.0F, attackSpeedIn - 4.0F, new Properties().tab(SMCTabs.TOOLS));
		this.name = name;
		RegUtil.registerItem(this, this.name);
	}
	
	public AxeItemSMC(IItemTier tier, float attackDamageIn, float attackSpeedIn, boolean fire) 
	{
		this(tier, attackDamageIn, attackSpeedIn);
		this.fire = fire;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) 
	{
		if(fire) entity.setRemainingFireTicks(5);
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	
	
}