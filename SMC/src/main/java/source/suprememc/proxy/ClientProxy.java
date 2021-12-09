package source.suprememc.proxy;

import net.minecraft.client.gui.ScreenManager;
import source.suprememc.container.brewing.gui.screen.BrewingStandScreenSMC;
import source.suprememc.container.crafting.gui.screen.CraftingScreenSMC;
import source.suprememc.container.freezer.gui.screen.FreezerScreenSMC;
import source.suprememc.container.furnace.gui.screen.FurnaceScreenSMC;
import source.suprememc.container.phonograph.gui.screen.PhonographScreenSMC;
import source.suprememc.init.objects.SMCContainers;

public class ClientProxy extends CommonProxy
{
	@Override
    public void onSetupClient()
    {
		ScreenManager.register(SMCContainers.CRAFTING, CraftingScreenSMC::new);
		ScreenManager.register(SMCContainers.FURNACE, FurnaceScreenSMC::new);
		ScreenManager.register(SMCContainers.SUPREME_BREWING_STAND, BrewingStandScreenSMC::new);
		ScreenManager.register(SMCContainers.FREEZER, FreezerScreenSMC::new);
		ScreenManager.register(SMCContainers.PHONOGRAPH, PhonographScreenSMC::new);
    }
}
