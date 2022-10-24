package xyz.someboringnerd.someboringclient.commands;

import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;
import xyz.someboringnerd.someboringclient.commands.Debug.DebugMode;
import xyz.someboringnerd.someboringclient.commands.Debug.HelloWorld;
import xyz.someboringnerd.someboringclient.commands.Util.Set;
import xyz.someboringnerd.someboringclient.commands.Util.Toggle;
import xyz.someboringnerd.someboringclient.commands.exploits.CoordFinder;
import xyz.someboringnerd.someboringclient.commands.infos.EntityList;
import xyz.someboringnerd.someboringclient.commands.infos.mod;
import xyz.someboringnerd.someboringclient.commands.infos.player;

import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

public class CommandManager
{
    public CopyOnWriteArrayList<Command> commands = new CopyOnWriteArrayList<>();

    public CommandManager()
    {
        MinecraftForge.EVENT_BUS.register(this);
        commands.add(new Set());
        commands.add(new Toggle());
        commands.add(new DebugMode());
        commands.add(new player());
        commands.add(new HelloWorld());
        commands.add(new CoordFinder());
        commands.add(new mod());
        commands.add(new EntityList());
    }

    @SubscribeEvent
    public void onPlayerChat(ClientChatEvent event)
    {
        if(!event.getMessage().startsWith("*")) return;

        event.setCanceled(true);
        boolean found = false;
        for(Command cmd : commands)
        {
            if(event.getMessage().toLowerCase(Locale.ROOT).startsWith("*" + cmd.name.toLowerCase(Locale.ROOT)))
            {
                event.setMessage(event.getMessage().replace("*" + cmd.name, "").trim());
                String[] args = event.getMessage().split(" ");
                cmd.PreExecute(args);
                found = true;
            }
        }

        if(!found){
            ChatUtil.sendMessageToPlayer("Command wasn't found, type *help for a list of available commands.");
        }
    }
}
