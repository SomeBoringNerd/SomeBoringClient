package xyz.someboringnerd.someboringclient.commands.infos;

import xyz.someboringnerd.someboringclient.Modules.Module;
import xyz.someboringnerd.someboringclient.Modules.ModuleManager;
import xyz.someboringnerd.someboringclient.Someboringclient;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;
import xyz.someboringnerd.someboringclient.commands.Command;

public class mod extends Command
{
    public mod()
    {
        name = "mod";
        usage = "*mod";
    }

    @Override
    public void Execute(String[] args)
    {
        ChatUtil.sendMessageToPlayer("List of modules : ");
        ChatUtil.sendMessageToPlayer("________________________________");
        for(Module mod : Someboringclient.manager.modules){
            ChatUtil.sendMessageToPlayer(mod.cat + "/" + mod.name);
        }
        ChatUtil.sendMessageToPlayer("________________________________");
        ChatUtil.sendMessageToPlayer("Do *settings to show chat you can do with a command");
    }
}
