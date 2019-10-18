package homework.lesson4.swing;

import homework.lesson4.swing.gui_forms.ChatForm;
import homework.lesson4.swing.listeners.MenuActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainHomeWorkLesson4 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Messanger");
        ChatForm chatForm = new ChatForm();
        frame.setContentPane(chatForm.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Создаем панель для меню
        JMenuBar menuBar = new JMenuBar();
        //Создаем разделы меню на панеле
        JMenu menu = new JMenu("File");
        //Создаем пункт раздела меню
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        //Добавляем на пункт меню слушалку
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(new MenuActionListener(chatForm.getInputText(),chatForm.getListModel()));

        //Добавляем раздел меню в пункт меню
        menu.add(clearMenuItem);
        menu.add(exitMenuItem);
        //Добавляем пункт меню в панель для меню
        menuBar.add(menu);
        //добавляем панель меню в основное окно
        frame.setJMenuBar(menuBar);

        //Установим размер главного окна по умолчанию
        //frame.setSize(new Dimension(600, 300));
        //Установим размер главного окна в зависимости от отображаемых элементов.
        frame.pack();


        frame.setVisible(true);
    }

}
