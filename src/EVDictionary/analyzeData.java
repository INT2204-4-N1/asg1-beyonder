package EVDictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class analyzeData extends Thread {
    private ArrayList<Word> wordList;
    private ArrayList<Word> _wordList;
    private File file;
    analyzeData(File _file){
        this.file = _file;
        wordList = new ArrayList<>();


    }

    private BufferedReader readFile(File _file)throws IOException {
        BufferedReader fileIn = null;
        try {
            BufferedReader fileIn = new BufferedReader(new FileReader(_file));

        } catch (FileNotFoundException e) {
            System.out.print("Not found");
        }

        return fileIn;
    }
    private void anaLine(String line){

    }

}
