package EVDictionary;


import com.ibm.icu.text.ArabicShaping;
import groovy.json.internal.IO;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
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

    private   ListView<String> wordView = new ListView<>();
    public    AnchorPane mainLayout  ;
    public    TextField searchField   ;
    public    Button TranButton;
    public    Button listenButton  ;
    public    Button btAdd ;
    public    AnchorPane acPane ;
    public    Button btEdit ;
    public    Button btRemove  ;
    public    Button OnlineSearch ;
    public    File F1 = new File("data/E_V.txt");
    public    File F2 = new File("data/V_E.txt");
    public    ScrollPane mean= new ScrollPane();
    public    Button ggTran = new Button("Dịch Online");
    public    Button addWord = new Button("Thêm từ"); // button xuất hiện trên thông báo ko tìm thấy từ
    public    ArrayList<String> aE_V, aV_E;
    public    HashMap<String, String> hE_V, hV_E;
    public    File F3 = new File("data/evTest.txt");


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Read();
        mainLayout.setStyle("-fx-background-color: rgb(165,177,186)");


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
        btAdd.setOnAction(event -> {
            addWord(aE_V,hE_V,F1);
            //showWord(aE_V);



        });
        btRemove.setOnAction(event -> {
            removeWord(aE_V,hE_V,F1);
        });




    }

    public void ListViewToSearchField( ){
        String  add  = wordView.getSelectionModel().getSelectedItem();
        searchField.setText(add);
    }

    public void VEtransalator(){




        HtmlDisplay htmlDisplay = new HtmlDisplay();
        htmlDisplay.start("",mean);
        searchField.setText("");

        showWord(aV_E);



      
        TranButton.setOnAction(event -> {
            showMeaning(hV_E);
        });
       TextFields.bindAutoCompletion(searchField,aV_E);
        ggTran.setOnAction(event -> {
            googleTransalate("vi", "en");
        });
        OnlineSearch.setOnAction(event -> {
            googleTransalate("vi", "en");
        });
        btAdd.setOnAction(event -> {
            addWord(aV_E,hV_E,F2);
            //showWord(aE_V);



        });
        btRemove.setOnAction(event -> {
            removeWord(aV_E,hV_E,F2);
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
        stage.setTitle("Dịch online");
        stage.setResizable(false);
        Pane root = new Pane();
        root.setStyle("-fx-background-color: rgb(165,177,186)");
        TextField ggText = new TextField();
        ggText.setPromptText("Nhập từ hoặc câu bạn muốn tra vào đây");
        TextArea ggMeans = new TextArea();
        Button tran = new Button("Dịch");
        Button hide = new Button("Ẩn");
        tran.relocate(116,272);
        hide.relocate(560,272);
        ggMeans.relocate(116,87);
        ggText.relocate(116,54);
        ggText.setPrefWidth(319);
        ggText.setText(searchField.getText());
        root.getChildren().addAll(ggText,ggMeans,tran,hide);
        stage.setScene(new Scene(root, 600, 350));

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
        hide.setOnAction(event -> stage.close());;


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

    public void addWord( ArrayList<String> a,HashMap<String, String> h, File F) {

        Stage stage = new Stage();
        stage.setTitle("Thêm từ");
        stage.setResizable(false);
        Button btSave  = new Button("Lưu");
        Button btCancel= new Button("Huỷ");
        TextField enterWord = new TextField();
        TextArea  enterMean = new TextArea();
        Text t1 = new Text("Nhập từ   ");
        Text t2 = new Text("Nhập nghĩa");
        btSave.relocate(116,272);
        btCancel.relocate(560,272);
        enterWord.relocate(116,54);
        enterMean.relocate(116,87);
        t1.relocate(30,57);
        t2.relocate(29,163);
        Pane root = new Pane();
        root.setStyle("-fx-background-color: rgb(165,177,186)");
//        enterWord.setText(searchField.getText());
//        enterMean.setText(acPane.getAccessibleText());
        root.getChildren().addAll(btSave,btCancel,enterWord,enterMean,t1,t2);
        stage.setScene(new Scene(root, 600, 350));
        btSave.setOnAction(event -> {
            a.add(enterWord.getText());
            h.put(enterWord.getText(),enterMean.getText());
            showWord(a);
            try{
                FileWriter fw = new FileWriter(F,true);
                fw.write(enterWord.getText() + "<html><i></i><br/><ul><li><font color='#cc0000'><b>" + enterMean.getText() +"</b></font></li></ul></html>"+ '\n');
                fw.close();

            }catch (Exception e){
                e.printStackTrace();
            }
            TextFields.bindAutoCompletion(searchField,a);
            stage.hide();

        });
        btCancel.setOnAction(event -> {
            stage.hide();
        });
        stage.show();





    }
    public void removeWord(ArrayList<String > a , HashMap<String, String> h, File F){
        Stage stage = new Stage();
        stage.setTitle("Xoá từ");
        stage.setResizable(false);
        Button btSave  = new Button("Lưu");
        Button btCancel= new Button("Huỷ");
        TextField enterWord = new TextField();

        Text t1 = new Text("Nhập từ   ");
       // Text t2 = new Text("Nhập nghĩa");
        btSave.relocate(116,272);
        btCancel.relocate(560,272);
        enterWord.relocate(116,54);

        t1.relocate(30,57);
        enterWord.setText(searchField.getText());

        Pane root = new Pane();
        root.setStyle("-fx-background-color: rgb(165,177,186)");


        root.getChildren().addAll(btSave,btCancel,enterWord,t1);
        stage.setScene(new Scene(root, 600, 350));
        stage.show();
        btSave.setOnAction(event -> {
            int i = 0;
            for(i=0;i<a.size();i++){
                if(a.get(i).equals(enterWord.getText())){
                    a.remove(i);
                }
            }
            showWord(a);
            h.remove(enterWord.getText());
            try{
                FileWriter fw = new FileWriter(F);
                fw.write(" ");
                fw.close();
                FileWriter fw2 = new FileWriter(F,true);
                for(i  =0;i<a.size();i++){
                    fw2.write(a.get(i) + h.get(a.get(i)) + "\n");
                }
                fw2.close();

            }catch (Exception e){
                e.printStackTrace();
            }
            TextFields.bindAutoCompletion(searchField,a);

            stage.close();
        });
        btCancel.setOnAction(event -> {
            stage.close();
        });



    }
//    public static void main(String [] a){
//        File F1 = new File("data/E_V.txt");
//        analyzeData A1= new analyzeData(F1);
//        ArrayList<String> a1 = new ArrayList<>();
//        HashMap<String,String> h1 = new HashMap();
//        A1.readData();
//        a1 = A1.getWordList();
//        h1 = A1.getData();
//        System.out.print(a1.get(10));
//        System.out.println(h1.get(a1.get(10)));
//
//    }
































}













