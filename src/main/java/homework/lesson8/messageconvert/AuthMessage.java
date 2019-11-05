package homework.lesson8.messageconvert;

import com.google.gson.Gson;

public class AuthMessage {
    public String login;
    public String pass;


    public String toJson() {
        return new Gson().toJson(this);
    }

    public static AuthMessage fromJson(String json) {
        return new Gson().fromJson(json, AuthMessage.class);
    }
}
