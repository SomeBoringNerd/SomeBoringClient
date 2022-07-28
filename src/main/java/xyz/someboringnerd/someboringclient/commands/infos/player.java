package xyz.someboringnerd.someboringclient.commands.infos;

import org.json.JSONArray;
import org.json.JSONObject;
import xyz.someboringnerd.someboringclient.Modules.GlobalVariables;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;
import xyz.someboringnerd.someboringclient.commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class player extends Command implements  Runnable
{


    public player(){
        name = "player";
    }


    private String _UUID;
    private String _Player;
    @Override
    public void Execute(String[] args) {
        if(args.length > 0){
            GlobalVariables.PlayerScan = args[0];

            Thread t = new Thread(new ThreadedRequest(_Player), "request");
            t.start();

        }else{
            ChatUtil.sendMessageToPlayer("§4ERROR : NOT ENOUGH ARGUMENTS PROVIDED !§r");
            ChatUtil.sendMessageToPlayer("correct usage : " + usage);
        }


    }

    @Override
    public void run() {

    }
}

class ThreadedRequest implements Runnable {

    private String _UUID;
    private String _Player;
    private static final String USER_AGENT = "Mozilla/5.0";

    // note : dont remove %UUID% as it is a placeholder updated when needed

    private String UUID_GETTER = "https://api.mojang.com/users/profiles/minecraft/";
    private String NAME_HISTORY_GETTER = "https://api.mojang.com/user/profiles/%UUID%/names";
    private String SKIN_AND_CAPE = "https://sessionserver.mojang.com/session/minecraft/profile/%UUID%";

    public ThreadedRequest(String player_name)
    {
        _Player = player_name;
    }

    private void getUUID(String url_to_request) throws IOException
    {
        URL obj = new URL(url_to_request);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = httpURLConnection.getResponseCode();
        ChatUtil.sendMessageToPlayer("Response from API : " + responseCode, true);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            System.out.println(json.toString());
            _UUID = json.getString("id");

            NAME_HISTORY_GETTER = NAME_HISTORY_GETTER.replace("%UUID%", _UUID);
            SKIN_AND_CAPE = SKIN_AND_CAPE.replace("%UUID%", _UUID);

            ChatUtil.sendMessageToPlayer("Name : " + json.getString("name").replace("CDara", "CDara §4❤§r"));
            ChatUtil.sendMessageToPlayer("UUID : " + json.getString("id"));

            getNameHistory(NAME_HISTORY_GETTER);
        } else {
            ChatUtil.sendMessageToPlayer("URL : " + UUID_GETTER, true);
            ChatUtil.sendMessageToPlayer("GET request not worked");
        }
    }

    private void getNameHistory(String url_to_request) throws IOException {
        URL obj = new URL(url_to_request);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = httpURLConnection.getResponseCode();
        ChatUtil.sendMessageToPlayer("Response from API : " + responseCode, true);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONArray json = new JSONArray(response.toString());

            ChatUtil.sendMessageToPlayer("Name history : ");

            for (int i = 0; i != json.length(); i++) {
                if (json.getJSONObject(i).getString("name") != _Player) {
                    ChatUtil.sendMessageToPlayer(" ");
                    ChatUtil.sendMessageToPlayer("Name : " + json.getJSONObject(i).getString("name").replace("CDara", "CDara §4❤§r"));
                }
            }

            NAME_HISTORY_GETTER = NAME_HISTORY_GETTER.replace(_UUID, "%UUID%");

        } else {
            ChatUtil.sendMessageToPlayer("GET request not worked");
        }
    }

    @Override
    public void run()
    {
        try {
            getUUID(UUID_GETTER + GlobalVariables.PlayerScan);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}