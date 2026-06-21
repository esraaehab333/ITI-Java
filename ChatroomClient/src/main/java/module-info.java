module com.mycompany.chatroomclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.chatroomclient to javafx.fxml;
    exports com.mycompany.chatroomclient;
}
