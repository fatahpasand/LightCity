module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.controllers;
    opens org.example.controllers to javafx.fxml;
}