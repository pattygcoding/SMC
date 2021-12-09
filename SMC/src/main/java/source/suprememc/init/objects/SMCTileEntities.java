package source.suprememc.init.objects;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import source.suprememc.SupremeMC;
import source.suprememc.blocks.sub.SignBlockSMC;
import source.suprememc.tileentity.brewingstand.BrewingStandTileEntitySMC;
import source.suprememc.tileentity.freezer.FreezerTileEntitySMC;
import source.suprememc.tileentity.furnace.FurnaceTileEntitySMC;
import source.suprememc.tileentity.sign.SignTileEntitySMC;
import source.suprememc.util.Dict;

@Mod.EventBusSubscriber(modid = SupremeMC.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMCTileEntities  
{
	@SuppressWarnings("rawtypes")
	private static final List<TileEntityType> TILE_ENTITY_TYPES = new ArrayList<>();

	public static final TileEntityType<FreezerTileEntitySMC> FREEZER = buildType(Dict.FREEZER_ID, TileEntityType.Builder.of
	(
		FreezerTileEntitySMC::new, 
		SMCBlocks.FREEZER
	));
	
	public static final TileEntityType<BrewingStandTileEntitySMC> BREWING_STAND = buildType(Dict.BREWING_STAND_ID, TileEntityType.Builder.of
	(
		BrewingStandTileEntitySMC::new, 
		SMCBlocks.SOUL_BREWING_STAND
	));
	
	public static final TileEntityType<FurnaceTileEntitySMC> FURNACES = buildType("suprememc:furnaces", TileEntityType.Builder.of
	(
		FurnaceTileEntitySMC::new, 
		SMCBlocks.ANDESITE_FURNACE,
		SMCBlocks.DIORITE_FURNACE,
		SMCBlocks.GRANITE_FURNACE,
		SMCBlocks.BLACKSTONE_FURNACE,
		SMCBlocks.DEEPSLATE_FURNACE
	));
	
	public static final TileEntityType<SignTileEntitySMC> SIGNS = buildType("suprememc:smc_signs", TileEntityType.Builder.of
	(
		SignTileEntitySMC::new, 
		SMCBlocks.PALM_SIGN, getWallBlock(SMCBlocks.PALM_SIGN), 
		SMCBlocks.MUSTARD_SIGN, getWallBlock(SMCBlocks.MUSTARD_SIGN), 
		SMCBlocks.LAVENDER_SIGN, getWallBlock(SMCBlocks.LAVENDER_SIGN)
	));
	
	private static <T extends TileEntity> TileEntityType<T> buildType(String id, TileEntityType.Builder<T> builder)
    {
        TileEntityType<T> type = builder.build(null); //TODO may not allow null
        type.setRegistryName(id);
        TILE_ENTITY_TYPES.add(type);
        return type;
    }

    @SubscribeEvent
    public static void registerTypes(final RegistryEvent.Register<TileEntityType<?>> event)
    {
        TILE_ENTITY_TYPES.forEach(type -> event.getRegistry().register(type));
        TILE_ENTITY_TYPES.clear();
    }
    
    private static Block getWallBlock(Block sign) 
    {
    	if(sign instanceof SignBlockSMC) return ((SignBlockSMC)(sign)).getWallBlock();
    	else
    	{
    		System.out.println("ERROR: Block is not instance of sign {SMCTileEntities}");
    		return null;
    	}
	}
}