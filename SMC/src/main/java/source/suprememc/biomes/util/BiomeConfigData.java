package source.suprememc.biomes.util;

import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;

import java.util.TreeMap;

public class BiomeConfigData
{
    @SerializedName("standard_weights")
    public TreeMap<String, WeightedBiomeEntry> standardBiomeWeights = Maps.newTreeMap();


    public static class WeightedBiomeEntry
    {
        public int weight;

        public WeightedBiomeEntry(int weight)
        {
            this.weight = weight;
        }
    }
}