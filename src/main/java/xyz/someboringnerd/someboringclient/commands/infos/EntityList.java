package xyz.someboringnerd.someboringclient.commands.infos;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;
import xyz.someboringnerd.someboringclient.commands.Command;

public class EntityList extends Command
{
    public EntityList()
    {
        name = "EntityList";
    }

    @Override
    public void Execute(String[] args)
    {
        for(Entity entity : Minecraft.getMinecraft().world.getLoadedEntityList()){
            ChatUtil.sendMessageToPlayer("Entity " + entity.getName() + " | X:" + (int)entity.posX + " | Y:" + (int)entity.posY + " | Z:" + (int)entity.posZ);
        }
    }
}
