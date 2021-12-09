package source.suprememc.items.foods;

import javax.annotation.Nullable;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.init.objects.SMCFoods;
import source.suprememc.util.RegUtil;

public class TomatoSMC extends Item
{
	public TomatoSMC(String name) 
	{
		super(new Properties().tab(SMCTabs.FOODS).food(SMCFoods.TOMATO));
		RegUtil.registerItem(this, name);
		ComposterBlock.COMPOSTABLES.put(this, 0.3F);
	}
	
	public ActionResultType onItemUse(ItemUseContext context) 
	{
		ActionResultType actionresulttype = this.tryPlace(new BlockItemUseContext(context));
		return !actionresulttype.consumesAction() && this.isEdible() ? this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult() : actionresulttype;
	}
	
	
	public ActionResultType tryPlace(BlockItemUseContext context) 
	{
		BlockItemUseContext blockitemusecontext = this.getBlockItemUseContext(context);
		BlockState blockstate = this.getStateForPlacement(blockitemusecontext);
		if (blockstate == null) return ActionResultType.FAIL;
		else if (!this.placeBlock(blockitemusecontext, blockstate)) return ActionResultType.FAIL;
		else 
		{
			BlockPos blockpos = blockitemusecontext.getClickedPos();
			World world = blockitemusecontext.getLevel();
			PlayerEntity playerentity = blockitemusecontext.getPlayer();
			ItemStack itemstack = blockitemusecontext.getItemInHand();
			BlockState blockstate1 = world.getBlockState(blockpos);
			Block block = blockstate1.getBlock();
			
			if (block == blockstate.getBlock()) 
			{
				blockstate1 = this.getNBTBlockState(blockpos, world, itemstack, blockstate1);
				this.onBlockPlaced(blockpos, world, playerentity, itemstack, blockstate1);
				block.setPlacedBy(world, blockpos, blockstate1, playerentity, itemstack);
				if (playerentity instanceof ServerPlayerEntity) 
				{
					CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerentity, blockpos, itemstack);
				}
			}

			SoundType soundtype = blockstate1.getSoundType(world, blockpos, context.getPlayer());
			world.playSound(playerentity, blockpos, this.getPlaceSound(blockstate1, world, blockpos, context.getPlayer()), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
			if (playerentity == null || !playerentity.abilities.instabuild) itemstack.shrink(1);
			return ActionResultType.sidedSuccess(world.isClientSide);
		}
	}
	
	@Nullable
	public BlockItemUseContext getBlockItemUseContext(BlockItemUseContext context) 
	{
		return context;
	}
	
	protected boolean onBlockPlaced(BlockPos pos, World worldIn, @Nullable PlayerEntity player, ItemStack stack, BlockState state) 
	{
		return updateCustomBlockEntityTag(worldIn, player, pos, stack);
	}

	@Nullable
	protected BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		BlockState blockstate = SMCBlocks.TOMATO_BUSH.getStateForPlacement(context);
		//return blockstate != null && this.canPlace(context, blockstate) ? blockstate : null;
		return blockstate;
	}

	private BlockState getNBTBlockState(BlockPos blockPos, World world, ItemStack itemStack, BlockState blockStateParam) 
	{
		BlockState blockstate = blockStateParam;
		CompoundNBT compoundnbt = itemStack.getTag();
		if(compoundnbt != null) 
		{
			CompoundNBT compoundnbt1 = compoundnbt.getCompound("BlockStateTag");
			StateContainer<Block, BlockState> statecontainer = blockStateParam.getBlock().getStateDefinition();

			for(String s : compoundnbt1.getAllKeys()) 
			{
				Property<?> property = statecontainer.getProperty(s);
	            if (property != null) 
	            {
	            	String s1 = compoundnbt1.get(s).getAsString();
	            	blockstate = getPropertyBlockState(blockstate, property, s1);
	            }
	        }
		}

		if (blockstate != blockStateParam) world.setBlock(blockPos, blockstate, 2);
		return blockstate;
	}

	private static <T extends Comparable<T>> BlockState getPropertyBlockState(BlockState blockState, Property<T> propertyT, String name) 
	{
		return propertyT.getValue(name).map((x) -> 
		{
			return blockState.setValue(propertyT, x);
		}).orElse(blockState);
	}
	
	protected boolean canPlace(BlockItemUseContext blockContext, BlockState blockState) 
	{
		PlayerEntity playerentity = blockContext.getPlayer();
		ISelectionContext iselectioncontext = playerentity == null ? ISelectionContext.empty() : ISelectionContext.of(playerentity);
		return (!this.checkPosition() || blockState.canSurvive(blockContext.getLevel(), blockContext.getClickedPos())) && blockContext.getLevel().isUnobstructed(blockState, blockContext.getClickedPos(), iselectioncontext);
	}

	protected boolean checkPosition() 
	{
		return true;
	}

	protected boolean placeBlock(BlockItemUseContext context, BlockState state) 
	{
		return context.getLevel().setBlock(context.getClickedPos(), state, 11);
	}

	public static boolean updateCustomBlockEntityTag(World worldIn, @Nullable PlayerEntity player, BlockPos pos, ItemStack stackIn) 
	{
		MinecraftServer minecraftserver = worldIn.getServer();
		if(minecraftserver == null) return false;
		else 
		{
			CompoundNBT compoundnbt = stackIn.getTagElement("BlockEntityTag");
			if(compoundnbt != null) 
			{
				TileEntity tileentity = worldIn.getBlockEntity(pos);
	            if(tileentity != null) 
	            {
	            	if (!worldIn.isClientSide && tileentity.onlyOpCanSetNbt() && (player == null || !player.canUseGameMasterBlocks())) 
	            	{
	                  return false;
	            	}

	            	CompoundNBT compoundnbt1 = tileentity.save(new CompoundNBT());
	            	CompoundNBT compoundnbt2 = compoundnbt1.copy();
	            	compoundnbt1.merge(compoundnbt);
	            	compoundnbt1.putInt("x", pos.getX());
	            	compoundnbt1.putInt("y", pos.getY());
	            	compoundnbt1.putInt("z", pos.getZ());
	            	if(!compoundnbt1.equals(compoundnbt2)) 
	            	{
	            		tileentity.load(worldIn.getBlockState(pos), compoundnbt1);
	            		tileentity.setChanged();
	            		return true;
	            	}
	            }
			}

			return false;
		}
	}
	
	protected SoundEvent getPlaceSound(BlockState state, World world, BlockPos pos, PlayerEntity entity) 
	{
		return state.getSoundType(world, pos, entity).getPlaceSound();
	}
	
	public String getTranslationKey() 
	{
		return this.getOrCreateDescriptionId();
	}
}