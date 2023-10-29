module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires org.controlsfx.controls;
    requires java.sql;
    opens main.dictionary to javafx.fxml;
    exports main.dictionary;
    opens main.base to javafx.fxml;
    exports main.base;
    opens main.game to javafx.fxml;
    exports main.game;
    opens main.database to javafx.fxml;
    exports main.database;
}