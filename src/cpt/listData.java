package cpt;

import java.io.*;
import java.util.*;

public class listData {

    // Create array list of data
    private static ArrayList<data> listofData = new ArrayList<data>();

    public listData() throws IOException {
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
                addData(datapoint);
            }
        }

        // Executes the close method
        reader.close();
    }

    public static void addData(data dataInput) {
        listofData.add(dataInput);
    }

    public void sortYear() {
        mergeSort(listofData, "year");
    }

    public int getIndex(int intNumber) {
        return listofData.indexOf(intNumber);
    }

    public int getSize(int intNumber) {
        return listofData.size();
    }

    /*
     * Merge sort takes in an array and returns the same array, sorted.
     */
    public static void mergeSort(ArrayList<data> lister, String strProperty) {
        if (lister.size() <= 1) {
            return; // Base case: list is already sorted or empty
        }

        int mid = lister.size() / 2;
        ArrayList<data> left = new ArrayList<>(lister.subList(0, mid)); // Divide the list into left sublist
        ArrayList<data> right = new ArrayList<>(lister.subList(mid, lister.size())); // Divide the list into right //
                                                                                     // sublist

        mergeSort(left, strProperty); // Recursively sort the left sublist
        mergeSort(right, strProperty); // Recursively sort the right sublist

        merge(lister, left, right, strProperty); // Merge the sorted sublists
    }

    private static void merge(ArrayList<data> result, ArrayList<data> left, ArrayList<data> right, String strProperty) {
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (getPropertyValue(left.get(leftIndex), strProperty)
                    .compareTo(getPropertyValue(right.get(rightIndex), strProperty)) <= 0) {
                result.set(resultIndex++, left.get(leftIndex++));
            }

            else {
                result.set(resultIndex++, right.get(rightIndex++));
            }
        }

        while (leftIndex < left.size()) {
            result.set(resultIndex++, left.get(leftIndex++));
        }

        while (rightIndex < right.size()) {
            result.set(resultIndex++, right.get(rightIndex++));
        }
    }

    private static Comparable getPropertyValue(data dataPoint, String strProperty) {
        if (strProperty.equals("year")) {
            return dataPoint.getYear();
        }

        else if (strProperty.equals("entity")) {
            return dataPoint.getEntity();
        }

        else if (strProperty.equals("publication")) {
            return dataPoint.getNumberPub();
        }

        else {
            throw new IllegalArgumentException("Invalid property: " + strProperty);
        }
    }

}
