package EVDictionary;


import groovy.json.internal.IO;
import javafx.event.Event;
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
//    public Button ggTran;
//    public Button addWord;
    public Button OnlineSearch;
    private File F1 = new File("data/E_V.txt");
    private File F2 = new File("data/V_E.txt");
    public ScrollPane mean= new ScrollPane();
    public Button ggTran = new Button("Dịch Online");
    public  Button addWord = new Button("Thêm từ");

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
        ggTran.setOnAction(event -> {
            //stage.hide();
            googleTransalate("en", "vi");
        });
        OnlineSearch.setOnAction(event -> {
            googleTransalate("en", "vi");
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
        ggTran.setOnAction(event -> {
            googleTransalate("vi", "en");
        });
        OnlineSearch.setOnAction(event -> {
            googleTransalate("vi", "en");
        });


    }

    public void TextToSpeech(){
//        voice dfki-poppy-hsmm
//        Voice: cmu-slt-hsmm
//        Voice: cmu-rms-hsmm
        Voice voice = new Voice("cmu-rms-hsmm");
        voice.say(searchField.getText());



    }

    public void googleTransalate(String From, String To){
        Stage stage = new Stage();
        stage.setTitle("Dịch Online");
        stage.setWidth(300);
        stage.setHeight(300);
        Scene scene = new Scene(new Group());
        VBox root = new VBox();
        TextField ggText = new TextField();
        ggText.setPromptText("Nhập từ hoặc câu bạn muốn tra vào đây");
        TextArea ggMeans = new TextArea();
        Button tran = new Button("Dịch");
        Button hide = new Button("Ẩn");
        ggText.setText(searchField.getText());
        root.getChildren().addAll(ggText,ggMeans,tran,hide);
        GoogleTransalate  googleTransalate = new GoogleTransalate();
        try {
            ggMeans.setText(googleTransalate.translate(From ,To, ggText.getText()));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        tran.setOnAction(event -> {
            try{
            ggMeans.setText(googleTransalate.translate(From, To, ggText.getText()));
            }catch (IOException e){
                e.printStackTrace();
            }

        });
        hide.setOnAction(event -> stage.hide());;

        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ALERT(){
        Stage stage = new Stage();
        stage.setTitle("Từ không tồn tại, bạn muốn làm gì?");
        stage.setResizable(false);
        Pane root = new Pane();
        ggTran.relocate(175,30);
        addWord.relocate(75,30);
        root.getChildren().addAll(ggTran, addWord);
        stage.setScene(new Scene(root, 370, 100));
        stage.show();

        ggTran.setOnMouseClicked(event ->{
            stage.hide();
        } );


    }
































}













