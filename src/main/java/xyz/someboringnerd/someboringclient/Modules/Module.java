package xyz.someboringnerd.someboringclient.Modules;

import net.minecraftforge.common.MinecraftForge;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;

import java.util.HashMap;
import java.util.Map;

public class Module
{

    public Map<String, Object> settings = new HashMap<>();
    public String name = "DEFAULT_NAME";
    boolean enabled = false;

    public Module()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void Toggle()
    {
        enabled = !enabled;
    }


}

