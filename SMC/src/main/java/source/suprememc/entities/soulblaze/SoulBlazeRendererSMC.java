package source.suprememc.entities.soulblaze;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BlazeModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import source.suprememc.SupremeMC;

public class SoulBlazeRendererSMC extends MobRenderer<SoulBlazeEntitySMC, BlazeModel<SoulBlazeEntitySMC>> {
	private static final ResourceLocation BLAZE_LOCATION = new ResourceLocation(SupremeMC.MOD_ID, "textures/entity/soul_blaze.png");

	public SoulBlazeRendererSMC(EntityRendererManager p_i46191_1_) {
		super(p_i46191_1_, new BlazeModel<>(), 0.5F);
	}

	protected int getBlockLightLevel(SoulBlazeEntitySMC p_225624_1_, BlockPos p_225624_2_) {
		return 15;
	}

	public ResourceLocation getTextureLocation(SoulBlazeEntitySMC p_110775_1_) {
		return BLAZE_LOCATION;
	}
}

