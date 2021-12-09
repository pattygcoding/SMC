package source.suprememc.blocks.plant;

import net.minecraft.block.AbstractBodyPlantBlock;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import source.suprememc.client.RenderBlocks;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.util.RegUtil;

public class GrapeVineBlockSMC extends AbstractBodyPlantBlock 
{
	public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public GrapeVineBlockSMC(String name) 
	{
		super(Properties.of(Material.PLANT, MaterialColor.GRASS).noCollission().instabreak().sound(SoundType.WEEPING_VINES), Direction.DOWN, SHAPE, false);
		RegUtil.registerBlockNoDisplay(this, name);
		RenderBlocks.RENDER_BLOCK_LIST_2.add(this);
	}

	@Override
	protected AbstractTopPlantBlock getHeadBlock() 
	{
	      return (AbstractTopPlantBlock)SMCBlocks.GRAPE_VINE;
	}
}