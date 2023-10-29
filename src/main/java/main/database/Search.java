package main.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import main.database.DatabaseConnection;
import javax.xml.parsers.*;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.w3c.dom.*;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Search {
    private DatabaseConnection database = new DatabaseConnection("jdbc:mysql://localhost:3306/edict", "root", "123456");
    private ArrayList<String> HintSearch ;
    public String search(String text) {
        database.connectToDatabase();
        String resultQuery = database.querySearch(text);
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(resultQuery.getBytes("UTF-8")));
            // root element
            Element root = doc.getDocumentElement();
            // file and access Q tag
            NodeList qElements = root.getElementsByTagName("Q");
            Element qElement = (Element) qElements.item(0);
            String qContent = qElement.getTextContent();
            String [] lines = qContent.split("\n");
            StringBuilder stringBuilder = new StringBuilder();
            for (String line : lines) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setHintSearch(TextField txtFieldSearch) {
        database.connectToDatabase();
        String searchQuery = database.querySearch(txtFieldSearch.getText());
        HintSearch = database.SearchHint();
        database.disconnectToDatabase();
    }
    public void initAutoCompletion(TextField txtFieldSearch) {
        AutoCompletionBinding<String> auto = TextFields.bindAutoCompletion(txtFieldSearch, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection<String>>() {
            @Override
            public Collection<String> call(AutoCompletionBinding.ISuggestionRequest iSuggestionRequest) {
                String userText = iSuggestionRequest.getUserText().toLowerCase();
                List<String> suggestions = new ArrayList<>(HintSearch);
                suggestions.removeIf(word -> !word.toLowerCase().startsWith(userText));
                return suggestions;
            }
        });
        auto.setOnAutoCompleted(event -> {
            // Xử lý khi một gợi ý được chọn
            String selectedHint = event.getCompletion();
            // Ví dụ: có thể cập nhật TextField với gợi ý đã chọn
            txtFieldSearch.setText(selectedHint);
        });
    }
    public void suggestionHint(ListView<String> listView, TextField txtFieldSearch) {
        ObservableList<String> observableSuggestions = FXCollections.observableArrayList(HintSearch);
            String filter = txtFieldSearch.getText().toLowerCase();
            ObservableList<String> filteredList = FXCollections.observableArrayList();
            for (String suggestion : observableSuggestions) {
                if (suggestion.toLowerCase().contains(filter)) {
                    filteredList.add(suggestion);
                }
            }
            listView.setItems(filteredList);
            listView.setOnMouseClicked(event -> {
            String selectedValue = listView.getSelectionModel().getSelectedItem();
            if (selectedValue != null) {
                txtFieldSearch.setText(selectedValue);
            }
        });
    }



}