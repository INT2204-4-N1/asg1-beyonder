package EVDictionary;

import EVDictionary.Controller;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Search {
    private ArrayList<String> wordSearch;
    private  File F  ;
    private ListView wordSearchView;
    private TextField searchBar;
    Search(File _F){
       this.F = _F;
       this.wordSearch = new ArrayList<>();

    }
    public void binarySearch(){
        analyzeData aD = new analyzeData(F);
        aD.readData();
        wordSearch = aD.getWordList();





    }


}
