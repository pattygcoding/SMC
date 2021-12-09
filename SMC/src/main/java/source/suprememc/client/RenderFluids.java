package source.suprememc.client;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import source.suprememc.init.objects.SMCFluids;

public class RenderFluids 
{
	public static final List<Fluid> RENDER_FLUID_LIST = new ArrayList<Fluid>();
	public static void setup()
	{
		if(FMLEnvironment.dist == Dist.CLIENT)
        {
			RenderType rendertype = RenderType.translucent();
			
			RenderTypeLookup.setRenderLayer(SMCFluids.RADIATED_WATER, rendertype);
			RenderTypeLookup.setRenderLayer(SMCFluids.FLOWING_RADIATED_WATER, rendertype);
        }
	}
}
