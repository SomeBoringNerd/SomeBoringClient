package xyz.someboringnerd.someboringclient.commands.Debug;

import xyz.someboringnerd.someboringclient.Util.ChatUtil;
import xyz.someboringnerd.someboringclient.commands.Command;

public class HelloWorld extends Command
{
    public HelloWorld()
    {
        name = "hello";
        usage = "*hello";
    }

    @Override
    public void Execute(String[] args) {
        ChatUtil.sendMessageToPlayer("Hello World !");
    }
}
