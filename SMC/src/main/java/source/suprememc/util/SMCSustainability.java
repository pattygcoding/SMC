package source.suprememc.util;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import source.suprememc.init.objects.SMCBlocks;

public class SMCSustainability 
{
	/**
	 * All the lists for the types of crops each block could support.
	 * Note that plains is public due to it's use in the aiStep() function
	 * in MoobloomEntitySMC.java. 
	 */
	private static final List<Block> DESERT = new ArrayList<Block>();
	private static final List<Block> NETHER = new ArrayList<Block>();
	private static final List<Block> CROP= new ArrayList<Block>();
	private static final List<Block> CAVE = new ArrayList<Block>();
	public static final List<Block> PLAINS= new ArrayList<Block>();
	private static final List<Block> WATER = new ArrayList<Block>();
	private static final List<Block> BEACH = new ArrayList<Block>();
	private static final List<Block> BEACH_WATER = new ArrayList<Block>();
	
	// Static block where blocks can be added as a sustainable plant support.
	static
	{
		CROP.add(SMCBlocks.ARABLE_SAND);
		PLAINS.add(Blocks.GRASS_BLOCK);
		PLAINS.add(Blocks.DIRT);
		PLAINS.add(SMCBlocks.ARABLE_SAND);
		WATER.add(SMCBlocks.ARABLE_SAND);
		BEACH.add(SMCBlocks.ARABLE_SAND);
	}
	
	// This is the main function that should be called in block classes, it sends the boolean value for whether the block is sustainable or not.
	public static boolean canSustainPlant(Block block, IBlockReader world, BlockPos pos, net.minecraft.util.Direction facing, IPlantable plantable) 
	{
		PlantType type = plantable.getPlantType(world, pos.relative(facing));
		if(type == PlantType.DESERT) return isPlant(DESERT, block);
		if(type == PlantType.NETHER) return isPlant(NETHER, block);
		if(type == PlantType.CROP) return isPlant(CROP, block);
		if(type == PlantType.CAVE) return isPlant(CAVE, block);
		if(type == PlantType.PLAINS) return isPlant(PLAINS, block);
		if(type == PlantType.WATER) return isPlant(WATER, block);
		if(type == PlantType.BEACH)
		{
			if(isPlant(BEACH, block) == true) return true;
			else return isBeachWaterPlant(block, world, pos);
		}
		return false;
	}
	
	// Checks to see if the block is inside the plant list.
	public static boolean isPlant(List<Block> list, Block block)
	{
		// Beach water plants are excluded because they are called in the else statement in canSustainPlant
		if(list.isEmpty() || list == BEACH_WATER) return false;
		for(Block b : list) {if(b == block) return true;}
		return false;
	}
	
	// This function checks to see if water is touching the block (based on the same restrictions sugar cane has)
	private static boolean isBeachWaterPlant(Block block, IBlockReader world, BlockPos pos)
	{
		if(BEACH_WATER.isEmpty()) return false;
		boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
				world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
				world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
				world.getBlockState(pos.south()).getMaterial() == Material.WATER);
		if(!hasWater) return false;
		for(Block b : BEACH_WATER) {if(b == block) return true;}
		return false;
	}
	
}
