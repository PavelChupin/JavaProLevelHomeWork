package homework.lesson4.swing.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatActionListener extends GUIActionListener {

    public ChatActionListener(JTextField inputText, DefaultListModel listModel){
        super(inputText,listModel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listModel.addElement(inputText.getText());
        inputText.setText("");
    }
}
