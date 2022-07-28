package xyz.someboringnerd;

import net.fabricmc.api.ClientModInitializer;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class SbhClient implements ClientModInitializer
{

    @Override
    public void onInitializeClient()
    {
        System.out.println("Registering Command Manager");

    }
}
