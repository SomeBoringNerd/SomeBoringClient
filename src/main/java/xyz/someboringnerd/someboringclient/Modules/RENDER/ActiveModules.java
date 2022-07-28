package xyz.someboringnerd.someboringclient.Modules.RENDER;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.someboringnerd.someboringclient.Modules.Module;
import xyz.someboringnerd.someboringclient.Someboringclient;

public class ActiveModules extends Module
{

    public ActiveModules()
    {
        name = "ModuleList";
    }

    @SubscribeEvent
    public void OnRender(RenderGameOverlayEvent.Post e)
    {
        if(isEnabled())
        {
            int Y = 0;
            for(Module mod : Someboringclient.manager.modules)
            {
                if(mod.isEnabled()) {
                    Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("> " + mod.name, 2, 15 + Y, 0xFFFFFF);
                    Y += 10;
                }
            }
        }
    }
}
