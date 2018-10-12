package EVDictionary;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public  class HtmlDisplay {
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();


    public HtmlDisplay(){
        webEngine.loadContent("");

    }


    public void start( String text, ScrollPane pane) {




        webEngine.loadContent("");
        pane.setContent(browser);
        webEngine.loadContent(text);

//        root.getChildren().addAll(pane);
//        scene.setRoot(root);
//
//        stage.setScene(scene);
//        stage.show();
    }


}











