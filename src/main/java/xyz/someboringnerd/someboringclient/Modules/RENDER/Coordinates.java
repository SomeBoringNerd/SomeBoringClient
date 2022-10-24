package xyz.someboringnerd.someboringclient.Modules.RENDER;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.someboringnerd.someboringclient.Modules.CATEGORY;
import xyz.someboringnerd.someboringclient.Modules.Module;
import xyz.someboringnerd.someboringclient.Someboringclient;

public class Coordinates extends Module
{
    public Coordinates(String name, CATEGORY cat) {
        super(name, cat);

        Create();
    }


    @SubscribeEvent
    public void OnRender(RenderGameOverlayEvent.Text e)
    {
        if(!isEnabled()) return ;

        int X = Minecraft.getMinecraft().player.getPosition().getX();
        int Y = Minecraft.getMinecraft().player.getPosition().getY();
        int Z = Minecraft.getMinecraft().player.getPosition().getZ();

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("> X : " + X + "| Y : " + Y + "| Z : " + Z + " <", 2, sr.getScaledHeight() - 30, 0xFFFFFF);
    }
}
