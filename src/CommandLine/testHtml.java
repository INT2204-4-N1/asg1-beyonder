package CommandLine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class testHtml {
    public static void main(String[] abc) throws Exception {
        File file = new File("D:\\asg1-beyonder\\E_V.txt");
        BufferedReader test = new BufferedReader(new FileReader(file));
        String html;

        while ((html = test.readLine()) != null){
            System.out.println(html);
            Document doc = Jsoup.parse(html);
            String title = doc.title();


        }
    }
}