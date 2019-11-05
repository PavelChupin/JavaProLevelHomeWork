package homework.lesson8.javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import homework.lesson8.messageconvert.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.exception.ExceptionUtils;
import homework.lesson8.javafx.controller.message.ServerMessageService;
import homework.lesson8.javafx.controller.message.IMessageService;

public class PrimaryController implements Initializable {

    public @FXML TextArea chatTextArea;
    public @FXML TextField messageText;
    public @FXML Button sendMessageButton;

    public @FXML TextField loginField;
    public @FXML PasswordField passField;

    public @FXML HBox authPanel;
    public @FXML VBox chatPanel;
    public String nickName;

    public @FXML ListView<String> clientList;


    private IMessageService messageService;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.messageService = new MockMessageService(chatTextArea);
        try {
            this.messageService = new ServerMessageService(this, true);
        } catch (Exception e) {
            showError(e);
        }
    }

    private void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("oops! Something went wrong!");
        alert.setHeaderText(e.getMessage());

        VBox dialogPaneContent = new VBox();

        Label label = new Label("Stack Trace:");

        String stackTrace = ExceptionUtils.getStackTrace(e);
        TextArea textArea = new TextArea();
        textArea.setText(stackTrace);

        dialogPaneContent.getChildren().addAll(label, textArea);

        // Set content for Dialog Pane
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.setResizable(true);
        alert.showAndWait();

        e.printStackTrace();
    }

    @FXML
    public void sendText(ActionEvent actionEvent) {
        sendMessage();
    }

    @FXML
    public void sendMessage(ActionEvent actionEvent) {
        sendMessage();
    }

    private void sendMessage() {
        String message = messageText.getText();
        chatTextArea.appendText("Я: " + message + System.lineSeparator());

        Message msg = buildMessage(message);

        //messageService.sendMessage(message);
        messageService.sendMessage(msg.toJson());
        messageText.clear();
    }

    private Message buildMessage(String message) {
        String selectedNickName = clientList.getSelectionModel().getSelectedItem();
        if(selectedNickName != null){
            PrivateMessage msg = new PrivateMessage();
            msg.from = nickName;
            msg.to = selectedNickName;
            msg.message = message;

            return Message.createPrivate(msg);
        }

        return buildPublickMessage(message);
    }

    private Message buildPublickMessage(String message) {
        PublickMessage publickMessage = new PublickMessage();
        publickMessage.from = nickName;
        publickMessage.message = message;

        Message msg = new Message();
        msg.command = Command.PUBLIC_MESSAGE;
        msg.publickMessage = publickMessage;

        return msg;
    }

    public void shutdown() {
        try {
            messageService.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sendAuth(ActionEvent actionEvent) {
        String login = loginField.getText();
        String password = passField.getText();

        AuthMessage msg = new AuthMessage();
        msg.login = login;
        msg.pass = password;

        Message authMessage = Message.createAuth(msg);

        //messageService.sendMessage(String.format("/auth %s %s", login, password));
        messageService.sendMessage(authMessage.toJson());
    }
}
