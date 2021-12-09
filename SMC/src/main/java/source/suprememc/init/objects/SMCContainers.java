package source.suprememc.init.objects;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.IContainerFactory;
import source.suprememc.SupremeMC;
import source.suprememc.container.brewing.SupremeBrewingStandContainerSMC;
import source.suprememc.container.crafting.WorkbenchContainerSMC;
import source.suprememc.container.freezer.FreezerContainerSMC;
import source.suprememc.container.furnace.FurnaceContainerSMC;
import source.suprememc.container.phonograph.PhonographContainerSMC;
import source.suprememc.util.Dict;

@Mod.EventBusSubscriber(modid = SupremeMC.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMCContainers 
{
	private static final List<ContainerType<?>> CONTAINER_TYPES = new ArrayList<>();

	public static final ContainerType<WorkbenchContainerSMC> CRAFTING = register("suprememc:crafting", (IContainerFactory<WorkbenchContainerSMC>) (id, playerInventory, data) -> 
	{
		return new WorkbenchContainerSMC(id, playerInventory);
	});

	public static final ContainerType<FurnaceContainerSMC> FURNACE = register("suprememc:furnace", (IContainerFactory<FurnaceContainerSMC>) (id, playerInventory, data) -> 
	{
		return new FurnaceContainerSMC(id, playerInventory);
	});

	public static final ContainerType<SupremeBrewingStandContainerSMC> SUPREME_BREWING_STAND = register(Dict.BREWING_STAND_ID, (IContainerFactory<SupremeBrewingStandContainerSMC>) (id, playerInventory, data) -> 
	{
		return new SupremeBrewingStandContainerSMC(id, playerInventory);
	});

	public static final ContainerType<FreezerContainerSMC> FREEZER = register(Dict.FREEZER_ID, (IContainerFactory<FreezerContainerSMC>) (id, playerInventory, data) -> 
	{
		return new FreezerContainerSMC(id, playerInventory);
	});

	public static final ContainerType<PhonographContainerSMC> PHONOGRAPH = register(Dict.PHONOGRAPH_ID, (IContainerFactory<PhonographContainerSMC>) (id, playerInventory, data) -> 
	{
		return new PhonographContainerSMC(id, playerInventory);
	});

	private static <T extends Container> ContainerType<T> register(String key, ContainerType.IFactory<T> factory)
	{
		ContainerType<T> type = new ContainerType<>(factory);
		type.setRegistryName(key);
		CONTAINER_TYPES.add(type);
		return type;
	}

	@SubscribeEvent
	public static void registerTypes(final RegistryEvent.Register<ContainerType<?>> event)
	{
		CONTAINER_TYPES.forEach(type -> event.getRegistry().register(type));
		CONTAINER_TYPES.clear();
	}
}