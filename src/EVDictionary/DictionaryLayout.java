package EVDictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class DictionaryLayout extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EVDictionary.fxml"));
        primaryStage.setTitle("Beyonder - Dictionary");
        primaryStage.setScene(new Scene(root, 960, 700));
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) { launch(args);}
}

