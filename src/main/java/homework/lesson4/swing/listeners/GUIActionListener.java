package homework.lesson4.swing.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GUIActionListener implements ActionListener {
    protected JTextField inputText;
    protected DefaultListModel<String> listModel;

    protected GUIActionListener(JTextField inputText, DefaultListModel listModel) {
        this.inputText = inputText;
        this.listModel = listModel;
    }


    @Override
    public abstract void actionPerformed(ActionEvent e);

}
