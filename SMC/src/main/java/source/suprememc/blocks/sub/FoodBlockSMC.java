package source.suprememc.blocks.sub;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

@SuppressWarnings("deprecation")
public class FoodBlockSMC extends Block
{
	private int hungerBite;
	private float hungerSat;
	public static final IntegerProperty BITES = BlockStateProperties.BITES;
	
	// The shape of the cake in each state
	protected static final VoxelShape[] SHAPES = new VoxelShape[]
	{
		// State 0 (full, no bites)
		Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), 
		// State 1
		Block.box(3.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), 
		// State 2
		Block.box(5.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), 
		// State 3
		Block.box(7.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), 
		// State 4
		Block.box(9.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), 
		// State 5
		Block.box(11.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D), 
		// State 6
		Block.box(13.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D)
	};

	public FoodBlockSMC(String name, int hungerBite, float hungerSat) 
	{
		super(Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL));
		this.hungerBite = hungerBite;
		this.hungerSat = hungerSat;
		this.registerDefaultState(this.stateDefinition.any().setValue(BITES, Integer.valueOf(0)));
		RegUtil.registerBlock(this, name, SMCTabs.FOODS);
	}

	// This function comes from abstract blocks 
	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos, ISelectionContext iSelectionContext) 
	{
		return SHAPES[blockState.getValue(BITES)];
	}

	
	public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult traceResult) 
	{
		if(world.isClientSide) 
		{
			ItemStack itemstack = playerEntity.getItemInHand(hand);
			if(this.eat(world, blockPos, blockState, playerEntity).consumesAction()) return ActionResultType.SUCCESS;
			if(itemstack.isEmpty()) return ActionResultType.CONSUME;
		}

		return this.eat(world, blockPos, blockState, playerEntity);
	}

	private ActionResultType eat(IWorld iWorld, BlockPos blockPos, BlockState blockState, PlayerEntity playerEntity) 
	{
		if(!playerEntity.canEat(false)) return ActionResultType.PASS;
		else 
		{
			playerEntity.awardStat(Stats.EAT_CAKE_SLICE);
			playerEntity.getFoodData().eat(this.hungerBite, this.hungerSat);
			int i = blockState.getValue(BITES);
			if(i < 6) iWorld.setBlock(blockPos, blockState.setValue(BITES, Integer.valueOf(i + 1)), 3);
			else iWorld.removeBlock(blockPos, false);
			return ActionResultType.SUCCESS;
		}
	}

	public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, IWorld iWorld, BlockPos blockPos, BlockPos blockPos2) 
	{
		return direction == Direction.DOWN && !blockState.canSurvive(iWorld, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState2, iWorld, blockPos, blockPos2);
	}

	public boolean canSurvive(BlockState blockState, IWorldReader iWorldReader, BlockPos blockPos) 
	{
		return iWorldReader.getBlockState(blockPos.below()).getMaterial().isSolid();
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(BITES);
	}

	public int getAnalogOutputSignal(BlockState blockState, World world, BlockPos blockPos) 
	{
		return (7 - blockState.getValue(BITES)) * 2;
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState blockState) 
	{
		return true;
	}

	@Override
	public boolean isPathfindable(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos, PathType pathType) 
	{
		return false;
	}
}
