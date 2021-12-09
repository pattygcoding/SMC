package source.suprememc.blocks.redstone;


import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import source.suprememc.blocks.BlockSMC;

public class RedstoneOreBlockSMC extends BlockSMC
{
	public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
	private int minXP, maxXP;
	public RedstoneOreBlockSMC(String name, int minXP, int maxXP, Properties properties) 
	{
		super(name, properties);
		this.minXP = minXP;
		this.maxXP = maxXP;
	}

	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) 
	{
		if(silktouch == 0) return ((int) (Math.random()*(this.maxXP - this.minXP))) + this.minXP;
		else return 0;
	}

	@Override @SuppressWarnings("deprecation")
	public void attack(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity) 
	{
		interact(blockState, world, blockPos);
		super.attack(blockState, world, blockPos, playerEntity);
	}

	@Override
	public void stepOn(World world, BlockPos blockPos, Entity entity) 
	{
		interact(world.getBlockState(blockPos), world, blockPos);
		super.stepOn(world, blockPos, entity);
	}

	@Override
	public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) 
	{
		if (world.isClientSide) spawnParticles(world, blockPos);
		else interact(blockState, world, blockPos);	
		ItemStack itemstack = playerEntity.getItemInHand(hand);
		return itemstack.getItem() instanceof BlockItem && (new BlockItemUseContext(playerEntity, hand, itemstack, blockRayTraceResult)).canPlace() ? ActionResultType.PASS : ActionResultType.SUCCESS;
	}

	private static void interact(BlockState blockState, World world, BlockPos blockPos) 
	{
		spawnParticles(world, blockPos);
		if (!blockState.getValue(LIT)) world.setBlock(blockPos, blockState.setValue(LIT, Boolean.valueOf(true)), 3);
	}

	@Override
	public boolean isRandomlyTicking(BlockState blockState) 
	{
		return blockState.getValue(LIT);
	}

	@Override
	public void randomTick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) 
	{
		if (blockState.getValue(LIT)) serverWorld.setBlock(blockPos, blockState.setValue(LIT, Boolean.valueOf(false)), 3);
	}

	@Override @SuppressWarnings("deprecation")
	public void spawnAfterBreak(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, ItemStack itemStack) 
	{
		super.spawnAfterBreak(blockState, serverWorld, blockPos, itemStack);
	}


	@Override @OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockState, World world, BlockPos blockPos, Random random) 
	{
		if (blockState.getValue(LIT)) spawnParticles(world, blockPos);
	}

	private static void spawnParticles(World world, BlockPos blockPos) 
	{
		Random random = world.random;
		for(Direction direction : Direction.values()) 
		{
			BlockPos blockpos = blockPos.relative(direction);
			if (!world.getBlockState(blockpos).isSolidRender(world, blockpos)) 
			{
				Direction.Axis direction$axis = direction.getAxis();
				double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getStepX() : (double)random.nextFloat();
				double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getStepY() : (double)random.nextFloat();
				double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getStepZ() : (double)random.nextFloat();
				world.addParticle(RedstoneParticleData.REDSTONE, (double)blockPos.getX() + d1, (double)blockPos.getY() + d2, (double)blockPos.getZ() + d3, 0.0D, 0.0D, 0.0D);
			}
		}
	}
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(LIT);
	}

}