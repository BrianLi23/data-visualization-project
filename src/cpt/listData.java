package cpt;

import java.io.*;
import java.util.*;

import javafx.beans.property.BooleanProperty;

public class listData {

    // Create array list of data
    private static ArrayList<data> listofData = new ArrayList<data>();

    public listData() throws IOException {
        readData();
    }

    public static void readData() throws IOException {

        // Text file input
        BufferedReader reader = new BufferedReader(new FileReader(
                "C:/Users/brian/github-classroom/SACHSTech/ics4u-cpt-brian-li-solo/src/cpt/datasheet3.csv"));
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

    public void sortYear(boolean boolReverse) {
        if (boolReverse) {
            mergeSort(listofData, "year", true);
        }

        else {
            mergeSort(listofData, "year", false);
        }
    }

    public void sortEntity(boolean boolReverse) {
        if (boolReverse) {
            mergeSort(listofData, "entity", true);
        }

        else {
            mergeSort(listofData, "entity", false);
        }
    }

    public void sortParameter(boolean boolReverse) {
        if (boolReverse) {
            mergeSort(listofData, "parameter", true);
        }

        else {
            mergeSort(listofData, "parameter", false);
        }
    }

    public void sortDay(boolean boolReverse) {
        if (boolReverse) {
            mergeSort(listofData, "day", true);
        }

        else {
            mergeSort(listofData, "day", false);
        }
    }

    public void sortDomain(boolean boolReverse) {
        if (boolReverse) {
            mergeSort(listofData, "domain", true);
        }

        else {
            mergeSort(listofData, "domain", false);
        }
    }

    public data getElement(int intNumber) {
        return listofData.get(intNumber);
    }

    public int getSize() {
        return listofData.size();
    }

    /*
     * Merge sort takes in an array and returns the same array, sorted.
     */
    public static void mergeSort(ArrayList<data> lister, String strProperty, boolean boolValue) {

        if (lister.size() <= 1) {
            return; // Base case: list is already sorted or empty
        }

        int mid = lister.size() / 2;
        ArrayList<data> left = new ArrayList<>(lister.subList(0, mid)); // Divide the list into left sublist
        ArrayList<data> right = new ArrayList<>(lister.subList(mid, lister.size())); // Divide the list into right //
                                                                                     // sublist
        mergeSort(left, strProperty, boolValue); // Recursively sort the left sublist
        mergeSort(right, strProperty, boolValue); // Recursively sort the right sublist

        if (boolValue) {
            merge(lister, left, right, strProperty, true); // Merge the sorted sublists
        } else {
            merge(lister, left, right, strProperty, false); // Merge the sorted sublists
        }
    }

    private static void merge(ArrayList<data> result, ArrayList<data> left, ArrayList<data> right, String strProperty,
            boolean boolValue) {
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {

            if (boolValue) {

                if (getPropertyValue(left.get(leftIndex), strProperty)
                        .compareTo(getPropertyValue(right.get(rightIndex), strProperty)) <= 0) {
                    result.set(resultIndex++, left.get(leftIndex++));
                }

                else {
                    result.set(resultIndex++, right.get(rightIndex++));
                }

            }

            else {
                if (-getPropertyValue(left.get(leftIndex), strProperty)
                        .compareTo(getPropertyValue(right.get(rightIndex), strProperty)) <= 0) {
                    result.set(resultIndex++, left.get(leftIndex++));
                }

                else {
                    result.set(resultIndex++, right.get(rightIndex++));
                }
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

        else if (strProperty.equals("day")) {
            return dataPoint.getDay();
        }

        else if (strProperty.equals("parameter")) {
            return dataPoint.getParameter();
        }

        else if (strProperty.equals("domain")) {
            return dataPoint.getDomain();
        }

        else {
            throw new IllegalArgumentException("Invalid property: " + strProperty);
        }
    }

}
