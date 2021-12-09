package source.suprememc.blocks.plant;

import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusConfig;
import net.minecraft.world.server.ServerWorld;
import source.suprememc.client.RenderBlocks;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.util.RegUtil;

public class FungusBlockSMC extends BushBlock implements IItemProvider, IGrowable 
{
	protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
	private final Supplier<ConfiguredFeature<HugeFungusConfig, ?>> fungusFeature;

	public FungusBlockSMC(String name, Supplier<ConfiguredFeature<HugeFungusConfig, ?>> fungusFeature) 
	{
		super(Properties.of(Material.REPLACEABLE_FIREPROOF_PLANT, MaterialColor.COLOR_YELLOW).instabreak().noCollission().sound(SoundType.FUNGUS));
		this.fungusFeature = fungusFeature;
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
		return state.is(SMCBlocks.MUSTARD_NYLIUM) || state.is(BlockTags.NYLIUM) || state.is(Blocks.MYCELIUM) || state.is(Blocks.SOUL_SOIL) || super.mayPlaceOn(state, worldIn, pos);
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) 
	{
		Block block = ((HugeFungusConfig)(this.fungusFeature.get()).config).validBaseState.getBlock();
		Block block1 = worldIn.getBlockState(pos.below()).getBlock();
		return block1 == block;
	}

	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) 
	{
		return (double)rand.nextFloat() < 0.4D;
	}

	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) 
	{
		this.fungusFeature.get().place(worldIn, worldIn.getChunkSource().getGenerator(), rand, pos);
	}
}