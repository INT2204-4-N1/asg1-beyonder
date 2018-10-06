package EVDictionary;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class DictionaryLayout extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EVDictionary.fxml"));
        primaryStage.setTitle("Beyonder - Dictionary");
        primaryStage.setScene(new Scene(root, 960, 700));
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {

        launch(args);}
}
