package source.suprememc.entities.tropicaldrudge;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import source.suprememc.SupremeMC;

@OnlyIn(Dist.CLIENT)
public class TropicalDrudgeRendererSMC extends ZombieRenderer {
   private static final ResourceLocation LOCATION = new ResourceLocation(SupremeMC.MOD_ID, "textures/entity/zombie/tropical_drudge.png");

   public TropicalDrudgeRendererSMC(EntityRendererManager p_i47204_1_) {
      super(p_i47204_1_);
   }

   protected void scale(ZombieEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
      p_225620_2_.scale(1.0625F, 1.0625F, 1.0625F);
      super.scale(p_225620_1_, p_225620_2_, p_225620_3_);
   }

   public ResourceLocation getTextureLocation(ZombieEntity p_110775_1_) {
      return LOCATION;
   }
}