package xyz.someboringnerd.someboringclient.commands.Util;

import xyz.someboringnerd.someboringclient.Modules.Module;
import xyz.someboringnerd.someboringclient.Someboringclient;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;
import xyz.someboringnerd.someboringclient.commands.Command;

import java.util.Locale;
import java.util.Map;

public class Set extends Command
{
    public Set()
    {
        name = "set";
        usage = "*set <ModuleName> <OptionName> <Value>";
    }

    @Override
    public void Execute(String[] args) {
        if(args.length < 2){
            ChatUtil.sendMessageToPlayer("§4ERROR : NOT ENOUGH ARGUMENTS PROVIDED !§r");
            ChatUtil.sendMessageToPlayer("correct usage : " + usage);
        }else{
            for(Module mod : Someboringclient.manager.modules){
                if(mod.name.toLowerCase(Locale.ROOT).equals(args[0].toLowerCase(Locale.ROOT)))
                {
                    ChatUtil.sendMessageToPlayer("Found module " + args[0], true);

                    for(Map.Entry<String, Object> entry : mod.settings.entrySet())
                    {
                        if(entry.getKey().toLowerCase(Locale.ROOT).equals(args[1].toString().toLowerCase(Locale.ROOT)))
                        {
                            ChatUtil.sendMessageToPlayer("Found setting " + args[1], true);
                            entry.setValue(args[2]);
                            mod.Save();
                            ChatUtil.sendMessageToPlayer("Set " + args[1] + " from module " + args[0] + " to " + args[2]);
                        }
                    }
                }
            }
        }
    }
}
