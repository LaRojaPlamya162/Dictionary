package main.base;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.database.DatabaseConnection;
import main.database.Save_Or_Read;
import main.database.Search;
import main.dictionary.DictionaryManagement;
import main.dictionary.Recent;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import main.game.HangmanMain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class MainMenu {
    @FXML
    private Button btnDaily;
    @FXML
    private Button btnFavorites;
    @FXML
    private Button btnGames;
    @FXML
    private Button btnRecent;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnVoice;
    @FXML
    private VBox dictionaryDisplay;
    @FXML
    protected TextField txtFieldSearch = new TextField();
    @FXML
    private JFXTextArea textToDisplay;

    @FXML
    private ImageView btnSetting;

    @FXML
    private ListView<String> listview;
    private Save_Or_Read saveOrRead = new Save_Or_Read();
    private Search SEARCH = new Search();
    /*private AudioLogic audioUK = new AudioLogic("UK.mp3");
    private AudioLogic audioUS = new AudioLogic("US.mp3");
    private AudioLogic audioTest = new AudioLogic("TestAudio.mp3");
    private SearchLogic searchLogic = new SearchLogic();

    private API api = new API();
*/

    @FXML
    private void txtFieldSearchAction(KeyEvent keyEvent) {

    }
    // Search
    @FXML
    void btnSearchAction(ActionEvent event) {
        // get text from TextField Search
        String textToSearch = txtFieldSearch.getText();
        // check length of String
        if (textToSearch.length() == 0) {
            //new Shake(txtFieldSearch).play();
            //new Shake(btnSearch).play();
        } else {
            //textToDisplay.setText(searchLogic.search(textToSearch));
            textToDisplay.setText(SEARCH.search(textToSearch));
            saveOrRead.setSearchedWord(textToSearch);
            // Sound
            //api.textToSpeech(textToSearch, "US");
            //api.textToSpeech(textToSearch, "UK");
        }
    }


    // similar with btnSearchAction
    @FXML
    void pressSearch(KeyEvent keyEvent) {
        /*String textToSearch = txtFieldSearch.getText();
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (txtFieldSearch.getText().length() == 0) {
                //new Shake(txtFieldSearch).play();
                //new Shake(btnSearch).play();
            } else {
                textToDisplay.setText(SEARCH.search(textToSearch));
            }
        }*/
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            SEARCH.setHintSearch(txtFieldSearch);
            SEARCH.suggestionHint(listview, txtFieldSearch);
        }
    }
    @FXML
    void clickStar(KeyEvent keyEvent){

    }

    // Voice Button
    @FXML
    void btnVoiceAction(ActionEvent event) {

    }

    // Daily Button
    @FXML
    void dailyAction(ActionEvent event) {

    }

    // Favorites Button
    @FXML
    void favoritesAction(ActionEvent event) {

    }

    // Games Button
    @FXML
    public void gamesAction(ActionEvent event) {
        Platform.runLater((new Runnable() {
            @Override
            public void run() {
                try {
                    Stage newstage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    new HangmanMain().start(newstage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }));
        //btnGames.setOnAction(e -> hangmanMain.START());
        //System.out.println("DUMA");
    }

    // Recent Button
    @FXML
    void recentAction(ActionEvent event) {
        Platform.runLater((new Runnable() {
            @Override
            public void run() {
                try {
                    Stage newstage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    new Recent().start(newstage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }));
    }

    // Setting Button
    @FXML
    void moveSettingBtn(MouseEvent mouseEvent) {

    }

    @FXML
    void clickSettingBtn(MouseEvent mouseEvent) {

    }

    // Sound Button
    @FXML
    void clickBtnUK(MouseEvent event) {

    }

    @FXML
    void clickBtnUS(MouseEvent event) {

    }
    @FXML
    void keyTyped(KeyEvent keyEvent)  {
        //SEARCH.setHintSearch(txtFieldSearch);
        //SEARCH.initAutoCompletion(txtFieldSearch);
    }
}

