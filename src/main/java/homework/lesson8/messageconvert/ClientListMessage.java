package homework.lesson8.messageconvert;

import com.google.gson.Gson;

import java.util.List;

public class ClientListMessage {

    public List<String> online;
    //public List<String> offline;


    public String toJson() {

        return new Gson().toJson(this);
    }

    public static ClientListMessage fromJson(String json){
        return new Gson().fromJson(json, ClientListMessage.class);
    }

}
