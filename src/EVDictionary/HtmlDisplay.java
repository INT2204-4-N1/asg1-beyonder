package EVDictionary;

import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public  class HtmlDisplay {
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();


    public HtmlDisplay(){
        webEngine.loadContent("");

    }


    public void start( String text, ScrollPane pane) {





        pane.setContent(browser);
        webEngine.loadContent(text);



    }


}











