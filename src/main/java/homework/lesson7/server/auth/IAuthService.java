package homework.lesson7.server.auth;


import javax.annotation.Nullable;

public interface IAuthService {
    void start();

    void stop();


    @Nullable
    String getNickByLoginPass(String login, String pass);

}
