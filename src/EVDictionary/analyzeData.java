package EVDictionary;

import java.io.*;
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

    //private String path;
    analyzeData(File _file) {
        this.file = _file;
        wordList = new ArrayList<>();
        data = new HashMap<>();
    }

    private BufferedReader readFile(File _file) throws IOException {
        BufferedReader readFile = null;
        try {
            readFile = new BufferedReader(new FileReader(_file));

        } catch (FileNotFoundException e) {
            System.out.print("Not found");
        }

        return readFile;
    }

    public void readData() {
        try {
            BufferedReader readFile = new BufferedReader(new FileReader(file));


            String line, word, mean;
            int num = 0;
            while ((line = readFile.readLine()) != null) {
                // trả về chỉ mục vs sự xuấ hiện đầu tiên của ký tự đã cho
                int index = line.indexOf("<html>");
                int index2 = line.indexOf("<ul>");

                if (index2 != -1 && index > index2) {
                    index = index2;
                }
                if (index != -1) {
                    word = line.substring(0, index);
                    word = word.trim();// xoá khoảng cách
                    wordList.add(word);


                    mean = line.substring(index);
                    data.put(word, mean);
                }


            }

            //System.out.print(num);

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
// test đọc file
    public static void main(String[] a) {
        File F = new File("E_V.txt");
        analyzeData ad = new analyzeData(F);
        ad.readData();
        ArrayList<String> b = ad.getWordList();
        HashMap<String, String> c = ad.getData();
        System.out.println(b.get(12));
        System.out.println(c.get(b.get(12)));
    }
}