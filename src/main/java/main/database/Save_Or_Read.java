package main.database;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.*;
import java.util.ArrayList;

public class Save_Or_Read  {
    private String searchWord_directory = "src/main/resources/main/dictionary/History.txt";
    private ArrayList<String> searchedWord;
    public Save_Or_Read(){
        searchedWord = new ArrayList<>();
    }
    public void readSearchedWord(){
        searchedWord = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(searchWord_directory))){
            String line;
            while ((line = reader.readLine()) != null) {
                searchedWord.add(line);
            }
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public void saveSearchedWord() {
        try (FileWriter fileWriter = new FileWriter(searchWord_directory, false);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (String e : searchedWord) {
                writer.write(e);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public void updateSearchedWord(String word) {
        searchedWord.add(0, word);
    }
    public ArrayList<String> returnRecentWord(ArrayList<String> recentWord)
    {
        recentWord = new ArrayList<>(searchedWord);
        return recentWord;
    }
    public void setRecentMenu(ListView<String> listView, TextField txtFieldSearch) {
        ObservableList<String> observableSuggestions = FXCollections.observableArrayList(searchedWord);
        listView.setItems(observableSuggestions);
        listView.setOnMouseClicked(event -> {
            String selectedValue = listView.getSelectionModel().getSelectedItem();
            if (selectedValue != null) {
                txtFieldSearch.setText(selectedValue);
            }
        });
    }
    public static void main(String[] args) {
        /*Save_Or_Read saveOrRead = new Save_Or_Read();
        saveOrRead.readSearchedWord();
        saveOrRead.saveSearchedWord();
        for (String e : saveOrRead.searchedWord) {
            System.out.println(e + '\n');
        }*/
    }
    public void setSearchedWord(String word) {
        readSearchedWord();
        updateSearchedWord(word);
        saveSearchedWord();
    }


}
