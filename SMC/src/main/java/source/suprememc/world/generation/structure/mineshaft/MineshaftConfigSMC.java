package source.suprememc.world.generation.structure.mineshaft;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.gen.feature.IFeatureConfig;

public class MineshaftConfigSMC implements IFeatureConfig {
   public static final Codec<MineshaftConfigSMC> CODEC = RecordCodecBuilder.create((p_236543_0_) -> {
      return p_236543_0_.group(Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((p_236544_0_) -> {
         return p_236544_0_.probability;
      }), MineshaftStructureSMC.Type.CODEC.fieldOf("type").forGetter((p_236542_0_) -> {
         return p_236542_0_.type;
      })).apply(p_236543_0_, MineshaftConfigSMC::new);
   });
   public final float probability;
   public final MineshaftStructureSMC.Type type;

   public MineshaftConfigSMC(float p_i241988_1_, MineshaftStructureSMC.Type p_i241988_2_) {
      this.probability = p_i241988_1_;
      this.type = p_i241988_2_;
   }
}