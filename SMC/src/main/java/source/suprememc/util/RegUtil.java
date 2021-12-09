package source.suprememc.util;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import source.suprememc.SupremeMC;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.init.objects.SMCItems;
import source.suprememc.init.objects.SMCPotions;

public class RegUtil 
{
	
	/*
	 * registerBlock(Block block, String name, ItemGroup tab)
	 * block: The block being registered
	 * name: the name of the block
	 * tab: the tab the block will be a part of in the menu
	 * 
	 * DESCRIPTION:
	 * This function registers blocks to the mod by assigning it a registry name, adding
	 * it to the Block list registered in SupremeMC.java, and adding it to the Item
	 * list as a BlockItem, which is also registered in SupremeMC.java.
	 */
	public static void registerBlock(Block block, String name, ItemGroup tab)
	{
		// Set the registry name for the block
		block.setRegistryName(new ResourceLocation(SupremeMC.MOD_ID, name));
		
		// Add the block to the BLOCKS list in SMCBlocks.java
		SMCBlocks.BLOCKS.add(block);
		
		// Add the block to the ITEMS list in SMCItems.java as an item with a tab property
		SMCItems.ITEMS.add(new BlockItem(block, new Item.Properties().tab(tab)).setRegistryName(block.getRegistryName()));
	}
	
	/*
	 * registerBlockNoDisplay(Block block, String name)
	 * block: The block being registered
	 * name: the name of the block
	 * tab: the tab the block will be a part of in the menu
	 * 
	 * DESCRIPTION:
	 * This function serves the same purpose as registerBlock, except it does not
	 * add the block to the menu. This function is meant for technical blocks such
	 * as the hanging grape blocks (which should be able to be placed instead 
	 * using the grape item).
	 */
	public static void registerBlockNoDisplay(Block block, String name)
	{
		// Set the registry name for the block
		block.setRegistryName(new ResourceLocation(SupremeMC.MOD_ID, name));
		
		// Add the block to the BLOCKS list in SMCBlocks.java
		SMCBlocks.BLOCKS.add(block);
		
		// Add the block to the ITEMS list in SMCItems.java as an item
		SMCItems.ITEMS.add(new BlockItem(block, new Item.Properties()).setRegistryName(block.getRegistryName()));
	}
	
	/*
	 * registerItem(Item item, String name)
	 * item: The item being registered
	 * name: the name of the block
	 * 
	 * DESCRIPTION:
	 * This function registers the item to the mod by assigning it a registry name, and
	 * assigning it to the items list that is registered in SupremeMC.java.
	 */
	public static void registerItem(Item item, String name)
	{
		// Set the registry name for the item
		item.setRegistryName(new ResourceLocation(SupremeMC.MOD_ID, name));
		
		// Add the item to the ITEMS list in SMCItems.java
		SMCItems.ITEMS.add(item);
	}
	
	
	/*
	 * registerPotion(Potion potion, String name)
	 * potion: The potion being registered
	 * name: the name of the block
	 * 
	 * DESCRIPTION:
	 * This function registers the item to the potion by assigning it a registry name, and
	 * assigning it to the potions list that is registered in SupremeMC.java.
	 */
	public static void registerPotion(Potion potion, String name)
	{
		// Set the registry name for the potion
		potion.setRegistryName(new ResourceLocation(SupremeMC.MOD_ID, name));
		
		// Add the potion to the POTIONS list in SMCPotions.java
		SMCPotions.POTIONS.add(potion);
	}
}
