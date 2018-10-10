package EVDictionary;

import EVDictionary.Controller;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Search {
    private ArrayList<String> word;
    private  File F  ;
    private ListView wordSearchView;
    private TextField searchBar;
    Search(File _F){
       this.F = _F;
       this.word = new ArrayList<>();

    }
    public void appSearch(){
        analyzeData aD = new analyzeData(F);
        aD.readData();
        word = aD.getWordList();
        //searchBar.setText("dhhg");
        ArrayList<String> word2;

        String a = searchBar.getText();









    }


}
