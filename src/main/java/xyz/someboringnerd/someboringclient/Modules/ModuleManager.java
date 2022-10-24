package xyz.someboringnerd.someboringclient.Modules;

import xyz.someboringnerd.someboringclient.Modules.CHAT.ChatBot;
import xyz.someboringnerd.someboringclient.Modules.CHAT.Suffix;
import xyz.someboringnerd.someboringclient.Modules.COMBAT.KillAura;
import xyz.someboringnerd.someboringclient.Modules.EXPLOITS.AutoFrameDupe;
import xyz.someboringnerd.someboringclient.Modules.RENDER.*;
import xyz.someboringnerd.someboringclient.Modules.WORLD.WaterFiller;

import java.io.File;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleManager
{

    public CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();

    public ModuleManager()
    {

        for (CATEGORY cat: CATEGORY.values()) {
            File file = new File("SomeBoringClient/Modules/" + cat.name());

            if(!file.exists()){
                file.mkdir();
            }
        }

        // combat
        modules.add(new KillAura("KillAura", CATEGORY.COMBAT));

        // chat
        modules.add(new ChatBot("ChatBot", CATEGORY.CHAT));
        modules.add(new Suffix("Suffix", CATEGORY.CHAT));

        // exploits
        modules.add(new AutoFrameDupe("AutoFrameDupe", CATEGORY.EXPLOIT));

        // render
        modules.add(new ActiveModules("List", CATEGORY.RENDER));
        modules.add(new Coordinates("Coordinate", CATEGORY.RENDER));
        modules.add(new PlayerNearby("PlayerList", CATEGORY.RENDER));
        modules.add(new StorageESP("StorageESP", CATEGORY.RENDER));
        modules.add(new StorageRadar("StorageRadar", CATEGORY.RENDER));
        modules.add(new Watermark("Watermark", CATEGORY.RENDER));

        // world
        modules.add(new WaterFiller("WaterFiller", CATEGORY.WORLD));
    }
}
