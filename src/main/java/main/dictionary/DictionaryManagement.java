package main.dictionary;
import main.base.MainMenu;
import main.game.HangmanMain;
import main.game.WordReader;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.database.DatabaseConnection;
import main.database.Search;

import java.io.IOException;

public class DictionaryManagement extends Application {
    public void start(Stage primaryStage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(DictionaryManagement.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Từ điển tiếng anh");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}