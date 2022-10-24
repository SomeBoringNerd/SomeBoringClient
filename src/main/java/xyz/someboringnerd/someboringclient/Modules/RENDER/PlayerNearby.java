package xyz.someboringnerd.someboringclient.Modules.RENDER;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.someboringnerd.someboringclient.Modules.CATEGORY;
import xyz.someboringnerd.someboringclient.Modules.Module;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;

public class PlayerNearby extends Module {
    public PlayerNearby(String name, CATEGORY cat) {
        super(name, cat);

        Create();
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Text e)
    {
        if(!this.isEnabled()) return;
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fr = mc.fontRenderer;
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int i = 25;
        for (final Entity entity : mc.world.loadedEntityList) {
            if (entity instanceof EntityPlayer && !entity.getName().equals(mc.player.getName())) {


                String str = "<§6" + entity.getName() + "§r>" + " §4hp : " + ((EntityPlayer) entity).getHealth() + "§r"
                        + " X:" + (int)entity.posX + " Y:" + (int)entity.posY + " Z:" + (int)entity.posZ;

                //final Vec3d playerPos = new Vec3d(entity.posX, entity.posY, entity.posZ);

                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(
                        str
                        ,sr.getScaledWidth() - fr.getStringWidth(str) - 5 ,
                        i + 10, 0xFFFFFF);

                i += 15;
            }
        }
    }
}
