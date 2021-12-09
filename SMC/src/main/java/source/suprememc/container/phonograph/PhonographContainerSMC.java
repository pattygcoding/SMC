package source.suprememc.container.phonograph;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.init.objects.SMCContainers;
import source.suprememc.init.recipes.SMCRecipeTypes;
import source.suprememc.recipes.PhonographRecipeSMC;

public class PhonographContainerSMC extends Container 
{
	private final IWorldPosCallable worldPosCallable;
	private final IntReferenceHolder selectedRecipe = IntReferenceHolder.standalone();
	private final World world;
	private List<PhonographRecipeSMC> recipes = Lists.newArrayList();
	private ItemStack itemStackInput = ItemStack.EMPTY;
	private long lastOnTake;
	final Slot inputInventorySlot;
	/** The inventory slot that stores the output of the crafting recipe. */
	final Slot outputInventorySlot;
	private Runnable inventoryUpdateListener = () -> {};
	public final IInventory inputInventory = new Inventory(1) 
	{
		public void setChanged() 
		{
			super.setChanged();
			PhonographContainerSMC.this.slotsChanged(this);
			PhonographContainerSMC.this.inventoryUpdateListener.run();
		}
	};
	private final CraftResultInventory inventory = new CraftResultInventory();

	public PhonographContainerSMC(int windowIdIn, PlayerInventory playerInventoryIn) 
	{
		this(windowIdIn, playerInventoryIn, IWorldPosCallable.NULL);
	}

	public PhonographContainerSMC(int windowIdIn, PlayerInventory playerInventoryIn, final IWorldPosCallable worldPosCallableIn) 
	{
		super(SMCContainers.PHONOGRAPH, windowIdIn);
		this.worldPosCallable = worldPosCallableIn;
		this.world = playerInventoryIn.player.level;
		this.inputInventorySlot = this.addSlot(new Slot(this.inputInventory, 0, 20, 33));
		this.outputInventorySlot = this.addSlot(new Slot(this.inventory, 1, 143, 33) {
			/**
			 * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
			 */
			public boolean mayPlace(ItemStack stack) {
				return false;
			}

			public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
				stack.onCraftedBy(thePlayer.level, thePlayer, stack.getCount());
				PhonographContainerSMC.this.inventory.startOpen(thePlayer);
				ItemStack itemstack = PhonographContainerSMC.this.inputInventorySlot.remove(1);
				if (!itemstack.isEmpty()) {
					PhonographContainerSMC.this.setupResultSlot();
				}

				worldPosCallableIn.execute((p_216954_1_, p_216954_2_) -> {
					long l = p_216954_1_.getGameTime();
					if (PhonographContainerSMC.this.lastOnTake != l) {
						p_216954_1_.playSound((PlayerEntity)null, p_216954_2_, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
						PhonographContainerSMC.this.lastOnTake = l;
					}

				});
				return super.onTake(thePlayer, stack);
			}
		});

		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 142));
		}

		this.addDataSlot(this.selectedRecipe);
	}

	/**
	 * Returns the index of the selected recipe.
	 */
	@OnlyIn(Dist.CLIENT)
	public int getSelectedRecipeIndex() {
		return this.selectedRecipe.get();
	}

	@OnlyIn(Dist.CLIENT)
	public List<PhonographRecipeSMC> getRecipes() {
		return this.recipes;
	}

	@OnlyIn(Dist.CLIENT)
	public int getNumRecipes() {
		return this.recipes.size();
	}

	@OnlyIn(Dist.CLIENT)
	public boolean hasInputItem() {
		return this.inputInventorySlot.hasItem() && !this.recipes.isEmpty();
	}

	/**
	 * Determines whether supplied player can use this container
	 */
	public boolean stillValid(PlayerEntity playerIn) {
		return stillValid(this.worldPosCallable, playerIn, SMCBlocks.PHONOGRAPH);
	}

	/**
	 * Handles the given Button-click on the server, currently only used by enchanting. Name is for legacy.
	 */
	public boolean clickMenuButton(PlayerEntity playerIn, int id) {
		if (this.isValidRecipeIndex(id)) {
			this.selectedRecipe.set(id);
			this.setupResultSlot();
		}

		return true;
	}

	private boolean isValidRecipeIndex(int index) {
		return index >= 0 && index < this.recipes.size();
	}

	/**
	 * Callback for when the crafting matrix is changed.
	 */
	public void slotsChanged(IInventory inventoryIn) {
		ItemStack itemstack = this.inputInventorySlot.getItem();
		if (itemstack.getItem() != this.itemStackInput.getItem()) {
			this.itemStackInput = itemstack.copy();
			this.setupRecipeList(inventoryIn, itemstack);
		}

	}

	private void setupRecipeList(IInventory inventoryIn, ItemStack stack) {
		this.recipes.clear();
		this.selectedRecipe.set(-1);
		this.outputInventorySlot.set(ItemStack.EMPTY);
		if (!stack.isEmpty()) {
			this.recipes = this.world.getRecipeManager().getRecipesFor(SMCRecipeTypes.COMPOSING, inventoryIn, this.world);
		}

	}

	private void setupResultSlot() {
		if (!this.recipes.isEmpty() && this.isValidRecipeIndex(this.selectedRecipe.get())) {
			PhonographRecipeSMC musicTableRecipe = this.recipes.get(this.selectedRecipe.get());
			this.inventory.setRecipeUsed(musicTableRecipe);
			this.outputInventorySlot.set(musicTableRecipe.assemble(this.inputInventory));
		} else {
			this.outputInventorySlot.set(ItemStack.EMPTY);
		}

		this.broadcastChanges();
	}

	public ContainerType<?> getType() {
		return SMCContainers.PHONOGRAPH;
	}

	@OnlyIn(Dist.CLIENT)
	public void registerUpdateListener(Runnable listenerIn) {
		this.inventoryUpdateListener = listenerIn;
	}
	

	/**
	 * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in is
	 * null for the initial slot that was double-clicked.
	 */
	@Override
	 public boolean canTakeItemForPickAll(ItemStack p_94530_1_, Slot p_94530_2_) {
	      return p_94530_2_.container != this.inventory && super.canTakeItemForPickAll(p_94530_1_, p_94530_2_);
	   }

	/**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
	 * inventory and the other inventory(s).
	 */
	@Override
	public ItemStack quickMoveStack(PlayerEntity p_82846_1_, int p_82846_2_) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(p_82846_2_);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			Item item = itemstack1.getItem();
			itemstack = itemstack1.copy();
			if (p_82846_2_ == 1) {
				item.onCraftedBy(itemstack1, p_82846_1_.level, p_82846_1_);
				if (!this.moveItemStackTo(itemstack1, 2, 38, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (p_82846_2_ == 0) {
				if (!this.moveItemStackTo(itemstack1, 2, 38, false)) {
					return ItemStack.EMPTY;
				}
			} else if (this.world.getRecipeManager().getRecipeFor(SMCRecipeTypes.COMPOSING, new Inventory(itemstack1), this.world).isPresent()) {
				if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
					return ItemStack.EMPTY;
				}
			} else if (p_82846_2_ >= 2 && p_82846_2_ < 29) {
				if (!this.moveItemStackTo(itemstack1, 29, 38, false)) {
					return ItemStack.EMPTY;
				}
			} else if (p_82846_2_ >= 29 && p_82846_2_ < 38 && !this.moveItemStackTo(itemstack1, 2, 29, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			}

			slot.setChanged();
			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(p_82846_1_, itemstack1);
			this.broadcastChanges();
		}

		return itemstack;
	}

	/**
	 * Called when the container is closed.
	 */
	@Override
	public void removed(PlayerEntity p_75134_1_) {
		super.removed(p_75134_1_);
		this.inventory.removeItemNoUpdate(1);
		this.worldPosCallable.execute((p_217079_2_, p_217079_3_) -> {
			this.clearContainer(p_75134_1_, p_75134_1_.level, this.inputInventory);
		});
	}
}
