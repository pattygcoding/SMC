package source.suprememc.tileentity.sign;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;
import source.suprememc.init.objects.SMCTileEntities;

public class SignTileEntitySMC extends SignTileEntity 
{
    @Override
    public TileEntityType<?> getType() 
    {
        return SMCTileEntities.SIGNS;
    }
}