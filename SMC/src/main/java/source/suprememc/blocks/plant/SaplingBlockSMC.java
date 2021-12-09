package source.suprememc.blocks.plant;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.Tree;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.PlantType;
import source.suprememc.client.RenderBlocks;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.util.RegUtil;

public class SaplingBlockSMC extends SaplingBlock implements IGrowable
{
	public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
	public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
	private final Tree tree;

	public SaplingBlockSMC(String name, Tree tree)
	{
		super(tree, Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));
		this.tree = tree;
		this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, Integer.valueOf(0)));
		RegUtil.registerBlock(this, name, SMCTabs.DECORATION_BLOCKS);
		RenderBlocks.RENDER_BLOCK_LIST_2.add(this);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext)
	{
		return SHAPE;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) 
	{
		if(worldIn.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(7) == 0) 
		{
			if(!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
			this.advanceTree(worldIn, pos, state, random);
		}
	}
	
	// performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state)
	
	@Override
	public void advanceTree(ServerWorld world, BlockPos pos, BlockState state, Random rand)
	{
		if(state.getValue(STAGE) == 0) world.setBlock(pos, state.cycle(STAGE), 4);
		else
		{
			if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(world, rand, pos)) return;
			this.tree.growTree(world, world.getChunkSource().getGenerator(), pos, state, rand);
		}
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
	{
		return true;
	}
	
	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state)
	{
		return (double)worldIn.random.nextFloat() < 0.45D;
	}

	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) 
	{
		this.advanceTree(worldIn, pos, state, rand);
	}
	
	@Override
	public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(STAGE);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) 
	{
		Block ground = worldIn.getBlockState(pos.below()).getBlock();
		//Block ground = state.getBlock();
		if(this == SMCBlocks.PALM_SAPLING)
		{
			return ground == SMCBlocks.ARABLE_SAND || ground == Blocks.RED_SAND || ground == Blocks.SAND || super.mayPlaceOn(state, worldIn, pos);
		}
		return super.mayPlaceOn(state, worldIn, pos);
	}
	
	
	@Override
	public BlockRenderType getRenderShape(BlockState state) 
	{
		return BlockRenderType.MODEL;
	}
	
	@Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
    	return PlantType.PLAINS;
    }
	
	
}