package xyz.someboringnerd.commands;

public class CommandBase
{
    public CommandBase()
    {

    }

    public void PreExecute(String message){
        Execute(message);
    }

    public void Execute(String message){

    }
}
