package source.suprememc.container.freezer.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import source.suprememc.container.freezer.FreezerContainerSMC;

public class FreezerFuelSlotSMC extends Slot 
{
	private final FreezerContainerSMC container;

	public FreezerFuelSlotSMC(FreezerContainerSMC container, IInventory inventory, int index, int x, int y) 
	{
		super(inventory, index, x, y);
		this.container = container;
	}

	@Override
	public boolean mayPlace(ItemStack stack) 
	{
		return this.container.isFuel(stack);
	}
}