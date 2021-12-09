package source.suprememc.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.loading.FMLEnvironment;
import source.suprememc.entities.bear.black.BlackBearRendererSMC;
import source.suprememc.entities.bear.grizzly.GrizzlyBearRendererSMC;
import source.suprememc.entities.blackwidow.BlackWidowSpiderRendererSMC;
import source.suprememc.entities.boat.BoatRendererSMC;
import source.suprememc.entities.brownrecluse.BrownRecluseSpiderRendererSMC;
import source.suprememc.entities.caved.CavedRendererSMC;
import source.suprememc.entities.firecreeper.FireCreeperRendererSMC;
import source.suprememc.entities.miner.MinerRendererSMC;
import source.suprememc.entities.moobloom.MoobloomRendererSMC;
import source.suprememc.entities.netheritetrident.NetheriteTridentRendererSMC;
import source.suprememc.entities.soulblaze.SoulBlazeRendererSMC;
import source.suprememc.entities.tropicaldrudge.TropicalDrudgeRendererSMC;
import source.suprememc.init.objects.SMCEntities;

public class RenderEntities 
{
	public static void setup()
	{
		if(FMLEnvironment.dist == Dist.CLIENT)
        {
			RenderingRegistry.registerEntityRenderingHandler(SMCEntities.BLACK_BEAR, BlackBearRendererSMC::new);
			RenderingRegistry.registerEntityRenderingHandler(SMCEntities.GRIZZLY_BEAR, GrizzlyBearRendererSMC::new);
			RenderingRegistry.registerEntityRenderingHandler(SMCEntities.CAVED, CavedRendererSMC::new);
			RenderingRegistry.registerEntityRenderingHandler(SMCEntities.TROPICAL_DRUDGE, TropicalDrudgeRendererSMC::new);
			RenderingRegistry.registerEntityRenderingHandler(SMCEntities.FIRE_CREEPER, FireCreeperRendererSMC::new);
			RenderingRegistry.registerEntityRenderingHandler(SMCEntities.MINER, MinerRendererSMC::new);
			RenderingRegistry.registerEntityRenderingHandler(SMCEntities.BLACK_WIDOW_SPIDER, BlackWidowSpiderRendererSMC::new);
			RenderingRegistry.registerEntityRenderingHandler(SMCEntities.BROWN_RECLUSE_SPIDER, BrownRecluseSpiderRendererSMC::new);
			RenderingRegistry.registerEntityRenderingHandler(SMCEntities.SOUL_BLAZE, SoulBlazeRendererSMC::new);
			RenderingRegistry.registerEntityRenderingHandler(SMCEntities.MOOBLOOM, MoobloomRendererSMC::new);
			RenderingRegistry.registerEntityRenderingHandler(SMCEntities.BOAT, BoatRendererSMC::new);
			RenderingRegistry.registerEntityRenderingHandler(SMCEntities.NETHERITE_TRIDENT, NetheriteTridentRendererSMC::new);
        
			RenderingRegistry.registerEntityRenderingHandler(SMCEntities.SOUL_FIREBALL, x -> new SpriteRenderer<>(x, Minecraft.getInstance().getItemRenderer(), 0.75F, true));
        }
	}
}
