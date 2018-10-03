package  Dictionary;

import java.util.*;

public class EVDictionary {
        String [] word_target = new String[100];
        String [] word_explain = new String[100];
        static class DictionaryManagement{
            void insertformCommandline(){
                Word a = new Word();
                EVDictionary b = new EVDictionary();
                Scanner in = new Scanner(System.in);
                a.word_target = in.nextLine();
                a.word_explain = in.nextLine();

                b.word_explain[0]=a.word_explain;
                b.word_target[0]=a.word_target;

            }
        }
        static class DictionaryComandline{
             void showAllWord(){
                 Word a = new Word();
                 EVDictionary b = new EVDictionary();
                 b.word_explain[0]=a.word_explain;
                 b.word_target[0]=a.word_target;
                System.out.println("NO"+ "\t" +"| English" +"\t" +"| Vietnamese");
                for (int i=0;i<1;i++){
                    System.out.println((i+1) + "\t" + "| " + b.word_target[i] +"\t" + "| " +b.word_explain[i] );

                }
            }
        }
        public static void main(String [] abc){
            DictionaryManagement a = new DictionaryManagement();
            DictionaryComandline b = new DictionaryComandline();
            a.insertformCommandline();
            b.showAllWord();
        }
}
