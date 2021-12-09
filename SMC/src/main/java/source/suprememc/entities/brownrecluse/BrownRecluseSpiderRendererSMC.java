package source.suprememc.entities.brownrecluse;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.util.ResourceLocation;
import source.suprememc.SupremeMC;

public class BrownRecluseSpiderRendererSMC extends SpiderRenderer<BrownRecluseSpiderEntitySMC> {
	private static final ResourceLocation LOCATION = new ResourceLocation(SupremeMC.MOD_ID, "textures/entity/spider/brown_recluse_spider.png");

	public BrownRecluseSpiderRendererSMC(EntityRendererManager p_i46189_1_) {
		super(p_i46189_1_);
	}

	public ResourceLocation getTextureLocation(BrownRecluseSpiderEntitySMC p_110775_1_) {
		return LOCATION;
	}
}