package source.suprememc.blocks.plant;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import source.suprememc.client.RenderBlocks;
import source.suprememc.util.RegUtil;

@SuppressWarnings("deprecation")
public class BushBlockSMC extends BushBlock implements IGrowable
{
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	private static final VoxelShape BUSHLING_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	private static final VoxelShape GROWING_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	private Item item;
	   
	public BushBlockSMC(String name, Item item) 
	{
		super(Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.SWEET_BERRY_BUSH));
		this.item = item;
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
		RegUtil.registerBlockNoDisplay(this, name);
		RenderBlocks.RENDER_BLOCK_LIST_2.add(this);
	}
	
	@Override
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) 
	{
		return new ItemStack(this.item);
	}
	
	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) 
	{
		return state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT);
	}
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		if(state.getValue(AGE) == 0) return BUSHLING_SHAPE;
	    else return state.getValue(AGE) < 3 ? GROWING_SHAPE : super.getShape(state, worldIn, pos, context);

	}

	@Override
	public boolean isRandomlyTicking(BlockState state) 
	{
		return state.getValue(AGE) < 3;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) 
	{
		int i = state.getValue(AGE);
		if (i < 3 && worldIn.getRawBrightness(pos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state,random.nextInt(5) == 0)) 
		{
			worldIn.setBlock(pos, state.setValue(AGE, Integer.valueOf(i + 1)), 2);
			net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@Override
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) 
	{
		if(entityIn instanceof LivingEntity && entityIn.getType() != EntityType.FOX && entityIn.getType() != EntityType.BEE) 
		{
			entityIn.makeStuckInBlock(state, new Vector3d((double)0.8F, 0.75D, (double)0.8F));
			if(!worldIn.isClientSide && state.getValue(AGE) > 0 && (entityIn.xOld != entityIn.getX() || entityIn.zOld != entityIn.getZ())) 
			{
				double d0 = Math.abs(entityIn.getX() - entityIn.xOld);
	            double d1 = Math.abs(entityIn.getX() - entityIn.zOld);
	            if (d0 >= (double)0.003F || d1 >= (double)0.003F) 
	            {
	            	entityIn.hurt(DamageSource.SWEET_BERRY_BUSH, 1.0F);
	            }
			}
		}
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) 
	{
		int i = state.getValue(AGE);
		boolean flag = i == 3;
		if (!flag && player.getItemInHand(handIn).getItem() == Items.BONE_MEAL) return ActionResultType.PASS;
		else if(i > 1) 
		{
			int j = 1 + worldIn.random.nextInt(2);
			popResource(worldIn, pos, new ItemStack(this.item, j + (flag ? 1 : 0)));
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlock(pos, state.setValue(AGE, Integer.valueOf(1)), 2);
			return ActionResultType.sidedSuccess(worldIn.isClientSide);
		} 
		else return super.use(state, worldIn, pos, player, handIn, hit);   
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(AGE);
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) 
	{
		return state.getValue(AGE) < 3;
	}

	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) 
	{
		return true;
	}

	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) 
	{
		int i = Math.min(3, state.getValue(AGE) + 1);
		worldIn.setBlock(pos, state.setValue(AGE, Integer.valueOf(i)), 2);
	}
	
	@Override
	public net.minecraftforge.common.PlantType getPlantType(IBlockReader world, BlockPos pos) 
	{
		return net.minecraftforge.common.PlantType.PLAINS;
	}
}
