package CommandLine;

import javax.swing.text.html.HTML;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class testInputFiles {
    public static void main(String []abc) throws IOException{
        FileWriter a = null;
        Scanner in = new Scanner(System.in);
        String addWord;
        String addMean;
        addWord = in.nextLine();
        addMean = in.nextLine();
        /**
        try {
        a = new FileWriter("D://asg1-beyonder//testInput.txt");
        a.write(addWord+"<html><i>" + addWord +"</i><br/><ul><li><font color='#cc0000'><b>" + addMean + "</b></font></li></ul></html>");
        a.write(System.lineSeparator());//newline
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (a != null){
                    a.flush();
                    a.close();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
         */
        BufferedWriter writer = new BufferedWriter(new FileWriter("testInput.txt",true));
        writer.append(' ');
        writer.append(addWord+"<html><i>" + addWord +"</i><br/><ul><li><font color='#cc0000'><b>" + addMean + "</b></font></li></ul></html>" );
        writer.append("\n");
        writer.close();

    }
}
