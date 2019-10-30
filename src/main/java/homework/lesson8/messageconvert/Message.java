package homework.lesson8.messageconvert;


import com.google.gson.Gson;

import java.util.List;

public class Message {
    public Command command;
    public PrivateMessage privateMessage;
    public AuthMessage authMessage;
    public PublickMessage publickMessage;
    public ClientListMessage clientListMessage;

    public static Message createClientList(List<String> nickNames) {
        Message message = create(Command.CLIET_LIST);
        ClientListMessage msg = new ClientListMessage();
        msg.online = nickNames;
        message.clientListMessage = msg;
        return message;
    }

    public String toJson() {
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //return gson.toJson(this);
        return new Gson().toJson(this);
    }

    public static Message createPublick(PublickMessage msg){
        Message m = create(Command.PUBLIC_MESSAGE);
        m.publickMessage = msg;
        return m;
    }

    public static Message createPrivate(PrivateMessage msg){
        Message m = create(Command.PRIVATE_MESSAGE);
        m.privateMessage = msg;
        return m;
    }

    public static Message createAuth(AuthMessage msg){
        Message m = create(Command.AUTH_MESSAGE);
        m.authMessage = msg;
        return m;
    }

    private static Message create(Command cmd) {
        Message m = new Message();
        m.command = cmd;
        return m;
    }

    public static Message fromJson(String json){
        return new Gson().fromJson(json, Message.class);
    }
}
