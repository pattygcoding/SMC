package source.suprememc.blocks.interactive;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import source.suprememc.container.crafting.WorkbenchContainerSMC;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class CraftingTableBlockSMC extends CraftingTableBlock 
{
	private static final ITextComponent CONTAINER_TITLE = new TranslationTextComponent("container.crafting");

	private String name;
	public CraftingTableBlockSMC(String name) 
	{
		super(Properties.copy(Blocks.CRAFTING_TABLE));
		this.name = name;
		RegUtil.registerBlock(this, this.name, SMCTabs.DECORATION_BLOCKS);
	}

	@Override
	public INamedContainerProvider getMenuProvider(BlockState blockState, World world, BlockPos blockPos) 
	{
		return new SimpleNamedContainerProvider((num, inventory, playerEntity) -> 
		{
			return new WorkbenchContainerSMC(num, inventory, IWorldPosCallable.create(world, blockPos));
		}, CONTAINER_TITLE);
	}
}
