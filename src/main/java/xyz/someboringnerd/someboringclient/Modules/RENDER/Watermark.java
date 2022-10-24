package xyz.someboringnerd.someboringclient.Modules.RENDER;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.someboringnerd.someboringclient.Modules.CATEGORY;
import xyz.someboringnerd.someboringclient.Modules.Module;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;

import java.awt.*;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Watermark extends Module
{
    public Watermark(String name, CATEGORY cat)
    {
        super(name, cat);
        settings.put("Mode", "Full");

        //Toggle();
        name = "Watermark";

        Create();
    }

    public String getWatermark()
    {
        switch(settings.get("Mode").toString().toLowerCase(Locale.ROOT))
        {
            case "full":
                return "Welcome to SomeBoringClient a-1 " + Minecraft.getMinecraft().getSession().getUsername();
            case "clientname":
                return "SomeBoringClient";
            case "clientversion":
            return "SomeBoringClient a-1";

            default:
                ChatUtil.sendMessageToPlayer("Mode was set to an invalid value and was set back to Full");
                ChatUtil.sendMessageToPlayer("Remember, those are the valid options : ");
                ChatUtil.sendMessageToPlayer("Full : nice words + client name + version + username");
                ChatUtil.sendMessageToPlayer("ClientName : only client name");
                ChatUtil.sendMessageToPlayer("ClientVersion : client name + version");
                for(Map.Entry<String, Object> entry : settings.entrySet()){
                    if(Objects.equals(entry.getKey(), "Mode")){
                        entry.setValue("Full");
                    }
                }
                return "Welcome to SomeBoringClient a-1 " + Minecraft.getMinecraft().getSession().getUsername();

        }
    }

    @SubscribeEvent
    public void OnRender(RenderGameOverlayEvent.Text e)
    {
        if(!isEnabled()) return;

        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(getWatermark(), 1, 1, 0xFFFFFF);
    }
}