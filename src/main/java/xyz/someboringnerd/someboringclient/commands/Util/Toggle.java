package xyz.someboringnerd.someboringclient.commands.Util;

import xyz.someboringnerd.someboringclient.Modules.Module;
import xyz.someboringnerd.someboringclient.Someboringclient;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;
import xyz.someboringnerd.someboringclient.commands.Command;

import java.util.Locale;

public class Toggle extends Command
{
    public Toggle()
    {
        name = "toggle";
        usage = "*toggle <ModuleName>";
    }

    @Override
    public void Execute(String[] args)
    {
        if(args.length == 0) {
            ChatUtil.sendMessageToPlayer("§4ERROR : NOT ENOUGH ARGUMENTS PROVIDED !§r");
            ChatUtil.sendMessageToPlayer("correct usage : " + usage);
        }else {
            for (Module module : Someboringclient.manager.modules) {
                if (module.name.toLowerCase(Locale.ROOT).equals(args[0].toLowerCase(Locale.ROOT))) {
                    module.Toggle();
                    module.Save();
                    ChatUtil.sendMessageToPlayer("Module " + args[0] + " was toggled " + (module.isEnabled() ? "§2ON§r" : "§4OFF§r"));
                }
            }
        }
    }
}
