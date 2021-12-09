package source.suprememc.biomes;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import source.suprememc.SupremeMC;
import source.suprememc.biomes.util.BiomeInfoSMC;
import source.suprememc.init.generation.SMCBiomes;

public class BiomeSMC extends AbstractBiomeSMC
{
	private RegistryKey<Biome> biomeKey;
	public BiomeSMC(String name) 
	{
		BiomeInfoSMC biomeInfo = new BiomeInfoSMC(this, name);
		SMCBiomes.BIOMES.add(biomeInfo);
		RegistryKey<Biome> biomeKey = register(name);
		this.biomeKey = biomeKey;
	}
	
	private static RegistryKey<Biome> register(String name)
    {
        return RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(SupremeMC.MOD_ID, name));
    }
	
	public RegistryKey<Biome> getBiomeKey()
	{
		return this.biomeKey;
	}
	
	
	public BiomeSMC getfromId(int num)
	{
		return this;
	}
	
	public static int getSkyColorWithTemperatureModifier(float temperature) 
	{
		float lvt_1_1_ = temperature / 3.0F;
		lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
		return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
	}
}