package xyz.someboringnerd.someboringclient.QOL;

import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.someboringnerd.someboringclient.Events.EventNetworkPacketEvent;

public class BuildHeight implements Listenable
{

    @EventHandler
    private Listener<EventNetworkPacketEvent> PacketEvent = new Listener<>(p_Event ->
    {
        if (p_Event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock)
        {
            final CPacketPlayerTryUseItemOnBlock packet = (CPacketPlayerTryUseItemOnBlock) p_Event.getPacket();
            if (packet.getPos().getY() >= 255 && packet.getDirection() == EnumFacing.UP)
            {
                //packet.placedBlockDirection = EnumFacing.DOWN;
            }
        }
    });

}
