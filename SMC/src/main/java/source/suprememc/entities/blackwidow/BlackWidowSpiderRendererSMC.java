package source.suprememc.entities.blackwidow;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.util.ResourceLocation;
import source.suprememc.SupremeMC;

public class BlackWidowSpiderRendererSMC extends SpiderRenderer<BlackWidowSpiderEntitySMC> {
	private static final ResourceLocation LOCATION = new ResourceLocation(SupremeMC.MOD_ID, "textures/entity/spider/black_widow_spider.png");

	public BlackWidowSpiderRendererSMC(EntityRendererManager p_i46189_1_) {
		super(p_i46189_1_);
	}

	public ResourceLocation getTextureLocation(BlackWidowSpiderEntitySMC p_110775_1_) {
		return LOCATION;
	}
}