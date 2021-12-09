package source.suprememc.blocks.redstone;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;
import source.suprememc.util.SMCColors;

public class WirelessReceiverBlockSMC extends Block implements IItemProvider
{
	public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

	private Block sender;
	public WirelessReceiverBlockSMC(SMCColors color, Block sender) 
	{
		super(Properties.of(Material.METAL, color.getColor()).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL));
		this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.valueOf(false)));
		this.sender = sender;
		RegUtil.registerBlock(this, color.getName() + "_wireless_receiver", SMCTabs.REDSTONE);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.defaultBlockState().setValue(LIT, Boolean.valueOf(this.isReceiverPowered(context)));
	}
	
	public boolean isReceiverPowered(BlockItemUseContext context) 
	{
		for(int x = -15; x < 16; ++x)
		{
			for(int y = -15; y < 16; ++y)
			{
				for(int z = -15; z < 16; ++z)
				{
					if(x != 0 || y != 0 || z != 0)
					{
						int posX = context.getClickedPos().getX() + x;
						int posY = context.getClickedPos().getY() + y;
						int posZ = context.getClickedPos().getZ() + z;
						BlockPos otherPos = new BlockPos(posX, posY, posZ);
						if(context.getLevel().getBlockState(otherPos) == sender.defaultBlockState().setValue(LIT, Boolean.valueOf(true))) return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean isReceiverPowered(World world, BlockPos pos) 
	{
		for(int x = -15; x < 16; ++x)
		{
			for(int y = -15; y < 16; ++y)
			{
				for(int z = -15; z < 16; ++z)
				{
					if(x != 0 || y != 0 || z != 0)
					{
						int posX = pos.getX() + x;
						int posY = pos.getY() + y;
						int posZ = pos.getZ() + z;
						BlockPos otherPos = new BlockPos(posX, posY, posZ);
						if(world.getBlockState(otherPos) == sender.defaultBlockState().setValue(LIT, Boolean.valueOf(true)))
						{
							updateNeighbors(world.getBlockState(pos), world, pos);
							return true;
						}
					}
				}
			}
		}
		updateNeighbors(world.getBlockState(pos), world, pos);
		return false;
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) 
	{
		if(state.getValue(LIT) && isReceiverPowered(worldIn, pos)) worldIn.setBlock(pos, state.cycle(LIT), 2);
	}

	
	@Override
	public boolean isSignalSource(BlockState state) 
	{
		return true;
	}
	
	
	@Override
	public int getSignal(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) 
	{
		if(blockState.getValue(LIT)) return 15;
		return 0;
	}
	
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(LIT);
	}
	
	private void updateNeighbors(BlockState state, World world, BlockPos pos) 
	{
		world.updateNeighborsAt(pos, this);
	}
	
	
}
