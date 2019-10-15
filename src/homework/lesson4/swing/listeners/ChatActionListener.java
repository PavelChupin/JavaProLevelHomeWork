package homework.lesson4.swing.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatActionListener implements ActionListener {
    private JTextField inputText;
    private DefaultListModel listModel;

    public ChatActionListener(JTextField inputText, DefaultListModel listModel){
        this.inputText = inputText;
        this.listModel = listModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listModel.addElement(inputText.getText());
        inputText.setText("");
    }
}
