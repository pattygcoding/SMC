package source.suprememc.init.generation;

import java.util.ArrayList;
import java.util.List;

import com.mojang.serialization.Codec;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import source.suprememc.SupremeMC;
import source.suprememc.world.generation.trees.foliage.PalmFoliagePlacerSMC;

@Mod.EventBusSubscriber(modid = SupremeMC.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMCFoliagePlacerTypes 
{
	private static final List<FoliagePlacerType<?>> FOILAGE_TYPES = new ArrayList<>();
	
	public static final FoliagePlacerType<PalmFoliagePlacerSMC> PALM = register("palm_foliage_placer", PalmFoliagePlacerSMC.PALM_CODEC);
	
	
	private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String name, Codec<P> codec)
	{
		FoliagePlacerType<P> foliagePlacer = new FoliagePlacerType<P>(codec);
		foliagePlacer.setRegistryName(new ResourceLocation(SupremeMC.MOD_ID, name));
		FOILAGE_TYPES.add(foliagePlacer);
		return foliagePlacer;
	}
	
	@SubscribeEvent
	public static void registerFoliageTypes(final RegistryEvent.Register<FoliagePlacerType<?>> event)
	{
		event.getRegistry().registerAll(FOILAGE_TYPES.toArray(new FoliagePlacerType<?>[0]));
	}
}
