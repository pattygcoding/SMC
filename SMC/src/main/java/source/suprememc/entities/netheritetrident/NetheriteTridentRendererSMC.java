package source.suprememc.entities.netheritetrident;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import source.suprememc.SupremeMC;

@OnlyIn(Dist.CLIENT)
public class NetheriteTridentRendererSMC extends EntityRenderer<NetheriteTridentEntitySMC> 
{
	public static final ResourceLocation TRIDENT_LOCATION = new ResourceLocation(SupremeMC.MOD_ID, "textures/entity/trident.png");
	private final NetheriteTridentModelSMC model = new NetheriteTridentModelSMC();

	public NetheriteTridentRendererSMC(EntityRendererManager entityRenderer) 
	{
		super(entityRenderer);
	}

	@Override
	public void render(NetheriteTridentEntitySMC trident, float x, float z, MatrixStack matrix, IRenderTypeBuffer renderType, int i) 
	{
		matrix.pushPose();
		matrix.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(z, trident.yRotO, trident.yRot) - 90.0F));
		matrix.mulPose(Vector3f.ZP.rotationDegrees(MathHelper.lerp(z, trident.xRotO, trident.xRot) + 90.0F));
		IVertexBuilder ivertexbuilder = net.minecraft.client.renderer.ItemRenderer.getFoilBufferDirect(renderType, this.model.renderType(this.getTextureLocation(trident)), false, trident.isFoil());
		this.model.renderToBuffer(matrix, ivertexbuilder, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrix.popPose();
		super.render(trident, x, z, matrix, renderType, i);
	}

	@Override
	public ResourceLocation getTextureLocation(NetheriteTridentEntitySMC p_110775_1_) 
	{
		return TRIDENT_LOCATION;
	}
}
