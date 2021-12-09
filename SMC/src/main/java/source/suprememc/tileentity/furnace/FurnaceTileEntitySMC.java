package source.suprememc.tileentity.furnace;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import source.suprememc.init.objects.SMCTileEntities;

public class FurnaceTileEntitySMC extends AbstractFurnaceTileEntity 
{
	public FurnaceTileEntitySMC() 
	{
		super(SMCTileEntities.FURNACES, IRecipeType.SMELTING);
	}

	@Override
	protected ITextComponent getDefaultName() 
	{
		return new TranslationTextComponent("container.furnace");
	}

	@Override
	protected Container createMenu(int num, PlayerInventory inventory) 
	{
		return new FurnaceContainer(num, inventory, this, this.dataAccess);
	}
}