package xyz.someboringnerd.someboringclient.commands.Debug;

import xyz.someboringnerd.someboringclient.Modules.GlobalVariables;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;
import xyz.someboringnerd.someboringclient.commands.Command;

import java.util.Locale;

public class DebugMode extends Command
{
    public DebugMode(){
        name = "debug";
        usage = "*debug <true/false>";
    }

    @Override
    public void Execute(String[] args) {
        if(args.length == 0){
            ChatUtil.sendMessageToPlayer("§4ERROR : NOT ENOUGH ARGUMENTS PROVIDED !§r");
            ChatUtil.sendMessageToPlayer("correct usage : " + usage);
        }else{

            GlobalVariables.VerboseMode = args[0].toLowerCase(Locale.ROOT).trim().equals("true");

            ChatUtil.sendMessageToPlayer("Debug Mode was set to " + GlobalVariables.VerboseMode);
        }
    }
}
