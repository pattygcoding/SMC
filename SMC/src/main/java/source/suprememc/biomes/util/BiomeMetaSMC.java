package source.suprememc.biomes.util;

import java.util.ArrayList;
import java.util.List;

public class BiomeMetaSMC
{
    private final List<BiomeWeightSMC> weightList;


    public BiomeMetaSMC(List<BiomeWeightSMC> weights)
    {
        this.weightList = new ArrayList<>(weights);
    }

    public List<BiomeWeightSMC> getWeightList()
    {
        return this.weightList;
    }

    public boolean hasWeights()
    {
        return !this.weightList.isEmpty() && !this.weightList.stream().allMatch((entry) -> entry.getWeight() == 0);
    }
}