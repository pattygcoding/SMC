package source.suprememc.blocks.plant;

import java.util.Random;

import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlockHelper;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import source.suprememc.client.RenderBlocks;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.util.RegUtil;

public class CornStalkTopBlockSMC extends AbstractTopPlantBlock 
{
	public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);

	public CornStalkTopBlockSMC(String name) 
	{
		super(Properties.of(Material.PLANT, MaterialColor.GRASS).noCollission().instabreak().sound(SoundType.TWISTING_VINES), Direction.UP, SHAPE, false, 0.1D);
		RegUtil.registerBlockNoDisplay(this, name);
		RenderBlocks.RENDER_BLOCK_LIST_2.add(this);
	}

	@Override
	protected int getBlocksToGrowWhenBonemealed(Random rand) 
	{
		return PlantBlockHelper.getBlocksToGrowWhenBonemealed(rand);
	}

	@Override
	protected Block getBodyBlock() 
	{
		return SMCBlocks.CORN_STALK_PLANT;
	}

	@Override
	protected boolean canGrowInto(BlockState state) 
	{
		return PlantBlockHelper.isValidGrowthState(state);
	}
	
	@Override
	public BlockState getStateForPlacement(IWorld world) {
	      return this.defaultBlockState().setValue(AGE, Integer.valueOf(world.getRandom().nextInt(6)));
	   }
	
	@Override
	public boolean isRandomlyTicking(BlockState state) {
	      return state.getValue(AGE) < 6;
	   }
	
	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
	      BlockPos blockpos = pos.relative(this.growthDirection);
	      int i = Math.min(state.getValue(AGE) + 1, 6);
	      int j = this.getBlocksToGrowWhenBonemealed(rand);

	      for(int k = 0; k < j && this.canGrowInto(worldIn.getBlockState(blockpos)); ++k) {
	         worldIn.setBlockAndUpdate(blockpos, state.setValue(AGE, Integer.valueOf(i)));
	         blockpos = blockpos.relative(this.growthDirection);
	         i = Math.min(i + 1, 6);
	      }

	   }
	
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
	      if (state.getValue(AGE) < 6 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos.relative(this.growthDirection), worldIn.getBlockState(pos.relative(this.growthDirection)),random.nextDouble() < 0.1D)) {
	         BlockPos blockpos = pos.relative(this.growthDirection);
	         if (this.canGrowInto(worldIn.getBlockState(blockpos))) {
	            worldIn.setBlockAndUpdate(blockpos, state.cycle(AGE));
	            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, blockpos, worldIn.getBlockState(blockpos));
	         }
	      }

	   }
	
}