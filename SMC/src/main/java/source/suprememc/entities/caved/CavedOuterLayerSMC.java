package source.suprememc.entities.caved;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import source.suprememc.SupremeMC;

@OnlyIn(Dist.CLIENT)
public class CavedOuterLayerSMC<T extends CavedEntitySMC> extends LayerRenderer<T, CavedModelSMC<T>> {
	private static final ResourceLocation DROWNED_OUTER_LAYER_LOCATION = new ResourceLocation(SupremeMC.MOD_ID, "textures/entity/zombie/caved_outer_layer.png");
	private final CavedModelSMC<T> model = new CavedModelSMC<>(0.25F, 0.0F, 64, 64);

	public CavedOuterLayerSMC(IEntityRenderer<T, CavedModelSMC<T>> p_i50943_1_) {
		super(p_i50943_1_);
	}

	public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, T p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
		coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, DROWNED_OUTER_LAYER_LOCATION, p_225628_1_, p_225628_2_, p_225628_3_, p_225628_4_, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_, p_225628_7_, 1.0F, 1.0F, 1.0F);
	}
}