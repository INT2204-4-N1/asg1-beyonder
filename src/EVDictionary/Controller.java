package EVDictionary;
import javafx.fxml.Initializable;

import java.awt.*;
import EVDictionary.analyzeData;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Controller extends Application {
    private  ListView<String> wordView;
    public   void showWord(ArrayList<String> arr){

        ObservableList<String>  words = FXCollections.observableArrayList(arr);
        wordView = new ListView(words);
        StackPane root = new StackPane();
        root.getChildren().add(wordView);
        Scene scene = new Scene(root, 350, 200);
        stage.setScene(scene);
        stage.show();

    }


}










