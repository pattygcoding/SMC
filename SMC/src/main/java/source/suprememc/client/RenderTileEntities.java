package source.suprememc.client;

import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.loading.FMLEnvironment;
import source.suprememc.init.objects.SMCTileEntities;

public class RenderTileEntities 
{
	public static void setup()
	{
		if(FMLEnvironment.dist == Dist.CLIENT)
        {
			ClientRegistry.bindTileEntityRenderer(SMCTileEntities.SIGNS, SignTileEntityRenderer::new);
        }
	}
}
