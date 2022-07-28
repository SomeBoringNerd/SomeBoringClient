package xyz.someboringnerd.someboringclient.Util;

import ibxm.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.network.login.server.SPacketDisconnect;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import xyz.someboringnerd.someboringclient.Modules.GlobalVariables;

public class ChatUtil
{

    public ChatUtil(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void sendMessage(String content)
    {
        Minecraft.getMinecraft().player.sendChatMessage(content);
    }

    public static void sendMessageToPlayer(String content)
    {
        Minecraft.getMinecraft().player.sendMessage(new TextComponentString("[§4Som§6eB§eor§2ing§1Cli§dent§r] > " + content));
    }

    public static void sendMessageAsOtherPlayer(String content, String username)
    {
        Minecraft.getMinecraft().player.sendMessage(new TextComponentString("<" + username + "> " + content));
    }

    public static void sendMessageToPlayer(String content, boolean DebugOnly)
    {
        if(!DebugOnly) sendMessageToPlayer(content);

        else if(GlobalVariables.VerboseMode) sendMessageToPlayer(content);
    }
}
