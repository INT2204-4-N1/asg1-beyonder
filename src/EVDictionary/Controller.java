package EVDictionary;


import javafx.fxml.FXML;
import java.lang.String;

import javafx.scene.web.WebView;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//import javafx.scene.web.HTMLEditor;
import javafx.scene.web.*;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;


import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static javafx.application.Application.launch;

//import javax.swing.text.html.ListView;

public class Controller implements Initializable {
    @FXML
   // private AnchorPane mainLayout;
    private ListView<String> wordView = new ListView<>();
    public   TextField searchField;
    public  TextArea meaningField ;
    public Button TranButton;
   public WebView mean;
    public HTMLEditor html;
    private File F1 = new File("E_V.txt");
    private File F2 = new File("V_E.txt");




    public ArrayList<String> aE_V, aV_E;
    public HashMap<String, String> hE_V, hV_E;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        EVtransalator();

    }

    public void showWord(ArrayList<String> word){

//

        ObservableList<String> items = FXCollections.observableArrayList(word);
        wordView.setItems(items);


    }







    public void showMeaning(HashMap<String, String >H){
            String  text = H.get(searchField.getText());
            meaningField.setWrapText(true);
            meaningField.setText(text);




}

    public void EVtransalator(){
        meaningField.setText(" ");
        searchField.setText("");
        analyzeData aD = new analyzeData(F1);
        aD.readData();
        aE_V = aD.getWordList();
        hE_V = aD.getData();
        showWord(aE_V);
//        searchField.setOnKeyTyped(t -> {
//            testSearch();
//        });
        TextFields.bindAutoCompletion(searchField,aE_V);



        TranButton.setOnAction(event ->
        {
            showMeaning(hE_V);
        });





    }
    public void ListViewToSearchField( ){
        String  add  = wordView.getSelectionModel().getSelectedItem();
        searchField.setText(add);
    }

    public void VEtransalator(){
        meaningField.setText(" ");
        searchField.setText("");
        analyzeData aD = new analyzeData(F2);
        aD.readData();
        aV_E = aD.getWordList();
        hV_E = aD.getData();
        showWord(aV_E);
      
        TranButton.setOnAction(event ->
        {
            showMeaning(hV_E);
        });


    }

//    public void testSearch( ){
//        String text = searchField.getText();
//       ArrayList<String > a2 = new ArrayList<>();
//        TextFields.bindAutoCompletion(searchField,aV_E);
//
//
        //meaningField.setText(text);
















}













