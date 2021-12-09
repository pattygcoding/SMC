package source.suprememc.items.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRideable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class OnAStickItemSMC<T extends Entity & IRideable> extends Item 
{
	private final EntityType<T> canInteractWith;
	private final int consumeItemDamage;

	public OnAStickItemSMC(int durability, String name, EntityType<T> canInteractWith, int consumeItemDamage) 
	{
		super(new Properties().durability(durability).tab(SMCTabs.TRANSPORTATION));
		RegUtil.registerItem(this, name);
		this.canInteractWith = canInteractWith;
		this.consumeItemDamage = consumeItemDamage;
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		ItemStack itemstack = playerEntity.getItemInHand(hand);
		if (world.isClientSide) {
			return ActionResult.pass(itemstack);
		} else {
			Entity entity = playerEntity.getVehicle();
			if (playerEntity.isPassenger() && entity instanceof IRideable && entity.getType() == this.canInteractWith) {
				IRideable irideable = (IRideable)entity;
				if (irideable.boost()) {
					itemstack.hurtAndBreak(this.consumeItemDamage, playerEntity, (p_234682_1_) -> {
						p_234682_1_.broadcastBreakEvent(hand);
					});
					if (itemstack.isEmpty()) {
						ItemStack itemstack1 = new ItemStack(Items.FISHING_ROD);
						itemstack1.setTag(itemstack.getTag());
						return ActionResult.success(itemstack1);
					}

					return ActionResult.success(itemstack);
				}
			}

			playerEntity.awardStat(Stats.ITEM_USED.get(this));
			return ActionResult.pass(itemstack);
		}
	}
}