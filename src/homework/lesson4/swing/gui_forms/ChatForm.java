package homework.lesson4.swing.gui_forms;

import homework.lesson4.swing.listeners.ChatActionListener;

import javax.swing.*;

public class ChatForm {
    private JPanel mainPanel;
    private JPanel navigationPanel;
    private JButton send_button;
    private JTextField inputText;
    private JList outputChat;

    public ChatForm() {
        //mainPanel.setSize(500,500);

        DefaultListModel listModel = new DefaultListModel();
        outputChat.setModel(listModel);

        ChatActionListener chatActionListener = new ChatActionListener(inputText, listModel);
        send_button.addActionListener(chatActionListener);
        inputText.addActionListener(chatActionListener);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
