package source.suprememc.world.generation.trees;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import source.suprememc.init.generation.SMCTrees;

public class CherryTreeSMC extends Tree
{
	@Nullable
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random randomIn, boolean largeHive) 
	{
		return SMCTrees.CHERRY_TREE;
	}

}
