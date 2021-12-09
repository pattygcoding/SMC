package source.suprememc.blocks.interactive;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import source.suprememc.client.RenderBlocks;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.tileentity.brewingstand.BrewingStandTileEntitySMC;
import source.suprememc.util.RegUtil;

public class SoulBrewingStandSMC extends ContainerBlock 
{
	public static final BooleanProperty[] HAS_BOTTLE = new BooleanProperty[]{BlockStateProperties.HAS_BOTTLE_0, BlockStateProperties.HAS_BOTTLE_1, BlockStateProperties.HAS_BOTTLE_2};
	protected static final VoxelShape SHAPE = VoxelShapes.or(Block.box(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D), Block.box(7.0D, 0.0D, 7.0D, 9.0D, 14.0D, 9.0D));

	public SoulBrewingStandSMC(String name) 
	{
		super(AbstractBlock.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(0.5F).lightLevel((x) -> {return 1;}).noOcclusion());
		this.registerDefaultState(this.stateDefinition.any().setValue(HAS_BOTTLE[0], Boolean.valueOf(false)).setValue(HAS_BOTTLE[1], Boolean.valueOf(false)).setValue(HAS_BOTTLE[2], Boolean.valueOf(false)));
		RegUtil.registerBlock(this, name, SMCTabs.BREWING);
		RenderBlocks.RENDER_BLOCK_LIST_2.add(this);
	}

	@Override
	public BlockRenderType getRenderShape(BlockState blockState) 
	{
		return BlockRenderType.MODEL;
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader iBlockReader) 
	{
		return new BrewingStandTileEntitySMC();
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos, ISelectionContext iSelectionContext) 
	{
		return SHAPE;
	}

	@Override
	public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace) 
	{
		if (world.isClientSide) return ActionResultType.SUCCESS;
		else 
		{
			TileEntity tileentity = world.getBlockEntity(blockPos);
			if (tileentity instanceof BrewingStandTileEntitySMC) 
			{
				player.openMenu((BrewingStandTileEntitySMC)tileentity);
				player.awardStat(Stats.INTERACT_WITH_BREWINGSTAND);
			}
			return ActionResultType.CONSUME;
		}
	}

	@Override
	public void setPlacedBy(World world, BlockPos blockPos, BlockState blockState, LivingEntity entity, ItemStack itemStack) 
	{
		if (itemStack.hasCustomHoverName()) 
		{
			TileEntity tileentity = world.getBlockEntity(blockPos);
			if (tileentity instanceof BrewingStandTileEntitySMC) ((BrewingStandTileEntitySMC)tileentity).setCustomName(itemStack.getHoverName());
			
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState blockState, World world, BlockPos blockPos, Random random) 
	{
		double d0 = (double)blockPos.getX() + 0.4D + (double)random.nextFloat() * 0.2D;
		double d1 = (double)blockPos.getY() + 0.7D + (double)random.nextFloat() * 0.3D;
		double d2 = (double)blockPos.getZ() + 0.4D + (double)random.nextFloat() * 0.2D;
		world.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}

	@SuppressWarnings("deprecation")
	public void onRemove(BlockState blockState, World world, BlockPos blockPos, BlockState state2, boolean flag) 
	{
		if (!blockState.is(state2.getBlock())) 
		{
			TileEntity tileentity = world.getBlockEntity(blockPos);
			if (tileentity instanceof BrewingStandTileEntitySMC) InventoryHelper.dropContents(world, blockPos, (BrewingStandTileEntitySMC)tileentity);
			super.onRemove(blockState, world, blockPos, state2, flag);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState block) 
	{
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState block, World world, BlockPos pos) 
	{
		return Container.getRedstoneSignalFromBlockEntity(world.getBlockEntity(pos));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(HAS_BOTTLE[0], HAS_BOTTLE[1], HAS_BOTTLE[2]);
	}

	@Override
	public boolean isPathfindable(BlockState state, IBlockReader reader, BlockPos pos, PathType path) 
	{
		return false;
	}
}
