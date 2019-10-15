package homework.lesson3;

import java.util.*;

public class TelephoneList implements IPhoneBook{
    Map<String, List<String>> telephoneList;

    public TelephoneList() {
        this.telephoneList = new HashMap<>();
    }

    public TelephoneList(String phone, String lastName) throws RuntimeException {
        //Вызываем конструктор по умолчанию
        this();

        //Проверим валидность данных
        checkData(phone, lastName);

        //Добавляем новую запись в телефонный справочник
        putNewPerson(phone, lastName);
    }

    private void putNewPerson(String phone, String lastName) {
        List<String> phones = new ArrayList<>();
        phones.add(phone);
        this.telephoneList.put(lastName, phones);
    }

    private void checkData(String phone, String lastName) throws RuntimeException {
        if (phone == null || lastName == null || phone.isEmpty() || lastName.isEmpty()) {
            throw new RuntimeException("Переданы пустые параметры: Phone = " + phone + ". LastName = " + lastName + ".");
        }
    }

    @Override
    public List<String> get(String lastName) {
        return this.telephoneList.get(lastName);
    }

    @Override
    public void add(String phone, String lastName) throws RuntimeException {
        //Проверим валидность данных
        checkData(phone, lastName);

        //Если ранее данная фамилия уже была добавлена и нужно добавить новый номер телефона
        if (this.telephoneList.get(lastName) != null) {
            this.telephoneList.get(lastName).add(phone);
        } else {
            //Добавляем новую запись в телефонный справочник
            putNewPerson(phone, lastName);
        }
    }
}
