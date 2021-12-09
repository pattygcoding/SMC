package source.suprememc.blocks.interactive;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.tileentity.freezer.FreezerTileEntitySMC;
import source.suprememc.util.RegUtil;

@SuppressWarnings("deprecation")
public class FreezerBlockSMC extends Block
{
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public FreezerBlockSMC(String name)
	{
		super(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
        
		RegUtil.registerBlock(this, name, SMCTabs.DECORATION_BLOCKS);
    }
	@Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new FreezerTileEntitySMC();
    }
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) 
	{
		IProcessBlock.animate(state, world, pos, rand);
	}
	@Override
    public boolean hasAnalogOutputSignal(BlockState state) 
    {
        return true;
	}
	
    @Override
    public int getAnalogOutputSignal(BlockState state, World world, BlockPos pos)
    {
        return Container.getRedstoneSignalFromBlockEntity(world.getBlockEntity(pos));
    }
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection());
    }
    @Override
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) 
    {
    	if(stack.hasCustomHoverName()) 
    	{
	         TileEntity tileentity = worldIn.getBlockEntity(pos);
	         if (tileentity instanceof FreezerTileEntitySMC) ((FreezerTileEntitySMC)tileentity).setCustomName(stack.getDisplayName());
    	}
	}
    @Override
    public BlockState rotate(BlockState state, Rotation rotation)
    {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }
    @Override
    public BlockState mirror(BlockState state, Mirror mirror)
    {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, LIT);
    }
    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }
    @Override
    public BlockRenderType getRenderShape(BlockState state) 
    {
    	return BlockRenderType.MODEL;
	}
	
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result)
    {
    	if (world.isClientSide) return ActionResultType.SUCCESS;
	    else
	    {
	    	TileEntity tileentity = world.getBlockEntity(pos);
	        // It might be "INamedContainerProvider" instead of "FreezerTileEntitySMC" here in the if statement, but I am not sure.
	        if (tileentity instanceof FreezerTileEntitySMC) 
	        {
	        	// This line is definitely correct though.
	        	player.openMenu((INamedContainerProvider)tileentity);
	        	// TODO: Add stat for opening freezers
	        }
	    	return ActionResultType.CONSUME;
	    }
	}
    
    // Whether this function works or not remains unknown. Keep an eye out for the FreezerTileEntitySMC instance if statement
    @Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) 
	{
		if(!state.is(newState.getBlock())) 
		{
	         TileEntity tileentity = worldIn.getBlockEntity(pos);
	         if (tileentity instanceof FreezerTileEntitySMC) 
	         {
	        	 InventoryHelper.dropContents(worldIn, pos, (FreezerTileEntitySMC)tileentity);
	        	 ((FreezerTileEntitySMC)tileentity).getRecipesToAwardAndPopExperience(worldIn, Vector3d.atCenterOf(pos));
	        	 worldIn.updateNeighbourForOutputSignal(pos, this);
	         }
	         super.onRemove(state, worldIn, pos, newState, isMoving);
		}
	}
    
    
    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        TileEntity tileEntity = world.getBlockEntity(pos);
        if(tileEntity instanceof FreezerTileEntitySMC)
        {
            ((FreezerTileEntitySMC) tileEntity).onScheduledTick();
        }
    }
    
    
    
    
    
    /******** POTENTIALLY NEEDED CODE *********/
    /*
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("deprecation")
	@Override
    public boolean eventReceived(BlockState state, World world, BlockPos pos, int id, int type)
    {
        super.eventReceived(state, world, pos, id, type);
        System.out.println("TESTLINE D");
        TileEntity tileEntity = world.getTileEntity(pos);
        System.out.println("TESTLINE E");
        return tileEntity != null && tileEntity.receiveClientEvent(id, type);
    }

    */
    
    
}