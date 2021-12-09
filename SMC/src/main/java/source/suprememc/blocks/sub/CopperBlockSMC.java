package source.suprememc.blocks.sub;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import source.suprememc.blocks.BlockSMC;
import source.suprememc.blocks.portal.PortalSizeSMC;
import source.suprememc.init.generation.SMCDimensions;
import source.suprememc.init.objects.SMCBlocks;

public class CopperBlockSMC extends BlockSMC
{
	// Variables for ticking updates
	private static final int TICK_WAIT_TIME = 24000;
	private static final int TICK_RANDOM_TIME = 1125;
	
	// These lists will be used to link the input blocks to output blocks when an update occurs (see the use and tick functions)
	protected static final Map<Block, Block> WAXABLES = new HashMap<Block, Block>();
	protected static final Map<Block, Block> AXEABLES = new HashMap<Block, Block>();
	protected static final Map<Block, Block> ERODABLES = new HashMap<Block, Block>();

	public CopperBlockSMC(String name, Properties properties) 
	{
		// Pass to the BlockSMC.java constructor
		super(name, properties);
	}

	/**
	 * The following function below deals with the right click functionality for waxing and shaving copper blocks. I am
	 * personally not a huge fan of this code and know there's probably a more efficient way of doing this, but I've
	 * already had to deal with a ton of bugs and crashes just getting this to work, so I have elected to leave it as 
	 * is. I'm sure the 1.17 code Mojang wrote for this is far better, but this at least does the job.
	 */
	@Override
	public ActionResultType use(BlockState state, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult hit) 
	{
		Item itemInHand = playerEntity.getItemInHand(hand).getItem();
		
		// Called when the item in hand is a honeycomb.
		if(itemInHand == Items.HONEYCOMB)
		{
			
			/**
			 * These WAXABLES.put(input, output) lines of code store the input and output blocks when right-clicked
			 * on using a honeycomb.
			 */
			WAXABLES.put(SMCBlocks.COPPER_BLOCK, SMCBlocks.WAXED_COPPER_BLOCK);
			WAXABLES.put(SMCBlocks.EXPOSED_COPPER, SMCBlocks.WAXED_EXPOSED_COPPER_BLOCK);
			WAXABLES.put(SMCBlocks.WEATHERED_COPPER, SMCBlocks.WAXED_WEATHERED_COPPER_BLOCK);
			WAXABLES.put(SMCBlocks.OXIDIZED_COPPER, SMCBlocks.WAXED_OXIDIZED_COPPER_BLOCK);
			WAXABLES.put(SMCBlocks.CUT_COPPER, SMCBlocks.WAXED_CUT_COPPER);
			WAXABLES.put(SMCBlocks.EXPOSED_CUT_COPPER, SMCBlocks.WAXED_EXPOSED_CUT_COPPER);
			WAXABLES.put(SMCBlocks.WEATHERED_CUT_COPPER, SMCBlocks.WAXED_WEATHERED_CUT_COPPER);
			WAXABLES.put(SMCBlocks.OXIDIZED_CUT_COPPER, SMCBlocks.WAXED_OXIDIZED_CUT_COPPER);
			
			/**
			 * The inputs for the WAXABLES.put(input, output) lines above also need to be accounted for in the
			 * if statement below as "this == [input]
			 */
			if
			(
				this == SMCBlocks.CUT_COPPER || 
				this == SMCBlocks.EXPOSED_CUT_COPPER || 
				this == SMCBlocks.WEATHERED_CUT_COPPER || 
				this == SMCBlocks.COPPER_BLOCK || 
				this == SMCBlocks.EXPOSED_COPPER || 
				this == SMCBlocks.WEATHERED_COPPER || 
				this == SMCBlocks.OXIDIZED_COPPER || 
				this == SMCBlocks.OXIDIZED_CUT_COPPER
			)
				
			/**
			 *  This if statement implementation is where the output in the WAXABLES list is found and updated.
			 *  The execution is done using the setBlockandUpdate function.
			 */
			{
				Block block = WAXABLES.get(this);
				world.setBlockAndUpdate(blockPos, block.defaultBlockState());
			}

			/**
			 *  TODO: The two lines below may need to be put inside the if statement. If not,
			 *  then the corresponding lines in the AxeItem if statement do not need to be.
			 *  Either way, I believe there is at least one minor issue with this.
			 */
			
			// Spawn particles upon use
			spawnParticles(world, blockPos);
			
			// Reduce the honeycomb stack by 1 (which "uses" the honeycomb)
			if(!playerEntity.isCreative()) playerEntity.getItemInHand(hand).shrink(1);
			
			return ActionResultType.PASS;
		}
		
		// Called when the item in hand is an axe
		else if(itemInHand instanceof AxeItem)
		{
			/**
			 * These AXEABLES.put(input, output) lines of code store the input and output blocks when right-clicked
			 * on using an axe.
			 */
			AXEABLES.put(SMCBlocks.WAXED_COPPER_BLOCK, SMCBlocks.COPPER_BLOCK);
			AXEABLES.put(SMCBlocks.WAXED_EXPOSED_COPPER_BLOCK, SMCBlocks.EXPOSED_COPPER);
			AXEABLES.put(SMCBlocks.WAXED_WEATHERED_COPPER_BLOCK, SMCBlocks.WEATHERED_COPPER);
			AXEABLES.put(SMCBlocks.WAXED_OXIDIZED_COPPER_BLOCK, SMCBlocks.OXIDIZED_COPPER);
			AXEABLES.put(SMCBlocks.WAXED_CUT_COPPER, SMCBlocks.CUT_COPPER);
			AXEABLES.put(SMCBlocks.WAXED_EXPOSED_CUT_COPPER, SMCBlocks.EXPOSED_CUT_COPPER);
			AXEABLES.put(SMCBlocks.WAXED_WEATHERED_CUT_COPPER, SMCBlocks.WEATHERED_CUT_COPPER);
			AXEABLES.put(SMCBlocks.WAXED_OXIDIZED_CUT_COPPER, SMCBlocks.OXIDIZED_CUT_COPPER);
			AXEABLES.put(SMCBlocks.EXPOSED_COPPER, SMCBlocks.COPPER_BLOCK);
			AXEABLES.put(SMCBlocks.WEATHERED_COPPER, SMCBlocks.EXPOSED_COPPER);
			AXEABLES.put(SMCBlocks.OXIDIZED_COPPER, SMCBlocks.WEATHERED_COPPER);
			AXEABLES.put(SMCBlocks.EXPOSED_CUT_COPPER, SMCBlocks.CUT_COPPER);
			AXEABLES.put(SMCBlocks.WEATHERED_CUT_COPPER, SMCBlocks.EXPOSED_CUT_COPPER);
			AXEABLES.put(SMCBlocks.OXIDIZED_CUT_COPPER, SMCBlocks.WEATHERED_CUT_COPPER);
			
			
			/**
			 * The inputs for the AXEABLES.put(input, output) lines above also need to be accounted for in the
			 * if statement below as "this == [input]
			 */
			if
			(
				this == SMCBlocks.WAXED_COPPER_BLOCK || 
				this == SMCBlocks.WAXED_EXPOSED_COPPER_BLOCK || 
				this == SMCBlocks.WAXED_WEATHERED_COPPER_BLOCK || 
				this == SMCBlocks.WAXED_CUT_COPPER || 
				this == SMCBlocks.WAXED_EXPOSED_CUT_COPPER || 
				this == SMCBlocks.WAXED_WEATHERED_CUT_COPPER || 
				this == SMCBlocks.EXPOSED_COPPER || 
				this == SMCBlocks.WEATHERED_COPPER || 
				this == SMCBlocks.OXIDIZED_COPPER || 
				this == SMCBlocks.EXPOSED_CUT_COPPER || 
				this == SMCBlocks.WEATHERED_CUT_COPPER || 
				this == SMCBlocks.OXIDIZED_CUT_COPPER || 
				this == SMCBlocks.WAXED_OXIDIZED_COPPER_BLOCK || 
				this == SMCBlocks.WAXED_OXIDIZED_CUT_COPPER
			)
				
			{
				// Update the block from the map
				Block block = AXEABLES.get(this);
				world.setBlockAndUpdate(blockPos, block.defaultBlockState());
				
				// Spawn particles
				spawnParticles(world, blockPos);
				
				// Reduce the durability by 1 (or break the axe if the durability value is 1)
				if(!playerEntity.isCreative()) playerEntity.getItemInHand(hand).hurtAndBreak(1, playerEntity, (x) -> {x.broadcastBreakEvent(hand);});
				return ActionResultType.PASS;
			}
		}
		return ActionResultType.FAIL;
	}

	@Override
	public void tick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) 
	{
		/**
		 * These ERODABLES.put(input, output) lines of code store the input and output blocks when ticked.
		 */
		ERODABLES.put(SMCBlocks.COPPER_BLOCK, SMCBlocks.EXPOSED_COPPER);
		ERODABLES.put(SMCBlocks.EXPOSED_COPPER, SMCBlocks.WEATHERED_COPPER);
		ERODABLES.put(SMCBlocks.WEATHERED_COPPER, SMCBlocks.OXIDIZED_COPPER);
		ERODABLES.put(SMCBlocks.CUT_COPPER, SMCBlocks.EXPOSED_CUT_COPPER);
		ERODABLES.put(SMCBlocks.EXPOSED_CUT_COPPER, SMCBlocks.WEATHERED_CUT_COPPER);
		ERODABLES.put(SMCBlocks.WEATHERED_CUT_COPPER, SMCBlocks.OXIDIZED_CUT_COPPER);
		
		/**
		 * The inputs for the ERODABLES.put(input, output) lines above also need to be accounted for in the
		 * if statement below as "this == [input]
		 */
		if
		(
			this == SMCBlocks.CUT_COPPER || 
			this == SMCBlocks.EXPOSED_CUT_COPPER || 
			this == SMCBlocks.WEATHERED_CUT_COPPER || 
			this == SMCBlocks.COPPER_BLOCK || 
			this == SMCBlocks.EXPOSED_COPPER || 
			this == SMCBlocks.WEATHERED_COPPER
		)
		// Update the block
		{
			Block block = ERODABLES.get(this);
			serverWorld.setBlockAndUpdate(blockPos, block.defaultBlockState());
		}
	}

	/**
	 * Function where time change (in ticks) is handled
	 */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, IWorld iWorld, BlockPos blockPos, BlockPos blockPos2) 
	{
		iWorld.getBlockTicks().scheduleTick(blockPos, this, TICK_WAIT_TIME + iWorld.getRandom().nextInt(TICK_RANDOM_TIME));
		return super.updateShape(blockState, direction, blockState2, iWorld, blockPos, blockPos2);
	}

	/**
	 * Function where time change (in ticks) is handled
	 */
	@Override @Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		context.getLevel().getBlockTicks().scheduleTick(context.getClickedPos(), this, TICK_WAIT_TIME + context.getLevel().getRandom().nextInt(TICK_RANDOM_TIME));
		return this.defaultBlockState();
	}

	/**
	 * Handles portal generation when activated
	 * 
	 * I also am not a huge fan of having to handle portal generation inside of this class. I wish I could do it in another
	 * class, but it does it's job and I don't have any incentive of changing this anytime soon. Getting portal generation
	 * to work at all was already one of the most difficult parts of this entire project. I may revisit this later, but
	 * for now I'm keeping it in this class.
	 */
	@Override
	public void onPlace(BlockState blockState, World world, BlockPos blockPos, BlockState blockState2, boolean flag) 
	{
		if (!blockState2.is(blockState.getBlock())) 
		{
			if(inPortalDimension(world)) 
			{
				Optional<PortalSizeSMC> optional = PortalSizeSMC.findEmptyPortalShape(SMCBlocks.DEEPDARK_PORTAL, this, world, blockPos, Direction.Axis.X);
				optional =  onTrySpawnPortal(world, blockPos, optional);
				if (optional.isPresent()) 
				{
					optional.get().createPortalBlocks();
					return;
				}
			}
			
			// Removes portal block if portal is "broken"
			if (!blockState.canSurvive(world, blockPos)) world.removeBlock(blockPos, false);

		}
	}

	// Portal spawn event handler function
	public static Optional<PortalSizeSMC> onTrySpawnPortal(IWorld world, BlockPos pos, Optional<PortalSizeSMC> size)
	{
		if (!size.isPresent()) return size;
		return !MinecraftForge.EVENT_BUS.post(new CopperBlockSMC.PortalSpawnEvent(world, pos, world.getBlockState(pos), size.get())) ? size : Optional.empty();
	}

	// Portal spawn event handler class
	@Cancelable
	public static class PortalSpawnEvent extends BlockEvent
	{
		private final PortalSizeSMC size;

		public PortalSpawnEvent(IWorld world, BlockPos pos, BlockState state, PortalSizeSMC size)
		{
			super(world, pos, state);
			this.size = size;
		}

		public PortalSizeSMC getPortalSize()
		{
			return size;
		}
	}

	// Boolean to return the deepdark dimension if in overworld
	private static boolean inPortalDimension(World world) 
	{
		return world.dimension() == World.OVERWORLD || world.dimension() == SMCDimensions.DEEPDARK;
	}

	/**
	 * TODO: Create implementation for new particle types. This is a placeholder.
	 * Also, I don't know if the placement of the particles are correct (though they
	 * look acceptable in game)
	 */
	private static void spawnParticles(World world, BlockPos blockPos) 
	{
		Random random = world.random;

		for(Direction direction : Direction.values()) 
		{
			BlockPos blockpos = blockPos.relative(direction);
			if (!world.getBlockState(blockpos).isSolidRender(world, blockpos)) 
			{
				Direction.Axis direction$axis = direction.getAxis();
				double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getStepX() : (double)random.nextFloat();
				double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getStepY() : (double)random.nextFloat();
				double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getStepZ() : (double)random.nextFloat();
				world.addParticle(ParticleTypes.HAPPY_VILLAGER, (double)blockPos.getX() + d1, (double)blockPos.getY() + d2, (double)blockPos.getZ() + d3, 0.0D, 0.0D, 0.0D);
			}
		}
	}
}
