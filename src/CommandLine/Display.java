package CommandLine;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Display extends Application {



    public static void main(String[] args) {
        launch(args);
    }



        @Override
        public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("E_VDictionary1.fxml"));
            primaryStage.setTitle("Test");
            primaryStage.setScene(new Scene(root,850,650));
            primaryStage.show();
        }
    /**
     * Tạo một stage mới để gọi cửa số thêm từ
     * @throws IOException
     *
    public static  void showAddWordStage() throws IOException {

        // Create a new stage for add word
        Stage addWordStage = new Stage();
        addWordStage.setTitle("Thêm  từ");
        addWordStage.initModality(Modality.WINDOW_MODAL);
        addWordStage.initOwner(primaryStage);
        Scene scene = new Scene();
        addWordStage.setScene(scene);
        addWordStage.show();
    }
    */
}
