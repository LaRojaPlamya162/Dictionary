package main.dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.base.RecentMenu;
import main.database.Save_Or_Read;

import java.io.IOException;

public class Recent extends Application {
    public void start(Stage primaryStage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(DictionaryManagement.class.getResource("History.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Từ điển tiếng anh");
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
