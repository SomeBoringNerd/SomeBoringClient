package xyz.someboringnerd.someboringclient.Modules;

import xyz.someboringnerd.someboringclient.Modules.CHAT.Suffix;
import xyz.someboringnerd.someboringclient.Modules.RENDER.ActiveModules;
import xyz.someboringnerd.someboringclient.Modules.RENDER.Watermark;

import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleManager
{

    public CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();

    public ModuleManager()
    {
        // chat
        modules.add(new Suffix("Suffix", CATEGORY.CHAT));

        // render
        modules.add(new ActiveModules("List", CATEGORY.RENDER));
        modules.add(new Watermark("Watermark", CATEGORY.RENDER));
    }
}
