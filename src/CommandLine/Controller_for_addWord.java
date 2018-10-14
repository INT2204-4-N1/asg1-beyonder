package CommandLine;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;


import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_for_addWord implements Initializable {


    public TextField addWord = new TextField();
    public TextField addMean = new TextField();
    public Button add = new Button();

    @FXML
    void Add() {

        try{
           String _addWord = addWord.getText();
           String _addMean = addMean.getText();
            BufferedWriter writer = new BufferedWriter(new FileWriter("testInput.txt",true));
            writer.append(' ');
            writer.append(_addWord+"<html><i>" + _addWord +"</i><br/><ul><li><font color='#cc0000'><b>" + _addMean + "</b></font></li></ul></html>" );
            writer.append("\n");
            writer.close();
        }catch(Exception e){}


    }

    public void initialize(URL location, ResourceBundle resouce) {
    add.setOnAction(event -> Add());
    }
}
