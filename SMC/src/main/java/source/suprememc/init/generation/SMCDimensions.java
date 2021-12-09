package source.suprememc.init.generation;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import source.suprememc.SupremeMC;

public class SMCDimensions 
{
	public static final RegistryKey<World> DEEPDARK = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(SupremeMC.MOD_ID, "cave"));
}
