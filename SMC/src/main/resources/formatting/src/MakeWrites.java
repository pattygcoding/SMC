package formatting.src;

import java.util.List;

import com.google.common.collect.ImmutableList;

@SuppressWarnings("unused")
class MakeWrites 
{
	private static final String CUBE = "cube", FENCE = "fence", FENCE_GATE = "fence_gate", LOG = "log", SLAB = "slab", STAIRS = "stairs", 
			WOOD = "wood", DOOR = "door", TRAPDOOR = "trapdoor", LEAVES = "leaves", BUTTON = "button", PRESSURE_PLATE = "pressure_plate",
			SAPLING = "sapling", BOOKSHELF = "bookshelf", WIRELESS_RECEIVER = "wireless_receiver", WIRELESS_SENDER = "wireless_sender",
			SLIME_BLOCK = "slime_block", VANILLA = "vanilla_", WALL = "wall", CRAFTING_TABLE = "crafting_table", FURNACE = "furnace";
	
	private static final List<String> TOOLS = ImmutableList.of("sword", "axe", "pickaxe", "shovel", "hoe");
	private static final List<String> ARMOR = ImmutableList.of("helmet", "chestplate", "leggings", "boots", "horse_armor");
	private static final List<String> COLORS = ImmutableList.of("red", "orange", "yellow", "lime", "green", "cyan", "light_blue", "blue",
			"purple", "magenta", "pink", "white", "light_gray", "gray", "black", "brown");
	
	static void writeItem(String item)
	{
		Writes.writeItemModel(item);
	}
	
	static void writeBasicBlock(String block)
	{
		Writes.writeBlockFiles(block, CUBE);
		Writes.writeModel(block, CUBE);
	}
	
	static void writeBasicBlockWithDrops(String block)
	{
		Writes.writeBlockFiles(block, CUBE);
		Writes.writeModel(block, CUBE);
		Writes.writeLootTable(block);
	}
	
	static void writePlankBlock(String block, String sourceWoodTag)
	{
		writeBasicBlockWithDrops(block);
		Writes.writeRecipe(block, sourceWoodTag, "planks");
	}
	
	static void writeSlabBlock(String block, String sourceBlock, Boolean isStone)
	{
		Writes.writeSlabFiles(block, sourceBlock, SLAB);
		Writes.writeModel(block, sourceBlock, SLAB);
		Writes.writeModel(block, sourceBlock, "_top", SLAB);
		Writes.writeLootTable(block, SLAB);
		Writes.writeRecipe(block, sourceBlock, SLAB);
		if(isStone) Writes.writeRecipeStonecutter(block, sourceBlock, SLAB);
	}
	
	static void writeVanillaSlabBlock(String block, String sourceBlock, Boolean isStone)
	{
		UtilWrites.writeAssetATo(block, sourceBlock, "blockstates", VANILLA + SLAB + ".txt");
		UtilWrites.writeAssetTo(block, "models\\item", "block.txt");
		Writes.writeModel(block, sourceBlock, VANILLA + SLAB);
		Writes.writeModel(block, sourceBlock, "_top", VANILLA + SLAB);
		Writes.writeLootTable(block, SLAB);
		Writes.writeRecipe(block, sourceBlock, VANILLA + SLAB);
		if(isStone) Writes.writeRecipeStonecutter(block, sourceBlock, VANILLA + SLAB);
	}
	
	static void writeWallBlock(String block, String sourceBlock)
	{
		Writes.writeBlockFilesInv(block, WALL);
		Writes.writeModel(block, sourceBlock, "_inventory", WALL);
		Writes.writeModel(block, sourceBlock, "_post", WALL);
		Writes.writeModel(block, sourceBlock, "_side_tall", WALL);
		Writes.writeModel(block, sourceBlock, "_side", WALL);
		Writes.writeLootTable(block);
		Writes.writeRecipe(block, sourceBlock, WALL);
		Writes.writeRecipeStonecutter(block, sourceBlock, WALL);
	}
	
	static void writeVanillaWallBlock(String block, String sourceBlock)
	{
		Writes.writeBlockFilesInv(block, WALL);
		Writes.writeModel(block, sourceBlock, "_inventory", VANILLA + WALL);
		Writes.writeModel(block, sourceBlock, "_post", VANILLA + WALL);
		Writes.writeModel(block, sourceBlock, "_side_tall", VANILLA + WALL);
		Writes.writeModel(block, sourceBlock, "_side", VANILLA + WALL);
		Writes.writeLootTable(block);
		Writes.writeRecipe(block, sourceBlock, VANILLA + WALL);
		Writes.writeRecipeStonecutter(block, sourceBlock, VANILLA + WALL);
	}
	
	static void writeStairsBlock(String block, String sourceBlock, Boolean isStone)
	{
		Writes.writeBlockFiles(block, STAIRS);
		Writes.writeModel(block, sourceBlock, STAIRS);
		Writes.writeModel(block, sourceBlock, "_inner", STAIRS);
		Writes.writeModel(block, sourceBlock, "_outer", STAIRS);
		Writes.writeLootTable(block);
		Writes.writeRecipe(block, sourceBlock, STAIRS);
		if(isStone) Writes.writeRecipeStonecutter(block, sourceBlock, STAIRS);
	}
	
	static void writeVanillaStairsBlock(String block, String sourceBlock, Boolean isStone)
	{
		Writes.writeBlockFiles(block, STAIRS);
		Writes.writeModel(block, sourceBlock, VANILLA + STAIRS);
		Writes.writeModel(block, sourceBlock, "_inner", VANILLA + STAIRS);
		Writes.writeModel(block, sourceBlock, "_outer", VANILLA + STAIRS);
		Writes.writeLootTable(block);
		Writes.writeRecipe(block, sourceBlock, VANILLA + STAIRS);
		if(isStone) Writes.writeRecipeStonecutter(block, sourceBlock, VANILLA + STAIRS);
	}
	
	static void writeLogBlock(String block)
	{
		Writes.writeBlockFiles(block, LOG);
		Writes.writeModel(block, LOG);
		Writes.writeLootTable(block, LOG);
		Writes.writeModel(block, "", "_horizontal", LOG);
	}
	
	static void writeWoodBlock(String block, String logBlock)
	{
		Writes.writeBlockFiles(block, WOOD);
		Writes.writeLootTable(block, LOG);
		Writes.writeRecipe(block, logBlock, WOOD);
		Writes.writeModel(block, logBlock, WOOD);
	}
	
	static void writeFenceBlock(String block, String plank)
	{
		Writes.writeBlockFilesInv(block, FENCE);
		Writes.writeModel(block, plank, "_side", FENCE);
		Writes.writeModel(block, plank, "_post", FENCE);
		Writes.writeModel(block, plank, "_inventory", FENCE);
		Writes.writeLootTable(block);
		Writes.writeRecipe(block, plank, FENCE);
	}
	
	static void writeFenceGateBlock(String block, String plank)
	{
		Writes.writeBlockFiles(block, FENCE_GATE);
		Writes.writeModel(block, plank, FENCE_GATE);
		Writes.writeModel(block, plank, "_open", FENCE_GATE);
		Writes.writeModel(block, plank, "_wall", FENCE_GATE);
		Writes.writeModel(block, plank, "_wall_open", FENCE_GATE);
		Writes.writeLootTable(block);
		Writes.writeRecipe(block, plank, FENCE_GATE);
	}
	
	static void writeDoorBlock(String block, String plank)
	{
		Writes.writeBlockFilesItemInv(block, DOOR);
		Writes.writeModel(block, "", "_top", DOOR);
		Writes.writeModel(block, "", "_top_hinge", DOOR);
		Writes.writeModel(block, "", "_bottom", DOOR);
		Writes.writeModel(block, "", "_bottom_hinge", DOOR);
		Writes.writeLootTable(block, DOOR);
		Writes.writeRecipe(block, plank, DOOR);
	}
	
	static void writeTrapdoorBlock(String block, String plank)
	{
		Writes.writeBlockFilesTrapdoor(block, TRAPDOOR);
		Writes.writeModel(block, "", "_top", TRAPDOOR);
		Writes.writeModel(block, "", "_bottom", TRAPDOOR);
		Writes.writeModel(block, "", "_open", TRAPDOOR);
		Writes.writeLootTable(block);
		Writes.writeRecipe(block, plank, TRAPDOOR);
	}
	
	static void writeButtonBlock(String block, String plank)
	{
		Writes.writeBlockFilesInv(block, BUTTON);
		Writes.writeModel(block, plank, BUTTON);
		Writes.writeModel(block, plank, "_pressed", BUTTON);
		Writes.writeModel(block, plank, "_inventory", BUTTON);
		Writes.writeLootTable(block);
		Writes.writeRecipe(block, plank, BUTTON);
	}
	
	static void writePressurePlateBlock(String block, String plank)
	{
		Writes.writeBlockFiles(block, PRESSURE_PLATE);
		Writes.writeModel(block, plank, PRESSURE_PLATE);
		Writes.writeModel(block, plank, "_down", PRESSURE_PLATE);
		Writes.writeLootTable(block);
		Writes.writeRecipe(block, plank, PRESSURE_PLATE);
	}
	
	static void writeBookshelfBlock(String block, String plank)
	{
		Writes.writeBlockFiles(block, CUBE);
		Writes.writeModel(block, plank, BOOKSHELF);
		Writes.writeLootTable(block, plank, BOOKSHELF);
		Writes.writeRecipe(block, plank, BOOKSHELF);
	}
	
	static void writeVanillaBookshelfBlock(String block, String plank)
	{
		Writes.writeBlockFiles(block, CUBE);
		Writes.writeModel(block, plank, "vanilla_" + BOOKSHELF);
		Writes.writeLootTable(block, plank, BOOKSHELF);
		Writes.writeRecipe(block, plank, "vanilla_" + BOOKSHELF);
	}
	
	static void writeLeavesBlock(String block, String sapling)
	{
		Writes.writeBlockFiles(block, CUBE);
		Writes.writeModel(block, LEAVES);
		Writes.writeLootTable(block, sapling, LEAVES);
	}
	
	static void writeSaplingBlock(String block)
	{
		Writes.writeBlockFilesItemInv(block, CUBE);
		Writes.writeModel(block, SAPLING);
		Writes.writeLootTable(block);
	}
	
	static void writeMineralBlock(String block, String mineral)
	{
		writeBasicBlockWithDrops(block);
		Writes.writeRecipe(block, mineral, "mineral_block");
		Writes.writeRecipe(mineral, block, "mineral");
	}
	
	static void writeOreBlock(String block, String mineral)
	{
		writeBasicBlock(block);
		Writes.writeLootTable(block, mineral, "ore");
		Writes.writeRecipeOreCook(mineral, block);
	}
	
	static void writeCraftingTable(String block, String plank)
	{
		Writes.writeBlockFiles(block, CUBE);
		Writes.writeModel(block, plank, CRAFTING_TABLE);
		Writes.writeLootTable(block);
		Writes.writeRecipe(block, plank, CRAFTING_TABLE);
	}
	
	static void writeCraftingTableVanilla(String block, String plank)
	{
		Writes.writeBlockFiles(block, CUBE);
		Writes.writeModel(block, plank, CRAFTING_TABLE + "_vanilla");
		Writes.writeLootTable(block);
		Writes.writeRecipe(block, plank, CRAFTING_TABLE + "_vanilla");
	}
	
	static void writeFurnace(String block, String stone)
	{
		Writes.writeBlockFiles(block, FURNACE);
		Writes.writeModel(block, FURNACE);
		Writes.writeModel(block, "", "_on", FURNACE);
		Writes.writeLootTable(block, FURNACE);
		Writes.writeRecipe(block, stone, FURNACE);
	}
	
	static void writeGlowBlocks(String block)
	{
		for(String i : COLORS)
		{
			writeBasicBlockWithDrops(i + "_" + block);
			Writes.writeRecipe(i + "_" + block, i, "glowblock");
		}
	}
	
	static void writeWirelessSenders(String block)
	{
		for(String i : COLORS)
		{
			Writes.writeBlockFiles(i + "_" + block, WIRELESS_SENDER);
			Writes.writeModel(i + "_" + block, CUBE);
			Writes.writeModel(i + "_" + block + "_on", CUBE);
			Writes.writeLootTable(i + "_" + block);
		}
	}
	
	static void writeWirelessReceivers(String block)
	{
		for(String i : COLORS)
		{
			Writes.writeBlockFiles(i + "_" + block, WIRELESS_RECEIVER);
			Writes.writeModel(i + "_" + block, CUBE);
			Writes.writeModel(i + "_" + block + "_on", CUBE);
			Writes.writeLootTable(i + "_" + block);
		}
	}
	
	static void writeSlimeBlocks(String block)
	{
		for(String i : COLORS)
		{
			Writes.writeBlockFiles(i + "_" + block, CUBE);
			Writes.writeModel(i + "_" + block, SLIME_BLOCK);
			Writes.writeLootTable(i + "_" + block);
		}
	}
	
	static void writeTools(String material)
	{
		for(String i : TOOLS)
		{
			Writes.writeItemModel(material + "_" + i);
			Writes.writeRecipe(material + "_" + i, material, i);
		}
	}
	
	static void writeArmor(String material)
	{
		for(String i : ARMOR)
		{
			Writes.writeItemModel(material + "_" + i);
			Writes.writeRecipe(material + "_" + i, material, i);
		}
	}
	
	static void writeAllWood(String wood)
	{
		writePlankBlock(wood + "_planks", wood + "_logs");
		writeLogBlock(wood + "_log");
		writeWoodBlock(wood + "_wood", wood + "_log");
		writeLogBlock("stripped_" + wood + "_log");
		writeWoodBlock("stripped_" + wood + "_wood", "stripped_" + wood + "_log");
		writeSlabBlock(wood + "_slab", wood + "_planks", false);
		writeStairsBlock(wood + "_stairs", wood + "_planks", false);
		writeFenceBlock(wood + "_fence", wood + "_planks");
		writeFenceGateBlock(wood + "_fence_gate", wood + "_planks");
		writeDoorBlock(wood + "_door", wood + "_planks");
		writeTrapdoorBlock(wood + "_trapdoor", wood + "_planks");
		writeButtonBlock(wood + "_button", wood + "_planks");
		writePressurePlateBlock(wood + "_pressure_plate", wood + "_planks");
		writeBookshelfBlock(wood + "_bookshelf", wood + "_planks");
		writeLeavesBlock(wood + "_leaves", wood + "_sapling");
		writeSaplingBlock(wood + "_sapling");
	}
	
	static void writeRecipeGiveMaterial(String item, String material)
	{
		UtilWrites.writeDataTo(item + "_helmet", material, "advancements\\recipes\\combat", "has_item.txt");
		UtilWrites.writeDataTo(item + "_chestplate", material, "advancements\\recipes\\combat", "has_item.txt");
		UtilWrites.writeDataTo(item + "_leggings", material, "advancements\\recipes\\combat", "has_item.txt");
		UtilWrites.writeDataTo(item + "_boots", material, "advancements\\recipes\\combat", "has_item.txt");
		UtilWrites.writeDataTo(item + "_horse_armor", material, "advancements\\recipes\\combat", "has_item.txt");
		UtilWrites.writeDataTo(item + "_sword", material, "advancements\\recipes\\combat", "has_item.txt");
		UtilWrites.writeDataTo(item + "_hoe", material, "advancements\\recipes\\tools", "has_item.txt");
		UtilWrites.writeDataTo(item + "_axe", material, "advancements\\recipes\\tools", "has_item.txt");
		UtilWrites.writeDataTo(item + "_pickaxe", material, "advancements\\recipes\\tools", "has_item.txt");
		UtilWrites.writeDataTo(item + "_shovel", material, "advancements\\recipes\\tools", "has_item.txt");
		UtilWrites.writeDataTo(item + "_block", material, "advancements\\recipes\\building_blocks", "has_item.txt");
	}
	
	static void writeRecipeGiveMaterialVanilla(String item, String material)
	{
		UtilWrites.writeDataTo(item + "_helmet", material, "advancements\\recipes\\combat", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_chestplate", material, "advancements\\recipes\\combat", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_leggings", material, "advancements\\recipes\\combat", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_boots", material, "advancements\\recipes\\combat", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_horse_armor", material, "advancements\\recipes\\combat", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_sword", material, "advancements\\recipes\\combat", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_hoe", material, "advancements\\recipes\\tools", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_axe", material, "advancements\\recipes\\tools", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_pickaxe", material, "advancements\\recipes\\tools", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_shovel", material, "advancements\\recipes\\tools", "has_item_vanilla.txt");
	}
	
	static void writeRecipeToolsGiveMaterialVanilla(String item, String material)
	{
		UtilWrites.writeDataTo(item + "_sword", material, "advancements\\recipes\\combat", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_hoe", material, "advancements\\recipes\\tools", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_axe", material, "advancements\\recipes\\tools", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_pickaxe", material, "advancements\\recipes\\tools", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_shovel", material, "advancements\\recipes\\tools", "has_item_vanilla.txt");
	}
	
	static void writeRecipeArmorGiveMaterial(String item, String material)
	{
		UtilWrites.writeDataTo(item + "_helmet", material, "advancements\\recipes\\combat", "has_item.txt");
		UtilWrites.writeDataTo(item + "_chestplate", material, "advancements\\recipes\\combat", "has_item.txt");
		UtilWrites.writeDataTo(item + "_leggings", material, "advancements\\recipes\\combat", "has_item.txt");
		UtilWrites.writeDataTo(item + "_boots", material, "advancements\\recipes\\combat", "has_item.txt");
		//UtilWrites.writeDataTo(item + "_horse_armor", material, "advancements\\recipes\\combat", "has_item.txt");
	}
	
	static void writeRecipeArmorGiveMaterialVanilla(String item, String material)
	{
		UtilWrites.writeDataTo(item + "_helmet", material, "advancements\\recipes\\combat", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_chestplate", material, "advancements\\recipes\\combat", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_leggings", material, "advancements\\recipes\\combat", "has_item_vanilla.txt");
		UtilWrites.writeDataTo(item + "_boots", material, "advancements\\recipes\\combat", "has_item_vanilla.txt");
		//UtilWrites.writeDataTo(item + "_horse_armor", material, "advancements\\recipes\\combat", "has_item.txt");
	}
}
