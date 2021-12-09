package source.suprememc.blocks.portal;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.ITeleporter;
import source.suprememc.client.RenderBlocks;
import source.suprememc.util.RegUtil;

public class PortalBlockSMC extends Block
{

	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	protected static final VoxelShape X_AXIS_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape Z_AXIS_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
	private Block ignition, frame;
	private RegistryKey<World> dimension;

	public PortalBlockSMC(String name, RegistryKey<World> dimension, Block frame) 
	{
		super(Properties.copy(Blocks.NETHER_PORTAL));
		this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
		RegUtil.registerBlockNoDisplay(this, name);
		RenderBlocks.RENDER_BLOCK_LIST_3.add(this);
		this.dimension = dimension;
		this.frame = frame;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos, ISelectionContext iSelectionContext) 
	{
		switch((Direction.Axis)blockState.getValue(AXIS)) 
		{
		case Z:
			return Z_AXIS_AABB;
		case X:
		default:
			return X_AXIS_AABB;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, IWorld iWorld, BlockPos blockPos, BlockPos blockPos2) 
	{
		Direction.Axis direction$axis = direction.getAxis();
		Direction.Axis direction$axis1 = blockState.getValue(AXIS);
		boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
		return !flag && !blockState2.is(this) && !(new PortalSizeSMC(this, this.ignition, iWorld, blockPos, direction$axis1)).isComplete() ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState2, iWorld, blockPos, blockPos2);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(AXIS);
	}

	
	@Override
	public void entityInside(BlockState blockState, World world, BlockPos blockPos, Entity entity) 
	{
		if(entity.level.isClientSide || !entity.isAlive()) return;
		if(entity.isPassenger() || entity.hasOnePlayerPassenger() || !entity.canChangeDimensions()) return;
		
		if(entity.isOnPortalCooldown())
		{
			entity.setPortalCooldown();
			return;
		}
		
		RegistryKey<World> destination = world.dimension() == this.dimension ? World.OVERWORLD : this.dimension;
		ServerWorld serverWorld = entity.getCommandSenderWorld().getServer().getLevel(destination);
		ITeleporter teleporter = new SMCTeleporter(this.dimension, serverWorld, this.frame, this);
		
		entity.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
		entity.changeDimension(serverWorld, teleporter);
		
		
		
		if(entity != null) 
		{
			entity.setPortalCooldown();
		}

	}

	@OnlyIn(Dist.CLIENT) @Override
	public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
		if (p_180655_4_.nextInt(100) == 0) {
			p_180655_2_.playLocalSound((double)p_180655_3_.getX() + 0.5D, (double)p_180655_3_.getY() + 0.5D, (double)p_180655_3_.getZ() + 0.5D, SoundEvents.PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, p_180655_4_.nextFloat() * 0.4F + 0.8F, false);
		}

		for(int i = 0; i < 4; ++i) {
			double d0 = (double)p_180655_3_.getX() + p_180655_4_.nextDouble();
			double d1 = (double)p_180655_3_.getY() + p_180655_4_.nextDouble();
			double d2 = (double)p_180655_3_.getZ() + p_180655_4_.nextDouble();
			double d3 = ((double)p_180655_4_.nextFloat() - 0.5D) * 0.5D;
			double d4 = ((double)p_180655_4_.nextFloat() - 0.5D) * 0.5D;
			double d5 = ((double)p_180655_4_.nextFloat() - 0.5D) * 0.5D;
			int j = p_180655_4_.nextInt(2) * 2 - 1;
			if (!p_180655_2_.getBlockState(p_180655_3_.west()).is(this) && !p_180655_2_.getBlockState(p_180655_3_.east()).is(this)) {
				d0 = (double)p_180655_3_.getX() + 0.5D + 0.25D * (double)j;
				d3 = (double)(p_180655_4_.nextFloat() * 2.0F * (float)j);
			} else {
				d2 = (double)p_180655_3_.getZ() + 0.5D + 0.25D * (double)j;
				d5 = (double)(p_180655_4_.nextFloat() * 2.0F * (float)j);
			}

			p_180655_2_.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
		}

	}


}

