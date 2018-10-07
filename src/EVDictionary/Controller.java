package EVDictionary;


import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import java.io.*;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static javafx.application.Application.launch;

//import javax.swing.text.html.ListView;

public class Controller implements Initializable {
    @FXML
    private ListView<String> wordView;

//    File F = new File("E_V.txt");
//
//    analyzeData analyzedata = new analyzeData(F);
//
//    ArrayList<String> wordList = analyzedata.readData();
//
//
//    ObservableList<String> item = FXCollections.observableArrayList(wordList);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showWord();


    }

    public void showWord(){
        File F = new File("E_V.txt");

        analyzeData analyzedata = new analyzeData(F);
        analyzedata.readData();
        //wordView = new ListView<String>();

        ArrayList<String> wordList = analyzedata.getWordList();


        ObservableList<String> item = FXCollections.observableArrayList(wordList);
        wordView.setItems(item);












    }
//  test doc file
//    public  static void main(String[] a){
//        File F = new File("E_V.txt");
//        analyzeData aD = new analyzeData(F);
//        aD.readData();
//        //wordView = new ListView<String>();
//        ArrayList<String> arr = aD.getWordList();
//        HashMap<String, String> hM = aD.getData();
//
//        System.out.println(arr.get(12));
//        System.out.println(hM.get(arr.get(12)));
//
//    }


}













