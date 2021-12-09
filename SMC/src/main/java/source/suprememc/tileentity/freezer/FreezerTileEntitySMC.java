package source.suprememc.tileentity.freezer;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import source.suprememc.blocks.interactive.FreezerBlockSMC;
import source.suprememc.container.freezer.FreezerContainerSMC;
import source.suprememc.events.FreezerFuelTimeEvent;
import source.suprememc.init.objects.SMCTileEntities;
import source.suprememc.init.recipes.SMCRecipeTypes;

@SuppressWarnings("unused")
public class FreezerTileEntitySMC extends LockableTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity
{
	private static final int[] SLOTS_FOR_UP = new int[]{0};
	private static final int[] SLOTS_FOR_DOWN = new int[]{2, 1};
	private static final int[] SLOTS_FOR_SIDES = new int[]{1};
	protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
	private int litTime;
	private int litDuration;
	private int cookingProgress;
	private int cookingTotalTime;
	private int playerCount;
	protected final IIntArray dataAccess = new IIntArray() {
		public int get(int p_221476_1_) {
			switch(p_221476_1_) {
			case 0:
				return FreezerTileEntitySMC.this.litTime;
			case 1:
				return FreezerTileEntitySMC.this.litDuration;
			case 2:
				return FreezerTileEntitySMC.this.cookingProgress;
			case 3:
				return FreezerTileEntitySMC.this.cookingTotalTime;
			default:
				return 0;
			}
		}

		public void set(int p_221477_1_, int p_221477_2_) {
			switch(p_221477_1_) {
			case 0:
				FreezerTileEntitySMC.this.litTime = p_221477_2_;
				break;
			case 1:
				FreezerTileEntitySMC.this.litDuration = p_221477_2_;
				break;
			case 2:
				FreezerTileEntitySMC.this.cookingProgress = p_221477_2_;
				break;
			case 3:
				FreezerTileEntitySMC.this.cookingTotalTime = p_221477_2_;
			}

		}

		public int getCount() {
			return 4;
		}
	};
	private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
	protected final IRecipeType<? extends AbstractCookingRecipe> recipeType;

	public FreezerTileEntitySMC() 
	{
		super(SMCTileEntities.FREEZER);
		this.recipeType = SMCRecipeTypes.FREEZING;
	}

	@Override
	protected ITextComponent getDefaultName()
	{
		return new TranslationTextComponent("container.suprememc.freezer");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory playerInventory) {
		return new FreezerContainerSMC(id, playerInventory, this, this.dataAccess);
	}
	public int getFreezeTime(ItemStack stack)
	{
		if(stack.isEmpty())
		{
			return 0;
		}
		else if(stack.getItem() == Items.SNOWBALL) return 50; // 1/4 of an item
		else if(stack.getItem() == Items.SNOW) return 50; // 1/4 of an item
		else if(stack.getItem() == Items.SNOW_BLOCK) return 300; // 1.5 items
		else if(stack.getItem() == Items.ICE) return 500; // 2.5 items
		else if(stack.getItem() == Items.PACKED_ICE) return 5000; // 25 items
		else if(stack.getItem() == Items.BLUE_ICE) return 50000; // 250 items
		FreezerFuelTimeEvent event = new FreezerFuelTimeEvent(stack);
		MinecraftForge.EVENT_BUS.post(event);
		return event.getFuelTime();
	}

	private boolean isLit() {
		return this.litTime > 0;
	}

	@Override
	public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) { //TODO: MARK
		super.load(p_230337_1_, p_230337_2_);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(p_230337_2_, this.items);
		this.litTime = p_230337_2_.getInt("BurnTime");
		this.cookingProgress = p_230337_2_.getInt("CookTime");
		this.cookingTotalTime = p_230337_2_.getInt("CookTimeTotal");
		this.litDuration = this.getFreezeTime(this.items.get(1));
		CompoundNBT compoundnbt = p_230337_2_.getCompound("RecipesUsed");

		for(String s : compoundnbt.getAllKeys()) {
			this.recipesUsed.put(new ResourceLocation(s), compoundnbt.getInt(s));
		}

	}

	@Override
	public CompoundNBT save(CompoundNBT p_189515_1_) {
		super.save(p_189515_1_);
		p_189515_1_.putInt("BurnTime", this.litTime);
		p_189515_1_.putInt("CookTime", this.cookingProgress);
		p_189515_1_.putInt("CookTimeTotal", this.cookingTotalTime);
		ItemStackHelper.saveAllItems(p_189515_1_, this.items);
		CompoundNBT compoundnbt = new CompoundNBT();
		this.recipesUsed.forEach((p_235643_1_, p_235643_2_) -> {
			compoundnbt.putInt(p_235643_1_.toString(), p_235643_2_);
		});
		p_189515_1_.put("RecipesUsed", compoundnbt);
		return p_189515_1_;
	}

	public void tick() {
		boolean flag = this.isLit();
		boolean flag1 = false;
		if (this.isLit()) {
			--this.litTime;
		}

		if (!this.level.isClientSide) {
			ItemStack itemstack = this.items.get(1);
			if (this.isLit() || !itemstack.isEmpty() && !this.items.get(0).isEmpty()) {
				IRecipe<?> irecipe = this.level.getRecipeManager().getRecipeFor(SMCRecipeTypes.FREEZING, this, this.level).orElse(null);
				if (!this.isLit() && this.canBurn(irecipe)) {
					this.litTime = this.getFreezeTime(itemstack);
					this.litDuration = this.litTime;
					if (this.isLit()) {
						flag1 = true;
						if (itemstack.hasContainerItem())
							this.items.set(1, itemstack.getContainerItem());
						else
							if (!itemstack.isEmpty()) {
								Item item = itemstack.getItem();
								itemstack.shrink(1);
								if (itemstack.isEmpty()) {
									this.items.set(1, itemstack.getContainerItem());
								}
							}
					}
				}

				if (this.isLit() && this.canBurn(irecipe)) {
					++this.cookingProgress;
					if (this.cookingProgress == this.cookingTotalTime) {
						this.cookingProgress = 0;
						this.cookingTotalTime = this.getTotalCookTime();
						this.burn(irecipe);
						flag1 = true;
					}
				} else {
					this.cookingProgress = 0;
				}
			} else if (!this.isLit() && this.cookingProgress > 0) {
				this.cookingProgress = MathHelper.clamp(this.cookingProgress - 2, 0, this.cookingTotalTime);
			}

			if (flag != this.isLit()) {
				flag1 = true;
				this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(AbstractFurnaceBlock.LIT, Boolean.valueOf(this.isLit())), 3);
			}
		}

		if (flag1) {
			this.setChanged();
		}

	}

	protected boolean canBurn(@Nullable IRecipe<?> p_214008_1_) {
		if (!this.items.get(0).isEmpty() && p_214008_1_ != null) {
			ItemStack itemstack = p_214008_1_.getResultItem();
			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = this.items.get(2);
				if (itemstack1.isEmpty()) {
					return true;
				} else if (!itemstack1.sameItem(itemstack)) {
					return false;
				} else if (itemstack1.getCount() + itemstack.getCount() <= this.getMaxStackSize() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
					return true;
				} else {
					return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
				}
			}
		} else {
			return false;
		}
	}

	private void burn(@Nullable IRecipe<?> p_214007_1_) {
		if (p_214007_1_ != null && this.canBurn(p_214007_1_)) {
			ItemStack itemstack = this.items.get(0);
			ItemStack itemstack1 = p_214007_1_.getResultItem();
			ItemStack itemstack2 = this.items.get(2);
			if (itemstack2.isEmpty()) {
				this.items.set(2, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}

			if (!this.level.isClientSide) {
				this.setRecipeUsed(p_214007_1_);
			}

			itemstack.shrink(1);
		}
	}

	protected int getBurnDuration(ItemStack p_213997_1_) {
		if (p_213997_1_.isEmpty()) {
			return 0;
		} else {
			Item item = p_213997_1_.getItem();
			return net.minecraftforge.common.ForgeHooks.getBurnTime(p_213997_1_);
		}
	}

	protected int getTotalCookTime() 
	{
		return this.level.getRecipeManager().getRecipeFor(SMCRecipeTypes.FREEZING, this, this.level).map(AbstractCookingRecipe::getCookingTime).orElse(200);

	}

	public static boolean isFuel(ItemStack p_213991_0_) {
		return net.minecraftforge.common.ForgeHooks.getBurnTime(p_213991_0_) > 0;
	}

	public int[] getSlotsForFace(Direction p_180463_1_) {
		if (p_180463_1_ == Direction.DOWN) {
			return SLOTS_FOR_DOWN;
		} else {
			return p_180463_1_ == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
		}
	}

	public boolean canPlaceItemThroughFace(int p_180462_1_, ItemStack p_180462_2_, @Nullable Direction p_180462_3_) {
		return this.canPlaceItem(p_180462_1_, p_180462_2_);
	}

	public boolean canTakeItemThroughFace(int p_180461_1_, ItemStack p_180461_2_, Direction p_180461_3_) {
		if (p_180461_3_ == Direction.DOWN && p_180461_1_ == 1) {
			Item item = p_180461_2_.getItem();
			if (item != Items.WATER_BUCKET && item != Items.BUCKET) {
				return false;
			}
		}

		return true;
	}

	public int getContainerSize() {
		return this.items.size();
	}

	public boolean isEmpty() {
		for(ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	public ItemStack getItem(int p_70301_1_) {
		return this.items.get(p_70301_1_);
	}

	public ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
		return ItemStackHelper.removeItem(this.items, p_70298_1_, p_70298_2_);
	}

	public ItemStack removeItemNoUpdate(int p_70304_1_) {
		return ItemStackHelper.takeItem(this.items, p_70304_1_);
	}

	public void setItem(int p_70299_1_, ItemStack p_70299_2_) {
		ItemStack itemstack = this.items.get(p_70299_1_);
		boolean flag = !p_70299_2_.isEmpty() && p_70299_2_.sameItem(itemstack) && ItemStack.tagMatches(p_70299_2_, itemstack);
		this.items.set(p_70299_1_, p_70299_2_);
		if (p_70299_2_.getCount() > this.getMaxStackSize()) {
			p_70299_2_.setCount(this.getMaxStackSize());
		}

		if (p_70299_1_ == 0 && !flag) {
			this.cookingTotalTime = this.getTotalCookTime();
			this.cookingProgress = 0;
			this.setChanged();
		}

	}

	public boolean stillValid(PlayerEntity p_70300_1_) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return p_70300_1_.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		if(index == 2) return false;
        else if(index != 1) return true;
        return this.getFreezeTime(stack) > 0;
	}

	public void clearContent() {
		this.items.clear();
	}

	public void setRecipeUsed(@Nullable IRecipe<?> p_193056_1_) {
		if (p_193056_1_ != null) {
			ResourceLocation resourcelocation = p_193056_1_.getId();
			this.recipesUsed.addTo(resourcelocation, 1);
		}

	}

	@Nullable
	public IRecipe<?> getRecipeUsed() {
		return null;
	}

	public void awardUsedRecipes(PlayerEntity p_201560_1_) {
	}

	public void awardUsedRecipesAndPopExperience(PlayerEntity p_235645_1_) {
		List<IRecipe<?>> list = this.getRecipesToAwardAndPopExperience(p_235645_1_.level, p_235645_1_.position());
		p_235645_1_.awardRecipes(list);
		this.recipesUsed.clear();
	}

	public List<IRecipe<?>> getRecipesToAwardAndPopExperience(World p_235640_1_, Vector3d p_235640_2_) {
		List<IRecipe<?>> list = Lists.newArrayList();

		for(Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
			p_235640_1_.getRecipeManager().byKey(entry.getKey()).ifPresent((p_235642_4_) -> {
				list.add(p_235642_4_);
				createExperience(p_235640_1_, p_235640_2_, entry.getIntValue(), ((AbstractCookingRecipe)p_235642_4_).getExperience());
			});
		}

		return list;
	}

	private static void createExperience(World p_235641_0_, Vector3d p_235641_1_, int p_235641_2_, float p_235641_3_) {
		int i = MathHelper.floor((float)p_235641_2_ * p_235641_3_);
		float f = MathHelper.frac((float)p_235641_2_ * p_235641_3_);
		if (f != 0.0F && Math.random() < (double)f) {
			++i;
		}

		while(i > 0) {
			int j = ExperienceOrbEntity.getExperienceValue(i);
			i -= j;
			p_235641_0_.addFreshEntity(new ExperienceOrbEntity(p_235641_0_, p_235641_1_.x, p_235641_1_.y, p_235641_1_.z, j));
		}

	}

	public void fillStackedContents(RecipeItemHelper p_194018_1_) {
		for(ItemStack itemstack : this.items) {
			p_194018_1_.accountStack(itemstack);
		}

	}

	net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
			net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing == Direction.UP)
				return handlers[0].cast();
			else if (facing == Direction.DOWN)
				return handlers[1].cast();
			else
				return handlers[2].cast();
		}
		return super.getCapability(capability, facing);
	}

	@Override
	protected void invalidateCaps() {
		super.invalidateCaps();
		for (int x = 0; x < handlers.length; x++)
			handlers[x].invalidate();
	}
	
	private void scheduleTick()
    {
        this.level.getBlockTicks().scheduleTick(this.getBlockPos(), this.getBlockState().getBlock(), 5);
    }

    public void onScheduledTick()
    {
        World world = this.getLevel();
        if(world != null)
        {
            this.updatePlayerCount();
            if(this.playerCount > 0)
            {
                this.scheduleTick();
            }
            else
            {
                BlockState blockState = this.getBlockState();
                if(!(blockState.getBlock() instanceof FreezerBlockSMC))
                {
                    this.setRemoved();
                    return;
                }

                boolean open = blockState.getValue(FreezerBlockSMC.LIT);
                if(open)
                {
                    //this.playDoorSound(blockState, ModSounds.BLOCK_FRIDGE_CLOSE);
                    this.setDoorState(blockState, false);
                }
            }
        }
    }
    private void setDoorState(BlockState blockState, boolean open)
    {
        World world = this.getLevel();
        if(world != null)
        {
            world.setBlock(this.getBlockPos(), blockState.setValue(FreezerBlockSMC.LIT, open), 3);
        }
    }
    
    public void updatePlayerCount()
    {
        int count = 0;
        float radius = 5.0F;
        for(PlayerEntity playerentity : this.level.getEntitiesOfClass(PlayerEntity.class, new AxisAlignedBB((double) ((float) this.worldPosition.getX() - radius), (double) ((float) this.worldPosition.getY() - radius), (double) ((float) this.worldPosition.getZ() - radius), (double) ((float) (this.worldPosition.getX() + 1) + radius), (double) ((float) (this.worldPosition.getY() + 1) + radius), (double) ((float) (this.worldPosition.getZ() + 1) + radius))))
        {
            if(playerentity.containerMenu instanceof FreezerContainerSMC)
            {
                count++;
            }
        }
        this.playerCount = count;
    }

}