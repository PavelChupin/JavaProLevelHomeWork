package homework.lesson4.swing;

import homework.lesson4.swing.gui_forms.ChatForm;

import javax.swing.*;

public class MainHomeWorkLesson4 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Messanger");
        //frame.setSize(500,500);
        frame.setContentPane(new ChatForm().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
