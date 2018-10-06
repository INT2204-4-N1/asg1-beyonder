package EVDictionary;

public class Word {
    private String word;
    private String meaning;

    Word(){
        this.word = "";
        this.meaning = "";
    }
    Word(String _word,String _meaning){
        this.word = _word;
        this.meaning = _meaning;
    }
    public String getWord(){
        return this.word;
    }
    public String getMind(){
        return this.meaning;
    }
}
