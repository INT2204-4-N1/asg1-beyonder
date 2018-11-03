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


    analyzeData(){

    }
    analyzeData(File _file) {
        this.file = _file;
        wordList = new ArrayList<>();
        data = new HashMap<>();
    }



    public void readData() {

        System.setProperty("file.encoding", "utf-8");
        try {
            BufferedReader readFile = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));



            String line, word, mean;

            while ((line = readFile.readLine()) != null) {
                // trả về chỉ mục vs sự xuất hiện đầu tiên của ký tự đã cho
                int index = line.indexOf("<html");



                if (index != -1) {
                    word = line.substring(0, index);
                    word = word.trim();// xoá khoảng cách trước và sau
                    wordList.add(word);


                    mean = line.substring(index);
                    data.put(word, mean);

                }


            }



        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public HashMap<String, String> getData() {
        return data;

    }

    public ArrayList<String> getWordList() {
        return wordList;
    }
  }
