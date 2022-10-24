package xyz.someboringnerd.someboringclient.Modules.RENDER;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.datafix.fixes.ShulkerBoxTileColor;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import xyz.someboringnerd.someboringclient.Modules.CATEGORY;
import xyz.someboringnerd.someboringclient.Modules.Module;

public class StorageESP extends Module
{
    public StorageESP(String name, CATEGORY cat) {
        super(name, cat);

        Create();
    }

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent e)
    {
        if(!isEnabled()) return;
        for (Object c : Minecraft.getMinecraft().world.loadedTileEntityList) {
            if (c instanceof TileEntityChest) {
                blockESP(((TileEntityChest) c).getPos(), type.CHEST);
            }if (c instanceof TileEntityShulkerBox) {
                blockESP(((TileEntityShulkerBox) c).getPos(), type.SHULKER);
            } if(c instanceof TileEntityEnderChest){
                blockESP(((TileEntityEnderChest) c).getPos(), type.ENDER_CHEST);
            }
        }
    }

    public static void blockESP(BlockPos blockPos, type _type) {
        GL11.glPushMatrix();

        double x =
                blockPos.getX()
                        - Minecraft.getMinecraft().getRenderManager().viewerPosX;
        double y =
                blockPos.getY()
                        - Minecraft.getMinecraft().getRenderManager().viewerPosY;
        double z =
                blockPos.getZ()
                        - Minecraft.getMinecraft().getRenderManager().viewerPosZ;

        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        GL11.glDepthMask(false);

        if(_type == type.CHEST) {
            RenderGlobal.renderFilledBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 0, 1, 0, .25f);
        }else if(_type == type.SHULKER) {
            RenderGlobal.renderFilledBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 0, 1, 1, .25f);
        }else if(_type == type.ENDER_CHEST) {
            RenderGlobal.renderFilledBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 0, 0, 0, .25f);
        }


        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        GL11.glDepthMask(true);

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
}

enum type
{
    CHEST,
    SHULKER,
    ENDER_CHEST,
    TRAPPED_CHEST,
    FURNACE,
    DISPENSER
}
