package CommandLine;
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
}
