package source.suprememc.container.freezer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.item.crafting.ServerRecipePlacerFurnace;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import source.suprememc.container.freezer.slots.FreezerFuelSlotSMC;
import source.suprememc.container.freezer.slots.FreezerResultSlotSMC;
import source.suprememc.init.objects.SMCContainers;
import source.suprememc.init.recipes.SMCRecipeTypes;

@SuppressWarnings({"rawtypes","unchecked"})
public class FreezerContainerSMC extends RecipeBookContainer<IInventory> 
{
	private final IInventory freezerInventory;
	private final IIntArray freezerData;
	protected final World world;
	private final IRecipeType<? extends AbstractCookingRecipe> recipeType;
	private final RecipeBookCategory recipeBookCategory;

	public FreezerContainerSMC(int id, PlayerInventory playerInventory) 
	{
		this(SMCContainers.FREEZER, SMCRecipeTypes.FREEZING, id, playerInventory, new Inventory(3), new IntArray(4));
	}
	public FreezerContainerSMC(int id, PlayerInventory playerInventory, IInventory inventory, IIntArray intArray) 
	{
		this(SMCContainers.FREEZER, SMCRecipeTypes.FREEZING,id, playerInventory, inventory, intArray);
	}
	public FreezerContainerSMC(ContainerType<?> containerType, IRecipeType<? extends AbstractCookingRecipe> recipeType, int id, PlayerInventory playerInventory, IInventory inventory, IIntArray intArray) 
	{
	      super(containerType, id);
	      this.recipeType = recipeType;
	      this.recipeBookCategory = RecipeBookCategory.FURNACE;
	      checkContainerSize(inventory, 3);
	      checkContainerDataCount(intArray, 4);
	      this.freezerInventory = inventory;
	      this.freezerData = intArray;
	      this.world = playerInventory.player.level;
	      this.addSlot(new Slot(inventory, 0, 56, 17));
	      this.addSlot(new FreezerFuelSlotSMC(this, inventory, 1, 56, 53));
	      this.addSlot(new FreezerResultSlotSMC(playerInventory.player, inventory, 2, 116, 35));
	      for(int i = 0; i < 3; ++i) 
	      {
	         for(int j = 0; j < 9; ++j)  this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18)); 
	      }
	      for(int k = 0; k < 9; ++k) this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
	      this.addDataSlots(intArray);
	}

	@Override
	public void fillCraftSlotsStackedContents(RecipeItemHelper itemHelperIn) 
	{
	      if (this.freezerInventory instanceof IRecipeHelperPopulator) ((IRecipeHelperPopulator)this.freezerInventory).fillStackedContents(itemHelperIn);
	}
	@Override
	public void clearCraftingContent() 
	{
	      this.freezerInventory.clearContent();
	}
	@Override
	public boolean stillValid(PlayerEntity playerIn) 
	{
		return this.freezerInventory.stillValid(playerIn);
	}
	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) 
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if(slot != null && slot.hasItem()) 
		{
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if(index == 2) 
			{
	            if(!this.moveItemStackTo(itemstack1, 3, 39, true)) return ItemStack.EMPTY;
	            slot.onQuickCraft(itemstack1, itemstack);
			} 
			else if(index != 1 && index != 0) 
			{
				if(this.hasRecipe(itemstack1)) 
				{
	               if(!this.moveItemStackTo(itemstack1, 0, 1, false)) return ItemStack.EMPTY;
	            } 
				else if (this.isFuel(itemstack1)) 
				{
	               if (!this.moveItemStackTo(itemstack1, 1, 2, false)) return ItemStack.EMPTY; 
	            } 
				else if (index >= 3 && index < 30) 
				{
					if(!this.moveItemStackTo(itemstack1, 30, 39, false)) return ItemStack.EMPTY;
	            } 
				else if (index >= 30 && index < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) return ItemStack.EMPTY; 
			} 
			else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) return ItemStack.EMPTY;
			if (itemstack1.isEmpty()) slot.set(ItemStack.EMPTY);
	        else slot.setChanged();
	        if (itemstack1.getCount() == itemstack.getCount()) return ItemStack.EMPTY;
	        slot.onTake(playerIn, itemstack1);
		}
		return itemstack;
	}
	public boolean isFuel(ItemStack stack) 
	{
		if(stack.getItem() == Items.ICE) return true;
		else if(stack.getItem() == Items.PACKED_ICE) return true;
		else if(stack.getItem() == Items.BLUE_ICE) return true;
		else if(stack.getItem() == Items.SNOWBALL) return true;
		else if(stack.getItem() == Items.SNOW) return true;
		else if(stack.getItem() == Items.SNOW_BLOCK) return true;
		else return false;
	}
	@Override
	public boolean recipeMatches(IRecipe<? super IInventory> recipeIn) 
	{
	      return recipeIn.matches(this.freezerInventory, this.world);
	}
	@Override
	public int getResultSlotIndex() {return 2;}
	@Override
	public int getGridWidth() {return 1;}
	@Override
	public int getGridHeight() {return 1;}
	@Override
	@OnlyIn(Dist.CLIENT)
	public int getSize() {return 3;}
	@OnlyIn(Dist.CLIENT)
	public int getSolidifyProgressionScaled() 
	{
		int i = this.freezerData.get(2);
		int j = this.freezerData.get(3);
		return j != 0 && i != 0 ? i * 24 / j : 0;
	}
	@OnlyIn(Dist.CLIENT)
	public int getFuelLeftScaled() 
	{
		int i = this.freezerData.get(1);
		if (i == 0) i = 200;
		return this.freezerData.get(0) * 13 / i;
	}
	@OnlyIn(Dist.CLIENT)
	public boolean isFueling() 
	{
		return this.freezerData.get(0) > 0;
	}
	@OnlyIn(Dist.CLIENT)
	public RecipeBookCategory getRecipeBookType() 
	{
		return this.recipeBookCategory;
	}
	/***********************************************************************************************************************************/
	/***********************************************************************************************************************************/
	/***********************************************************************************************************************************/
	@Override
	public void handlePlacement(boolean p_217056_1_, IRecipe<?> p_217056_2_, ServerPlayerEntity player) 
	{
		(new ServerRecipePlacerFurnace<>(this)).recipeClicked(player, (IRecipe<IInventory>) p_217056_2_, p_217056_1_);
	}

	protected boolean hasRecipe(ItemStack stack) 
	{
		return this.world.getRecipeManager().getRecipeFor((IRecipeType)this.recipeType, new Inventory(stack), this.world).isPresent();
	}
	
	

	

	   
}
