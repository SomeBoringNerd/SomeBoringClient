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
        modules.add(new ActiveModules());
        modules.add(new Suffix());
        modules.add(new Watermark());
    }
}
