package homework.lesson4.swing.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActionListener extends GUIActionListener {

    public MenuActionListener(JTextField inputText, DefaultListModel listModel){
        super(inputText,listModel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listModel.clear();
        inputText.setText("");
    }
}
