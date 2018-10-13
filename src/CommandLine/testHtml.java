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
    public void readData() throws Exception{
        BufferedReader readFile = new BufferedReader(new FileReader("E_V.txt"));


        String line, word, mean;
        int num = 0;
        while ((line = readFile.readLine()) != null) {
            // trả về chỉ mục vs sự xuất hiện đầu tiên của ký tự đã cho
            int index = line.indexOf("<html>");
            //int index2 = line.indexOf("<ul>");

            //if (index2 != -1 && index > index2) {
             //   index = index2;
            //}
            if (index != -1) {
                word = line.substring(0, index);
                word = word.trim();// xoá khoảng cách

                System.out.println(word);


                mean = line.substring(index);

                System.out.println(mean);

            }


        }
    }
    public static void main(String [] abc) throws Exception{
       testHtml a = new testHtml();
       a.readData();
    }
}