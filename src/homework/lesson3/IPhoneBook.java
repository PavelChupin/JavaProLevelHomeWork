package homework.lesson3;

import java.util.List;

public interface IPhoneBook {

    void add(String phone, String lastName);

    List<String> get(String lastName);
}
