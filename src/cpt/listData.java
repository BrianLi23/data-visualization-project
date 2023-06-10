package cpt;

import java.io.*;
import java.util.*;

import javafx.beans.property.BooleanProperty;

public class listData {

    // Create array list of data
    private static ArrayList<data> listofData = new ArrayList<data>();

    public listData() throws IOException {

    }

    public static ArrayList<data> getDataPoints() throws IOException {
        readData();
        return listofData;
    }

    public static void readData() throws IOException {

        // Text file input
        BufferedReader reader = new BufferedReader(new FileReader(
                "C:/Users/brian/github-classroom/SACHSTech/ics4u-cpt-brian-li-solo/src/cpt/datasheet.csv"));
        String strTextFile = "";
        String[] tempArray;

        // Read the heading first
        strTextFile = reader.readLine();

        // Using while loop to read each line of text file, if readline returns null,
        // end while loop
        while (strTextFile != null) {
            strTextFile = reader.readLine();
            if (strTextFile != null) {
                tempArray = strTextFile.split(",");
                data datapoint = new data(tempArray[0], Integer.parseInt(tempArray[1]), tempArray[2],
                        tempArray[3], tempArray[4]);
                addData(datapoint);
            }
        }

        // Executes the close method
        reader.close();
    }

    public static void addData(data dataInput) {
        listofData.add(dataInput);
    }

    public data getElement(int intNumber) {
        return listofData.get(intNumber);
    }

    public int getSize() {
        return listofData.size();
    }

}
