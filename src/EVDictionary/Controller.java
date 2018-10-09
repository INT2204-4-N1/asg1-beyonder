package EVDictionary;


import javafx.beans.value.ObservableStringValue;
import javafx.fxml.FXML;
import java.lang.String;

import java.text.Format;
import javafx.beans.value.ChangeListener;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.*;

import java.io.*;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.value.ObservableValue;

import static javafx.application.Application.launch;

//import javax.swing.text.html.ListView;

public class Controller implements Initializable {
    @FXML
   // private AnchorPane mainLayout;
    private ListView<String> wordView = new ListView<>();
    public   TextField searchField;
    public  TextArea meaningField;
    public Button TranButton;
    private File F1 = new File("E_V.txt");
    private File F2 = new File("V_E.txt");

    String s = "hùngg";
    public analyzeData aD = new analyzeData(F1);

    public ArrayList<String> a1,a2 ;
    public HashMap<String, String> H1,H2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        EVtransalator();
       // VEtransalator();


        //showWord(a);

//        TranButton.setOnAction(event ->
//        {
//            showMeaning();
//        });
        //click();

    }

    public void showWord(ArrayList<String> word){

//

        ObservableList<String> items = FXCollections.observableArrayList(word);
        wordView.setItems(items);


    }







    public void showMeaning(HashMap<String, String >H){
            String text = H.get(searchField.getText());

            meaningField.setText(text);
}

    public void EVtransalator(){
        analyzeData aD = new analyzeData(F1);
        aD.readData();
        a1 = aD.getWordList();
        H1 = aD.getData();
        showWord(a1);
        //click(a1);
        TranButton.setOnAction(event ->
        {
            showMeaning(H1);
        });





    }
    public void click( ){
        String  add  = wordView.getSelectionModel().getSelectedItem();
        searchField.setText(add);
    }

    public void VEtransalator(){
        analyzeData aD = new analyzeData(F2);
        aD.readData();
        a2 = aD.getWordList();
        H2 = aD.getData();
        showWord(a2);
        //click(a2);
        TranButton.setOnAction(event ->
        {
            showMeaning(H2);
        });


    }












}













