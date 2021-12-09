package source.suprememc.blocks.plant;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import source.suprememc.client.RenderBlocks;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class LeavesBlockSMC extends LeavesBlock
{
	public LeavesBlockSMC(String name, MaterialColor color) 
	{
		super(Properties.of(Material.LEAVES, color).strength(0.2F).randomTicks().sound(SoundType.CROP).noCollission().isValidSpawn(LeavesBlockSMC::allowsSpawnOnLeaves).isSuffocating(LeavesBlockSMC::isntSolid).isViewBlocking(LeavesBlockSMC::isntSolid));
		RegUtil.registerBlock(this, name, SMCTabs.DECORATION_BLOCKS);
		RenderBlocks.RENDER_BLOCK_LIST_1.add(this);
		
	}
	
	private static Boolean allowsSpawnOnLeaves(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) 
	{
		return entity == EntityType.OCELOT || entity == EntityType.PARROT;
	}
	
	private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) 
	{
		return false;
	}
}