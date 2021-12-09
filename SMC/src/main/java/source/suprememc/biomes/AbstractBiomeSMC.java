package source.suprememc.biomes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import source.suprememc.biomes.util.BiomeMetaSMC;
import source.suprememc.biomes.util.BiomeWeightSMC;

public class AbstractBiomeSMC
{
    private List<BiomeWeightSMC> weightMap = new ArrayList<BiomeWeightSMC>();

    protected void configureBiome(Biome.Builder builder) {}
    protected void configureGeneration(BiomeGenerationSettings.Builder builder) {}
    protected void configureMobSpawns(MobSpawnInfo.Builder builder) {}

    protected void configureDefaultMobSpawns(MobSpawnInfo.Builder builder)
    {
        builder.setPlayerCanSpawn();
    }

    public final Biome build()
    {
        Biome.Builder biomeBuilder = new Biome.Builder();

        // Configure the biome generation
        BiomeGenerationSettings.Builder biomeGenBuilder = new BiomeGenerationSettings.Builder();
        this.configureGeneration(biomeGenBuilder);
        biomeBuilder.generationSettings(biomeGenBuilder.build());

        // Configure mob spawning
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();
        this.configureDefaultMobSpawns(mobSpawnBuilder);
        this.configureMobSpawns(mobSpawnBuilder);
        biomeBuilder.mobSpawnSettings(mobSpawnBuilder.build());

        // Configure and build the biome
        this.configureBiome(biomeBuilder);
        return biomeBuilder.build();
    }

    public final BiomeMetaSMC buildMetadata()
    {
        return new BiomeMetaSMC(this.weightMap);
    }


    public static int calculateSkyColor(float temperature)
    {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
}
