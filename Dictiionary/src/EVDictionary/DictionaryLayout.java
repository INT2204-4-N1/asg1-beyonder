package EVDictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class DictionaryLayout extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.setProperty("file.encoding", "utf-8");
        Parent root = FXMLLoader.load(getClass().getResource("EVDictionary.fxml"));
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(new Scene(root, 1080, 650));
        primaryStage.setResizable(false);

        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }
}