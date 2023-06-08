package cpt;

import java.io.*;
import java.util.*;
import java.lang.Math;

public class main{
    
    public static void readData() throws IOException{

    // Text file input
    BufferedReader reader = new BufferedReader(new FileReader("datasheet.csv"));
    String strTextFile = "";

    // Using while loop to read each line of text file, if readline returns null,
    // end while loop
    while (strTextFile != null) {
        strTextFile = reader.readLine();
        if (strTextFile != null) {
            data(strTextFileMay)
        }
    }

    // Executes the close method
    reader.close();
}

}

