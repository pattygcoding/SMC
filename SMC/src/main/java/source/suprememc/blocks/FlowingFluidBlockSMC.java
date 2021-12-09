package source.suprememc.blocks;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import source.suprememc.client.RenderBlocks;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.init.objects.SMCFluids;
import source.suprememc.util.RegUtil;

public class FlowingFluidBlockSMC extends FlowingFluidBlock implements IBucketPickupHandler 
{

	public FlowingFluidBlockSMC(String name, Supplier<? extends FlowingFluid> supplier) 
	{
		// Call constructor for flowing fluid block
		super(supplier, Properties.copy(Blocks.WATER));
		
		// Register the block without display
		RegUtil.registerBlockNoDisplay(this, name);
		
		// Add to the render list for fluids
		RenderBlocks.RENDER_BLOCK_LIST_3.add(this);
	}

	// Override to convert block on contact
	@Override
	public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState blockState2, boolean isMoving) 
	{
		this.convertBlock(state, worldIn, pos);
	}

	// Override to convert block on contact
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) 
	{
		this.convertBlock(state, worldIn, pos);
	}

	// Update the shape of the liquid if it moves
	@Override
	public BlockState updateShape(BlockState state, Direction dir, BlockState state2, IWorld world, BlockPos pos, BlockPos pos2) 
	{
		if(state.getFluidState().getType() == SMCFluids.RADIATED_WATER || 
				state2.getFluidState().getType() == SMCFluids.FLOWING_RADIATED_WATER) {
			world.getLiquidTicks().scheduleTick(pos, state.getFluidState().getType(), 5);
		}

		return super.updateShape(state, dir, state2, world, pos, pos2);
	}

	
	// This function deals with how the fluid is converted if it contacts another fluid
	private void convertBlock(BlockState state, World worldIn, BlockPos pos)
	{
		List<BlockPos> positions = ImmutableList.of(pos.above(), pos.below(), pos.east(), pos.north(), pos.west(), pos.south());
		
		if(!worldIn.isClientSide && (state.getFluidState().getType() == SMCFluids.RADIATED_WATER || 
				state.getFluidState().getType() == SMCFluids.FLOWING_RADIATED_WATER)) 
		{
			for(BlockPos sides : positions)
			{
				if(sides == pos.above() && worldIn.getFluidState(sides).is(FluidTags.LAVA))
				{
					worldIn.setBlockAndUpdate(pos, Blocks.BLACKSTONE.defaultBlockState());
					worldIn.levelEvent(1501, pos, 0);
				}
				else if(worldIn.getFluidState(sides).is(FluidTags.LAVA))
				{
					Block block = worldIn.getFluidState(sides).isSource() ? SMCBlocks.GLOWING_OBSIDIAN : Blocks.BLACKSTONE;
					worldIn.setBlockAndUpdate(sides, ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, block.defaultBlockState()));
					worldIn.levelEvent(1501, pos, 0);
				}
			}
		}
		worldIn.getLiquidTicks().scheduleTick(pos, state.getFluidState().getType(), 5);

	}



}
