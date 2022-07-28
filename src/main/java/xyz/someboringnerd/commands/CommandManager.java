package xyz.someboringnerd.commands;

import xyz.someboringnerd.commands.render.fullbright;

import java.util.HashMap;

public class CommandManager
{
    public HashMap<String, CommandBase> commands = new HashMap<String, CommandBase>();

    private String prefix = "%";

    /**
    *   @param name : what the user need to type in game
    *   @param command : instance of {@code CommandBase}
     */
    private void registerCommand(String name, CommandBase command)
    {
        commands.put(prefix + name, command);
    }

    public CommandManager()
    {
        registerCommand("fullbright", new fullbright());
    }



}
