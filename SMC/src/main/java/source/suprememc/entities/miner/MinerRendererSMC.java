package source.suprememc.entities.miner;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.IllagerModel;
import net.minecraft.util.ResourceLocation;
import source.suprememc.SupremeMC;

public class MinerRendererSMC extends IllagerRenderer<MinerEntitySMC> 
{
	public static final ResourceLocation MINER_LOCATION = new ResourceLocation(SupremeMC.MOD_ID, "textures/entity/illager/miner.png");
	public MinerRendererSMC(EntityRendererManager entityRendererManager) 
	{
		super(entityRendererManager, new IllagerModel<>(0.0F, 0.0F, 64, 64), 0.5F);
		this.addLayer(new HeldItemLayer<MinerEntitySMC, IllagerModel<MinerEntitySMC>>(this) 
		{
			@Override
			public void render(MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int x, MinerEntitySMC entity, float a, float b, float c, float d, float e, float f) 
			{
				if (entity.isAggressive()) super.render(matrixStack, iRenderTypeBuffer, x, entity, a, b, c, d, e, f);
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(MinerEntitySMC p_110775_1_) 
	{
		return MINER_LOCATION;
	}
}