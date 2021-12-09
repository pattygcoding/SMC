package source.suprememc.blocks.sub;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BreakableBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import source.suprememc.client.RenderBlocks;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.util.RegUtil;
import source.suprememc.util.SMCColors;

public class SlimeBlockSMC extends BreakableBlock
{
	public SlimeBlockSMC(SMCColors color) 
	{
		super(Properties.of(Material.CLAY, color.getColor()).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion());
		RegUtil.registerBlock(this, color.getName() + "_slime_block", SMCTabs.DECORATION_BLOCKS);
		RenderBlocks.RENDER_BLOCK_LIST_3.add(this);
	}
	
	@Override
	public boolean isSlimeBlock(BlockState state) 
	{
		return true;
	}
	
	@Override
	public boolean isStickyBlock(BlockState state) 
	{
		return true;
	}
	
	@Override
	public boolean canStickTo(BlockState state, BlockState other) 
	{
		
		if (state.getBlock() == this)
		{
			ImmutableList<Block> slimeBlocks = ImmutableList.of
			(
				SMCBlocks.RED_SLIME_BLOCK, SMCBlocks.ORANGE_SLIME_BLOCK, SMCBlocks.YELLOW_SLIME_BLOCK, SMCBlocks.LIME_SLIME_BLOCK,
				SMCBlocks.GREEN_SLIME_BLOCK, SMCBlocks.LIGHT_BLUE_SLIME_BLOCK, SMCBlocks.CYAN_SLIME_BLOCK, SMCBlocks.BLUE_SLIME_BLOCK,
				SMCBlocks.PURPLE_SLIME_BLOCK, SMCBlocks.MAGENTA_SLIME_BLOCK, SMCBlocks.PINK_SLIME_BLOCK, SMCBlocks.WHITE_SLIME_BLOCK,
				SMCBlocks.LIGHT_GRAY_SLIME_BLOCK, SMCBlocks.GRAY_SLIME_BLOCK, SMCBlocks.BLACK_SLIME_BLOCK, SMCBlocks.BROWN_SLIME_BLOCK,
				Blocks.SLIME_BLOCK, Blocks.HONEY_BLOCK
			);
			for(Block block : slimeBlocks)
			{
				if(block != this && block == other.getBlock()) return false;
			}
		}
        return state.isStickyBlock() || other.isStickyBlock();
	}

	@Override
	public void fallOn(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) 
	{
		if(entityIn.isSuppressingBounce()) super.fallOn(worldIn, pos, entityIn, fallDistance);
		else entityIn.causeFallDamage(fallDistance, 0.0F);
	}

	@Override
	public void updateEntityAfterFallOn(IBlockReader worldIn, Entity entityIn) 
	{
		if(entityIn.isSuppressingBounce()) super.updateEntityAfterFallOn(worldIn, entityIn);
		else this.bounceEntity(entityIn);
	}

	private void bounceEntity(Entity entity) 
	{
		Vector3d vector3d = entity.getDeltaMovement();
		if(vector3d.y < 0.0D) 
		{
			double d0 = entity instanceof LivingEntity ? 1.0D : 0.8D;
			entity.setDeltaMovement(vector3d.x, -vector3d.y * d0, vector3d.z);
		}
	}

	@Override
	public void stepOn(World worldIn, BlockPos pos, Entity entityIn) 
	{
		double d0 = Math.abs(entityIn.getDeltaMovement().y);
		if(d0 < 0.1D && !entityIn.isSteppingCarefully()) 
		{
			double d1 = 0.4D + d0 * 0.2D;
			entityIn.setDeltaMovement(entityIn.getDeltaMovement().multiply(d1, 1.0D, d1));
		}
		super.stepOn(worldIn, pos, entityIn);
	}
}