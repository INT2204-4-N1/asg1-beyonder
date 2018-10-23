package EVDictionary;


import com.ibm.icu.text.ArabicShaping;
import groovy.json.internal.IO;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.*;
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
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import javafx.fxml.Initializable;

import java.security.PublicKey;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

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

    public    File F1 = new File("src/EVDictionary/data/E_V.txt");
    public    File F2 = new File("src/EVDictionary/data/V_E.txt");
    public    ScrollPane mean= new ScrollPane();
    public    Button ggTran = new Button("Dịch Online");
    public    Button addWord = new Button("Thêm từ"); // button xuất hiện trên thông báo ko tìm thấy từ
    public    ArrayList<String> aE_V, aV_E;
    public    HashMap<String, String> hE_V, hV_E;
    public    MenuButton onlineMenu;
    public    MenuItem dialog;





    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Read();
        mainLayout.setStyle("-fx-background-color: rgb(165,177,186)");
       // mainLayout.setStyle("-fx-background-image: url(img\24580857917_c72ea0eba9_o.jpg)");



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
        dialog.setOnAction(event -> {
            googleTransalate("en", "vi");
        });
        //ggTran.setOnAction(event -> ggTran2());
        btAdd.setOnAction(event -> {
            addWord(aE_V,hE_V,F1);
            //showWord(aE_V);



        });
        btRemove.setOnAction(event -> {
            if(!Found(searchField.getText(),aE_V)) {
                Alert2();

            }else
                removeWord(aE_V,hE_V,F1);
        });


        addWord.setOnAction(event -> {

            addWord(aE_V,hE_V,F1);

        });
        btEdit.setOnAction(event -> {
            if(!Found(searchField.getText(),aE_V)){
                Alert2();
            }else
                editWord(aE_V,hE_V,F1);
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
        dialog.setOnAction(event -> {
            googleTransalate("vi", "en");
        });
        //ggTran.setOnAction(event -> ggTran2());
        btAdd.setOnAction(event -> {
            addWord(aV_E,hV_E,F2);
            //showWord(aE_V);
        });
        btRemove.setOnAction(event -> {
            if(!Found(searchField.getText(),aV_E)) {
               Alert2();
            }else
                removeWord(aV_E,hV_E,F2);
        });
        addWord.setOnAction(event -> {
            addWord(aV_E,hV_E,F2);

        });
        btEdit.setOnAction(event -> {
            if(Found(searchField.getText(),aV_E)){
                editWord(aV_E,hV_E,F2);
            }
            else Alert2();
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
        Text t1 = new Text("Nhập từ hoặc câu   ");
        Text t2 = new Text(" Nghĩa sau khi dịch");
        root.setStyle("-fx-background-color: rgb(165,177,186)");
        TextField ggText = new TextField();
        ggText.setPromptText("Nhập từ hoặc câu bạn muốn tra vào đây");
        TextArea ggMeans = new TextArea();
        Button tran = new Button("Dịch");
        Button hide = new Button("Ẩn");
        Button listen = new Button("Nghe");
        t1.relocate(16,57);
        t2.relocate(16,163);
        tran.relocate(116,272);
        hide.relocate(560,272);
        listen.relocate(338,272);
        ggMeans.relocate(116,87);
        ggText.relocate(116,54);
        ggText.setPrefWidth(319);
        ggText.setText(searchField.getText());
        ggMeans.setEditable(false);
        root.getChildren().addAll(ggText,ggMeans,tran,hide,listen,t1,t2);
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
        hide.setOnAction(event -> stage.close());
        listen.setOnAction(event -> {
            searchField.setText(ggText.getText());
            TextToSpeech();
            searchField.setText("");
        });


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
        addWord.setOnMouseClicked(event -> {
            stage.close();
        });


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
        enterWord.setText(searchField.getText());
//        enterMean.setText(acPane.getAccessibleText());
        root.getChildren().addAll(btSave,btCancel,enterWord,enterMean,t1,t2);
        stage.setScene(new Scene(root, 600, 350));
        btSave.setOnAction(event -> {
            a.add(enterWord.getText());
            h.put(enterWord.getText(), "<html><i>"+ enterWord.getText()+"</i><br/><ul><li><font color='#cc0000'><b>" +enterMean.getText()+"</b></font></li></ul></html>");
            showWord(a);
            try{
                FileWriter fw = new FileWriter(F,true);
                fw.write(enterWord.getText() + "<html><i>"+enterWord.getText()+"</i><br/><ul><li><font color='#cc0000'><b>" + enterMean.getText() +"</b></font></li></ul></html>"+ '\n');
                fw.close();

            }catch (Exception e){
                e.printStackTrace();
            }
            TextFields.bindAutoCompletion(searchField,a);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Thông báo");

            alert.setContentText("Thêm từ thành công");
            alert.show();
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
        Text t2 = new Text("Chú ý: Hành động này không thể hoàn tác");
        btSave.relocate(30,120);
        btCancel.relocate(215,120);
        enterWord.relocate(116,54);

        t1.relocate(30,57);
        t2.relocate(30,100);
        enterWord.setText(searchField.getText());

        Pane root = new Pane();
        root.setStyle("-fx-background-color: rgb(165,177,186)");


        root.getChildren().addAll(btSave,btCancel,enterWord,t1,t2);
        stage.setScene(new Scene(root, 300, 150));
        stage.show();
        btSave.setOnAction(event -> {
            int i ;
            for(i=0;i<a.size();i++){
                if(a.get(i).equals(enterWord.getText())){
                    a.remove(i);
                }
            }
            showWord(a);
            h.remove(enterWord.getText());
            try{
                Writer fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(F), "UTF-8"));
                fw.write("");
                fw.close();
                Writer fw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(F), "UTF-8"));
                for(i  =0;i<a.size();i++){
                    fw2.write(a.get(i) + h.get(a.get(i)) + "\n");
                }
                fw2.close();

            }catch (Exception e){
                e.printStackTrace();
            }
            TextFields.bindAutoCompletion(searchField,a);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Thông báo");
            alert.setContentText("Xoá từ thành công");
            alert.show();

            stage.close();
        });
        btCancel.setOnAction(event -> {
            stage.close();
        });



    }
    public boolean Found(String s, ArrayList<String> a){

        for(String St : a){
            if(St.equals(s))
                return true;

        }

        return false;

    }
    public void editWord(ArrayList<String> a, HashMap<String,String> h, File F){
        Stage stage = new Stage();
        stage.setTitle("Sửa từ");
        stage.setResizable(false);
        Button btSave  = new Button("Lưu");
        Button btCancel= new Button("Huỷ");
        TextField enterWord = new TextField();
        //TextArea  enterMean = new TextArea();
        HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setPrefHeight(245);
        htmlEditor.setPrefSize(500,288);
        htmlEditor.relocate(159,120);


        htmlEditor.setHtmlText(h.get(searchField.getText()));

        Text t1 = new Text(" Từ   ");
        Text t2 = new Text("Sửa nghĩa");
        btSave.relocate(205,408);
        btCancel.relocate(580,408);
        enterWord.relocate(159,53);
        enterWord.setPrefSize(200,22);

        enterWord.setEditable(false);
        t1.relocate(65,53);
        t2.relocate(65,282);
        Pane root = new Pane();
        root.setStyle("-fx-background-color: rgb(165,177,186)");
        enterWord.setText(searchField.getText());

        root.getChildren().addAll(btSave,btCancel,enterWord,htmlEditor,t1,t2);
        stage.setScene(new Scene(root, 719, 440));
        stage.show();
        btSave.setOnAction(event -> {
            int i;
            String html = htmlEditor.getHtmlText();
            h.replace(searchField.getText(), h.get(searchField.getText()),html);
            try{
                //FileWriter fw1 = new FileWriter(F);
                BufferedWriter fw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(F), "UTF-8"));
                fw1.write(" ");
                fw1.close();
                Writer fw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(F), "UTF-8"));
                for(i  =0;i<a.size();i++){
                    fw2.write(a.get(i) + h.get(a.get(i)) + "\n");

                }
                fw2.close();

            }catch (Exception e){
                e.printStackTrace();
            }
            TextFields.bindAutoCompletion(searchField,a);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Thông báo");
            alert.setContentText("Từ sửa được lưu thành công");
            alert.show();

            stage.close();


        });
        btCancel.setOnAction(event -> stage.close());


    }
    public  void ggTran2(){
         WebView browser = new WebView();
         WebEngine webEngine = browser.getEngine();
         browser.setPrefWidth(960);
         browser.setPrefHeight(600);
         webEngine.load("https://translate.google.com/?hl=vi");
         Button back = new Button("Trở lại trang chính");

         back.setPrefSize(190,21);
         back.setLayoutX(370);
        Stage stage = new Stage();
        stage.setTitle("Google Translate");
        stage.setResizable(true);
        Pane root = new Pane();
        root.getChildren().addAll(browser,back);
        stage.setScene(new Scene(root, 960, 600));

        back.setOnAction(event -> {
            webEngine.load("https://translate.google.com/?hl=vi");

        });
        stage.show();

    }
    public void Alert2(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Thông báo");
        alert.setContentText("Từ không tồn tại. Để chắc chắn từ bạn muốn xoá hoặc sửa có trong danh sách,hãy tìm kiém từ và đặt từ cần xoá vào ô tìm kiếm");
        alert.show();

    }

































}













