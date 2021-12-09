package source.suprememc.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class OreBlockSMC extends BlockSMC
{
	private int minXP, maxXP;
	public OreBlockSMC(String name, int minXP, int maxXP, Properties properties) 
	{
		// Pass to the BlockSMC.java constructor
		super(name, properties);
		
		// Assign the XP values to the private variables
		this.minXP = minXP;
		this.maxXP = maxXP;
	}
	
	/**
	 * This override is for how much XP the block drops
	 */
	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) 
	{
		// XP doesn't drop if the tool has the silk touch enchantment
		if(silktouch == 0) return ((int) (Math.random()*(this.maxXP - this.minXP))) + this.minXP;
		else return 0;
	}
	
	
}