package cpt;

import java.io.*;
import java.util.*;

public class listData {
    
    // Create array list of data
    private static ArrayList<data> listofData = new ArrayList<data>();

    public listData(){

    }

    public static void addData(data dataInput){
        listofData.add(dataInput);
    }
}
