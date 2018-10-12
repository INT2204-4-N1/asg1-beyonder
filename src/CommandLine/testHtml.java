package CommandLine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class testHtml {
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    public testHtml(){
        webEngine.loadContent("");
    }
    public void start (String text){
        webEngine.loadContent("");

        webEngine.loadContent(text);
    }
}