package source.suprememc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import source.suprememc.biomes.util.BiomeInfoSMC;
import source.suprememc.biomes.util.BiomeRegistrySMC;
import source.suprememc.biomes.util.RegisterBiomes;
import source.suprememc.client.RenderBlocks;
import source.suprememc.client.RenderItems;
import source.suprememc.client.RenderTileEntities;
import source.suprememc.init.generation.SMCBiomes;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.init.objects.SMCEnchantments;
import source.suprememc.init.objects.SMCItems;
import source.suprememc.init.objects.SMCPotions;
import source.suprememc.proxy.ClientProxy;
import source.suprememc.proxy.CommonProxy;
import source.suprememc.world.generation.ores.SMCOreGeneration;

// The value here should match an entry in the META-INF/mods.toml file
@SuppressWarnings("deprecation")
@Mod("suprememc")
public class SupremeMC
{
    // Public string for the Mod ID.
	public static final String MOD_ID = "suprememc";
	
	// Logger instance for the Mod ID
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    
    // Proxy declaration (this is needed for registering the screens with each container for blocks such as the freezer).
	public static final CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    
    public SupremeMC() 
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
        
        // Register the biomes for the mod
        RegisterBiomes.setup();
        
        // Register the ore generation for the mod
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, SMCOreGeneration::new);
    }

    
    private void setup(final FMLCommonSetupEvent event) {}
    
    private void doClientStuff(final FMLClientSetupEvent event) 
    {
    	// Setup rendering animations for the bow, crossbow, etc.
    	RenderItems.setup();
    	// Setup rendering for signs, which need to register as tile entities
    	RenderTileEntities.setup();
    	// Called from CommonProxy.java (may be used later)
    	PROXY.onSetupClient();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {}

    private void processIMC(final InterModProcessEvent event)
    {
    	// Called from CommonProxy.java (may be used later)
    	PROXY.onSetupCommon();
    }
    
    // Sends message from logger when server starts
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) 
    {
        LOGGER.info("HELLO from server starting");
    }

    
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents 
    {
    	// SubscribeEvent function that registers blocks
    	@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event)
		{
    		// Registers the blocks from SMCBlocks.java
			event.getRegistry().registerAll(SMCBlocks.BLOCKS.toArray(new Block[0]));
			
			// Call to render non-opaque blocks such as glass, leaves, and saplings
			RenderBlocks.setup();
			
			// Send message that blocks registered
			LOGGER.info("Blocks registered");
		}
    	
    	@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
    		// Register the items from SMCItems.java
			event.getRegistry().registerAll(SMCItems.ITEMS.toArray(new Item[0]));
			
			// Send message that items registered
			LOGGER.info("Items registered");
		}
    	
    	@SubscribeEvent
		public static void registerEnchantments(final RegistryEvent.Register<Enchantment> event)
		{
    		// Register the enchantments from SMCEnchantments.java
			event.getRegistry().registerAll(SMCEnchantments.ENCHANTMENTS.toArray(new Enchantment[0]));
			
			// Send message that enchantments registered
			LOGGER.info("Enchantments registered");
		}
    	
    	@SubscribeEvent
		public static void registerPotions(final RegistryEvent.Register<Potion> event)
		{
    		// Register the potions from SMCPotions.java
			event.getRegistry().registerAll(SMCPotions.POTIONS.toArray(new Potion[0]));
			
			// Send message that potions registered
			LOGGER.info("Potions registered");
		}
    	
        @SubscribeEvent
        public static void registerBiomes(RegistryEvent.Register<Biome> event)
        {
    		for(BiomeInfoSMC biomeInfo : SMCBiomes.BIOMES)
    		{
    			BiomeRegistrySMC.registerSMCBiomes(biomeInfo.getBiome(), biomeInfo.getName());
    		}

    		BiomeRegistrySMC.configureBiomes();
            BiomeRegistrySMC.finalizeRegistrations(BiomeRegistrySMC.RegistrationType.STANDARD_BIOME);

            SMCBiomes.registerBiomeDictionaryTags();
            //registerVillagerTypes();
        }
    }
   
}
