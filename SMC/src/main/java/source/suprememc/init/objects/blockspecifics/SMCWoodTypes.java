package source.suprememc.init.objects.blockspecifics;

import net.minecraft.block.WoodType;
import source.suprememc.SupremeMC;

/**
 * @author phgoodw
 *	This class is basically just a list of WoodTypes. WoodType is a class used for
 *	sign tile entities, thus it is necessary to register each of the wood types 
 *	in the mod in order to extend the SignTileEntity class.
 */
public class SMCWoodTypes
{
	public static final WoodType CHERRY = WoodType.create(SupremeMC.MOD_ID + ":cherry");
	public static final WoodType PALM = WoodType.create(SupremeMC.MOD_ID + ":palm");
	public static final WoodType LAVENDER = WoodType.create(SupremeMC.MOD_ID + ":lavender");
	public static final WoodType MUSTARD = WoodType.create(SupremeMC.MOD_ID + ":mustard");
	
	static
	{
		WoodType.register(CHERRY);
		WoodType.register(PALM);
		WoodType.register(LAVENDER);
		WoodType.register(MUSTARD);
	}
}
