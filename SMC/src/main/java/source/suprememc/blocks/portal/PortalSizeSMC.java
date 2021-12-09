package source.suprememc.blocks.portal;

import java.util.Optional;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PortalInfo;
import net.minecraft.entity.EntitySize;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.TeleportationRepositioner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import source.suprememc.init.objects.SMCBlocks;

public class PortalSizeSMC 
{
	private static final int SIZE = 21;
	private static final AbstractBlock.IPositionPredicate FRAME = (blockState, iBlockReader, blockPos) -> 
	{
		return blockState.is(SMCBlocks.GLOWING_OBSIDIAN);
	};

	private final IWorld level;
	private final Direction.Axis axis;
	private final Direction rightDir;
	private int numPortalBlocks;
	@Nullable
	private BlockPos bottomLeft;
	private int height;
	private int width;
	private Block portal;
	public static Optional<PortalSizeSMC> findEmptyPortalShape(Block portal, Block ignition, IWorld level, BlockPos blockPos, Direction.Axis axis) 
	{
		
		return findPortalShape(portal, ignition, level, blockPos, (instance) -> 
		{
			return instance.isValid() && instance.numPortalBlocks == 0;
		}, axis);
	}

	public static Optional<PortalSizeSMC> findPortalShape(Block portal, Block ignition, IWorld level, BlockPos bottomLeft, Predicate<PortalSizeSMC> predicate, Direction.Axis axis) 
	{
		Optional<PortalSizeSMC> optional = Optional.of(new PortalSizeSMC(portal, ignition, level, bottomLeft, axis)).filter(predicate);
		if (optional.isPresent()) return optional;
		else 
		{
			Direction.Axis newAxis = (axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X);
			return Optional.of(new PortalSizeSMC(portal, ignition, level, bottomLeft, newAxis)).filter(predicate);
		}
	}

	public PortalSizeSMC(Block portal, Block ignition, IWorld level, BlockPos bottomLeft, Direction.Axis axis) 
	{
		this.level = level;
		this.portal = portal;
		this.axis = axis;
		this.rightDir = axis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
		this.bottomLeft = this.calculateBottomLeft(bottomLeft);
		if(this.bottomLeft == null) 
		{
			this.bottomLeft = bottomLeft;
			this.width = 1;
			this.height = 1;
		} 
		else 
		{
			this.width = this.calculateWidth();
			if(this.width > 0) this.height = this.calculateHeight();
		}
	}

	@Nullable
	private BlockPos calculateBottomLeft(BlockPos blockPos) 
	{
		for(int i = Math.max(0, blockPos.getY() - SIZE); blockPos.getY() > i && isEmpty(this.level.getBlockState(blockPos.below())); blockPos = blockPos.below()) {}
		Direction direction = this.rightDir.getOpposite();
		int j = this.getDistanceUntilEdgeAboveFrame(blockPos, direction) - 1;
		return j < 0 ? null : blockPos.relative(direction, j);
	}

	private int calculateWidth() 
	{
		int i = this.getDistanceUntilEdgeAboveFrame(this.bottomLeft, this.rightDir);
		return i >= 2 && i <= SIZE ? i : 0;
	}

	private int getDistanceUntilEdgeAboveFrame(BlockPos blockPos, Direction direction) {
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		for(int i = 0; i <= SIZE; ++i) 
		{
			blockpos$mutable.set(blockPos).move(direction, i);
			BlockState blockstate = this.level.getBlockState(blockpos$mutable);
			if (!isEmpty(blockstate)) 
			{
				if(FRAME.test(blockstate, this.level, blockpos$mutable)) return i;
				break;
			}
			BlockState blockstate1 = this.level.getBlockState(blockpos$mutable.move(Direction.DOWN));
			if(!FRAME.test(blockstate1, this.level, blockpos$mutable)) break;
		}
		return 0;
	}

	private int calculateHeight() 
	{
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		int i = this.getDistanceUntilTop(blockpos$mutable);
		return i >= 3 && i <= SIZE && this.hasTopFrame(blockpos$mutable, i) ? i : 0;
	}

	private boolean hasTopFrame(BlockPos.Mutable blockPosM, int multiplier) 
	{
		for(int i = 0; i < this.width; ++i) 
		{
			BlockPos.Mutable blockpos$mutable = blockPosM.set(this.bottomLeft).move(Direction.UP, multiplier).move(this.rightDir, i);
			if(!FRAME.test(this.level.getBlockState(blockpos$mutable), this.level, blockpos$mutable)) return false;
		}
		return true;
	}

	private int getDistanceUntilTop(BlockPos.Mutable blockPosM) 
	{
		for(int i = 0; i < SIZE; ++i) 
		{
			blockPosM.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, -1);
			if(!FRAME.test(this.level.getBlockState(blockPosM), this.level, blockPosM)) return i;
			blockPosM.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, this.width);
			if(!FRAME.test(this.level.getBlockState(blockPosM), this.level, blockPosM)) return i;
			for(int j = 0; j < this.width; ++j) 
			{
				blockPosM.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, j);
				BlockState blockstate = this.level.getBlockState(blockPosM);
				if(!isEmpty(blockstate)) return i;
				if(blockstate.is(this.portal)) ++this.numPortalBlocks;
			}
		}
		return SIZE;
	}

	@SuppressWarnings("deprecation")
	private static boolean isEmpty(BlockState blockState) 
	{
		return blockState.isAir() || blockState.is(SMCBlocks.COPPER_BLOCK) || blockState.is(SMCBlocks.DEEPDARK_PORTAL);
	}

	public boolean isValid() 
	{
		return this.bottomLeft != null && this.width >= 2 && this.width <= SIZE && this.height >= 3 && this.height <= SIZE;
	}

	public void createPortalBlocks() 
	{
		BlockState blockstate = this.portal.defaultBlockState().setValue(PortalBlockSMC.AXIS, this.axis);
		BlockPos.betweenClosed(this.bottomLeft, this.bottomLeft.relative(Direction.UP, this.height - 1).relative(this.rightDir, this.width - 1)).forEach((blockPos) -> 
		{
			this.level.setBlock(blockPos, blockstate, 18);
		});
	}

	public boolean isComplete() 
	{
		return this.isValid() && this.numPortalBlocks == this.width * this.height;
	}

	public static Vector3d getRelativePosition(TeleportationRepositioner.Result teleportationRepositioner, Direction.Axis axis, Vector3d vec3d, EntitySize entitySize) 
	{
		double d0 = (double)teleportationRepositioner.axis1Size - (double)entitySize.width;
		double d1 = (double)teleportationRepositioner.axis2Size - (double)entitySize.height;
		BlockPos blockpos = teleportationRepositioner.minCorner;
		double d2;
		if (d0 > 0.0D) {
			float f = (float)blockpos.get(axis) + entitySize.width / 2.0F;
			d2 = MathHelper.clamp(MathHelper.inverseLerp(vec3d.get(axis) - (double)f, 0.0D, d0), 0.0D, 1.0D);
		} else {
			d2 = 0.5D;
		}

		double d4;
		if (d1 > 0.0D) {
			Direction.Axis direction$axis = Direction.Axis.Y;
			d4 = MathHelper.clamp(MathHelper.inverseLerp(vec3d.get(direction$axis) - (double)blockpos.get(direction$axis), 0.0D, d1), 0.0D, 1.0D);
		} else {
			d4 = 0.0D;
		}

		Direction.Axis direction$axis1 = axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
		double d3 = vec3d.get(direction$axis1) - ((double)blockpos.get(direction$axis1) + 0.5D);
		return new Vector3d(d2, d4, d3);
	}

	public static PortalInfo createPortalInfo(ServerWorld world, TeleportationRepositioner.Result teleportationRepositioner, Direction.Axis axis, Vector3d vec3d, EntitySize entitySize, Vector3d vec3d2, float yRot, float xRot) 
	{
		BlockPos blockpos = teleportationRepositioner.minCorner;
		BlockState blockstate = world.getBlockState(blockpos);
		Direction.Axis direction$axis = blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS);
		double d0 = (double)teleportationRepositioner.axis1Size;
		double d1 = (double)teleportationRepositioner.axis2Size;
		int i = axis == direction$axis ? 0 : 90;
		Vector3d vector3d = axis == direction$axis ? vec3d2 : new Vector3d(vec3d2.z, vec3d2.y, -vec3d2.x);
		double d2 = (double)entitySize.width / 2.0D + (d0 - (double)entitySize.width) * vec3d.x();
		double d3 = (d1 - (double)entitySize.height) * vec3d.y();
		double d4 = 0.5D + vec3d.z();
		boolean flag = direction$axis == Direction.Axis.X;
		Vector3d vector3d1 = new Vector3d((double)blockpos.getX() + (flag ? d2 : d4), (double)blockpos.getY() + d3, (double)blockpos.getZ() + (flag ? d4 : d2));
		return new PortalInfo(vector3d1, vector3d, yRot + (float)i, xRot);
	}
}

