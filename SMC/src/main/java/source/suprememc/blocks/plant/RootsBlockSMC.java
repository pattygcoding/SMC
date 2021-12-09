package source.suprememc.blocks.plant;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import source.suprememc.client.RenderBlocks;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class RootsBlockSMC extends BushBlock implements IItemProvider
{
	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

	public RootsBlockSMC(String name) 
	{
		super(Properties.of(Material.REPLACEABLE_FIREPROOF_PLANT, MaterialColor.COLOR_YELLOW).noCollission().instabreak().sound(SoundType.ROOTS));
		RegUtil.registerBlock(this, name, SMCTabs.DECORATION_BLOCKS);
		RenderBlocks.RENDER_BLOCK_LIST_2.add(this);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return SHAPE;
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) 
	{
		return state.is(BlockTags.NYLIUM) || state.is(Blocks.SOUL_SOIL) || super.mayPlaceOn(state, worldIn, pos);
	}

	@Override
	public AbstractBlock.OffsetType getOffsetType() 
	{
		return AbstractBlock.OffsetType.XZ;
	}
}
