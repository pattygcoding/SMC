package source.suprememc.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.init.recipes.SMCRecipeSerializers;
import source.suprememc.init.recipes.SMCRecipeTypes;

public class PhonographRecipeSMC extends SingleItemRecipeSMC
{
	public PhonographRecipeSMC(ResourceLocation id, String group, Ingredient ingredient, ItemStack result) 
	{
		super(SMCRecipeTypes.COMPOSING, SMCRecipeSerializers.COMPOSING, id, group, ingredient, result);
	}

	public boolean matches(IInventory inv, World worldIn) 
	{
		return this.ingredient.test(inv.getItem(0));
	}

	@Override
	public ItemStack getToastSymbol() 
	{
		return new ItemStack(SMCBlocks.PHONOGRAPH);
	}



}

