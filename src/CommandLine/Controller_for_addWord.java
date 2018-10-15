package CommandLine;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_for_addWord implements Initializable {


    public TextField addWord = new TextField(); // ô nhập từ muốn thêm
    public TextField addMean = new TextField(); // ô nhập nghĩa muốn thêm
    public Button add = new Button();
    public Button hide = new Button();

    /**
     * Hàm add tác dụng thêm từ + nghĩa cho từ điển
     */
    @FXML
    void Add() {

        try{
           String _addWord = addWord.getText();
           String _addMean = addMean.getText();
            BufferedWriter writer = new BufferedWriter(new FileWriter("E_V.txt",true));   // Mở files
            writer.append(' ');
            writer.append(_addWord+"<html><i>" + _addWord +"</i></b><ul><li><font color='#cc0000'><b>" + _addMean + "</b></font></li></ul></li></ul></html>" );
            writer.append("\n");
            writer.close();     // Đóng files
        }catch(Exception e){}
        closeButtonAction();
    }

    /**
     * Hiển thi ̣cửa sổ thêm từ mới + nghĩa mới cho từ điển
     * @throws IOException
     */
    public void ShowaddWordStage() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addWord.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setScene(new Scene(root1));
            stage.show();
            //hide.setOnAction(event -> stage.hide());
        } catch (Exception e) {
            System.out.println("Can't load new windows");
        }
    }

    /**
     * Hàm gọi nút đóng cửa sổ thêm từ.
     */
        public void closeButtonAction(){
        Stage stage = (Stage) hide.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void initialize(URL location, ResourceBundle resouce) {


    }


}
