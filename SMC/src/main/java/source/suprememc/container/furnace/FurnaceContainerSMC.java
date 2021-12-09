package source.suprememc.container.furnace;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.util.IIntArray;
import source.suprememc.init.objects.SMCContainers;

public class FurnaceContainerSMC extends AbstractFurnaceContainer 
{
	public FurnaceContainerSMC(int num, PlayerInventory inventory) 
	{
		super(SMCContainers.FURNACE, IRecipeType.SMELTING, RecipeBookCategory.FURNACE, num, inventory);
	}

	public FurnaceContainerSMC(int num, PlayerInventory inventory, IInventory iInventory, IIntArray iIntArray) 
	{
		super(SMCContainers.FURNACE, IRecipeType.SMELTING, RecipeBookCategory.FURNACE, num, inventory, iInventory, iIntArray);
	}
}