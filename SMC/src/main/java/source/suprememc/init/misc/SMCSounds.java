package source.suprememc.init.misc;

import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;
import source.suprememc.SupremeMC;
@SuppressWarnings("deprecation")
public class SMCSounds 
{
	
	public static final SoundEvent AMETHYST_BREAK = registerSound("block.amethyst.break");
	public static final SoundEvent AMETHYST_STEP = registerSound("block.amethyst.step");
	public static final SoundEvent AMETHYST_PLACE = registerSound("block.amethyst.place");
	public static final SoundEvent AMETHYST_HIT = registerSound("block.amethyst.hit");
	public static final SoundEvent AMETHYST_FALL = registerSound("block.amethyst.fall");
	
	
	public static final SoundType AMETHYST = new SoundType(1.0F, 1.5F, AMETHYST_BREAK, AMETHYST_STEP, AMETHYST_PLACE, AMETHYST_HIT, AMETHYST_FALL);

	private static SoundEvent registerSound(String name)
	{
		ResourceLocation location = new ResourceLocation(SupremeMC.MOD_ID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
