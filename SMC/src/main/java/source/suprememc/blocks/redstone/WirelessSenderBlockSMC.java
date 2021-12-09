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
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.util.RegUtil;
import source.suprememc.util.SMCColors;

public class WirelessSenderBlockSMC extends Block implements IItemProvider
{
	public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
	
	public WirelessSenderBlockSMC(SMCColors color) 
	{
		super(Properties.of(Material.METAL, color.getColor()).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL));
		this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.valueOf(false)));
		RegUtil.registerBlock(this, color.getName() + "_wireless_sender", SMCTabs.REDSTONE);
	}
	  
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.defaultBlockState().setValue(LIT, Boolean.valueOf(context.getLevel().hasNeighborSignal(context.getClickedPos())));
	}

	private Block getReceiver()
	{
		if(this == SMCBlocks.RED_WIRELESS_SENDER) return SMCBlocks.RED_WIRELESS_RECEIVER;
		else if(this == SMCBlocks.ORANGE_WIRELESS_SENDER) return SMCBlocks.ORANGE_WIRELESS_RECEIVER;
		else if(this == SMCBlocks.YELLOW_WIRELESS_SENDER) return SMCBlocks.YELLOW_WIRELESS_RECEIVER;
		else if(this == SMCBlocks.LIME_WIRELESS_SENDER) return SMCBlocks.LIME_WIRELESS_RECEIVER;
		else if(this == SMCBlocks.GREEN_WIRELESS_SENDER) return SMCBlocks.GREEN_WIRELESS_RECEIVER;
		else if(this == SMCBlocks.CYAN_WIRELESS_SENDER) return SMCBlocks.CYAN_WIRELESS_RECEIVER;
		else if(this == SMCBlocks.LIGHT_BLUE_WIRELESS_SENDER) return SMCBlocks.LIGHT_BLUE_WIRELESS_RECEIVER;
		else if(this == SMCBlocks.BLUE_WIRELESS_SENDER) return SMCBlocks.BLUE_WIRELESS_RECEIVER;
		else if(this == SMCBlocks.PURPLE_WIRELESS_SENDER) return SMCBlocks.PURPLE_WIRELESS_RECEIVER;
		else if(this == SMCBlocks.MAGENTA_WIRELESS_SENDER) return SMCBlocks.MAGENTA_WIRELESS_RECEIVER;
		else if(this == SMCBlocks.PINK_WIRELESS_SENDER) return SMCBlocks.PINK_WIRELESS_RECEIVER;
		else if(this == SMCBlocks.WHITE_WIRELESS_SENDER) return SMCBlocks.WHITE_WIRELESS_RECEIVER;
		else if(this == SMCBlocks.LIGHT_GRAY_WIRELESS_SENDER) return SMCBlocks.LIGHT_GRAY_WIRELESS_RECEIVER;
		else if(this == SMCBlocks.GRAY_WIRELESS_SENDER) return SMCBlocks.GRAY_WIRELESS_RECEIVER;
		else if(this == SMCBlocks.BLACK_WIRELESS_SENDER) return SMCBlocks.BLACK_WIRELESS_RECEIVER;
		else return SMCBlocks.BROWN_WIRELESS_RECEIVER;
	}
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) 
	{
		if(!worldIn.isClientSide) 
		{
			boolean isLit = state.getValue(LIT);
			if(isLit != worldIn.hasNeighborSignal(pos)) 
			{
				if(isLit)
				{
					worldIn.getBlockTicks().scheduleTick(pos, this, 4);
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
									if(worldIn.getBlockState(otherPos) == getReceiver().defaultBlockState().setValue(LIT, Boolean.valueOf(true)))
									{
										worldIn.setBlockAndUpdate(otherPos, getReceiver().defaultBlockState().setValue(LIT, Boolean.valueOf(false)));
										updateNeighbors(state, worldIn, otherPos);
									}
								}
							}
						}
					}
				}
	            else
	            {
	            	worldIn.setBlock(pos, state.cycle(LIT), 2);
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
									if(worldIn.getBlockState(otherPos) == getReceiver().defaultBlockState().setValue(LIT, Boolean.valueOf(false)))
									{
										worldIn.setBlockAndUpdate(otherPos, getReceiver().defaultBlockState().setValue(LIT, Boolean.valueOf(true)));
										updateNeighbors(state, worldIn, otherPos);
									}
								}
							}
						}
					}
	            }
			}
		}
	}
	

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) 
	{
		if(state.getValue(LIT) && !worldIn.hasNeighborSignal(pos)) worldIn.setBlock(pos, state.cycle(LIT), 2);
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
