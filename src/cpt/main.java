package cpt;

import java.io.*;
import java.util.*;
import java.lang.Math;

public class main {

    public static void main(String[] args) throws IOException {
        readData();

    }

    public static void readData() throws IOException {

        // Text file input
        BufferedReader reader = new BufferedReader(new FileReader("datasheet.csv"));
        String strTextFile = "";
        String[] tempArray;

        // Using while loop to read each line of text file, if readline returns null,
        // end while loop
        while (strTextFile != null) {
            strTextFile = reader.readLine();
            tempArray = strTextFile.split(",");
            if (strTextFile != null) {
                data datapoint = new data(tempArray[0], Integer.parseInt(tempArray[2]), Integer.parseInt(tempArray[3]));
                listData.addData(datapoint);
            }
        }

        // Executes the close method
        reader.close();
    }

}
