package CommandLine;

import javafx.collections.ArrayChangeListener;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Thêm từ vào ArrayList cho viêc hiển thị
 * Trả về từ cần tra với HashList
 * Truyền vào data từ files text
 */
public class E_VDictionary {
    // tạo array lưu + hiển thị từ
    private ArrayList<String> WordList;
    // tạo hashmap hiển thị từ với input wordlist
    private HashMap<String,String> data;
    private File files ;

    /**
     * Constructor
     */
    E_VDictionary(File _files){
        this.files = _files;
        WordList = new ArrayList<>();
        data = new HashMap<>();
    }
    /**
     * Tách từ riêng với phần hiển thị riêng
     * xử lí data
     */
    public void readData() {
        try{
        BufferedReader readFile = new BufferedReader(new FileReader(files));
        String line,word,mean;

        while((line = readFile.readLine()) != null) {
            int index = line.indexOf("<html>");
            if (index != -1) {
                // Lấy từ đầu đến lúc xuất hiện <html>
                word = line.substring(0, index);
                // Lấy hết dòng đằng sau <html>
                mean = line.substring(index);
                //Thêm từ vào phần tra từ
                WordList.add(word);
                //Thêm từ vào phần hiển thị
                data.put(word, mean);
            }
        }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Lấy dữ liệu từ file vào hashMap + arrayList
     * @return
     */
    public HashMap<String,String> getData(){
        return data;
    }
    public ArrayList<String> getWordList(){
        return WordList;
    }
}


