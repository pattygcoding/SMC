package source.suprememc.blocks.sub;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import source.suprememc.blocks.BlockSMC;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.util.RegUtil;

public class CopperSlabBlockSMC extends SlabBlock
{
	// Variables for ticking updates
	private static final int TICK_WAIT_TIME = 24000;
	private static final int TICK_RANDOM_TIME = 1125;

	// These lists will be used to link the input blocks to output blocks when an update occurs (see the use and tick functions)
	protected static final Map<Block, Block> WAXABLES = new HashMap<Block, Block>();
	protected static final Map<Block, Block> AXEABLES = new HashMap<Block, Block>();
	protected static final Map<Block, Block> ERODABLES = new HashMap<Block, Block>();


	public CopperSlabBlockSMC(Block block) 
	{
		super(Properties.copy(block));
		RegUtil.registerBlock(this, ((BlockSMC)block).getBlockName() + "_slab", SMCTabs.BUILDING_BLOCKS);
	}

	/**
	 * Please refer to CopperBlockSMC.java for comments, it is the exact same code idea (with a few exceptions,
	 * which are noted accordingly)
	 */
	@Override
	public ActionResultType use(BlockState state, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult hit) 
	{
		Item itemInHand = playerEntity.getItemInHand(hand).getItem();
		if(itemInHand == Items.HONEYCOMB)
		{
			WAXABLES.put(SMCBlocks.CUT_COPPER_SLAB, SMCBlocks.WAXED_CUT_COPPER_SLAB);
			WAXABLES.put(SMCBlocks.EXPOSED_CUT_COPPER_SLAB, SMCBlocks.WAXED_EXPOSED_CUT_COPPER_SLAB);
			WAXABLES.put(SMCBlocks.WEATHERED_CUT_COPPER_SLAB, SMCBlocks.WAXED_WEATHERED_CUT_COPPER_SLAB);
			WAXABLES.put(SMCBlocks.OXIDIZED_CUT_COPPER_SLAB, SMCBlocks.WAXED_OXIDIZED_CUT_COPPER_SLAB);
			if(this == SMCBlocks.CUT_COPPER_SLAB || this == SMCBlocks.EXPOSED_CUT_COPPER_SLAB || this == SMCBlocks.WEATHERED_CUT_COPPER_SLAB || this == SMCBlocks.OXIDIZED_CUT_COPPER_SLAB)
			{
				Block block = WAXABLES.get(this);
				
				// This line checks to make sure the output block is in the corresponding shape to the input
				world.setBlockAndUpdate(blockPos, block.defaultBlockState().setValue(SlabBlock.TYPE, state.getValue(SlabBlock.TYPE)).setValue(SlabBlock.WATERLOGGED, state.getValue(SlabBlock.WATERLOGGED)));
				spawnParticles(world, blockPos);
				if(!playerEntity.isCreative())
				{
					playerEntity.getItemInHand(hand).shrink(1);
				}
				return ActionResultType.PASS;
			}
		}
		else if(itemInHand instanceof AxeItem)
		{
			AXEABLES.put(SMCBlocks.WAXED_CUT_COPPER_SLAB, SMCBlocks.CUT_COPPER_SLAB);
			AXEABLES.put(SMCBlocks.WAXED_EXPOSED_CUT_COPPER_SLAB, SMCBlocks.EXPOSED_CUT_COPPER_SLAB);
			AXEABLES.put(SMCBlocks.WAXED_WEATHERED_CUT_COPPER_SLAB, SMCBlocks.WEATHERED_CUT_COPPER_SLAB);
			AXEABLES.put(SMCBlocks.WAXED_OXIDIZED_CUT_COPPER_SLAB, SMCBlocks.OXIDIZED_CUT_COPPER_SLAB);
			AXEABLES.put(SMCBlocks.EXPOSED_CUT_COPPER_SLAB, SMCBlocks.CUT_COPPER_SLAB);
			AXEABLES.put(SMCBlocks.WEATHERED_CUT_COPPER_SLAB, SMCBlocks.EXPOSED_CUT_COPPER_SLAB);
			AXEABLES.put(SMCBlocks.OXIDIZED_CUT_COPPER_SLAB, SMCBlocks.WEATHERED_CUT_COPPER_SLAB);
			if(this == SMCBlocks.WAXED_CUT_COPPER_SLAB || this == SMCBlocks.WAXED_EXPOSED_CUT_COPPER_SLAB || this == SMCBlocks.WAXED_WEATHERED_CUT_COPPER_SLAB
					|| this == SMCBlocks.WAXED_OXIDIZED_CUT_COPPER_SLAB || this == SMCBlocks.OXIDIZED_CUT_COPPER_SLAB || this == SMCBlocks.EXPOSED_CUT_COPPER_SLAB || this == SMCBlocks.WEATHERED_CUT_COPPER_SLAB)
			{
				Block block = AXEABLES.get(this);
				
				// This line checks to make sure the output block is in the corresponding shape to the input
				world.setBlockAndUpdate(blockPos, block.defaultBlockState().setValue(SlabBlock.TYPE, state.getValue(SlabBlock.TYPE)).setValue(SlabBlock.WATERLOGGED, state.getValue(SlabBlock.WATERLOGGED)));
				spawnParticles(world, blockPos);
				if(!playerEntity.isCreative()) playerEntity.getItemInHand(hand).hurtAndBreak(1, playerEntity, (x) -> {x.broadcastBreakEvent(hand);});
				return ActionResultType.PASS;
			}

		}
		return ActionResultType.FAIL;
	}

	@Override
	public void tick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) 
	{

		ERODABLES.put(SMCBlocks.CUT_COPPER_SLAB, SMCBlocks.EXPOSED_CUT_COPPER_SLAB);
		ERODABLES.put(SMCBlocks.EXPOSED_CUT_COPPER_SLAB, SMCBlocks.WEATHERED_CUT_COPPER_SLAB);
		ERODABLES.put(SMCBlocks.WEATHERED_CUT_COPPER_SLAB, SMCBlocks.OXIDIZED_CUT_COPPER_SLAB);
		if(this == SMCBlocks.CUT_COPPER_SLAB || this == SMCBlocks.EXPOSED_CUT_COPPER_SLAB || this == SMCBlocks.WEATHERED_CUT_COPPER_SLAB)
		{
			Block block = ERODABLES.get(this);
			
			// This line checks to make sure the output block is in the corresponding shape to the input
			serverWorld.setBlockAndUpdate(blockPos, block.defaultBlockState().setValue(SlabBlock.TYPE, blockState.getValue(SlabBlock.TYPE)).setValue(SlabBlock.WATERLOGGED, blockState.getValue(SlabBlock.WATERLOGGED)));
		}
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, IWorld iWorld, BlockPos blockPos, BlockPos blockPos2) 
	{
		iWorld.getBlockTicks().scheduleTick(blockPos, this, TICK_WAIT_TIME + iWorld.getRandom().nextInt(TICK_RANDOM_TIME));
		return super.updateShape(blockState, direction, blockState2, iWorld, blockPos, blockPos2);
	}

	@Override @Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		context.getLevel().getBlockTicks().scheduleTick(context.getClickedPos(), this, TICK_WAIT_TIME + context.getLevel().getRandom().nextInt(TICK_RANDOM_TIME));
		return super.getStateForPlacement(context);
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
				world.addParticle(ParticleTypes.HAPPY_VILLAGER, (double)blockPos.getX() + d1, (double)blockPos.getY() + d2, (double)blockPos.getZ() + d3, 0.0D, 0.0D, 0.0D);
			}
		}
	}
}
