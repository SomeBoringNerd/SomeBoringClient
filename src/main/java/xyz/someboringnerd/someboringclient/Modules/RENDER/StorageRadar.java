package xyz.someboringnerd.someboringclient.Modules.RENDER;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.someboringnerd.someboringclient.Modules.CATEGORY;
import xyz.someboringnerd.someboringclient.Modules.Module;

public class StorageRadar extends Module
{
    public StorageRadar(String name, CATEGORY cat)
    {
        super(name, cat);

        Create();
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Text event)
    {
        if(!this.isEnabled()) return;
        int chest = 0;
        int shulker = 0;
        int enderchest = 0;

        for (Object c : Minecraft.getMinecraft().world.loadedTileEntityList)
        {
            if (c instanceof TileEntityChest) {
                chest++;
            }
            if (c instanceof TileEntityShulkerBox)
            {
                shulker++;
            }
            if(c instanceof TileEntityEnderChest)
            {
                enderchest++;
            }
        }

        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fr = mc.fontRenderer;
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        fr.drawStringWithShadow("TileEntity in render range", 2, sr.getScaledHeight() / 2,  0xFFFFFF);
        fr.drawStringWithShadow(">Chests : " + chest, 2, sr.getScaledHeight() / 2 + 10, 0xFFFFFF);
        fr.drawStringWithShadow(">Shulkers : " + shulker, 2, sr.getScaledHeight() / 2 + 20, 0xFFFFFF);
        fr.drawStringWithShadow(">Enderchests : " + enderchest, 2, sr.getScaledHeight() / 2 + 30, 0xFFFFFF);
    }
}
