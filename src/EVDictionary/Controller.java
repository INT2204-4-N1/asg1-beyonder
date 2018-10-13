package EVDictionary;


import groovy.json.internal.IO;
import javafx.fxml.FXML;

import java.io.IOException;
import java.lang.String;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    public      TextField searchField;
    public  TextArea meaningField  ;
    public   Button TranButton = new Button();
    public  Button listenButton  ;
    public Button ggTran;
    public Button addWord;

    private File F1 = new File("E_V.txt");
    private File F2 = new File("V_E.txt");
    public ScrollPane mean= new ScrollPane();
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
        ObservableList<String> items = FXCollections.observableArrayList(word);
        wordView.setItems(items);
    }
    public void showMeaning(HashMap<String, String >H){
            String  text = H.get(searchField.getText());
            if(text == null) {

                    ALERT();



            }
            else {

                HtmlDisplay html = new HtmlDisplay();
                html.start(text, mean);
            }

}

    public void EVtransalator(){
        HtmlDisplay htmlDisplay = new HtmlDisplay();
        htmlDisplay.start("",mean);
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

        //meaningField.setText("");
        HtmlDisplay htmlDisplay = new HtmlDisplay();
        htmlDisplay.start("",mean);
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

    public void googleTransalate(){
        Stage stage = new Stage();
        stage.setTitle("HTML");
        stage.setWidth(300);
        stage.setHeight(300);
        Scene scene = new Scene(new Group());
        VBox root = new VBox();
        TextField ggText = new TextField();
        TextArea ggTran = new TextArea();
        ggText.setText(searchField.getText());
        root.getChildren().addAll(ggText,ggTran);
        GoogleTransalate  googleTransalate = new GoogleTransalate();
        try {
            ggTran.setText(googleTransalate.translate("en", "vi", ggText.getText()));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ALERT(){
        Stage stage = new Stage();
//        stage.setWidth(500);
//        stage.setHeight(150);
        stage.setTitle("Từ không tồn tại, bạn muốn làm gì?");
       // Scene scene = new Scene(new Group());
//        Parent root = FXMLLoader.load(getClass().getResource("Alert.fxml"));
//        stage.setScene(new Scene(root, 500, 150));
        stage.setResizable(false);


       Pane root = new Pane();


        Button ggTran = new Button("Dịch Online");
        Button addWord = new Button("Thêm từ");
        ggTran.relocate(175,30);
        addWord.relocate(75,30);


        root.getChildren().addAll(ggTran, addWord);



        stage.setScene(new Scene(root, 350, 100));
        stage.show();
        ggTran.setOnAction(event -> {
            stage.hide();
            googleTransalate();
        });
    }
































}













