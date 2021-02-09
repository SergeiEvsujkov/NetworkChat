package client.controllers;

import client.NetworkClient;
import client.models.Network;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatController {

    @FXML
    public ListView<String> usersList;

    @FXML
    private Button sendButton;
    @FXML
    private TextArea chatHistory;
    @FXML
    private TextField textField;
    @FXML
    private Label usernameTitle;
    @FXML
    private ChoiceBox<String> userSend;

    private Network network;

    private List <String> user = new ArrayList<>();



    public void setLabel(String usernameTitle) {
        this.usernameTitle.setText(usernameTitle);
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    @FXML
    public void initialize() {
        user.add(0,"Всем");
        user.addAll(Network.userList);
        user.remove(usernameTitle.getText());
        userSend.setItems(FXCollections.observableArrayList(user));
        userSend.setValue(user.get(0));
        usersList.setItems(FXCollections.observableArrayList(Network.userList));
        sendButton.setOnAction(event -> ChatController.this.sendMessage());
        textField.setOnAction(event -> ChatController.this.sendMessage());


    }

    private void sendMessage() {
        String message = textField.getText();

        if(message.isBlank()) {
            return;
        }

        appendMessage("Я: " + message);
        textField.clear();

        try {
            if (userSend.getValue().equals("Всем")) {
                network.sendMessage(message);
            } else {
                network.sendPrivateMessage(message, userSend.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
            NetworkClient.showErrorMessage("Ошибка подключения", "Ошибка при отправке сообщения", e.getMessage());
        }

    }

    public void appendMessage(String message) {
        String timestamp = DateFormat.getInstance().format(new Date());
        chatHistory.appendText(timestamp);
        chatHistory.appendText(System.lineSeparator());
        chatHistory.appendText(message);
        chatHistory.appendText(System.lineSeparator());
        chatHistory.appendText(System.lineSeparator());
    }

    public void setUsernameTitle(String username) {

    }

    public void newUserList(){
        user.clear();
        user.add(0,"Всем");
        user.addAll(Network.userList);
        user.remove(usernameTitle.getText());
        userSend.setItems(FXCollections.observableArrayList(user));
        userSend.setValue(user.get(0));
        usersList.setItems(FXCollections.observableArrayList(Network.userList));
    }

}
