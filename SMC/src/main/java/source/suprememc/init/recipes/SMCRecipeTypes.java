package source.suprememc.init.recipes;

import net.minecraft.item.crafting.IRecipeType;
import source.suprememc.recipes.FreezerRecipeSMC;
import source.suprememc.recipes.PhonographRecipeSMC;
import source.suprememc.util.Dict;

public class SMCRecipeTypes 
{
	public static final IRecipeType<FreezerRecipeSMC> FREEZING = IRecipeType.register(Dict.FREEZING_ID);
	public static final IRecipeType<PhonographRecipeSMC> COMPOSING = IRecipeType.register(Dict.COMPOSING_ID);
}
