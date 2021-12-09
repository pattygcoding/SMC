package source.suprememc.biomes.util;

import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.google.common.collect.Maps;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.GameData;
import source.suprememc.SupremeMC;
import source.suprememc.world.SMCWorldType;
import source.suprememc.world.generation.SMCBiomeProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterBiomes 
{
	public static SMCWorldType smcWorldType = new SMCWorldType();
	public static Map<RegistryKey<Biome>, BiomeMetaSMC> biomeMetadata = Maps.newHashMap();

	public static void setup()
	{
		Logger gameDataLogger = (Logger)LogManager.getLogger(GameData.class);
		Level oldLevel = gameDataLogger.getLevel();
		gameDataLogger.setLevel(Level.OFF);
		smcWorldType.setRegistryName(new ResourceLocation(SupremeMC.MOD_ID));
		ForgeRegistries.WORLD_TYPES.register(smcWorldType);
		gameDataLogger.setLevel(oldLevel);
		Registry.register(Registry.BIOME_SOURCE, SupremeMC.MOD_ID + "_overworld", SMCBiomeProvider.CODEC);
		//Registry.register(Registry.BIOME_PROVIDER_CODEC, SupremeMC.MOD_ID + "_nether", SMCNetherBiomeProvider.CODEC);
	}
}
