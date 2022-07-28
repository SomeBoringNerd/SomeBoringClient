package xyz.someboringnerd.someboringclient.commands;

public class Command
{
    public String usage;
    public String name;

    public Command(){

    }

    public void PreExecute(String[] args)
    {
        Execute(args);
    }

    public void Execute(String[] args)
    {

    }
}
