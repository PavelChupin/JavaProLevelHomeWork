package homework.lesson4.swing.gui_forms;

import homework.lesson4.swing.listeners.ChatActionListener;

import javax.swing.*;
import java.awt.*;

public class ChatForm {
    private JPanel mainPanel;
    private JPanel navigationPanel;
    private JButton send_button;
    private JTextField inputText;
    private JList<String> outputChat;
    private DefaultListModel<String> listModel;

    public ChatForm() {
        //Задаем размер полю чата
        outputChat.setPreferredSize(new Dimension(600, 200));

        listModel = new DefaultListModel();
        outputChat.setModel(listModel);

        ChatActionListener chatActionListener = new ChatActionListener(inputText, listModel);
        send_button.addActionListener(chatActionListener);
        inputText.addActionListener(chatActionListener);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getInputText() {
        return inputText;
    }

    public DefaultListModel getListModel() {
        return listModel;
    }
}
