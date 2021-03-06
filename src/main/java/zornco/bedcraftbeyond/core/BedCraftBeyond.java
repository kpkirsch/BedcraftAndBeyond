package zornco.bedcraftbeyond.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zornco.bedcraftbeyond.frames.TabBeds;
import zornco.bedcraftbeyond.core.network.Registration;
import zornco.bedcraftbeyond.core.proxy.CommonProxy;
import zornco.bedcraftbeyond.core.commands.CommandBedcraft;
import zornco.bedcraftbeyond.core.gui.GuiHandler;
import zornco.bedcraftbeyond.core.util.ColorHelper;
import zornco.bedcraftbeyond.core.config.ConfigHelper;
import zornco.bedcraftbeyond.frames.registry.FrameLoader;
import zornco.bedcraftbeyond.storage.handling.CapabilityStorageHandler;


@Mod(
    modid = BedCraftBeyond.MOD_ID,
    name = BedCraftBeyond.MOD_NAME,
    version = BedCraftBeyond.MOD_VERSION,
    acceptedMinecraftVersions = "[1.9.4,],[1.10,)",
    guiFactory = "zornco.bedcraftbeyond.core.config.ConfigGuiFactory")
public class BedCraftBeyond {

    public static final String MOD_ID = "bedcraftbeyond";
    public static final String MOD_VERSION = "@VERSION@";
    public static final String MOD_NAME = "BedCraft And Beyond";

    // The instance of your mod that Forge uses.
    @Instance(BedCraftBeyond.MOD_ID)
    public static BedCraftBeyond INSTANCE;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide = "zornco.bedcraftbeyond.core.proxy.ClientProxy", serverSide = "zornco.bedcraftbeyond.core.proxy.ServerProxy")
    public static CommonProxy PROXY;

    public static CreativeTabs MAIN_TAB;
    public static CreativeTabs BEDS_TAB;

    public static Logger LOGGER = LogManager.getLogger(BedCraftBeyond.MOD_NAME);

    public static SimpleNetworkWrapper NETWORK;

    public static Configuration CONFIG;

    @EventHandler
    @SuppressWarnings("unused")
    public void preInit(FMLPreInitializationEvent event) {

        // Do not set up config here, it's setup in ConfigHelper
        ConfigHelper.allModConfigsDir = event.getModConfigurationDirectory();
        ConfigHelper.setup();

        MAIN_TAB = new TabMain();
        BEDS_TAB = new TabBeds();

        ColorHelper.initColorList();

        PROXY.registerModels();
        NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);

        Registration.registerMessages();

        MinecraftForge.EVENT_BUS.register(INSTANCE);

        GuiHandler.INSTANCE = new GuiHandler();

        CapabilityStorageHandler.register();
    }

    @SuppressWarnings({"unchecked", "unused"})
    @EventHandler
    public void init(FMLInitializationEvent event) {

        PROXY.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, GuiHandler.INSTANCE);

        long start = System.currentTimeMillis();

        /** Recipes **/
        OreDictionary.registerOre("bed", new ItemStack(ModContent.Items.woodenBed, 1, OreDictionary.WILDCARD_VALUE));
        Recipes.addRecipes();

        long elapsedTimeMillis = System.currentTimeMillis() - start;
        BedCraftBeyond.LOGGER.info("Generated recipes in " + elapsedTimeMillis + " milliseconds.");
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void postInit(FMLPostInitializationEvent event) {
        // PlankHelper.readyToColor = true;
        long start = System.currentTimeMillis();
        FrameLoader.compileFrames();
        BedCraftBeyond.LOGGER.info("Compiled frame whitelists in " + (System.currentTimeMillis() - start) + " milliseconds.");
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.getModID().equals(BedCraftBeyond.MOD_ID))
            ConfigHelper.refreshConfigs();
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void serverStarted(FMLServerStartingEvent ev) {
        ev.registerServerCommand(new CommandBedcraft());
    }
}