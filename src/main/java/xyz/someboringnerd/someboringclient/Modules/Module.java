package xyz.someboringnerd.someboringclient.Modules;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.zero.alpine.fork.listener.Listenable;
import net.minecraftforge.common.MinecraftForge;
import org.json.JSONObject;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Module implements Listenable
{

    public Map<String, Object> settings = new HashMap<>();
    public String name = "DEFAULT_NAME";
    boolean enabled = false;
    public int key = 0;
    public CATEGORY cat;

    public Module(String name, CATEGORY cat)
    {
        this.name = name;
        this.cat = cat;

        MinecraftForge.EVENT_BUS.register(this);


    }

    public void Awake(){};

    public boolean isEnabled()
    {
        return enabled;
    }

    public void Toggle()
    {
        enabled = !enabled;

        if(enabled){
            Awake();
        }
    }

    public void setEnabled(boolean _enabled){
        if(_enabled){
            Awake();
        }
        enabled = _enabled;
    }

    public void Create()
    {
        File file = new File("SomeBoringClient/Modules/" + cat + "/" + name + ".sbc");
        if(!file.exists()){
            try
            {
                file.createNewFile();

                JSONObject object = new JSONObject();

                object.append("key", key);
                object.append("active", false);

                for(Map.Entry entry : settings.entrySet())
                {
                    object.append((String) entry.getKey(), entry.getValue());
                }

                System.out.println(object);

                try
                {
                    FileWriter writer = new FileWriter("SomeBoringClient/Modules/" + cat + "/" + name + ".sbc");

                    writer.write(object.toString());

                    writer.close();
                }catch (IOException ignored){}
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }else{
            Load();
        }
    }

    public void Save()
    {
        JSONObject object = new JSONObject();

        object.append("key", key);
        object.append("active", isEnabled());

        for(Map.Entry entry : settings.entrySet())
        {
            object.append((String) entry.getKey(), entry.getValue());
        }

        System.out.println(object);

        try
        {
            FileWriter writer = new FileWriter("SomeBoringClient/Modules/" + cat + "/" + name + ".sbc");

            writer.write(object.toString());

            writer.close();
        }catch (IOException ignored){}
    }

    public void Load()
    {
        try {
            JsonParser jsonP = new JsonParser();

            JsonObject json = (JsonObject)jsonP.parse(new FileReader("SomeBoringClient/Modules/" + cat + "/" + name + ".sbc"));

            settings.clear();

            System.out.println(json);

            for(Map.Entry entry : json.entrySet())
            {
                if(entry.getKey().toString().toLowerCase(Locale.ROOT).equals("key")){
                    System.out.println(Integer.parseInt(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", "")));
                    key = Integer.parseInt(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", ""));
                }else if(entry.getKey().toString().toLowerCase(Locale.ROOT).equals("active")){
                    System.out.println(entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", ""));
                    enabled = entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", "").equals("true");
                }else {
                    System.out.println(entry.getKey() + " | " + entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", ""));
                    settings.put((String) entry.getKey(), entry.getValue().toString().replace("[", "").replace("]", "").replace("\"", ""));
                }
            }
        }catch (IOException ignored){}

    }
}

