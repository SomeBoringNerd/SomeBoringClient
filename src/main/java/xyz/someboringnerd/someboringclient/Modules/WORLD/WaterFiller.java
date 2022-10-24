package xyz.someboringnerd.someboringclient.Modules.WORLD;

import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import xyz.someboringnerd.someboringclient.Events.EventPlayerClickBlock;
import xyz.someboringnerd.someboringclient.Modules.CATEGORY;
import xyz.someboringnerd.someboringclient.Modules.Module;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WaterFiller extends Module
{
    public WaterFiller(String name, CATEGORY cat) {
        super(name, cat);

        Create();
    }
    private Block _clickSelectBlock = null;

    @Mod.EventHandler
    void PlayerUpdate(TickEvent.PlayerTickEvent event)
    {
        BlockPos far_Water = getSphere(Minecraft.getMinecraft().player.getPosition(), 4, 4, false, true, 0).stream()
                .filter(p_Pos -> IsValidBlockPos(p_Pos))
                .min(Comparator.comparing(p_Pos -> GetDistanceOfEntityToBlock(Minecraft.getMinecraft().player.getPosition(), p_Pos)))
                .orElse(null);

        ChatUtil.sendMessageToPlayer("nearest water is at " + far_Water.getX() + " / " + far_Water.getZ());

        if (far_Water != null)
        {

            final double l_Pos[] =  calculateLookAt(
                    far_Water.getX() + 0.5,
                    far_Water.getY() - 0.5,
                    far_Water.getZ() + 0.5,
                    Minecraft.getMinecraft().player);

            Minecraft.getMinecraft().player.rotationYawHead = (float) l_Pos[0];

            BlockPos pos = new BlockPos(l_Pos[0],l_Pos[1],l_Pos[2]);

            Minecraft.getMinecraft().playerController.processRightClickBlock(Minecraft.getMinecraft().player, Minecraft.getMinecraft().world, pos, EnumFacing.UP, new Vec3d(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight(), Minecraft.getMinecraft().player.posZ), EnumHand.MAIN_HAND);
        }
    }

    public static double[] calculateLookAt(double px, double py, double pz, EntityPlayer me)
    {
        double dirx = me.posX - px;
        double diry = me.posY - py;
        double dirz = me.posZ - pz;

        double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);

        dirx /= len;
        diry /= len;
        dirz /= len;

        double pitch = Math.asin(diry);
        double yaw = Math.atan2(dirz, dirx);

        // to degree
        pitch = pitch * 180.0d / Math.PI;
        yaw = yaw * 180.0d / Math.PI;

        yaw += 90f;

        return new double[]
                { yaw, pitch };
    }

    public static double GetDistanceOfEntityToBlock(BlockPos pos1, BlockPos pos2)
    {
        double d0 = pos1.getX() - pos2.getX();
        double d1 = pos1.getY() - pos2.getY();
        double d2 = pos1.getZ() - pos2.getZ();
        return (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
    }

    private boolean IsValidBlockPos(final BlockPos p_Pos)
    {
        IBlockState l_State = Minecraft.getMinecraft().world.getBlockState(p_Pos);

        if (l_State.getBlock() instanceof BlockLiquid)
        {
            return true;
        }

        return false;
    }

    public static List<BlockPos> getSphere(BlockPos loc, float r, int h, boolean hollow, boolean sphere, int plus_y)
    {
        List<BlockPos> circleblocks = new ArrayList<>();
        int cx = loc.getX();
        int cy = loc.getY();
        int cz = loc.getZ();
        for (int x = cx - (int) r; x <= cx + r; x++)
        {
            for (int z = cz - (int) r; z <= cz + r; z++)
            {
                for (int y = (sphere ? cy - (int) r : cy); y < (sphere ? cy + r : cy + h); y++)
                {
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
                    if (dist < r * r && !(hollow && dist < (r - 1) * (r - 1)))
                    {
                        circleblocks.add(new BlockPos(x, y + plus_y, z));
                    }
                }
            }
        }
        return circleblocks;
    }
}
