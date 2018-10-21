package EVDictionary;

import com.sun.tools.javac.Main;

import java.io.*;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;

public class analyzeData {
    private ArrayList<String> wordList;
    // private ArrayList<String> _wordList;
    private HashMap<String, String> data;
    private File file;

    private String path ;
    analyzeData(){

    }
    analyzeData(File _file) {
        this.file = _file;
        wordList = new ArrayList<>();
        data = new HashMap<>();
    }

//    private BufferedReader readFile(File _file) throws IOException {
//        BufferedReader readFile = null;
//        try {
//            readFile = new BufferedReader(new FileReader(_file));
//
//        } catch (FileNotFoundException e) {
//            System.out.print("Not found");
//        }
//
//        return readFile;
//    }

    public void readData() {
        //String s = this.file.getPath();
       // InputStream is = getClass().getClassLoader().getResourceAsStream(s);
        try {
            BufferedReader readFile = new BufferedReader(new FileReader(file));



            String line, word, mean;

            while ((line = readFile.readLine()) != null) {
                // trả về chỉ mục vs sự xuất hiện đầu tiên của ký tự đã cho
                int index = line.indexOf("<html>");
                int index2 = line.indexOf("<ul>");

                if (index2 != -1 && index > index2) {
                    index = index2;
                }
                if (index != -1) {
                    word = line.substring(0, index);
                    word = word.trim();// xoá khoảng cách trước và sau
                    wordList.add(word);


                    mean = line.substring(index);
                    data.put(word, mean);

                }


            }

            //System.out.print(num);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //return wordList;


    }

    public HashMap<String, String> getData() {
        return data;

    }

    public ArrayList<String> getWordList() {
        return wordList;
    }
  }
