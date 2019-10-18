package homework.lesson4.javafx.controller;

import homework.lesson4.javafx.controller.message.IMessageProcessor;
import homework.lesson4.javafx.controller.message.MessageProcessor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class PrimaryController implements Initializable {

    public @FXML
    TextArea chatTextArea;
    public @FXML
    TextField messageText;
    public @FXML
    Button sendMessageButton;

    private IMessageProcessor messageProcessor;

    @FXML
    public void sendText(ActionEvent actionEvent){
        sendMessage();
    }


    @FXML
    public void sendMessage(ActionEvent actionEvent){
        sendMessage();
    }

    private void sendMessage(){
        String message = messageText.getText();
        messageProcessor.sendMessage(message);
        messageText.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.messageProcessor = new MessageProcessor(chatTextArea);
    }
}
