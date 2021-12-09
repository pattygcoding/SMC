package source.suprememc.blocks.portal;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.PortalInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.TeleportationRepositioner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.village.PointOfInterest;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;
import net.minecraftforge.common.util.ITeleporter;
import source.suprememc.SupremeMC;
import source.suprememc.init.misc.SMCPois;

public class SMCTeleporter implements ITeleporter 
{

	private ServerWorld level;
	private PortalInfo portalInfo;
	private RegistryKey<World> dimension;
	private Block frame, portal;
	public SMCTeleporter(RegistryKey<World> dimension, ServerWorld level, Block frame, Block portal) 
	{
		this.dimension = dimension;
		this.level = level;
		this.frame = frame;
		this.portal = portal;
	}

	@Override
	public boolean playTeleportSound(ServerPlayerEntity player, ServerWorld sourceWorld, ServerWorld destWorld) 
	{
		return true;
	}
	
	@Override
	public PortalInfo getPortalInfo(Entity entity, ServerWorld destWorld, Function<ServerWorld, PortalInfo> defaultPortalInfo) 
	{
		boolean flag = destWorld.dimension() == this.dimension;
		if(entity.level.dimension() != this.dimension && !flag) return null;
		else 
		{
			WorldBorder worldborder = destWorld.getWorldBorder();
			double d0 = Math.max(-2.9999872E7D, worldborder.getMinX() + 16.0D);
			double d1 = Math.max(-2.9999872E7D, worldborder.getMinZ() + 16.0D);
			double d2 = Math.min(2.9999872E7D, worldborder.getMaxX() - 16.0D);
			double d3 = Math.min(2.9999872E7D, worldborder.getMaxZ() - 16.0D);
			double d4 = DimensionType.getTeleportationScale(entity.level.dimensionType(), destWorld.dimensionType());
			BlockPos blockpos1 = new BlockPos(MathHelper.clamp(entity.getX() * d4, d0, d2), entity.getY(), MathHelper.clamp(entity.getZ() * d4, d1, d3));


			return this.getExitPortal(entity, destWorld, blockpos1, flag).map((result) -> 
			{
				BlockState blockstate = entity.level.getBlockState(entity.blockPosition());
				Direction.Axis direction$axis;
				Vector3d vector3d;
				if (blockstate.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) 
				{
					direction$axis = blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS);
					TeleportationRepositioner.Result teleportationrepositioner$result = TeleportationRepositioner.getLargestRectangleAround(entity.blockPosition(), direction$axis, 21, Direction.Axis.Y, 21, (blockPos2) -> 
					{
						return entity.level.getBlockState(blockPos2) == blockstate;
					});
					vector3d = this.getRelativePortalPosition(entity, direction$axis, teleportationrepositioner$result);
				} 
				else 
				{
					direction$axis = Direction.Axis.X;
					vector3d = new Vector3d(0.5D, 0.0D, 0.0D);
				}
				this.portalInfo = PortalSizeSMC.createPortalInfo(destWorld, result, direction$axis, vector3d, entity.getDimensions(entity.getPose()), entity.getDeltaMovement(), entity.yRot, entity.xRot);
				return this.portalInfo;
			}).orElse((PortalInfo)null);
		}
	}



	@Override
	public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) 
	{
		entity.setDeltaMovement(0, 0, 0);
		entity.moveTo(this.portalInfo.pos.x + 0.5, this.portalInfo.pos.y + 0.5, this.portalInfo.pos.z + 0.5, entity.yRot, entity.xRot);
		currentWorld.getProfiler().push("moving");
		currentWorld.getProfiler().pop();
		currentWorld.getProfiler().push("placing");
		entity.setLevel(destWorld);
		if(entity instanceof ServerPlayerEntity) destWorld.addDuringPortalTeleport((ServerPlayerEntity)entity);
		else destWorld.addFromAnotherDimension(entity);
		entity.moveTo(this.portalInfo.pos.x, this.portalInfo.pos.y, this.portalInfo.pos.z);
		currentWorld.getProfiler().pop();
		
		return entity;
		
	}

	protected Vector3d getRelativePortalPosition(Entity entity, Direction.Axis axis, TeleportationRepositioner.Result tpResult) 
	{
		return PortalSizeSMC.getRelativePosition(tpResult, axis, entity.position(), entity.getDimensions(entity.getPose()));
	}

	protected Optional<TeleportationRepositioner.Result> getExitPortal(Entity entity, ServerWorld serverWorld, BlockPos blockPos, boolean flag) 
	{
		Optional<TeleportationRepositioner.Result> optional = this.findPortalAround(blockPos, flag);
		if (optional.isPresent()) return optional;
		else 
		{
			Direction.Axis direction$axis = serverWorld.getBlockState(blockPos).getOptionalValue(NetherPortalBlock.AXIS).orElse(Direction.Axis.X);
			Optional<TeleportationRepositioner.Result> optional1 = this.createPortal(blockPos, direction$axis);
			if (!optional1.isPresent()) SupremeMC.LOGGER.error("Unable to create a portal, likely target out of worldborder");
			return optional1;
		}
	}

	public Optional<TeleportationRepositioner.Result> createPortal(BlockPos paraBlockPos, Direction.Axis axis) 
	{
		Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
		double d0 = -1.0D, d1 = -1.0D;;
		BlockPos blockpos = null, blockpos1 = null;
		WorldBorder worldborder = this.level.getWorldBorder();
		int i = this.level.getHeight() - 1;
		BlockPos.Mutable blockpos$mutable = paraBlockPos.mutable();

		for(BlockPos.Mutable blockpos$mutable1 : BlockPos.spiralAround(paraBlockPos, 16, Direction.EAST, Direction.SOUTH)) 
		{
			int j = Math.min(i, this.level.getHeight(Heightmap.Type.MOTION_BLOCKING, blockpos$mutable1.getX(), blockpos$mutable1.getZ()));
			if(worldborder.isWithinBounds(blockpos$mutable1) && worldborder.isWithinBounds(blockpos$mutable1.move(direction, 1))) 
			{
				blockpos$mutable1.move(direction.getOpposite(), 1);

				for(int l = j; l >= 0; --l) 
				{
					blockpos$mutable1.setY(l);
					if(this.level.isEmptyBlock(blockpos$mutable1)) 
					{
						int i1;
						for(i1 = l; l > 0 && this.level.isEmptyBlock(blockpos$mutable1.move(Direction.DOWN)); --l) {}

						if(l + 4 <= i) 
						{
							int j1 = i1 - l;
							if(j1 <= 0 || j1 >= 3) 
							{
								blockpos$mutable1.setY(l);
								if (this.canHostFrame(blockpos$mutable1, blockpos$mutable, direction, 0)) {
									double d2 = paraBlockPos.distSqr(blockpos$mutable1);
									if (this.canHostFrame(blockpos$mutable1, blockpos$mutable, direction, -1) && this.canHostFrame(blockpos$mutable1, blockpos$mutable, direction, 1) && (d0 == -1.0D || d0 > d2)) {
										d0 = d2;
										blockpos = blockpos$mutable1.immutable();
									}

									if (d0 == -1.0D && (d1 == -1.0D || d1 > d2)) {
										d1 = d2;
										blockpos1 = blockpos$mutable1.immutable();
									}
								}
							}
						}
					}
				}
			}
		}

		if (d0 == -1.0D && d1 != -1.0D) {
			blockpos = blockpos1;
			d0 = d1;
		}

		if (d0 == -1.0D) {
			blockpos = (new BlockPos(paraBlockPos.getX(), MathHelper.clamp(paraBlockPos.getY(), 70, this.level.getHeight() - 10), paraBlockPos.getZ())).immutable();
			Direction direction1 = direction.getClockWise();
			if (!worldborder.isWithinBounds(blockpos)) {
				return Optional.empty();
			}

			for(int l1 = -1; l1 < 2; ++l1) 
			{
				for(int k2 = 0; k2 < 2; ++k2) 
				{
					for(int i3 = -1; i3 < 3; ++i3) 
					{
						BlockState blockstate1 = i3 < 0 ? this.frame.defaultBlockState() : Blocks.AIR.defaultBlockState();
						blockpos$mutable.setWithOffset(blockpos, k2 * direction.getStepX() + l1 * direction1.getStepX(), i3, k2 * direction.getStepZ() + l1 * direction1.getStepZ());
						this.level.setBlockAndUpdate(blockpos$mutable, blockstate1);
					}
				}
			}
		}
		
		
		for(int k1 = -1; k1 < 3; ++k1) {
			for(int i2 = -1; i2 < 4; ++i2) {
				if (k1 == -1 || k1 == 2 || i2 == -1 || i2 == 3) {
					blockpos$mutable.setWithOffset(blockpos, k1 * direction.getStepX(), i2, k1 * direction.getStepZ());
					this.level.setBlock(blockpos$mutable, this.frame.defaultBlockState(), 3);
				}
			}
		}
		
		for(int x = -1; x < 3; ++x)
		{
			for(int z = -1; z < 3; ++z)
			{
				blockpos$mutable.setWithOffset(blockpos, x * direction.getStepX(), -1, z * direction.getStepZ());
				this.level.setBlock(blockpos$mutable, this.frame.defaultBlockState(), 3);
			}
		}

		BlockState blockstate = this.portal.defaultBlockState().setValue(PortalBlockSMC.AXIS, axis);

		for(int j2 = 0; j2 < 2; ++j2) {
			for(int l2 = 0; l2 < 3; ++l2) {
				blockpos$mutable.setWithOffset(blockpos, j2 * direction.getStepX(), l2, j2 * direction.getStepZ());
				this.level.setBlock(blockpos$mutable, blockstate, 18);
			}
		}

		return Optional.of(new TeleportationRepositioner.Result(blockpos.immutable(), 2, 3));
	}

	private boolean canHostFrame(BlockPos p_242955_1_, BlockPos.Mutable p_242955_2_, Direction p_242955_3_, int p_242955_4_) {
		Direction direction = p_242955_3_.getClockWise();

		for(int i = -1; i < 3; ++i) {
			for(int j = -1; j < 4; ++j) {
				p_242955_2_.setWithOffset(p_242955_1_, p_242955_3_.getStepX() * i + direction.getStepX() * p_242955_4_, j, p_242955_3_.getStepZ() * i + direction.getStepZ() * p_242955_4_);
				if (j < 0 && !this.level.getBlockState(p_242955_2_).getMaterial().isSolid()) {
					return false;
				}

				if (j >= 0 && !this.level.isEmptyBlock(p_242955_2_)) {
					return false;
				}
			}
		}

		return true;
	}

	public Optional<TeleportationRepositioner.Result> findPortalAround(BlockPos pBlockPos, boolean flag) 
	{
		PointOfInterestManager pointofinterestmanager = this.level.getPoiManager();
		int i = flag ? 16 : 128;
		pointofinterestmanager.ensureLoadedAndValid(this.level, pBlockPos, i);
		Optional<PointOfInterest> optional = pointofinterestmanager.getInSquare((poiType) -> 
		{
			return poiType == SMCPois.DEEPDARK_PORTAL;
		}, pBlockPos, i, PointOfInterestManager.Status.ANY).sorted(Comparator.<PointOfInterest>comparingDouble((poiType) -> 
		{
			return poiType.getPos().distSqr(pBlockPos);
		}).thenComparingInt((poiType) -> 
		{
			return poiType.getPos().getY();
		})).filter((poiType) -> 
		{
			return this.level.getBlockState(poiType.getPos()).hasProperty(BlockStateProperties.HORIZONTAL_AXIS);
		}).findFirst();
		return optional.map((poiType) -> 
		{
			BlockPos blockpos = poiType.getPos();
			this.level.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, blockpos);
			BlockState blockstate = this.level.getBlockState(blockpos);
			return TeleportationRepositioner.getLargestRectangleAround(blockpos, blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21, (blockPos) -> 
			{
				return this.level.getBlockState(blockPos) == blockstate;
			});
		});
	}

}
