module ChatClient {
    requires javafx.controls;
    requires javafx.fxml;
    requires ChatCommands;
    requires javafx.media;

    opens client.controllers to javafx.fxml;
    exports client;
}