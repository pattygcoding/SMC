package source.suprememc.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class BookshelfBlockSMC extends BlockSMC
{
	public BookshelfBlockSMC(String name, Properties properties) 
	{
		// Pass to the BlockSMC.java constructor
		super(name, properties);
	}
	
	/**
	 * This function is an override of the getEnchantPowerBonus function in IForgeBlock.class. 
	 * When called, it returns the value for which the block "boosts" the ability for the
	 * enchantment table to work at higher levels. 
	 * 
	 * Since this is a feature of bookshelves in vanilla Minecraft, we need to declare this
	 * function, which is the reason why there is a class for bookshelf blocks that extends
	 * the BlockSMC class.
	 */
	@Override
	public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) 
	{
		return 1;
	}
	
	
}
