module com.mycompany.notepad {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.notepad to javafx.fxml;
    exports com.mycompany.notepad;
}
