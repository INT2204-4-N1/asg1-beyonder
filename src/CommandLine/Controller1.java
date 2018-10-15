package CommandLine;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;



public class Controller1  implements  Initializable {
    @FXML
    private ListView<String> wordView = new ListView<>();  // ô hiển thị từ
    public TextField searchField;   // ô nhập từ cần tra
    public Button TranButton = new Button();    // nút dịch
    public Button listenButton;  // nút nghe các từ
    private File F1 = new File("data/E_V.txt");
    private File F2 = new File("data/V_E.txt");
    public ScrollPane mean = new ScrollPane();   // ô hiển thị thông tin từ (phát âm, nghĩa,etc...)


    public ArrayList<String> aE_V, aV_E;
    public HashMap<String, String> hE_V, hV_E;

    /**
     * Khi pop up một window mới, nếu window mới dùng cùng controller với cũ -> không thể pop up.
     * Giải pháp ? tạo 1 controller mới
     * @param actionEvent
     */
    @FXML
    void AddWordBtn(ActionEvent actionEvent) throws IOException {
        Controller_for_addWord a = new Controller_for_addWord();
        a.ShowaddWordStage();
        /**
         *
         *
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addWord.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new windows");
        }
         */

    }

    public void initialize(URL location, ResourceBundle resouce) {

        read();
        EVtransalator();


    }


    public void read() {
        E_VDictionary E_V = new E_VDictionary(F1);
        E_VDictionary V_E = new E_VDictionary(F2);
        /**
         * Truyền data tiếng anh
         */
        E_V.readData();
        aE_V = E_V.getWordList();       // truyền vào arraylist để tra cứu
        hE_V = E_V.getData();             // truyền vào hashmap để xác định từ + hiển thị
        /**
         * Truyền data tiếng việt
         */
        V_E.readData();
        aV_E = V_E.getWordList();
        hV_E = V_E.getData();
    }

    /**
     * Hiển thị từ trong arraylist bên ô ListView
     *
     * @param data
     */
    public void showWord(ArrayList<String> data) {
        ObservableList<String> items = FXCollections.observableList(data);  // hiển thị các từ ra ô wordView
        wordView.setItems(items);

    }

    /**
     * Hiển thị nghĩa của từ được nhập vào trong searchField
     *
     * @param H
     */
    public void showMean(HashMap<String, String> H) {
        convertHTML html = new convertHTML();
        String text = H.get(searchField.getText());        // lấy từ trong searchField để so sánh + tìm từ trong hashMap H
        html.convert(text, mean);
    }

    /**
     * Sử dụng từ điển Anh-Việt
     */
    public void EVtransalator() {
        searchField.setText("");    //searchField để trống
        showWord(aE_V);
        // Auto complete TextField (Trả về các khả năng là từ mình cần tìm)
        TextFields.bindAutoCompletion(searchField, aE_V);
        TranButton.setOnAction(event -> showMean(hE_V));
    }

    /**
     * Hiển thị từ vào ô search từ danh sách các từ trong từ điển
     */
    public void ListViewToSearchField() {
        String put = wordView.getSelectionModel().getSelectedItem();        //khi chọn từ trong wordView, từ đấy sẽ hiển thị vào  seachField
        searchField.setText(put);
    }

    /**
     * Sử dụng từ điển Viêt-Anh
     */
    public void VEtransalator() {
        searchField.setText("");
        showWord(aV_E);
        TextFields.bindAutoCompletion(searchField, aV_E);    // Auto complete
        TranButton.setOnAction(event -> showMean(hV_E));    // Click nút dịch -> hiển thị từ được dịch
    }


}
