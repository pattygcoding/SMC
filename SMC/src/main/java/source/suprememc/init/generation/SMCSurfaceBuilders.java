package source.suprememc.init.generation;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.NetherForestsSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.registries.ForgeRegistries;
import source.suprememc.SupremeMC;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.world.generation.surface.CaveValleySurfaceAreaSMC;
import source.suprememc.world.generation.surface.DeepDarkSurfaceAreaSMC;
import source.suprememc.world.generation.surface.SandSurfaceAreaSMC;

public class SMCSurfaceBuilders 
{
	private static final SurfaceBuilder<SurfaceBuilderConfig> ANDESITE_CAVE = register("andesite_cave", new CaveValleySurfaceAreaSMC(Blocks.ANDESITE, SurfaceBuilderConfig.CODEC.stable()));
	private static final SurfaceBuilder<SurfaceBuilderConfig> BLACKSTONE_CAVE = register("blackstone_cave", new CaveValleySurfaceAreaSMC(Blocks.BLACKSTONE, SurfaceBuilderConfig.CODEC.stable()));
	private static final SurfaceBuilder<SurfaceBuilderConfig> DEEP_DARK = register("deep_dark", new DeepDarkSurfaceAreaSMC(SurfaceBuilderConfig.CODEC.stable()));
	private static final SurfaceBuilder<SurfaceBuilderConfig> DIORITE_CAVE = register("diorite_cave", new CaveValleySurfaceAreaSMC(Blocks.DIORITE, SurfaceBuilderConfig.CODEC.stable()));
	private static final SurfaceBuilder<SurfaceBuilderConfig> GEODE_CAVE = register("geode_cave", new CaveValleySurfaceAreaSMC(SMCBlocks.CALCITE, SurfaceBuilderConfig.CODEC.stable()));
	private static final SurfaceBuilder<SurfaceBuilderConfig> GRANITE_CAVE = register("granite_cave", new CaveValleySurfaceAreaSMC(Blocks.GRANITE, SurfaceBuilderConfig.CODEC.stable()));
	private static final SurfaceBuilder<SurfaceBuilderConfig> MAGMA_CAVE = register("magma_cave", new CaveValleySurfaceAreaSMC(Blocks.MAGMA_BLOCK, SurfaceBuilderConfig.CODEC.stable()));
	private static final SurfaceBuilder<SurfaceBuilderConfig> LAVENDER_FOREST = register("lavender_forest", new NetherForestsSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
	private static final SurfaceBuilder<SurfaceBuilderConfig> PALM = register("palm", new SandSurfaceAreaSMC(SurfaceBuilderConfig.CODEC.stable(), SMCBlocks.ARABLE_SAND, Blocks.SANDSTONE));
	private static final SurfaceBuilder<SurfaceBuilderConfig> QUARTZ_CAVE = register("quartz_cave", new CaveValleySurfaceAreaSMC(Blocks.SMOOTH_QUARTZ, SurfaceBuilderConfig.CODEC.stable()));
	private static final SurfaceBuilder<SurfaceBuilderConfig> SAHARA = register("sahara", new SandSurfaceAreaSMC(SurfaceBuilderConfig.CODEC.stable(), SMCBlocks.COARSE_SAND, Blocks.SANDSTONE));
	private static final SurfaceBuilder<SurfaceBuilderConfig> SALT_FLAT = register("salt_flat", new SandSurfaceAreaSMC(SurfaceBuilderConfig.CODEC.stable(), SMCBlocks.SALT_BLOCK, SMCBlocks.SALT_STONE));

	
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> ANDESITE_CAVE_SURFACE = surface(ANDESITE_CAVE, Blocks.ANDESITE, "andesite_cave");
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> BLACKSTONE_CAVE_SURFACE = surface(BLACKSTONE_CAVE, Blocks.ANDESITE, "blackstone_cave");
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DEEP_DARK_SURFACE = surface(DEEP_DARK, SMCBlocks.DEEPSLATE, "deep_dark");
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DIORITE_CAVE_SURFACE = surface(DIORITE_CAVE, Blocks.GRANITE, "diorite_cave");
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> GEODE_CAVE_SURFACE = surface(GEODE_CAVE, SMCBlocks.CALCITE, "geode_cave");
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> GRANITE_CAVE_SURFACE = surface(GRANITE_CAVE, Blocks.GRANITE, "granite_cave");
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> MAGMA_CAVE_SURFACE = surface(MAGMA_CAVE, Blocks.MAGMA_BLOCK, "magma_cave");
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PALM_SURFACE = surface(PALM, SMCBlocks.ARABLE_SAND, "palm");
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> QUARTZ_CAVE_SURFACE = surface(QUARTZ_CAVE, Blocks.SMOOTH_QUARTZ, "quartz_cave");
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> SAHARA_SURFACE = surface(SAHARA, SMCBlocks.COARSE_SAND, "sahara");
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> SALT_FLAT_SURFACE = surface(SALT_FLAT, SMCBlocks.SALT_BLOCK, "salt_flat");
	public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> LAVENDER_FOREST_SURFACE = surface(LAVENDER_FOREST, SMCBlocks.LAVENDER_ENDSPAR, Blocks.END_STONE, SMCBlocks.LAVENDER_WART_BLOCK, "lavender_forest");

	
	private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String key, F builder)
    {
        builder.setRegistryName(new ResourceLocation(SupremeMC.MOD_ID, key + "surface_builder"));
        ForgeRegistries.SURFACE_BUILDERS.register(builder);
        return builder;
    }
	
	protected static <SC extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<SurfaceBuilderConfig> surface(SurfaceBuilder<SurfaceBuilderConfig> surfaceBuilder, Block block, String key)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(SupremeMC.MOD_ID, key + "_configured_surface_builder"), new ConfiguredSurfaceBuilder<>(surfaceBuilder, new SurfaceBuilderConfig(block.defaultBlockState(), block.defaultBlockState(), block.defaultBlockState())));
    }
	
	protected static <SC extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<SurfaceBuilderConfig> surface(SurfaceBuilder<SurfaceBuilderConfig> surfaceBuilder, Block block1, Block block2, Block block3, String key)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(SupremeMC.MOD_ID, key + "_configured_surface_builder"), new ConfiguredSurfaceBuilder<>(surfaceBuilder, new SurfaceBuilderConfig(block1.defaultBlockState(), block2.defaultBlockState(), block3.defaultBlockState())));
    }
}
