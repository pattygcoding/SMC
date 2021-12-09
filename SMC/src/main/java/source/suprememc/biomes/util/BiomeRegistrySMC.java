package source.suprememc.biomes.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.reflect.TypeToken;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import source.suprememc.SupremeMC;
import source.suprememc.biomes.AbstractBiomeSMC;
import source.suprememc.biomes.BiomeSMC;
import source.suprememc.util.BiomeUtil;
import source.suprememc.util.JsonUtil;
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class BiomeRegistrySMC
{
	
	private static final String CONFIG_FILE_NAME = "biomes.json";


	private static Map<RegistrationType, List<BiomeRegData>> deferrances = Maps.newHashMap();

	public static void registerSMCBiomes(AbstractBiomeSMC biome, String name)
	{
		makeReg(RegistrationType.STANDARD_BIOME, new BiomeDataSMC(biome, name));
	}

	public static void configureBiomes()
	{
		List<BiomeRegData> regist = deferrances.get(RegistrationType.STANDARD_BIOME);
		TreeMap<String, BiomeConfigData.WeightedBiomeEntry> defaultEntries = Maps.newTreeMap();
		Map<String, BiomeDataSMC> regDataMap = Maps.newHashMap();

		for (BiomeRegData<BiomeDataSMC> registration : regist)
		{
			BiomeDataSMC regData = registration.registryData;

			if (regData.getMetadata().hasWeights())
			{
				String biomeName = new ResourceLocation(SupremeMC.MOD_ID, regData.getName()).toString();
				BiomeWeightSMC primaryWeight = regData.getPrimaryWeight();
				defaultEntries.put(biomeName, new BiomeConfigData.WeightedBiomeEntry(primaryWeight.getWeight()));
				regDataMap.put(biomeName, registration.registryData);
			}
		}

		BiomeConfigData defaultConfigData = new BiomeConfigData();
		defaultConfigData.standardBiomeWeights = defaultEntries;
		BiomeConfigData configData = getConfigData(defaultConfigData);

		TreeMap<String, BiomeConfigData.WeightedBiomeEntry> revisedStandardBiomeWeights = Maps.newTreeMap(defaultEntries);

		// Merge the config file with the default values
		for (Map.Entry<String, BiomeConfigData.WeightedBiomeEntry> biomeEntry : configData.standardBiomeWeights.entrySet())
		{
			if (revisedStandardBiomeWeights.containsKey(biomeEntry.getKey()))
			{
				revisedStandardBiomeWeights.put(biomeEntry.getKey(), biomeEntry.getValue());
			}
		}

		// Write back to the config file
		configData.standardBiomeWeights = revisedStandardBiomeWeights;
		JsonUtil.writeFile(getConfigFile(), configData);

		for (Map.Entry<String, BiomeConfigData.WeightedBiomeEntry> biomeEntry : configData.standardBiomeWeights.entrySet())
		{
			String name = biomeEntry.getKey();
			BiomeConfigData.WeightedBiomeEntry weight = biomeEntry.getValue();

			// Replace the default weight map for this biome with those from the config file
			if (regDataMap.containsKey(name))
			{
				BiomeDataSMC registrationData = regDataMap.get(name);
				registrationData.setPrimaryWeight(weight.weight);
			}
		}
	}

	private static File getConfigDirFile()
	{
		Path configPath = FMLPaths.CONFIGDIR.get();
		Path bopConfigPath = Paths.get(configPath.toAbsolutePath().toString(), SupremeMC.MOD_ID);
		return bopConfigPath.toFile();
	}

	private static File getConfigFile()
	{
		return new File(getConfigDirFile(), CONFIG_FILE_NAME);
	}

	private static BiomeConfigData getConfigData(BiomeConfigData defaultConfigData)
	{
		BiomeConfigData configData = JsonUtil.getOrCreateConfigFile(getConfigDirFile(), CONFIG_FILE_NAME, defaultConfigData, new TypeToken<BiomeConfigData>(){}.getType());
		return configData;
	}

	private static <T extends IRegistrationData> void makeReg(RegistrationType type, T data)
	{
		if (!deferrances.containsKey(type))
			deferrances.put(type, Lists.newArrayList());

		List<BiomeRegData> list = deferrances.get(type);
		list.add(new BiomeRegData(type.regFunc, data));
	}

	public static void finalizeRegistrations(RegistrationType type)
	{
		if (!deferrances.containsKey(type)) return;
		for (BiomeRegData reg : deferrances.get(type)) reg.register();   
	}

	public enum RegistrationType
	{
		STANDARD_BIOME((BiomeDataSMC data) -> 
		{
			Biome biome = data.getBiome();
			BiomeMetaSMC metadata = data.getMetadata();
			String name = data.getName();

			// Don't register biomes with their weight set to 0, that normally have weights that are non-zero
			if (!metadata.getWeightList().isEmpty() && (data.weightList.isEmpty() || data.weightList.stream().allMatch((entry) -> entry.getWeight() == 0)))
			{
				SupremeMC.LOGGER.debug("Weights absent for " + data.getName() + ", disabling...");
				return;
			}

			biome.setRegistryName(new ResourceLocation(SupremeMC.MOD_ID, name));
			ForgeRegistries.BIOMES.register(biome);

			for (BiomeWeightSMC entry : data.getWeights())
			{
				if (entry != null && entry.getWeight() > 0)
				{
					Biome smcbiome = entry.getBiome();
					int weight = entry.getWeight();
					SupremeMC.LOGGER.debug(String.format("%s weight set to %d for biome"));
					//biome.addBiome(weight, BiomeUtil.createKey(data.getBiome()));
				}
			}

			if (data.getMetadata() != null)
			{
				RegisterBiomes.biomeMetadata.put(BiomeUtil.createKey(data.getBiome()), data.getMetadata());
			}
		});

		public final Consumer<? extends IRegistrationData> regFunc;

		RegistrationType(Consumer<? extends IRegistrationData> regFunc)
		{
			this.regFunc = regFunc;
		}
	}

	private interface IRegistrationData {}


	private static class BiomeDataSMC implements IRegistrationData
	{
		private final Biome biome;
		private final BiomeMetaSMC metadata;

		private final String name;
		private List<BiomeWeightSMC> weightList;

		public BiomeDataSMC(AbstractBiomeSMC template, String name)
		{
			this.biome = template.build();
			this.metadata = template.buildMetadata();
			this.name = name;
			this.weightList = new ArrayList<>(this.getMetadata().getWeightList());
			this.ensureSingleWeight();
		}

		public Biome getBiome()
		{
			return this.biome;
		}

		@Nullable
		public BiomeMetaSMC getMetadata()
		{
			return this.metadata;
		}

		public String getName()
		{
			return this.name;
		}

		public ArrayList<BiomeWeightSMC> getWeights()
		{
			return new ArrayList<>(this.weightList);
		}

		public int getWeight(Biome biome)
		{
			for(BiomeWeightSMC i : this.weightList)
			{
				if(i.getBiome() == biome) return i.getWeight();
			}
			return 0;
		}

		public void setWeight(BiomeWeightSMC table)
		{
			this.weightList.add(table);
			this.ensureSingleWeight();
		}

		public BiomeWeightSMC getPrimaryWeight()
		{
			List<BiomeWeightSMC> list = new ArrayList<>();
			this.weightList.forEach((entry) -> list.add(new BiomeWeightSMC(entry.getBiome(), entry.getWeight())));
			return list.get(0);
		}

		public void setPrimaryWeight(int value)
		{
			Biome biome = this.getPrimaryWeight().getBiome();
			this.setWeight(new BiomeWeightSMC(biome, value));
		}

		// This limitation is enforced for config file simplicity, and because we don't need it at this time
		private void ensureSingleWeight()
		{
			if (this.weightList.size() > 1)
			{
				throw new RuntimeException(String.format("%s cannot be assigned to multiple climates!\n%s", new ResourceLocation(SupremeMC.MOD_ID, name).toString(), this.weightList));
			}
		}
	}

	private static class BiomeRegData<T extends IRegistrationData>
	{
		private final Consumer<T> registryFunction;
		private final T registryData;

		public BiomeRegData(Consumer<T> regFunc, T regData)
		{
			this.registryFunction = regFunc;
			this.registryData = regData;
		}

		public void register()
		{
			this.registryFunction.accept(this.registryData);
		}
	}
}