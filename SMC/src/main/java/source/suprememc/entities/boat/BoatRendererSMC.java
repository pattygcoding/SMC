package source.suprememc.entities.boat;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import source.suprememc.SupremeMC;

@OnlyIn(Dist.CLIENT)
public class BoatRendererSMC extends EntityRenderer<BoatEntitySMC>
{
	private static String location = "textures/entity/boat/";
	protected final BoatModelSMC modelBoat = new BoatModelSMC();
	
    private static final List<ResourceLocation> BOAT_TEXTURES = boatTextures();

    public BoatRendererSMC(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowRadius = 0.8F;
    }

    @Override
    public void render(BoatEntitySMC entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) 
    {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.0D, 0.375D, 0.0D);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
        float f = (float)entityIn.getHurtTime() - partialTicks;
        float f1 = entityIn.getDamage() - partialTicks;
        if(f1 < 0.0F) f1 = 0.0F;
        if(f > 0.0F) matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(MathHelper.sin(f) * f * f1 / 10.0F * (float)entityIn.getHurtDir()));
        float f2 = entityIn.getBubbleAngle(partialTicks);
        if(!MathHelper.equal(f2, 0.0F)) matrixStackIn.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), entityIn.getBubbleAngle(partialTicks), true));
        matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        this.modelBoat.setupAnim(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.modelBoat.renderType(this.getTextureLocation(entityIn)));
        this.modelBoat.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        IVertexBuilder ivertexbuilder1 = bufferIn.getBuffer(RenderType.waterMask());
        this.modelBoat.getNoWater().render(matrixStackIn, ivertexbuilder1, packedLightIn, OverlayTexture.NO_OVERLAY);
        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
     }

    @Override
    public ResourceLocation getTextureLocation(BoatEntitySMC entity)
    {
        return BOAT_TEXTURES.get(entity.getBoatModel().ordinal());
    }
    
    private static List<ResourceLocation> boatTextures()
    {
    	List<String> boatNames = BoatEntitySMC.Type.boatNames();
    	List<ResourceLocation> resourceLocations = new ArrayList<ResourceLocation>();
    	for(String boat : boatNames) resourceLocations.add(new ResourceLocation(SupremeMC.MOD_ID, location + boat + ".png"));
    	return resourceLocations;
    }
}
