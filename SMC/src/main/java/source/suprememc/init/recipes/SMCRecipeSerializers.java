package source.suprememc.init.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import source.suprememc.SupremeMC;
import source.suprememc.recipes.CookingRecipeSerializerSMC;
import source.suprememc.recipes.FreezerRecipeSMC;
import source.suprememc.recipes.PhonographRecipeSMC;
import source.suprememc.recipes.SingleItemRecipeSMC;
import source.suprememc.util.Dict;

@Mod.EventBusSubscriber(modid = SupremeMC.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMCRecipeSerializers 
{
	@SuppressWarnings("rawtypes")
	private static final List<IRecipeSerializer> RECIPES = new ArrayList<>();
	
	public static final CookingRecipeSerializerSMC<FreezerRecipeSMC> FREEZING = register(Dict.FREEZING_ID, new CookingRecipeSerializerSMC<>(FreezerRecipeSMC::new, 200));
	public static final SingleItemRecipeSMC.Serializer<PhonographRecipeSMC> COMPOSING = register(Dict.COMPOSING_ID, new SingleItemRecipeSMC.Serializer<>(PhonographRecipeSMC::new));
	
	private static <T extends IRecipeSerializer<? extends IRecipe<?>>> T register(String name, T t)
    {
        t.setRegistryName(new ResourceLocation(name));
        RECIPES.add(t);
        return t;
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<IRecipeSerializer<?>> event)
    {
        RECIPES.forEach(item -> event.getRegistry().register(item));
        RECIPES.clear();
    }
}
