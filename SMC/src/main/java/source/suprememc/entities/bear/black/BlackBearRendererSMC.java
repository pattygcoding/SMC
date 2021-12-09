package source.suprememc.entities.bear.black;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import source.suprememc.SupremeMC;

@OnlyIn(Dist.CLIENT)
public class BlackBearRendererSMC extends MobRenderer<BlackBearEntitySMC, BlackBearModelSMC<BlackBearEntitySMC>> {
	private static final ResourceLocation BEAR_LOCATION = new ResourceLocation(SupremeMC.MOD_ID, "textures/entity/bear/blackbear.png");

	public BlackBearRendererSMC(EntityRendererManager p_i47197_1_) {
		super(p_i47197_1_, new BlackBearModelSMC<>(), 0.9F);
	}

	public ResourceLocation getTextureLocation(BlackBearEntitySMC p_110775_1_) {
		return BEAR_LOCATION;
	}

	protected void scale(BlackBearEntitySMC p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
		p_225620_2_.scale(1.2F, 1.2F, 1.2F);
		super.scale(p_225620_1_, p_225620_2_, p_225620_3_);
	}
}