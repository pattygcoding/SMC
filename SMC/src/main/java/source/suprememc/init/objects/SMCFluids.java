package source.suprememc.init.objects;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;
import source.suprememc.SupremeMC;
import source.suprememc.blocks.FlowingFluidBlockSMC;
import source.suprememc.client.RenderFluids;

@Mod.EventBusSubscriber(modid = SupremeMC.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMCFluids 
{
	private static final List<FlowingFluid> FLUIDS = new ArrayList<>();
	
	public static final ResourceLocation RADIATED_WATER_STILL_RL = new ResourceLocation(SupremeMC.MOD_ID, "block/radiated_water_still");
	public static final ResourceLocation RADIATED_WATER_FLOWING_RL = new ResourceLocation(SupremeMC.MOD_ID, "block/radiated_water_flowing");
	public static final ResourceLocation RADIATED_WATER_OVERLAY_RL = new ResourceLocation(SupremeMC.MOD_ID, "block/radiated_water_overlay");
	
	
	public static final ForgeFlowingFluid.Properties RW_PROPERTIES = new ForgeFlowingFluid.Properties
			(
			() -> SMCFluids.RADIATED_WATER, () -> SMCFluids.FLOWING_RADIATED_WATER,
			FluidAttributes.builder(RADIATED_WATER_STILL_RL, RADIATED_WATER_FLOWING_RL).density(1024).viscosity(1024).luminosity(10).rarity(Rarity.RARE)
					.sound(SoundEvents.BUCKET_EMPTY).overlay(RADIATED_WATER_OVERLAY_RL))
							.block(() -> (FlowingFluidBlockSMC)SMCBlocks.RADIATED_WATER).bucket(() -> SMCItems.RADIATED_WATER_BUCKET);
	 
	
	public static final FlowingFluid FLOWING_RADIATED_WATER = register("suprememc:flowing_radiated_water_fluid", new ForgeFlowingFluid.Flowing(SMCFluids.RW_PROPERTIES));
	public static final FlowingFluid RADIATED_WATER = register("suprememc:radiated_water_fluid", new ForgeFlowingFluid.Source(SMCFluids.RW_PROPERTIES));

	
	private static FlowingFluid register(String id, FlowingFluid fluidType)
	{
		FlowingFluid type = fluidType; 
		type.setRegistryName(id);
		FLUIDS.add(type);
		return type;
	}

	@SubscribeEvent
	public static void registerTypes(final RegistryEvent.Register<Fluid> event)
	{
		FLUIDS.forEach(type -> event.getRegistry().register(type));
		RenderFluids.setup();
		FLUIDS.clear();
	}
	
	
}
