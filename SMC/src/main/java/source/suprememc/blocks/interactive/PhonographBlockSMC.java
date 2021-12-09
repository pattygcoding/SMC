package source.suprememc.blocks.interactive;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import source.suprememc.client.RenderBlocks;
import source.suprememc.container.phonograph.PhonographContainerSMC;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class PhonographBlockSMC extends Block
{
	private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container.suprememc.phonograph");
	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	protected static final VoxelShape SHAPE	 = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);

	public PhonographBlockSMC(String name)
	{
		super(Properties.copy(Blocks.JUKEBOX));
		RegUtil.registerBlock(this, name, SMCTabs.DECORATION_BLOCKS);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
		RenderBlocks.RENDER_BLOCK_LIST_2.add(this);
	}

	@Override
	public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_,
			ISelectionContext p_220053_4_) {
		// TODO Auto-generated method stub
		return SHAPE;
	}
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) 
	{
		if (worldIn.isClientSide) return ActionResultType.SUCCESS;
		else 
		{
			player.openMenu(state.getMenuProvider(worldIn, pos));
			player.awardStat(Stats.INTERACT_WITH_STONECUTTER);
			return ActionResultType.CONSUME;
		}
	}

	@Nullable
	@Override
	public INamedContainerProvider getMenuProvider(BlockState state, World worldIn, BlockPos pos) 
	{
		return new SimpleNamedContainerProvider((id, inventory, player) -> 
		{
			return new PhonographContainerSMC(id, inventory, IWorldPosCallable.create(worldIn, pos));
		}, CONTAINER_NAME);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) 
	{
		return this.defaultBlockState().setValue(FACING, p_196258_1_.getHorizontalDirection().getOpposite());
	}

	@Override
	public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) 
	{
		return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
	}

	@Override
	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) 
	{
		return p_185471_1_.rotate(p_185471_2_.getRotation(p_185471_1_.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) 
	{
		p_206840_1_.add(FACING);
	}


}
