package source.suprememc.world.generation;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.layer.Layer;
import source.suprememc.util.ListUtil;
import source.suprememc.world.generation.layer.SMCLayerUtil;

public class SMCBiomeProvider extends BiomeProvider
{

	public static final Codec<SMCBiomeProvider> CODEC = RecordCodecBuilder.create((builder) ->
    {
        return builder.group(
            Codec.LONG.fieldOf("seed").stable().forGetter((biomeProvider) -> biomeProvider.seed),
            RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((biomeProvider) -> biomeProvider.biomes)
        ).apply(builder, builder.stable(SMCBiomeProvider::new));
    });
	
	private final long seed;
	private final Registry<Biome> biomes;
    private final Layer layer;
    
    
	public SMCBiomeProvider(long seed, Registry<Biome> biomes) 
	{
		super(ListUtil.OVERWORLD_BIOMES.stream().map((key) -> {
            return () -> {
                return biomes.getOrThrow(key);
             };
          }));
        this.seed = seed;
        // (seed, legacyDesert, biomeSize, riverSize)
        this.layer = SMCLayerUtil.getDefaultLayer(seed, false, 4, 4);
        this.biomes = biomes;
	}

	@Override
	protected Codec<SMCBiomeProvider> codec() 
	{
		return CODEC;
	}
	
	@Override
	public BiomeProvider withSeed(long seed) 
	{
		return new SMCBiomeProvider(seed, this.biomes);
	}
	

    @Override
    public Biome getNoiseBiome(int x, int y, int z)
    {
        return this.layer.get(this.biomes, x, z);
    }
}
