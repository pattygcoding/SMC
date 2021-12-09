package formatting.src;

public class Writes 
{
	static void writeItemModel(String primary)
	{
		UtilWrites.writeAssetTo(primary, "models\\item", "item.txt");
	}
	
	static void writeBlockFiles(String primary, String file)
	{
		UtilWrites.writeAssetTo(primary, "blockstates", file + ".txt");
		UtilWrites.writeAssetTo(primary, "models\\item", "block.txt");
	}
	
	static void writeSlabFiles(String primary, String secondary, String file)
	{
		UtilWrites.writeAssetATo(primary, secondary, "blockstates", file + ".txt");
		UtilWrites.writeAssetTo(primary, "models\\item", "block.txt");
	}
	
	static void writeBlockFilesItemInv(String primary, String file)
	{
		UtilWrites.writeAssetTo(primary, "blockstates", file + ".txt");
		UtilWrites.writeAssetTo(primary, "models\\item", "item.txt");
	}
	
	static void writeBlockFilesInv(String primary, String file)
	{
		UtilWrites.writeAssetTo(primary, "blockstates", file + ".txt");
		UtilWrites.writeAssetTo(primary, "models\\item", "inventory.txt");
	}
	
	static void writeBlockFilesTrapdoor(String primary, String file)
	{
		UtilWrites.writeAssetTo(primary, "blockstates", file + ".txt");
		UtilWrites.writeAssetTo(primary, "models\\item", "trapdoor.txt");
	}
	
	static void writeModel(String primary, String file)
	{
		UtilWrites.writeAssetTo(primary, "models\\block", file + ".txt");
	}
	
	static void writeModel(String primary, String secondary, String file)
	{
		UtilWrites.writeAssetATo(primary, secondary, "models\\block", file + ".txt");
	}
	
	static void writeModel(String primary, String secondary, String suffix, String file)
	{
		UtilWrites.writeAssetAITo(primary, secondary, suffix, "models\\block", file + suffix + ".txt");
	}
	
	static void writeLootTable(String primary)
	{
		UtilWrites.writeDataTo(primary, "loot_tables\\blocks", "default.txt");
	}
	
	static void writeLootTable(String primary, String file)
	{
		UtilWrites.writeDataTo(primary, "loot_tables\\blocks", file + ".txt");
	}
	
	static void writeLootTable(String primary, String secondary, String file)
	{
		UtilWrites.writeDataTo(primary, secondary, "loot_tables\\blocks", file + ".txt");
	}
	
	static void writeRecipe(String primary, String secondary, String file)
	{
		UtilWrites.writeDataTo(primary, secondary, "recipes", file + ".txt");
	}
	
	static void writeRecipeStonecutter(String primary, String secondary, String file)
	{
		UtilWrites.writeDataToStonecutter(primary, secondary, "recipes", file + "_stonecutter.txt");
	}
	
	static void writeRecipeSc(String primary, String secondary, String file)
	{
		UtilWrites.writeDataToStonecutter(primary, secondary, "recipes", file);
	}
	
	static void writeRecipeOreCook(String mineral, String block)
	{
		UtilWrites.writeDataTo(mineral, block, "_from_blasting", "recipes", "mineral_from_blasting.txt");
		UtilWrites.writeDataTo(mineral, block, "_from_smelting", "recipes", "mineral_from_smelting.txt");
	}
}
