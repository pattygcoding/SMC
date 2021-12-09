package source.suprememc.items.tools;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class ShearsItemSMC extends Item 
{
	public ShearsItemSMC(int durability, String name) 
	{
		super(new Properties().durability(durability).tab(SMCTabs.TOOLS));
		RegUtil.registerItem(this, name);
	}

	@Override
	public boolean mineBlock(ItemStack itemStack, World world, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) 
	{
		if (!world.isClientSide && !blockState.getBlock().is(BlockTags.FIRE)) 
		{
			itemStack.hurtAndBreak(1, livingEntity, (entity) -> 
			{
				entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
			});
		}
		return !blockState.is(BlockTags.LEAVES) && !blockState.is(Blocks.COBWEB) && !blockState.is(Blocks.GRASS) && !blockState.is(Blocks.FERN) && !blockState.is(Blocks.DEAD_BUSH) && !blockState.is(Blocks.VINE) && !blockState.is(Blocks.TRIPWIRE) && !blockState.is(BlockTags.WOOL) ? super.mineBlock(itemStack, world, blockState, blockPos, livingEntity) : true;
	}

	@Override
	public boolean isCorrectToolForDrops(BlockState blockState) 
	{
		return blockState.is(Blocks.COBWEB) || blockState.is(Blocks.REDSTONE_WIRE) || blockState.is(Blocks.TRIPWIRE);
	}

	@Override
	public float getDestroySpeed(ItemStack itemStack, BlockState blockState) 
	{
		if (!blockState.is(Blocks.COBWEB) && !blockState.is(BlockTags.LEAVES)) 
		{
			return blockState.is(BlockTags.WOOL) ? 2.5F : super.getDestroySpeed(itemStack, blockState);
		} 
		else return 7.5F;
	}

	@Override
	public net.minecraft.util.ActionResultType interactLivingEntity(ItemStack stack, net.minecraft.entity.player.PlayerEntity playerIn, LivingEntity entity, net.minecraft.util.Hand hand) 
	{
		if (entity.level.isClientSide) return net.minecraft.util.ActionResultType.PASS;
		if (entity instanceof net.minecraftforge.common.IForgeShearable) {
			net.minecraftforge.common.IForgeShearable target = (net.minecraftforge.common.IForgeShearable)entity;
			BlockPos pos = new BlockPos(entity.getX(), entity.getY(), entity.getZ());
			if (target.isShearable(stack, entity.level, pos)) {
				java.util.List<ItemStack> drops = target.onSheared(playerIn, stack, entity.level, pos,
						net.minecraft.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.enchantment.Enchantments.BLOCK_FORTUNE, stack));
				java.util.Random rand = new java.util.Random();
				drops.forEach(d -> {
					net.minecraft.entity.item.ItemEntity ent = entity.spawnAtLocation(d, 1.0F);
					ent.setDeltaMovement(ent.getDeltaMovement().add((double)((rand.nextFloat() - rand.nextFloat()) * 0.1F), (double)(rand.nextFloat() * 0.05F), (double)((rand.nextFloat() - rand.nextFloat()) * 0.1F)));
				});
				stack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(hand));
			}
			return net.minecraft.util.ActionResultType.SUCCESS;
		}
		return net.minecraft.util.ActionResultType.PASS;
	}
}