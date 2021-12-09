package source.suprememc.blocks.sub;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.util.RegUtil;

public class UnstrippedLogBlockSMC extends RotatedPillarBlock
{
	protected static final Map<Block, Block> STRIPABLES = new HashMap<Block, Block>();
			
	
	public UnstrippedLogBlockSMC(String name, Properties properties) 
	{
		super(properties);
		RegUtil.registerBlock(this, name, SMCTabs.BUILDING_BLOCKS);
	}
	
	@Override
	public ActionResultType use(BlockState state, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult hit) 
	{
		Item itemInHand = playerEntity.getItemInHand(hand).getItem();
		STRIPABLES.put(SMCBlocks.CHERRY_WOOD, SMCBlocks.STRIPPED_CHERRY_WOOD);
		STRIPABLES.put(SMCBlocks.CHERRY_LOG, SMCBlocks.STRIPPED_CHERRY_LOG);
		STRIPABLES.put(SMCBlocks.PALM_WOOD, SMCBlocks.STRIPPED_PALM_WOOD);
		STRIPABLES.put(SMCBlocks.PALM_LOG, SMCBlocks.STRIPPED_PALM_LOG);
		STRIPABLES.put(SMCBlocks.MUSTARD_STEM, SMCBlocks.STRIPPED_MUSTARD_STEM);
		STRIPABLES.put(SMCBlocks.MUSTARD_HYPHAE, SMCBlocks.STRIPPED_MUSTARD_HYPHAE);
		STRIPABLES.put(SMCBlocks.LAVENDER_STEM, SMCBlocks.STRIPPED_LAVENDER_STEM);
		STRIPABLES.put(SMCBlocks.LAVENDER_HYPHAE, SMCBlocks.STRIPPED_LAVENDER_HYPHAE);
		Block block = STRIPABLES.get(state.getBlock());
		if(block != null && itemInHand instanceof AxeItem) 
		{
			world.setBlockAndUpdate(blockPos, block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)));
			world.playSound(playerEntity, blockPos, SoundEvents.AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
			return ActionResultType.PASS;
		}
		return ActionResultType.FAIL;
	}
	
}
