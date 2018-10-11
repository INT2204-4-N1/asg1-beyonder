package EVDictionary;


import javafx.fxml.FXML;

import java.io.IOException;
import java.io.InputStream;
import java.lang.String;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import marytts.MaryInterface;
import marytts.util.data.audio.AudioPlayer;
import org.controlsfx.control.textfield.TextFields;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;




public class Controller implements Initializable {
    @FXML
   // private AnchorPane mainLayout;
    private ListView<String> wordView = new ListView<>();
    public    TextField searchField;
    public  TextArea meaningField ;
    public  Button TranButton;
    public  Button listenButton;



    private File F1 = new File("E_V.txt");
    private File F2 = new File("V_E.txt");
    //public MaryInterface marytts;




    public ArrayList<String> aE_V, aV_E;
    public HashMap<String, String> hE_V, hV_E;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Read();
        EVtransalator();


    }
    public void Read(){
        analyzeData A1= new analyzeData(F1);

        A1.readData();
        aE_V = A1.getWordList();
        hE_V = A1.getData();

        analyzeData A2 = new analyzeData(F2);
        A2.readData();
        aV_E = A2.getWordList();
        hV_E = A2.getData();


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

        showWord(aE_V);

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

        showWord(aV_E);

      
        TranButton.setOnAction(event ->
        {
            showMeaning(hV_E);
        });



        TextFields.bindAutoCompletion(searchField,aV_E);



    }

    public void TextToSpeech(){
//        voice dfki-poppy-hsmm
//        Voice: cmu-slt-hsmm
//        Voice: cmu-rms-hsmm
        Voice voice = new Voice("cmu-rms-hsmm");
        voice.say(searchField.getText());



    }






























}













