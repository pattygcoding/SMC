package source.suprememc.items.tools;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class FishingRodItemSMC extends Item implements IVanishable 
{
	public FishingRodItemSMC(int durability, String name) 
	{
		super(new Properties().durability(durability).tab(SMCTabs.TOOLS));
		RegUtil.registerItem(this, name);
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) 
	{
		ItemStack itemstack = playerEntity.getItemInHand(hand);
		if (playerEntity.fishing != null) 
		{
			if (!world.isClientSide) 
			{
				int i = playerEntity.fishing.retrieve(itemstack);
				itemstack.hurtAndBreak(i, playerEntity, (entity) -> 
				{
					entity.broadcastBreakEvent(hand);
				});
			}
			world.playSound((PlayerEntity)null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		} 
		else 
		{
			world.playSound((PlayerEntity)null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
			if (!world.isClientSide)
			{
				int k = 2 * EnchantmentHelper.getFishingSpeedBonus(itemstack);
				int j = 2 * EnchantmentHelper.getFishingLuckBonus(itemstack);
				world.addFreshEntity(new FishingBobberEntity(playerEntity, world, j, k));
			}
			playerEntity.awardStat(Stats.ITEM_USED.get(this));
		}
		return ActionResult.sidedSuccess(itemstack, world.isClientSide());
	}

	@Override
	public int getEnchantmentValue() 
	{
		return 1;
	}
}