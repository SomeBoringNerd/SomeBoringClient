package xyz.someboringnerd.someboringclient.Modules.CHAT;

import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.lwjgl.Sys;
import xyz.someboringnerd.someboringclient.Events.EventNetworkPacketEvent;
import xyz.someboringnerd.someboringclient.Modules.CATEGORY;
import xyz.someboringnerd.someboringclient.Modules.Module;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;

public class ChatBot extends Module
{

    public ChatBot(String name, CATEGORY cat) {
        super(name, cat);

        Create();
    }

    @EventHandler
    private Listener<EventNetworkPacketEvent> PacketEvent = new Listener<>(p_Event ->
    {
        if (p_Event.getPacket() instanceof SPacketChat)
        {
            final SPacketChat packet = (SPacketChat) p_Event.getPacket();

            System.out.println("SPacketChat was called");
        }
    });
}
