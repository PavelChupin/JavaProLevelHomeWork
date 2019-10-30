package homework.lesson8.messageconvert;

import com.google.gson.Gson;

public class PublickMessage {
    public String from;
    public String message;


    public String toJson() {

        return new Gson().toJson(this);
    }

    public static PublickMessage fromJson(String json){
        return new Gson().fromJson(json, PublickMessage.class);
    }
}
