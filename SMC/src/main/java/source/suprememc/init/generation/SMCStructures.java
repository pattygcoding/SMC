package source.suprememc.init.generation;

import net.minecraftforge.fml.common.Mod;
import source.suprememc.SupremeMC;

@Mod.EventBusSubscriber(modid = SupremeMC.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMCStructures 
{
	/*private static final List<Structure<?>> STRUCTURES = new ArrayList<>();
	
	public static final Structure<MineshaftConfigSMC> MINESHAFT = register("Mineshaft_Smc", new MineshaftStructureSMC(MineshaftConfigSMC.CODEC));

	
	public static final StructureFeature<MineshaftConfigSMC, ? extends Structure<MineshaftConfigSMC>> CAVE_MINESHAFT = structure("cave_mineshaft", MINESHAFT.configured(new MineshaftConfigSMC(0.004F, MineshaftStructureSMC.Type.CAVE)));
	public static final StructureFeature<MineshaftConfigSMC, ? extends Structure<MineshaftConfigSMC>> CRIMSON_MINESHAFT = structure("crimson_mineshaft", MINESHAFT.configured(new MineshaftConfigSMC(0.004F, MineshaftStructureSMC.Type.CRIMSON)));
	public static final StructureFeature<MineshaftConfigSMC, ? extends Structure<MineshaftConfigSMC>> MUSTARD_MINESHAFT = structure("mustard_mineshaft", MINESHAFT.configured(new MineshaftConfigSMC(0.004F, MineshaftStructureSMC.Type.MUSTARD)));
	public static final StructureFeature<MineshaftConfigSMC, ? extends Structure<MineshaftConfigSMC>> WARPED_MINESHAFT = structure("warped_mineshaft", MINESHAFT.configured(new MineshaftConfigSMC(0.004F, MineshaftStructureSMC.Type.WARPED)));
	public static final StructureFeature<MineshaftConfigSMC, ? extends Structure<MineshaftConfigSMC>> NETHER_MINESHAFT = structure("nether_mineshaft", MINESHAFT.configured(new MineshaftConfigSMC(0.004F, MineshaftStructureSMC.Type.NETHER)));

	
	private static <C extends IFeatureConfig, F extends Structure<C>> F register(String id, F structureType)
	{
		Structure.STRUCTURES_REGISTRY.put(id.toLowerCase(Locale.ROOT), structureType);
		F type = structureType; 
		type.setRegistryName(SupremeMC.MOD_ID, id.toLowerCase(Locale.ROOT));
		STRUCTURES.add(type);
		return type;
	}

	@SubscribeEvent
	public static void registerTypes(final RegistryEvent.Register<Structure<?>> event)
	{
		STRUCTURES.forEach(type -> event.getRegistry().register(type));
		STRUCTURES.clear();
	}
	
	private static <FC extends IFeatureConfig, F extends Structure<FC>> StructureFeature<FC, F> structure(String name, StructureFeature<FC, F> feature) 
	{
		return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, new ResourceLocation(SupremeMC.MOD_ID, name), feature);
	}*/
}
