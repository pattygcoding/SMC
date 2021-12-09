package source.suprememc.blocks.interactive;

import net.minecraft.block.Blocks;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.tileentity.furnace.FurnaceTileEntitySMC;
import source.suprememc.util.RegUtil;

public class FurnaceBlockSMC extends FurnaceBlock
{
	private String name;
	public FurnaceBlockSMC(String name) 
	{
		super(Properties.copy(Blocks.FURNACE));
		this.name = name;
		RegUtil.registerBlock(this, this.name, SMCTabs.DECORATION_BLOCKS);
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader iBlockReader) 
	{
		return new FurnaceTileEntitySMC();
	}

	@Override
	protected void openContainer(World world, BlockPos blockPos, PlayerEntity entity) 
	{
		TileEntity tileentity = world.getBlockEntity(blockPos);
		if (tileentity instanceof FurnaceTileEntitySMC) {
			entity.openMenu((INamedContainerProvider)tileentity);
			entity.awardStat(Stats.INTERACT_WITH_FURNACE);
		}

	}
}
