package source.suprememc.blocks.sub;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockReader;
import source.suprememc.SupremeMC;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.init.objects.SMCItems;
import source.suprememc.tileentity.sign.SignTileEntitySMC;

public class SignBlockSMC extends StandingSignBlock
{
	private WoodType wood;
	private String name;
	private Block wallBlock;
	public SignBlockSMC(WoodType wood, String name, Block wallBlock) 
	{
		super(Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), wood);
		this.wallBlock = wallBlock;
		this.setRegistryName(new ResourceLocation(SupremeMC.MOD_ID, name));
		SMCBlocks.BLOCKS.add(this);
		SMCItems.ITEMS.add(new SignItem(new Item.Properties().stacksTo(16).tab(SMCTabs.DECORATION_BLOCKS), this, this.wallBlock).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return new SignTileEntitySMC();
	}

    @Override
    public boolean hasTileEntity(BlockState state) 
    {
        return true;
    }
    
    public WoodType getWood() 
    {
		return this.wood;
	}
    
    public String getBlockName() 
    {
		return this.name;
	}
    
    public Block getWallBlock() 
    {
		return this.wallBlock;
	}
}
