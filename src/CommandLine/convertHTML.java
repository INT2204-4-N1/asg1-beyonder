package CommandLine;

import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.hsqldb.persist.ScaledRAFileHybrid;

/**
 * Push dữ liệu lên web và hiển thị từ ở ô nghĩa của từ.
 * từ được hiển thị trong 1 Pane.
 */
public class convertHTML {
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    public convertHTML(){
        webEngine.loadContent("");
    }

    public void convert(String text, ScrollPane pane){      // start
        webEngine.loadContent("");
        pane.setContent(browser);       // chuyển phần dịch nghĩa lên web ?
        webEngine.loadContent(text);    // chuyển phần đã qua convert về ?
    }
}
