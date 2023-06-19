package cpt;

import java.util.ArrayList;

public class Sorting {

    /**
     * Static void method that performs mergesort on an list and returns the sorted
     * version of the list
     * 
     * @param lister      list being sorted
     * 
     * @param strProperty property to sort the list by (Year, day, entity, etc.)
     * 
     * @param boolValue   boolean value to sort list ascending or descending
     * 
     * @author Brian Li
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
        }

        else {
            merge(lister, left, right, strProperty, false); // Merge the sorted sublists
        }
    }

    /**
     * Static void method that merges the left and right side of list based on a
     * property
     * 
     * @param result      List being returned
     * @param left        Leftside list
     * @param right       Rightside List
     * @param strProperty Property to sort list by
     * @param boolValue   Boolean value determining ascending or descending sort
     * 
     * @author Brian Li
     */
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

    /**
     * Method that sorts list by year returns a sorted list
     * 
     * @param boolReverse boolean value to check if you want to
     * 
     */
    public static void sortYear(boolean boolReverse, ArrayList<data> sortingList) {
        if (boolReverse) {
            mergeSort(sortingList, "year", true);
        }

        else {
            mergeSort(sortingList, "year", false);
        }
    }

    /**
     * Method that sorts list by entity returns a sorted list
     * 
     * @param boolReverse boolean value to check if you want to
     * 
     */
    public static void sortEntity(boolean boolReverse, ArrayList<data> sortingList) {
        if (boolReverse) {
            mergeSort(sortingList, "entity", true);
        }

        else {
            mergeSort(sortingList, "entity", false);
        }
    }

    /**
     * Method that sorts list by parameter returns a sorted list
     * 
     * @param boolReverse boolean value to check if you want to
     * 
     */
    public static void sortParameter(boolean boolReverse, ArrayList<data> sortingList) {
        if (boolReverse) {
            mergeSort(sortingList, "parameter", true);
        }

        else {
            mergeSort(sortingList, "parameter", false);
        }
    }

    /**
     * Method that sorts list by day returns a sorted list
     * 
     * @param boolReverse boolean value to check if you want to
     * 
     */
    public static void sortDay(boolean boolReverse, ArrayList<data> sortingList) {
        if (boolReverse) {
            mergeSort(sortingList, "day", true);
        }

        else {
            mergeSort(sortingList, "day", false);
        }
    }

    /**
     * Method that sorts list by domain returns a sorted list
     * 
     * @param boolReverse boolean value to check if you want to
     * 
     */
    public static void sortDomain(boolean boolReverse, ArrayList<data> sortingList) {
        if (boolReverse) {
            mergeSort(sortingList, "domain", true);
        }

        else {
            mergeSort(sortingList, "domain", false);
        }
    }
}
