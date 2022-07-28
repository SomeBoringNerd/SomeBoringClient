package xyz.someboringnerd.someboringclient;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.someboringnerd.someboringclient.Modules.ModuleManager;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;
import xyz.someboringnerd.someboringclient.commands.CommandManager;

import java.io.File;

@Mod(
        modid = Someboringclient.MOD_ID,
        name = Someboringclient.MOD_NAME,
        version = Someboringclient.VERSION
)
public class Someboringclient {

    public static final String MOD_ID = "someboringclient";
    public static final String MOD_NAME = "Someboringclient";
    public static final String VERSION = "a-1";

    public static ModuleManager manager;

    /** This is the instance of your mod as created by Forge. It will never be null. */
    @Mod.Instance(MOD_ID)
    public static Someboringclient INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {

        File file = new File("SomeBoringClient");
        File file1 = new File("SomeBoringClient/Modules");

        if(!file.exists()){
            file.mkdir();
        }
        if(!file1.exists()){
            file1.mkdir();
        }

        manager = new ModuleManager();

        new ChatUtil();
        new CommandManager();
    }
}
