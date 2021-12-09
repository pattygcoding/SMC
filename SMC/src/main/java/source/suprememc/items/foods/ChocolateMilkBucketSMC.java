package source.suprememc.items.foods;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.init.objects.SMCFoods;
import source.suprememc.util.RegUtil;

public class ChocolateMilkBucketSMC extends Item
{
	public ChocolateMilkBucketSMC(String name) 
	{
		super(new Properties().craftRemainder(Items.BUCKET).stacksTo(1).food(SMCFoods.CHOCOLATE).tab(SMCTabs.FOODS));
		RegUtil.registerItem(this, name);
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) 
	{
		
		if (entityLiving instanceof ServerPlayerEntity) 
		{
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}

		if (!worldIn.isClientSide) entityLiving.curePotionEffects(stack);
		
		if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.instabuild) 
		{
			stack.shrink(1);
		}

		return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;
	}
	
	@Override
	public int getUseDuration(ItemStack stack) {return 32;}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {return UseAction.DRINK;}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {return DrinkHelper.useDrink(worldIn, playerIn, handIn);}

	  
}