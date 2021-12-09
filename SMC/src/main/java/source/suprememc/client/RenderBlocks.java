package source.suprememc.client;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class RenderBlocks 
{
	public static final List<Block> RENDER_BLOCK_LIST_0 = new ArrayList<Block>();
	public static final List<Block> RENDER_BLOCK_LIST_1 = new ArrayList<Block>();
	public static final List<Block> RENDER_BLOCK_LIST_2 = new ArrayList<Block>();
	public static final List<Block> RENDER_BLOCK_LIST_3 = new ArrayList<Block>();
	
	public static void setup()
	{
		if(FMLEnvironment.dist == Dist.CLIENT)
        {
			// Tripwire
			RenderType rendertype0 = RenderType.tripwire();
			// Leaves, normal glass
			RenderType rendertype1 = RenderType.cutoutMipped();
			// Everything else
			RenderType rendertype2 = RenderType.cutout();
			// Stained glass, stained glass panes, ice, slime & honey blocks
			RenderType rendertype3 = RenderType.translucent();
			
			for(int i = 0; i < RENDER_BLOCK_LIST_0.size(); ++i) RenderTypeLookup.setRenderLayer(RENDER_BLOCK_LIST_0.get(i), rendertype0);
			for(int i = 0; i < RENDER_BLOCK_LIST_1.size(); ++i) RenderTypeLookup.setRenderLayer(RENDER_BLOCK_LIST_1.get(i), rendertype1);
			for(int i = 0; i < RENDER_BLOCK_LIST_2.size(); ++i) RenderTypeLookup.setRenderLayer(RENDER_BLOCK_LIST_2.get(i), rendertype2);
			for(int i = 0; i < RENDER_BLOCK_LIST_3.size(); ++i) RenderTypeLookup.setRenderLayer(RENDER_BLOCK_LIST_3.get(i), rendertype3);
        }
	}
}
