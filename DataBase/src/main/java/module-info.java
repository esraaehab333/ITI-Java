module com.mycompany.database {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires derbyclient;
    requires java.base;
    opens com.mycompany.database to javafx.fxml;
    exports com.mycompany.database;
}
