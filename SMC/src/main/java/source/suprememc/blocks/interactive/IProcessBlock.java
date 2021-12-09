package source.suprememc.blocks.interactive;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IProcessBlock 
{
	public static void animate(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		if (stateIn.getValue(BlockStateProperties.LIT)) 
		{
			double d0 = (double)pos.getX() + 0.5D;
			double d1 = (double)pos.getY();
			double d2 = (double)pos.getZ() + 0.5D;
			if (rand.nextDouble() < 0.1D) 
			{
				worldIn.playLocalSound(d0, d1, d2, SoundEvents.SNOW_FALL, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			}
			Direction direction = stateIn.getValue(HorizontalBlock.FACING);
			Direction.Axis direction$axis = direction.getAxis();
			//double d3 = 0.52D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;
			double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : d4;
			double d6 = rand.nextDouble() * 6.0D / 16.0D;
			double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : d4;
			worldIn.addParticle(ParticleTypes.ITEM_SNOWBALL, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
			worldIn.addParticle(ParticleTypes.SOUL_FIRE_FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
		}
	}
}
