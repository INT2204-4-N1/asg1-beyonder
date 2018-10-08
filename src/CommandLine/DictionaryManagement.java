package CommandLine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.*;
public class DictionaryManagement extends E_VDictionary {
    public void insertfromCommandline() {
        Scanner in = new Scanner(System.in);
        Word a = new Word();
        for (int i = 0; i < 3; i++) {
            a.word_target = in.nextLine();
            a.word_explain = in.nextLine();
            word_target[i] = a.word_target;
            word_explain[i] = a.word_explain;
        }
    }

    public void insertformFile() throws Exception {
        File file = new File("D:\\asg1-beyonder\\E_V.txt");
        BufferedReader test = new BufferedReader(new FileReader(file));
        String html;

        while ((html = test.readLine()) != null){
            Document doc = Jsoup.parse(html);
            String VietWord = doc.body().getElementsByTag("b").text();
            String EngWord = doc.body().getElementsByTag("i").text();
            System.out.println(EngWord + "\t" + VietWord);
    }
}
}
