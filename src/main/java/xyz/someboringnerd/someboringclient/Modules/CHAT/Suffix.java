package xyz.someboringnerd.someboringclient.Modules.CHAT;

import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.someboringnerd.someboringclient.Modules.CATEGORY;
import xyz.someboringnerd.someboringclient.Modules.Module;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;

import java.util.Locale;
import java.util.Map;

public class Suffix extends Module
{

    public Suffix(String name, CATEGORY cat)
    {
        super(name, cat);
        settings.put("Mode", "FULL");
    }

    @SubscribeEvent
    public void onPlayerChat(ClientChatEvent event)
    {
        if(!this.isEnabled()) return;

        event.setMessage(event.getMessage() + getSuffix());
    }

    String getSuffix()
    {
        switch (settings.get("Mode").toString().toLowerCase(Locale.ROOT)){
            case "full":
                return " << ＳｏｍｅＢｏｒｉｎｇＣｌｉｅｎｔ ｖａ-１";
            case "name":
                return " << ＳｏｍｅＢｏｒｉｎｇＣｌｉｅｎｔ ♥";
            default:
                ChatUtil.sendMessageToPlayer("Mode value was set to an invalid value and was reset to it's default value (FULL)");
                for (Map.Entry<String, Object> stringObjectEntry : settings.entrySet()) {
                    if(stringObjectEntry.getKey().equals("Mode")){
                        stringObjectEntry.setValue("full");
                    }
                }

                return "";
        }
    }
}

enum Mode
{
    FULL,
    NAME
}