package source.suprememc.entities.caved;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import source.suprememc.SupremeMC;

public class CavedRendererSMC extends AbstractZombieRenderer<CavedEntitySMC, CavedModelSMC<CavedEntitySMC>> {
	private static final ResourceLocation DROWNED_LOCATION = new ResourceLocation(SupremeMC.MOD_ID, "textures/entity/zombie/caved.png");

	public CavedRendererSMC(EntityRendererManager p_i48906_1_) {
		super(p_i48906_1_, new CavedModelSMC<>(0.0F, 0.0F, 64, 64), new CavedModelSMC<>(0.5F, true), new CavedModelSMC<>(1.0F, true));
		this.addLayer(new CavedOuterLayerSMC<>(this));
	}

	public ResourceLocation getTextureLocation(ZombieEntity p_110775_1_) {
		return DROWNED_LOCATION;
	}

	protected void setupRotations(CavedEntitySMC p_225621_1_, MatrixStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
		super.setupRotations(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
		float f = p_225621_1_.getSwimAmount(p_225621_5_);
		if (f > 0.0F) {
			p_225621_2_.mulPose(Vector3f.XP.rotationDegrees(MathHelper.lerp(f, p_225621_1_.xRot, -10.0F - p_225621_1_.xRot)));
		}

	}
}