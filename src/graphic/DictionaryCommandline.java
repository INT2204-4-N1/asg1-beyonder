package graphic;

public class DictionaryCommandline extends DictionaryManagement {
    public void showAllWords() {
        System.out.println("NO" + "\t" + "| English" + "\t" + "| Vietnamese");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + "\t" + "| " + word_target[i] + "\t" + "| " + word_explain[i]);

        }
    }
}
