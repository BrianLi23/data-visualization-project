package cpt;

import java.io.*;
import java.util.*;

public class ListData {

    // Create instance variable of list of data
    private static ArrayList<data> listofData = new ArrayList<data>();

    // Constructor for the class
    public ListData() throws IOException {

    }

    /*
     * Static method that adds data to list of data
     * 
     * @return list of data
     */
    public static ArrayList<data> getDataPoints() throws IOException {
        readData();
        return listofData;
    }

    /*
     * Void method that reads data from csv file and adds datapoints to list
     * 
     */
    public static void readData() throws IOException {

        // Text file input
        BufferedReader reader = new BufferedReader(new FileReader(
                "C:/Users/brian/github-classroom/SACHSTech/ics4u-cpt-brian-li-solo/src/cpt/datasheet.csv"));
        String strTextFile = "";
        String[] tempArray;

        // Read the heading first (Skip over it)
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

    /*
     * Void method adds data to list of data
     * 
     * @param data to add
     */
    public static void addData(data dataInput) {
        listofData.add(dataInput);
    }

    /*
     * Method returns element of list of data from its index
     * 
     * @param index to check
     * 
     * @return element of list
     */
    public data getElement(int intNumber) {
        return listofData.get(intNumber);
    }

    /*
     * Method returns size of data
     * 
     * @return size of list
     */
    public int getSize() {
        return listofData.size();
    }

}
