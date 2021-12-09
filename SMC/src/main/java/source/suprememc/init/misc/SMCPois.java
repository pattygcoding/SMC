package source.suprememc.init.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import source.suprememc.SupremeMC;
import source.suprememc.init.objects.SMCBlocks;

@Mod.EventBusSubscriber(modid = SupremeMC.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMCPois 
{
	private static final List<PointOfInterestType> POIS = new ArrayList<>();

	public static final PointOfInterestType DEEPDARK_PORTAL = registerPoi("deepdark_portal_poi", new PointOfInterestType("deepdark_portal_poi", getBlockStates(SMCBlocks.DEEPDARK_PORTAL), 0, 1));

	

	private static PointOfInterestType registerPoi(String name, PointOfInterestType poi)
	{
		poi.setRegistryName(new ResourceLocation(SupremeMC.MOD_ID, name));
		POIS.add(poi);
		return poi;
	}

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<PointOfInterestType> event)
	{
		POIS.forEach(item -> event.getRegistry().register(item));
		POIS.clear();
	}
	
	private static Set<BlockState> getBlockStates(Block block) 
	{
		return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
	}
}
