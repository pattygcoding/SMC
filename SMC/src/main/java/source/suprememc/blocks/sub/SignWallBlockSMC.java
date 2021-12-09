package source.suprememc.blocks.sub;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockReader;
import source.suprememc.SupremeMC;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.tileentity.sign.SignTileEntitySMC;

public class SignWallBlockSMC extends WallSignBlock
{
	public SignWallBlockSMC(WoodType wood, String name) 
	{
		super(Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), wood);
		this.setRegistryName(new ResourceLocation(SupremeMC.MOD_ID, name));
		SMCBlocks.BLOCKS.add(this);
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
	
}