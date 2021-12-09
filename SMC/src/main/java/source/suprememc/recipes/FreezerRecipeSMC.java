package source.suprememc.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.init.recipes.SMCRecipeSerializers;
import source.suprememc.init.recipes.SMCRecipeTypes;

public class FreezerRecipeSMC extends AbstractCookingRecipe 
{
	public FreezerRecipeSMC(ResourceLocation idIn, String group, Ingredient ing, ItemStack item, float XP, int time) 
	{
		super(SMCRecipeTypes.FREEZING, idIn, group, ing, item, XP, time);
	}
	
	public ItemStack getIcon() 
	{
		return new ItemStack(SMCBlocks.FREEZER);
	}
	
	public IRecipeSerializer<?> getSerializer() 
	{
		return SMCRecipeSerializers.FREEZING;
	}
}