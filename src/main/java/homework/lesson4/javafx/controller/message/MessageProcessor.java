package homework.lesson4.javafx.controller.message;


import javafx.scene.control.TextArea;

public class MessageProcessor implements IMessageProcessor {
    private TextArea chatTextArea;

    public MessageProcessor(TextArea chatTextArea) {
        this.chatTextArea = chatTextArea;
    }

    @Override
    public void sendMessage(String message) {
        System.out.printf("Message %s has been sent%n", message);
        processRetrievedMessage(message);
    }

    @Override
    public void processRetrievedMessage(String message) {
        chatTextArea.appendText(message + System.lineSeparator());
    }
}
